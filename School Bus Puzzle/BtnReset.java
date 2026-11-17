import greenfoot.*;

/**
 * Tombol ulang level dari awal.
 */
public class BtnReset extends Button
{
    public BtnReset()
    {
        setImage("btn_reset.png");
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this) && getWorld() instanceof game1) {
            Audio.sfx("sfx_click.wav");
            Greenfoot.setWorld(new game1(((game1) getWorld()).getLevel()));
        }
    }
}
