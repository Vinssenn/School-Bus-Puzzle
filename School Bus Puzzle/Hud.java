import greenfoot.*;

/**
 * Pembuat gambar teks/angka untuk tampilan (HUD) dari sprite angka di folder images.
 */
public class Hud
{
    private static GreenfootImage[] cache = new GreenfootImage[128];

    private static GreenfootImage sprite(char c)
    {
        if (cache[c] == null) {
            String f;
            if (c == ':') f = "numcolon.png";
            else if (c == '/') f = "numslash.png";
            else f = "num" + c + ".png";
            cache[c] = new GreenfootImage(f);
        }
        return cache[c];
    }

    public static GreenfootImage number(String text, double scale)
    {
        int w = 0;
        int h = 0;
        for (int i = 0; i < text.length(); i++) {
            GreenfootImage s = sprite(text.charAt(i));
            w += s.getWidth();
            h = Math.max(h, s.getHeight());
        }
        GreenfootImage out = new GreenfootImage(w, h);
        int x = 0;
        for (int i = 0; i < text.length(); i++) {
            GreenfootImage s = sprite(text.charAt(i));
            out.drawImage(s, x, (h - s.getHeight()) / 2);
            x += s.getWidth();
        }
        if (scale != 1.0) {
            out.scale((int) Math.round(w * scale), (int) Math.round(h * scale));
        }
        return out;
    }

    /** Label (gambar teks) di kiri + angka di kanan, mis. "LEVEL 3" atau "TIME 0:25". */
    public static GreenfootImage line(String labelFile, String text, double scale, int gap)
    {
        GreenfootImage lbl = new GreenfootImage(labelFile);
        GreenfootImage num = number(text, scale);
        int w = lbl.getWidth() + gap + num.getWidth();
        int h = Math.max(lbl.getHeight(), num.getHeight());
        GreenfootImage out = new GreenfootImage(w, h);
        out.drawImage(lbl, 0, (h - lbl.getHeight()) / 2);
        out.drawImage(num, lbl.getWidth() + gap, (h - num.getHeight()) / 2);
        return out;
    }

    /** Ikon bintang + "have/total". */
    public static GreenfootImage starCount(int have, int total)
    {
        GreenfootImage star = new GreenfootImage("bintang penuh.png");
        star.scale(48, 48);
        GreenfootImage num = number(have + "/" + total, 0.8);
        int w = star.getWidth() + 10 + num.getWidth();
        int h = Math.max(star.getHeight(), num.getHeight());
        GreenfootImage out = new GreenfootImage(w, h);
        out.drawImage(star, 0, (h - star.getHeight()) / 2);
        out.drawImage(num, star.getWidth() + 10, (h - num.getHeight()) / 2);
        return out;
    }

    
    public static String clock(long ms)
    {
        long sec = ms / 1000;
        long m = sec / 60;
        long s = sec % 60;
        return m + ":" + (s < 10 ? "0" : "") + s;
    }
}
