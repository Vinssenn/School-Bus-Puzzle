import greenfoot.*;

public class Yes extends puase
{
    public Yes()
    {
        setImage("YES.png");
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            Audio.sfx("sfx_click.wav");
            World w = getWorld();
            if (w instanceof pause) {
                ((pause) w).onYes();
            } else if (w instanceof pause2) {
                ((pause2) w).onYes();
            }
        }
    }
}
