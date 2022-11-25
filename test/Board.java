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
        boolean isLegal = false;
        int counter = 0;
        if (w.getRow() < 0 || w.getRow() > 14 || w.getCol() < 0 || w.getCol() > 14) {
            return false;
        }
        if (w.getVertical()) {
            if (w.getRow() + w.getTilesArr().length > 14)
                return false;

            for (int i = 0; i < w.getTilesArr().length; i++) {
                if (boardArr[w.getRow() + i][w.getCol()] != null
                        && boardArr[w.getRow() + i][w.getCol()] != w.getTilesArr()[i])
                    return false;

                if (boardArr[w.getRow() + i][w.getCol()] != null
                        && boardArr[w.getRow() + i][w.getCol()] == w.getTilesArr()[i])
                    counter++;

                if (w.getRow() + i == 7 && w.getCol() == 7 && boardArr[7][7] == null)
                    isLegal = true;
            }
            if (counter < w.getTilesArr().length && counter > 0 && boardArr[7][7] != null)
                isLegal = true;

        } else {
            if (w.getCol() + w.getTilesArr().length > 14)
                return false;

            for (int i = 0; i < w.getTilesArr().length; i++) {
                if (boardArr[w.getRow()][w.getCol() + i] != null
                        && boardArr[w.getRow()][w.getCol() + i] != w.getTilesArr()[i])
                    return false;

                if (boardArr[w.getRow()][w.getCol() + i] != null
                        && boardArr[w.getRow()][w.getCol() + i] == w.getTilesArr()[i])
                    counter++;

                if (w.getRow() == 7 && w.getCol() + i == 7 && boardArr[7][7] == null)
                    isLegal = true;

                if (counter < w.getTilesArr().length && counter > 0 && boardArr[7][7] != null)
                    isLegal = true;
            }
        }
        return isLegal;
    }
}
