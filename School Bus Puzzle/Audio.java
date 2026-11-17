import greenfoot.*;

/**
 * Pengatur suara: musik latar (menu / game) dan efek suara.
 * Volume (0-100) diatur dari menu Setting dan berlaku untuk musik maupun efek.
 */
public class Audio
{
    public static int volume = 100;

    private static GreenfootSound menuMusic = new GreenfootSound("bgm.mp3");
    private static GreenfootSound gameMusic = new GreenfootSound("bgm2.mp3");
    private static GreenfootSound current;

    public static void playMenu()
    {
        switchTo(menuMusic);
    }

    public static void playGame()
    {
        switchTo(gameMusic);
    }

    private static void switchTo(GreenfootSound s)
    {
        if (current != null && current != s) {
            current.stop();
        }
        current = s;
        s.setVolume(volume);
        if (!s.isPlaying()) {
            s.playLoop();
        }
    }

    public static void setVolume(int v)
    {
        volume = Math.max(0, Math.min(100, v));
        if (current != null) {
            current.setVolume(volume);
        }
    }

    public static void sfx(String file)
    {
        if (volume <= 0) {
            return;
        }
        GreenfootSound s = new GreenfootSound(file);
        s.setVolume(volume);
        s.play();
    }
}
