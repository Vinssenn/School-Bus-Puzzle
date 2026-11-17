/**
 * Data semua level (dibuat otomatis oleh generator, setiap level dijamin punya solusi).
 * Kode sel: '.' = kosong (harus diisi), '#' = blok abu-abu, ' ' = tidak dipakai.
 * Kode piece: O = kotak, I = balok, T = T, S = S, U = L (biru, 5 kotak).
 */
public class Levels
{
    public static final int COUNT = 10;

    private static final String[][] GRID = {
        { "....", "....", "...." },
        { "...#", "...#", "...#", "...#" },
        { ".......", "....##.", "...##.." },
        { ".....", ".....", ".....", "...#.", "..###" },
        { "####..", ".#.##.", "...#..", "......", "......" },
        { " ####......", "##.......##", "#........# " },
        { "## ######", ".###..##.", ".##......", ".##......", ".##......" },
        { "##........", "#....##.#.", "##...#####", " ###..####", "   #......" },
        { "#### #...##", ".#.# ##...#", "...## #..##", "....# #....", "....# #####" },
        { "..#...#  #...", "..##..## ##..", "####...# #...", "######## ###.", "#.#.#      ##", "#...#        " },
    };

    private static final String[] PIECES = { "IOO", "OOI", "ITUS", "UOISO", "IOUUI", "OUSSI", "UTSTIT", "ITUIOO", "SUIOTO", "OTITUU" };

    /** Target waktu (detik) untuk bintang 3. */
    private static final int[] PAR = { 30, 40, 55, 70, 85, 100, 120, 140, 165, 200 };

    public static String[] grid(int level)   { return GRID[level - 1]; }
    public static String pieces(int level)   { return PIECES[level - 1]; }
    public static int par(int level)         { return PAR[level - 1]; }
    public static int width(int level)       { return GRID[level - 1][0].length(); }
    public static int height(int level)      { return GRID[level - 1].length; }
    public static boolean exists(int level)  { return level >= 1 && level <= COUNT; }
}
