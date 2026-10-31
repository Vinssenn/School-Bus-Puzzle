import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lobby here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lobby extends World
{
    public static GreenfootSound bgm = new GreenfootSound("bgm.mp3");
    public static int levelVolume = 100;
    /**
     * Constructor for objects of class Lobby.
     * 
     */
    public Lobby()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 700, 1); 
       if (!bgm.isPlaying()) {
            bgm.setVolume(100); 
            bgm.playLoop();
        }
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Play play = new Play();
        addObject(play,736,514);
        BtnSetting btnSetting = new BtnSetting();
        addObject(btnSetting,740,637);
        btnSetting.setLocation(804,626);
        btnSetting.setLocation(734,625);
        btnSetting.setLocation(731,626);
        btnSetting.setLocation(740,622);
    }
}
