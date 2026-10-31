import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Setting here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Setting extends World
{
    public int volume = Lobby.levelVolume;
    
    public GreenfootSound bgm = new GreenfootSound("bgm.mp3");
    public Setting()
    {    
        super(1000, 700, 1); 
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        BtnPlus btnPlus = new BtnPlus();
        addObject(btnPlus,752,333);
        BtnMinus btnMinus = new BtnMinus();
        addObject(btnMinus,520,507);
        btnPlus.setLocation(528,246);
        btnPlus.setLocation(524,232);
        btnPlus.setLocation(518,230);
        btnMinus.setLocation(321,338);
        btnPlus.setLocation(730,336);
        TombolVolume tombolVolume = new TombolVolume();
        addObject(tombolVolume,519,334);
        Vol vol = new Vol();
        addObject(vol,190,337);
        back back = new back();
        addObject(back,189,513);
    }
    
    public void tambahVolume(){
        if (volume < 100) {
            volume += 25;
            if (volume > 100) {
            volume = 100; 
        }
            Lobby.bgm.setVolume(volume);
            Lobby.levelVolume = volume;
        }
    }
    
    public void kurangVolume(){
        if (volume > 0) {
            volume -= 25;
            if (volume < 0) {
            volume = 0; 
        }
            Lobby.bgm.setVolume(volume);
            Lobby.levelVolume = volume;
        }
    }
}
