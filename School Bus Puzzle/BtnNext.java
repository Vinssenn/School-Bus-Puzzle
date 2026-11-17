import greenfoot.*;

/**
 * Tombol lanjut ke level berikutnya (di layar level selesai).
 */
public class BtnNext extends Button
{
    public BtnNext()
    {
        setImage("btn_next.png");
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this) && getWorld() instanceof Selesai) {
            Audio.sfx("sfx_click.wav");
            Greenfoot.setWorld(new game1(((Selesai) getWorld()).getLevel() + 1));
        }
    }
}
