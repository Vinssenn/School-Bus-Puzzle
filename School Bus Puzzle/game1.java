import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class game1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class game1 extends World
{

    /**
     * Constructor for objects of class game1.
     * 
     */
    public game1()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 750, 1); 
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        abu1 abu1 = new abu1();
        addObject(abu1,356,227);
        abu1.setLocation(356,227);
        abu1 abu12 = new abu1();
        addObject(abu12,930,228);
        abu12.setLocation(930,228);
        abu1 abu13 = new abu1();
        addObject(abu13,633,349);
        abu13.setLocation(633,349);
        L l = new L();
        addObject(l,284,684);
        l.setLocation(284,684);
        kotak kotak = new kotak();
        addObject(kotak,442,683);
        kotak.setLocation(442,683);
        S s = new S();
        addObject(s,605,683);
    }
}
