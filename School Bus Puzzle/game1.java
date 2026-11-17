import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Dunia permainan satu level.
 *
 * Cara main: isi SEMUA kotak kosong di papan tulis dengan piece yang ada di rak bawah.
 *  - Seret piece dari rak ke papan (otomatis pas ke kotak terdekat).
 *  - Klik piece (atau tekan R / spasi saat menyeret) untuk memutar 90 derajat.
 *  - Piece yang sudah terpasang boleh diangkat lagi dan dipindah.
 *  - Bintang ditentukan dari waktu penyelesaian.
 *
 * Papan disimpan sebagai grid: -2 = tidak dipakai, -1 = blok abu-abu, 0 = kosong,
 * lebih dari 0 = nomor piece yang menempati sel itu.
 */
public class game1 extends World
{
    public static final int CELL = 50;       
    public static final int TRAY_CELL = 32;  

    private static final int BOARD_CX = 643; 
    private static final int BOARD_CY = 330;
    private static final int TRAY_X0 = 168;  
    private static final int TRAY_W = 852;
    private static final int TRAY_Y = 686;
    private static final int TRAY_TOP = 598;
    private static final int SLOT_W = 142;
    private static final int DRAG_THRESHOLD = 6;

    private final int level;
    private final int gw, gh;     
    private final int gx0, gy0;   
    private final int[][] cell;
    private Piece[] pieces;
    private Ghost ghost;
    private Label timeLabel;

    // status drag
    private Piece held;
    private boolean dragged;
    private boolean wasPlaced;
    private int origGx, origGy, origRot;
    private int pressX, pressY, lastX, lastY;
    private double grabU, grabV;         
    private int heldLeft, heldTop;
    private int candX, candY;
    private boolean candOk;

   
    private long elapsedMs;
    private long lastTick;
    private long shownSec = -1;
    private boolean won;
    private int winDelay;
    private int stars;

    public game1()
    {
        this(1);
    }

    public game1(int level)
    {
        super(1024, 768, 1, false);
        if (!Levels.exists(level)) {
            level = 1;
        }
        this.level = level;
        String[] g = Levels.grid(level);
        gw = g[0].length();
        gh = g.length;
        gx0 = BOARD_CX - gw * CELL / 2;
        gy0 = BOARD_CY - gh * CELL / 2;
        cell = new int[gh][gw];

        setBackground("polosan.png");
        setPaintOrder(Label.class, Ghost.class, Piece.class, abu1.class, BoardGrid.class);
        Audio.playGame();
        prepare(g);
        lastTick = System.currentTimeMillis();
    }

    public int getLevel()
    {
        return level;
    }

    /**
     * Membuat papan, piece di rak, dan tampilan HUD.
     */
    private void prepare(String[] g)
    {
        addObject(new BoardGrid(g, CELL), gx0 + gw * CELL / 2, gy0 + gh * CELL / 2);
        for (int y = 0; y < gh; y++) {
            for (int x = 0; x < gw; x++) {
                char c = g[y].charAt(x);
                if (c == '.') {
                    cell[y][x] = 0;
                } else if (c == '#') {
                    cell[y][x] = -1;
                    addObject(new abu1(), gx0 + x * CELL + CELL / 2, gy0 + y * CELL + CELL / 2);
                } else {
                    cell[y][x] = -2;
                }
            }
        }
        ghost = new Ghost(gw, gh, CELL);
        addObject(ghost, gx0 + gw * CELL / 2, gy0 + gh * CELL / 2);

    
        String pcs = Levels.pieces(level);
        int k = pcs.length();
        pieces = new Piece[k];
        int startX = TRAY_X0 + (TRAY_W - k * SLOT_W) / 2;
        for (int i = 0; i < k; i++) {
            Piece p = createPiece(PieceType.fromCode(pcs.charAt(i)));
            p.id = i + 1;
            p.slotX = startX + SLOT_W * i + SLOT_W / 2;
            p.slotY = TRAY_Y;
            pieces[i] = p;
            addObject(p, p.slotX, p.slotY);
        }

        // HUD
        addObject(new BtnPause(), 62, 62);
        addObject(new BtnReset(), 156, 62);
        addObject(new Label(Hud.line("lbl_level.png", String.valueOf(level), 0.9, 14)), 400, 55);
        timeLabel = new Label();
        addObject(timeLabel, 650, 55);
        refreshTime();
        addObject(new Label(Hud.line("lbl_target.png", Hud.clock(Levels.par(level) * 1000L), 0.6, 10)), 890, 55);
        addObject(new Label(new GreenfootImage("hint.png")), 600, 112);
    }

    private Piece createPiece(int type)
    {
        switch (type) {
            case PieceType.O: return new kotak();
            case PieceType.I: return new balok();
            case PieceType.T: return new T();
            case PieceType.S: return new S();
            default:          return new L();
        }
    }

