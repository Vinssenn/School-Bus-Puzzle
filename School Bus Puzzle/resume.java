import greenfoot.*;

public class resume extends puase
{
    public resume()
    {
        setImage("resume.png");
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            Audio.sfx("sfx_click.wav");
            World w = getWorld();
            if (w instanceof pause) {
                ((pause) w).onResume();
            }
        }
    }
}
