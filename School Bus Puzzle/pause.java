import greenfoot.*;

/**
 * Layar pause: "Do you want back to lobby?"  YES (lanjut ke konfirmasi) / RESUME (balik main).
 * Game yang dijeda disimpan, jadi RESUME melanjutkan persis dari posisi terakhir.
 */
public class pause extends World
{
    private game1 game;

    public pause()
    {
        this(null);
    }

    public pause(game1 game)
    {
        super(1024, 768, 1);
        this.game = game;
        setBackground("back to lobby.png");
        prepare();
    }

    private void prepare()
    {
        addObject(new Yes(), 295, 493);
        addObject(new resume(), 717, 487);
    }

    public void onResume()
    {
        Greenfoot.setWorld(game != null ? game : new game1(1));
    }

    public void onYes()
    {
        Greenfoot.setWorld(new pause2(game));
    }
}