    public void act()
    {
        if (won) {
            winDelay--;
            if (winDelay <= 0) {
                Greenfoot.setWorld(new Selesai(level, stars, elapsedMs));
            }
            return;
        }
        tick();
        handleKeys();
        handleMouse();
    }



    private void tick()
    {
        long now = System.currentTimeMillis();
        long dt = now - lastTick;
        lastTick = now;
        if (dt > 250) {
            dt = 0;  
        }
        elapsedMs += dt;
        if (elapsedMs / 1000 != shownSec) {
            refreshTime();
        }
    }

    private void refreshTime()
    {
        shownSec = elapsedMs / 1000;
        timeLabel.setImg(Hud.line("lbl_time.png", Hud.clock(elapsedMs), 1.0, 14));
    }

    

    private void handleKeys()
    {
        String k = Greenfoot.getKey();
        while (k != null) {
            if (held != null && (k.equalsIgnoreCase("r") || k.equals("space"))) {
                rotateHeld();
            }
            k = Greenfoot.getKey();
        }
    }

    private void handleMouse()
    {
        MouseInfo mi;
        if (Greenfoot.mousePressed(null)) {
            mi = Greenfoot.getMouseInfo();
            if (mi != null) {
                onPress(mi.getX(), mi.getY(), mi.getButton());
            }
        }
        if (held != null && Greenfoot.mouseDragged(null)) {
            mi = Greenfoot.getMouseInfo();
            if (mi != null) {
                onDrag(mi.getX(), mi.getY());
            }
        }
        if (held != null && (Greenfoot.mouseDragEnded(null) || Greenfoot.mouseClicked(null))) {
            mi = Greenfoot.getMouseInfo();
            if (dragged && mi != null) {
                onDrag(mi.getX(), mi.getY());
            }
            onRelease();
        }
    }


    private Piece pickPiece(int x, int y)
    {
        for (int i = pieces.length - 1; i >= 0; i--) {
            Piece p = pieces[i];
            int cs = p.placed ? CELL : TRAY_CELL;
            GreenfootImage img = p.getImage();
            int left = p.getX() - img.getWidth() / 2;
            int top = p.getY() - img.getHeight() / 2;
            if (x < left || y < top) {
                continue;
            }
            int cx = (x - left) / cs;
            int cy = (y - top) / cs;
            int[][] c = p.getCells();
            for (int j = 0; j < c.length; j++) {
                if (c[j][0] == cx && c[j][1] == cy) {
                    return p;
                }
            }
        }
        return null;
    }

    private void onPress(int x, int y, int button)
    {
        if (held != null) {
            return;
        }
        Piece p = pickPiece(x, y);
        if (p == null) {
            return;
        }
        if (button == 3) {           
            rotatePiece(p);
            return;
        }
        held = p;
        dragged = false;
        pressX = x;
        pressY = y;
        lastX = x;
        lastY = y;
        wasPlaced = p.placed;
        origGx = p.gx;
        origGy = p.gy;
        origRot = p.rot;
        int cs = p.placed ? CELL : TRAY_CELL;
        GreenfootImage img = p.getImage();
        int left = p.getX() - img.getWidth() / 2;
        int top = p.getY() - img.getHeight() / 2;
        grabU = (x - left) / (double) cs;
        grabV = (y - top) / (double) cs;
    }

    private void onDrag(int x, int y)
    {
        lastX = x;
        lastY = y;
        if (!dragged) {
            int dx = x - pressX;
            int dy = y - pressY;
            if (dx * dx + dy * dy < DRAG_THRESHOLD * DRAG_THRESHOLD) {
                return;
            }
            startDrag();
        }
        positionHeld();
        updateGhost();
    }

    
    private void startDrag()
    {
        dragged = true;
        if (held.placed) {
            freeCells(held);
            held.placed = false;
        }
        held.setImage(held.render(CELL));
        removeObject(held);            
        addObject(held, lastX, lastY);
        positionHeld();
        Audio.sfx("sfx_pick.wav");
    }

    private void positionHeld()
    {
        GreenfootImage img = held.getImage();
        heldLeft = (int) Math.round(lastX - grabU * CELL);
        heldTop = (int) Math.round(lastY - grabV * CELL);
        held.setLocation(heldLeft + img.getWidth() / 2, heldTop + img.getHeight() / 2);
    }

