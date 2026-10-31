import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Map here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Map extends World
{
   
    /**
     * Constructor for objects of class Map.
     * 
     */
    public Map()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 700, 1); 
        if (!Lobby.bgm.isPlaying()) {
            Lobby.bgm.playLoop();
        }
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {

        Level1 level1 = new Level1();
        addObject(level1,158,554);
        Level2 level2 = new Level2();
        addObject(level2,337,457);
        level2.setLocation(337,455);
        level2.setLocation(330,455);
        level2.setLocation(330,459);
        level2.setLocation(322,457);
        Level level = new Level();
        addObject(level,499,357);
    }
}
