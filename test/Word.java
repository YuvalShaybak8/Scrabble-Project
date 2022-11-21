package test;

public class Word {
    private Tile tilesArr[] = new Tile[15];
    private int col;
    private int row;
    private boolean vertical;

    public Word(Tile[] tilesArr, int col, int row, boolean vertical) {
        this.tilesArr = tilesArr;
        this.col = col;
        this.row = row;
        this.vertical = vertical;
    }

    public boolean equals(Word other) {
        if (this.col != other.col || this.row != other.row || this.vertical != other.vertical) {
            return false;
        }
        for (int i = 0; i < 15; i++) {
            if (this.tilesArr[i] != other.tilesArr[i]) {
                return false;
            }
        }
        return true;
    }

    public Tile[] getTilesArr() {
        return tilesArr;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public boolean getVertical() {
        return vertical;
    }

}
