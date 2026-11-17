import greenfoot.*;

/**
 * Ikon bintang (kosong atau penuh).
 */
public class Bintang extends Actor
{
    public Bintang()
    {
        setFull(false, 64);
    }

    public void setFull(boolean full, int size)
    {
        GreenfootImage g = new GreenfootImage(full ? "bintang penuh.png" : "bintang kosong.png");
        if (size != g.getWidth()) {
            g.scale(size, size);
        }
        setImage(g);
    }
}
