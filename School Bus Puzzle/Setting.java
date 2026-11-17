import greenfoot.*;

/**
 * Pengaturan volume (musik dan efek suara), naik/turun per 25%.
 */
public class Setting extends World
{
    public int volume = Audio.volume;

    public Setting()
    {
        super(1024, 768, 1);
        setBackground("setting.png");
        prepare();
    }

    private void prepare()
    {
        addObject(new BtnPlus(), 730, 336);
        addObject(new BtnMinus(), 321, 338);
        addObject(new TombolVolume(), 519, 334);
        addObject(new Vol(), 190, 337);
        addObject(new back(), 189, 513);
    }

    public void tambahVolume()
    {
        if (volume < 100) {
            volume = Math.min(100, volume + 25);
            Audio.setVolume(volume);
            Audio.sfx("sfx_click.wav");
        }
    }

    public void kurangVolume()
    {
        if (volume > 0) {
            volume = Math.max(0, volume - 25);
            Audio.setVolume(volume);
            Audio.sfx("sfx_click.wav");
        }
    }
}
