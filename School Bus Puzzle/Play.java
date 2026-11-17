import greenfoot.*;

public class Play extends Button
{
    public Play()
    {
        setImage("PLAY 2.png");
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            Audio.sfx("sfx_click.wav");
            Greenfoot.setWorld(new Map());
        }
    }
}
