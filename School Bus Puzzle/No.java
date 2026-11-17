import greenfoot.*;

public class No extends puase
{
    public No()
    {
        setImage("NO.png");
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            Audio.sfx("sfx_click.wav");
            World w = getWorld();
            if (w instanceof pause2) {
                ((pause2) w).onNo();
            }
        }
    }
}
