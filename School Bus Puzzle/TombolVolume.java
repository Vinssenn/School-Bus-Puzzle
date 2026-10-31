import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TombolVolume here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TombolVolume extends Actor
{
    /**
     * Act - do whatever the TombolVolume wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (getWorld() instanceof Setting) {
            Setting duniaSetting = (Setting) getWorld();
            int nilaiVolume = duniaSetting.volume;
            
            GreenfootImage teks = new GreenfootImage(nilaiVolume + "%", 40, Color.WHITE, new Color(0,0,0,0));
            setImage(teks);
        }
    }
}
