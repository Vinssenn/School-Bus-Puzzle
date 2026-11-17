import greenfoot.*;

/**
 * Peta pemilihan level. Level 1 selalu terbuka, level berikutnya terbuka setelah
 * level sebelumnya diselesaikan. Bintang yang sudah didapat tampil di bawah tiap node.
 */
public class Map extends World
{
    // titik tengah lingkaran tombol tiap level (level 1..10)
    private static final int[][] NODE = {
        {160, 538}, {324, 441}, {533, 521}, {660, 452}, {493, 320},
        {398, 224}, {595, 151}, {724, 224}, {897, 342}, {858, 461}
    };

    private Label msg;
    private int msgTimer = 0;

    public Map()
    {
        super(1024, 768, 1);
        setBackground("Map cowok.png");
        Audio.playMenu();
        prepare();
    }

    private void prepare()
    {
        addObject(new Level(), 499, 357);
        addObject(new Level1(), 158, 554);
        addObject(new Level2(), 322, 457);

        for (int n = 1; n <= Levels.COUNT; n++) {
            int cx = NODE[n - 1][0];
            int cy = NODE[n - 1][1];
            for (int i = 0; i < Progress.stars[n]; i++) {
                Bintang b = new Bintang();
                b.setFull(true, 24);
                addObject(b, cx - 21 + 21 * i, cy + 40);
            }
            if (!Progress.isUnlocked(n)) {
                addObject(new Label(new GreenfootImage("node_locked.png")), cx, cy);
            }
        }

        addObject(new back(), 62, 55);
        addObject(new Label(new GreenfootImage("lbl_select.png")), 330, 705);
        addObject(new Label(Hud.starCount(Progress.totalStars(), Levels.COUNT * 3)), 770, 705);
    }

    public void act()
    {
        if (msgTimer > 0) {
            msgTimer--;
            if (msgTimer == 0 && msg != null) {
                removeObject(msg);
                msg = null;
            }
        }
        if (Greenfoot.mouseClicked(null)) {
            MouseInfo mi = Greenfoot.getMouseInfo();
            if (mi != null) {
                int n = nodeAt(mi.getX(), mi.getY());
                if (n > 0) {
                    if (Progress.isUnlocked(n)) {
                        Audio.sfx("sfx_click.wav");
                        Greenfoot.setWorld(new game1(n));
                    } else {
                        showLocked();
                    }
                }
            }
        }
    }

    
    private int nodeAt(int x, int y)
    {
        for (int n = 1; n <= Levels.COUNT; n++) {
            int cx = NODE[n - 1][0];
            int cy = NODE[n - 1][1];
            if (Math.abs(x - cx) <= 36 && y >= cy - 36 && y <= cy + 58) {
                return n;
            }
        }
        return -1;
    }

    private void showLocked()
    {
        Audio.sfx("sfx_error.wav");
        if (msg == null) {
            msg = new Label(new GreenfootImage("lbl_locked.png"));
            addObject(msg, 512, 585);
        }
        msgTimer = 100;
    }
}
