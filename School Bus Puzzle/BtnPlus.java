import greenfoot.*;

/**
 * Tombol tambah volume di layar Setting.
 */
public class BtnPlus extends Button
{
    public BtnPlus()
    {
        setImage("13.png");
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this) && getWorld() instanceof Setting) {
            ((Setting) getWorld()).tambahVolume();
        }
    }
}
