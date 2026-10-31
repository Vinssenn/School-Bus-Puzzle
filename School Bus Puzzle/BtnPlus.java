import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BtnPlus here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BtnPlus extends Button
{
    /**
     * Act - do whatever the BtnPlus wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            Setting duniaSetting = (Setting) getWorld();
            duniaSetting.tambahVolume();
        }
    }
}
