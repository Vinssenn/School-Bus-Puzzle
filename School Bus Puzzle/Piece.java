import greenfoot.*;

/**
 * Satu potongan puzzle (tetromino / pentomino) yang bisa digeser dan diputar.
 * Gambarnya disusun dari tile per sel, jadi bisa diputar ke 4 arah dan diskalakan.
 * Seluruh logika drag/drop ada di world game1; Piece hanya menyimpan data dan gambarnya.
 */
public class Piece extends ingame
{
    protected int type;
    public int rot = 0;           
    public boolean placed = false; 
    public int gx, gy;           
    public int slotX, slotY;      
    public int id;               
    public Piece(int type)
    {
        this.type = type;
        setImage(render(game1.TRAY_CELL));
    }

    public int getType()
    {
        return type;
    }

    public int[][] getCells()
    {
        return PieceType.cells(type, rot);
    }

    public int cellsW()
    {
        return PieceType.width(getCells());
    }

    public int cellsH()
    {
        return PieceType.height(getCells());
    }

    
    public GreenfootImage render(int cs)
    {
        int[][] c = getCells();
        GreenfootImage img = new GreenfootImage(PieceType.width(c) * cs, PieceType.height(c) * cs);
        GreenfootImage tile = new GreenfootImage(PieceType.tile(type));
        if (tile.getWidth() != cs) {
            tile.scale(cs, cs);
        }
        for (int i = 0; i < c.length; i++) {
            img.drawImage(tile, c[i][0] * cs, c[i][1] * cs);
        }
        return img;
    }

    public void act()
    {
        // Semua interaksi mouse ditangani oleh game1 (supaya pas per sel, bukan per kotak pembatas).
    }
}
