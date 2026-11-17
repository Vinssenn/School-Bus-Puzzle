/**
 * Menyimpan bintang yang sudah didapat per level selama game berjalan.
 * Level 1 selalu terbuka; level berikutnya terbuka kalau level sebelumnya sudah selesai.
 */
public class Progress
{
    public static final int[] stars = new int[Levels.COUNT + 2];

    public static boolean isUnlocked(int level)
    {
        return level == 1 || (level > 1 && level <= Levels.COUNT && stars[level - 1] > 0);
    }

    public static void record(int level, int s)
    {
        if (level >= 1 && level <= Levels.COUNT && s > stars[level]) {
            stars[level] = s;
        }
    }

    public static int totalStars()
    {
        int t = 0;
        for (int i = 1; i <= Levels.COUNT; i++) t += stars[i];
        return t;
    }
}
