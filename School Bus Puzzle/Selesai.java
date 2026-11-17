import greenfoot.*;

/**
 * Layar "Level Complete": menampilkan waktu dan bintang, lalu pilihan Retry / Next / Map.
 */
public class Selesai extends World
{
    private final int level;
    private final int stars;
    private final long timeMs;
    private Bintang[] star = new Bintang[3];
    private int tick = 0;
    private int revealed = 0;

    public Selesai()
    {
        this(1, 3, 20000L);
    }

    public Selesai(int level, int stars, long timeMs)
    {
        super(1024, 768, 1);
        this.level = level;
        this.stars = stars;
        this.timeMs = timeMs;
        setBackground("setting.png");
        prepare();
    }

    public int getLevel()
    {
        return level;
    }

    private void prepare()
    {
        boolean last = level >= Levels.COUNT;
        addObject(new Label(new GreenfootImage(last ? "title_allclear.png" : "title_complete.png")), 512, 215);

        int[] sx = { 392, 512, 632 };
        int[] sy = { 332, 318, 332 };
        for (int i = 0; i < 3; i++) {
            star[i] = new Bintang();
            star[i].setFull(false, 104);
            addObject(star[i], sx[i], sy[i]);
        }

        addObject(new Label(Hud.line("lbl_time.png", Hud.clock(timeMs), 1.0, 16)), 512, 432);

        if (last) {
            addObject(new BtnRetry(), 400, 535);
            addObject(new BtnMap(), 624, 535);
        } else {
            addObject(new BtnRetry(), 290, 535);
            addObject(new BtnNext(), 512, 535);
            addObject(new BtnMap(), 734, 535);
        }
    }

    public void act()
    {
        tick++;
        if (revealed < stars && tick >= 25 + revealed * 22) {
            star[revealed].setFull(true, 104);
            Audio.sfx("sfx_star.wav");
            revealed++;
        }
    }
}
