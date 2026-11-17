import greenfoot.*;

/**
 * Konfirmasi "Are You Sure?": YES = keluar ke peta level, NO = kembali ke layar pause.
 */
public class pause2 extends World
{
    private game1 game;

    public pause2()
    {
        this(null);
        prepare();
    }

    public pause2(game1 game)
    {
        super(1024, 768, 1);
        this.game = game;
        setBackground("keluar.png");
        prepare();
    }

    private void prepare()
    {
        addObject(new Yes(), 295, 493);
        addObject(new No(), 717, 493);
    }

    public void onYes()
    {
        Greenfoot.setWorld(new Map());
    }

    public void onNo()
    {
        Greenfoot.setWorld(new pause(game));
    }
}
