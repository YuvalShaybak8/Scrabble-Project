package test;

public class Board {
    private static Tile boardArr[][] = new Tile[15][15];
    Word wordsArr[] = new Word[15];
    int wordsCount = 0;
    static Board gameBoard = null;

    private Board() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                boardArr[i][j] = null;
            }
        }
    }

    public static Board getBoard() {
        if (gameBoard == null) {
            gameBoard = new Board();
        }
        return gameBoard;
    }

    public Tile[][] getTiles() {
        Tile[][] copy = (Tile[][]) boardArr.clone();
        return copy;
    }

    public boolean boardLegal(Word w) {
        for(int i = 0; i < w.getTilesArr().length; i++) {
            boolean isLegal = false;
            // if(w.getTilesArr()[i] 
            
        }
        if (w.getVertical()) {
            if (w.getRow() + w.getTilesArr().length > 15) {
                return false;
            }
            for (int i = 0; i < w.getTilesArr().length; i++) {
                if (boardArr[w.getRow() + i][w.getCol()] != null && boardArr[w.getRow() + i][w.getCol()] != w.getTilesArr()[i]) {
                    return false;
                }
            }


        } else {
            if (w.getCol() + w.getTilesArr().length > 15) {
                return false;
            }
            for (int i = 0; i < w.getTilesArr().length; i++) {
                if (boardArr[w.getRow()][w.getCol() + i] != null && boardArr[w.getRow()][w.getCol() + i] != w.getTilesArr()[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}    
