import greenfoot.*;

public class BtnSetting extends Button
{
    public BtnSetting()
    {
        setImage("SETTIN3.png");
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            Audio.sfx("sfx_click.wav");
            Greenfoot.setWorld(new Setting());
        }
    }
}
