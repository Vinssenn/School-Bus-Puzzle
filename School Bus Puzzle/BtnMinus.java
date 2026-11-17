import greenfoot.*;

/**
 * Tombol kurang volume di layar Setting.
 */
public class BtnMinus extends Button
{
    public BtnMinus()
    {
        setImage("12.png");
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this) && getWorld() instanceof Setting) {
            ((Setting) getWorld()).kurangVolume();
        }
    }
}
