import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BtnMinus here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BtnMinus extends Button
{
    /**
     * Act - do whatever the BtnMinus wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            Setting duniaSetting = (Setting) getWorld();
            duniaSetting.kurangVolume();
        }
    }
}