    /** Hitung sel tujuan terdekat dan tandai di papan (hijau / merah). */
    private void updateGhost()
    {
        ghost.clear();
        candX = (int) Math.round((heldLeft - gx0) / (double) CELL);
        candY = (int) Math.round((heldTop - gy0) / (double) CELL);
        int[][] c = held.getCells();
        candOk = fits(c, candX, candY);
        for (int i = 0; i < c.length; i++) {
            int x = candX + c[i][0];
            int y = candY + c[i][1];
            if (x >= 0 && x < gw && y >= 0 && y < gh && cell[y][x] != -2) {
                ghost.mark(x, y, candOk);
            }
        }
        ghost.refresh();
    }

    
    private void rotateHeld()
    {
        if (!dragged) {
            startDrag();
        }
        int oldH = held.cellsH();
        double u = grabU;
        double v = grabV;
        grabU = oldH - v;
        grabV = u;
        held.rot = (held.rot + 1) & 3;
        held.setImage(held.render(CELL));
        positionHeld();
        updateGhost();
        Audio.sfx("sfx_rotate.wav");
    }

    private void onRelease()
    {
        Piece p = held;
        held = null;
        ghost.clear();
        if (!dragged) {               
            rotatePiece(p);
            return;
        }
        dragged = false;
        if (candOk) {
            occupy(p, candX, candY);
            Audio.sfx("sfx_place.wav");
            checkWin();
        } else if (!wasPlaced || lastY >= TRAY_TOP) {
            returnToTray(p);          
            if (lastY < TRAY_TOP) {
                Audio.sfx("sfx_error.wav");
            }
        } else {
            p.rot = origRot;          
            occupy(p, origGx, origGy);
            Audio.sfx("sfx_error.wav");
        }
    }

    

    private boolean fits(int[][] c, int px, int py)
    {
        for (int i = 0; i < c.length; i++) {
            int x = px + c[i][0];
            int y = py + c[i][1];
            if (x < 0 || x >= gw || y < 0 || y >= gh || cell[y][x] != 0) {
                return false;
            }
        }
        return true;
    }

    private void freeCells(Piece p)
    {
        for (int y = 0; y < gh; y++) {
            for (int x = 0; x < gw; x++) {
                if (cell[y][x] == p.id) {
                    cell[y][x] = 0;
                }
            }
        }
    }

    /** Pasang piece di papan dengan sel kiri-atas (px, py);. */
    private void occupy(Piece p, int px, int py)
    {
        p.placed = true;
        p.gx = px;
        p.gy = py;
        int[][] c = p.getCells();
        for (int i = 0; i < c.length; i++) {
            cell[py + c[i][1]][px + c[i][0]] = p.id;
        }
        p.setImage(p.render(CELL));
        GreenfootImage img = p.getImage();
        p.setLocation(gx0 + px * CELL + img.getWidth() / 2, gy0 + py * CELL + img.getHeight() / 2);
    }

    private void returnToTray(Piece p)
    {
        p.placed = false;
        p.setImage(p.render(TRAY_CELL));
        p.setLocation(p.slotX, p.slotY);
    }

    /** Putar piece 90 derajat searah jarum jam di tempatnya. */
    private void rotatePiece(Piece p)
    {
        if (!p.placed) {
            p.rot = (p.rot + 1) & 3;
            returnToTray(p);
            Audio.sfx("sfx_rotate.wav");
            return;
        }
        int oldW = p.cellsW();
        int oldH = p.cellsH();
        int oldRot = p.rot;
        freeCells(p);
        p.rot = (oldRot + 1) & 3;
        int[][] nc = p.getCells();
        int bx = p.gx + (oldW - p.cellsW()) / 2;
        int by = p.gy + (oldH - p.cellsH()) / 2;
        int[][] offs = { {0,0}, {-1,0}, {1,0}, {0,-1}, {0,1}, {-1,-1}, {1,-1}, {-1,1}, {1,1} };
        for (int i = 0; i < offs.length; i++) {
            if (fits(nc, bx + offs[i][0], by + offs[i][1])) {
                occupy(p, bx + offs[i][0], by + offs[i][1]);
                Audio.sfx("sfx_rotate.wav");
                return;
            }
        }
        p.rot = oldRot;              
        occupy(p, p.gx, p.gy);
        Audio.sfx("sfx_error.wav");
    }

    

    private void checkWin()
    {
        for (int y = 0; y < gh; y++) {
            for (int x = 0; x < gw; x++) {
                if (cell[y][x] == 0) {
                    return;
                }
            }
        }
        win();
    }

    private void win()
    {
        won = true;
        winDelay = 80;
        long sec = elapsedMs / 1000;
        int par = Levels.par(level);
        stars = sec <= par ? 3 : (sec <= par * 2 ? 2 : 1);
        Progress.record(level, stars);
        Audio.sfx("sfx_win.wav");

        GreenfootImage title = new GreenfootImage("title_complete.png");
        GreenfootImage panel = new GreenfootImage(title.getWidth() + 70, title.getHeight() + 40);
        panel.setColor(new Color(0, 0, 0, 150));
        panel.fillRect(0, 0, panel.getWidth(), panel.getHeight());
        panel.drawImage(title, 35, 20);
        addObject(new Label(panel), BOARD_CX, BOARD_CY);
    }
}
