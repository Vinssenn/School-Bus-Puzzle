import greenfoot.*;

/**
 * Layar utama: tombol Play dan Setting.
 */
public class Lobby extends World
{
    public Lobby()
    {
        super(1024, 768, 1);
        setBackground("lobby end.png");
        Audio.playMenu();
        prepare();
    }

    private void prepare()
    {
        addObject(new Play(), 736, 514);
        addObject(new BtnSetting(), 740, 622);
    }
}
