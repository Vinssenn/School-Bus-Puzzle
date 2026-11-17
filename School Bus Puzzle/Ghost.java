import greenfoot.*;

/**
 * Lapisan transparan seukuran papan yang menandai sel tujuan saat piece diseret
 * (hijau = bisa dipasang, merah = tidak bisa).
 */
public class Ghost extends Actor
{
    private GreenfootImage img;
    private int cs;

    public Ghost(int cols, int rows, int cellSize)
    {
        cs = cellSize;
        img = new GreenfootImage(cols * cs, rows * cs);
        setImage(img);
    }

    public void clear()
    {
        img.clear();
        setImage(img);
    }

    public void mark(int x, int y, boolean ok)
    {
        img.setColor(ok ? new Color(90, 255, 130, 120) : new Color(255, 70, 70, 130));
        img.fillRect(x * cs, y * cs, cs, cs);
    }

    public void refresh()
    {
        setImage(img);
    }
}
