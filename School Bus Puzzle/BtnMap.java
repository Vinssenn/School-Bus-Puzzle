import greenfoot.*;

/**
 * Tombol kembali ke peta level.
 */
public class BtnMap extends Button
{
    public BtnMap()
    {
        setImage("btn_map.png");
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            Audio.sfx("sfx_click.wav");
            Greenfoot.setWorld(new Map());
        }
    }
}
