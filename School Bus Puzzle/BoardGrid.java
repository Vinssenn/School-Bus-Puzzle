import greenfoot.*;

/**
 * Gambar kotak-kotak kosong di papan tulis (sel yang harus diisi piece).
 */
public class BoardGrid extends Actor
{
    public BoardGrid(String[] grid, int cellSize)
    {
        int rows = grid.length;
        int cols = grid[0].length();
        GreenfootImage img = new GreenfootImage(cols * cellSize, rows * cellSize);
        GreenfootImage empty = new GreenfootImage("cell_empty.png");
        if (empty.getWidth() != cellSize) {
            empty.scale(cellSize, cellSize);
        }
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (grid[y].charAt(x) == '.') {
                    img.drawImage(empty, x * cellSize, y * cellSize);
                }
            }
        }
        setImage(img);
    }
}
