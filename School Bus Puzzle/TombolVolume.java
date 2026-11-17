import greenfoot.*;

/**
 * Teks persen volume di layar Setting.
 */
public class TombolVolume extends Actor
{
    private int shown = -1;

    public void act()
    {
        if (getWorld() instanceof Setting) {
            Setting duniaSetting = (Setting) getWorld();
            int nilaiVolume = duniaSetting.volume;
            if (nilaiVolume != shown) {      // gambar teks cuma dibuat ulang kalau angkanya berubah
                shown = nilaiVolume;
                GreenfootImage teks = new GreenfootImage(nilaiVolume + "%", 40, Color.WHITE, new Color(0,0,0,0));
                setImage(teks);
            }
        }
    }
}
