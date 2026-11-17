import greenfoot.*;

/**
 * Tombol main ulang level yang barusan selesai.
 */
public class BtnRetry extends Button
{
    public BtnRetry()
    {
        setImage("btn_retry.png");
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this) && getWorld() instanceof Selesai) {
            Audio.sfx("sfx_click.wav");
            Greenfoot.setWorld(new game1(((Selesai) getWorld()).getLevel()));
        }
    }
}
