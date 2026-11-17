/**
 * Bentuk-bentuk piece (sel-sel penyusunnya) + rotasi.
 * Kode: O = kotak, I = balok, T = T, S = S, U = "L" (biru, 5 kotak).
 * Koordinat sel (x, y), y ke bawah. Rotasi 90 derajat searah jarum jam.
 */
public class PieceType
{
    public static final int O = 0, I = 1, T = 2, S = 3, U = 4;
    public static final int COUNT = 5;

    private static final String CODES = "OITSU";
    private static final String[] TILE = {
        "tile_o.png", "tile_i.png", "tile_t.png", "tile_s.png", "tile_u.png"
    };
    private static final int[][][] BASE = {
        { {0,0}, {1,0}, {0,1}, {1,1} },            // O
        { {0,0}, {1,0}, {2,0}, {3,0} },            // I
        { {0,0}, {1,0}, {2,0}, {1,1} },            // T (batang ke bawah)
        { {1,0}, {2,0}, {0,1}, {1,1} },            // S
        { {0,0}, {2,0}, {0,1}, {1,1}, {2,1} }      // U
    };

    public static String tile(int type)
    {
        return TILE[type];
    }

    public static int fromCode(char c)
    {
        return CODES.indexOf(c);
    }

    public static int[][] cells(int type, int rot)
    {
        int[][] c = BASE[type];
        int turns = rot & 3;
        for (int i = 0; i < turns; i++) {
            c = rotateCw(c);
        }
        return c;
    }

    private static int[][] rotateCw(int[][] c)
    {
        int[][] r = new int[c.length][2];
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for (int i = 0; i < c.length; i++) {
            r[i][0] = -c[i][1];
            r[i][1] = c[i][0];
            minX = Math.min(minX, r[i][0]);
            minY = Math.min(minY, r[i][1]);
        }
        for (int i = 0; i < r.length; i++) {
            r[i][0] -= minX;
            r[i][1] -= minY;
        }
        return r;
    }

    public static int width(int[][] c)
    {
        int m = 0;
        for (int i = 0; i < c.length; i++) m = Math.max(m, c[i][0]);
        return m + 1;
    }

    public static int height(int[][] c)
    {
        int m = 0;
        for (int i = 0; i < c.length; i++) m = Math.max(m, c[i][1]);
        return m + 1;
    }
}
