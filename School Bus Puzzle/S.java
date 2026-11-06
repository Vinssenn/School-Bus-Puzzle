import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class S here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class S extends ingame
{
    private int awalX;
    private int awalY;
    /**
     * Act - do whatever the S wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mousePressed(this)) {
            awalX = getX();
            awalY = getY();
        }
        
        if (Greenfoot.mouseDragged(this)) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouse != null) {
                setLocation(mouse.getX(), mouse.getY());
            }
        }
        
        if (Greenfoot.mouseDragEnded(this)) {
            

            if (isTouching(abu1.class)) {
                
                
                setLocation(awalX, awalY);
                
            }
                else if (isTouching(abu2.class)) {
                
                
                setLocation(awalX, awalY);
                
            }
            else {
                
            }
    }
    }
}
