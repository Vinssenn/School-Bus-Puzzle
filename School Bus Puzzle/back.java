import greenfoot.*;

/**
 * Tombol panah kembali ke Lobby (dipakai di Setting dan Map).
 */
public class back extends Button
{
    public back()
    {
        setImage("back.png");
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            Audio.sfx("sfx_click.wav");
            Greenfoot.setWorld(new Lobby());
        }
    }
}
