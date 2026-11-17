import greenfoot.*;

/**
 * Tombol pause di dalam level: membuka layar "Do you want back to lobby?".
 */
public class BtnPause extends Button
{
    public BtnPause()
    {
        setImage("btn_pause.png");
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this) && getWorld() instanceof game1) {
            Audio.sfx("sfx_click.wav");
            Greenfoot.setWorld(new pause((game1) getWorld()));
        }
    }
}
