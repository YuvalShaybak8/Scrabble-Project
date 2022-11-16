//package test;

public class Board {
    private static Tile boardArr[][] = null;
    Word wordsArr[] = new Word[15];
    int wordsCount = 0;

    public Board getBoard() {
        if (boardArr == null) {
            boardArr = new Tile[15][15];
        }
        return this;
    }

    public Tile[][] getTiles() {
        Tile copy[][] = boardArr.clone();
        return copy;
    }

    public boolean boardLegal() {
        if (boardArr[7][7] == null) {
            return false;
        }

        for (Word w : wordsArr) {
            if (w.getVertical()) {
                if (w.getRow() + w.getTilesArr().length > 15) {
                    return false;
                }
            } else {
                if (w.getCol() + w.getTilesArr().length > 15) {
                    return false;
                }
            }
        }

    }
}
