package test;

import java.util.ArrayList;

import test.Tile.Bag;

public class Board {
    private static Tile boardArr[][] = new Tile[15][15];
    Word wordsArr[] = new Word[15];
    int wordsCount = 0;
    static Board gameBoard = null;
    private String bonus[][] = new String[15][15];

    private Board() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                boardArr[i][j] = null;
            }
        }
        bonus[0][0] = "TW";
        bonus[0][7] = "TW";
        bonus[0][14] = "TW";
        bonus[7][0] = "TW";
        bonus[7][14] = "TW";
        bonus[14][0] = "TW";
        bonus[14][7] = "TW";
        bonus[14][14] = "TW";
        bonus[1][1] = "DW";
        bonus[1][13] = "DW";
        bonus[2][2] = "DW";
        bonus[2][12] = "DW";
        bonus[3][3] = "DW";
        bonus[3][11] = "DW";
        bonus[4][4] = "DW";
        bonus[4][10] = "DW";
        bonus[7][7] = "DS";
        bonus[10][4] = "DW";
        bonus[10][10] = "DW";
        bonus[11][3] = "DW";
        bonus[11][11] = "DW";
        bonus[12][2] = "DW";
        bonus[12][12] = "DW";
        bonus[13][1] = "DW";
        bonus[13][13] = "DW";
        bonus[1][5] = "TL";
        bonus[1][9] = "TL";
        bonus[5][1] = "TL";
        bonus[5][5] = "TL";
        bonus[5][9] = "TL";
        bonus[5][13] = "TL";
        bonus[9][1] = "TL";
        bonus[9][5] = "TL";
        bonus[9][9] = "TL";
        bonus[9][13] = "TL";
        bonus[13][5] = "TL";
        bonus[13][9] = "TL";
        bonus[0][3] = "DL";
        bonus[0][11] = "DL";
        bonus[2][6] = "DL";
        bonus[2][8] = "DL";
        bonus[3][0] = "DL";
        bonus[3][7] = "DL";
        bonus[3][14] = "DL";
        bonus[6][2] = "DL";
        bonus[6][6] = "DL";
        bonus[6][8] = "DL";
        bonus[6][12] = "DL";
        bonus[7][3] = "DL";
        bonus[7][11] = "DL";
        bonus[8][2] = "DL";
        bonus[8][6] = "DL";
        bonus[8][8] = "DL";
        bonus[8][12] = "DL";
        bonus[11][0] = "DL";
        bonus[11][7] = "DL";
        bonus[11][14] = "DL";
        bonus[12][6] = "DL";
        bonus[12][8] = "DL";
        bonus[14][3] = "DL";
        bonus[14][11] = "DL";
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
                        && boardArr[w.getRow() + i][w.getCol()] != w.getTilesArr()[i] && w.getTilesArr()[i] != null) {
                    return false;
                }

                if (boardArr[w.getRow() + i][w.getCol()] == null
                        && boardArr[w.getRow() + i][w.getCol()] != w.getTilesArr()[i])
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
                        && boardArr[w.getRow()][w.getCol() + i] != w.getTilesArr()[i] && w.getTilesArr()[i] != null)
                    return false;

                if (boardArr[w.getRow()][w.getCol() + i] == null
                        && boardArr[w.getRow()][w.getCol() + i] != w.getTilesArr()[i])
                    counter++;

                if (w.getRow() == 7 && w.getCol() + i == 7 && boardArr[7][7] == null)
                    isLegal = true;

                if (counter < w.getTilesArr().length && counter > 0 && boardArr[7][7] != null)
                    isLegal = true;
            }
        }
        return isLegal;
    }

    public boolean dictionaryLegal(Word w) {
        return true;
    }

    public ArrayList<Word> getWords(Word w) {
        ArrayList<Word> words = new ArrayList<Word>();
        int col = w.getCol();
        int row = w.getRow();
        int start;
        int finish;
        boolean vertical = w.getVertical();
        Tile[] tiles = w.getTilesArr();
        ArrayList<Tile> temp = new ArrayList<Tile>();
        words.add(w);
        for (int i = 0; i < tiles.length; i++) {
            if (vertical) {
                start = col;
                finish = col;
                while (start - 1 > -1 && boardArr[row + i][start - 1] != null) {
                    start--;
                }
                while (finish + 1 < 15 && boardArr[row + i][finish + 1] != null) {
                    finish++;
                }
                if (start != col || finish != col) {
                    for (int j = start; j <= finish; j++) {
                        temp.add(boardArr[row + i][j]);
                    }
                    Word word = new Word(temp.toArray(new Tile[temp.size()]), row + i, start, false);
                    for (int j = 0; j < wordsCount; j++) {
                        if (wordsArr[j].equals(word)) {
                            word = null;
                            break;
                        }
                    }
                    if (word != null) {
                        words.add(word);
                    }
                }

            } else {
                start = row;
                finish = row;
                while (start - 1 > -1 && boardArr[start - 1][col + i] != null) {
                    start--;
                }
                while (finish + 1 < 15 && boardArr[finish + 1][col + i] != null) {
                    finish++;
                }
                if (start != row || finish != row) {
                    temp.clear();
                    for (int j = start; j <= finish; j++) {
                        temp.add(boardArr[j][col + i]);
                    }
                    if (temp.size() > 0) {
                        Word word = new Word(temp.toArray(new Tile[temp.size()]), start, col + i, true);
                        for (int j = 0; j < wordsCount; j++) {
                            if (wordsArr[j].equals(word)) {
                                word = null;
                                break;
                            }
                        }
                        if (word != null) {
                            words.add(word);
                        }
                    }
                }

            }
        }
        return words;
    }

    public int getScore(Word w) {
        if (wordsCount > 1) {
            bonus[7][7] = null;
        }
        int score = 0;
        int col = w.getCol();
        int row = w.getRow();
        boolean vertical = w.getVertical();
        Tile[] tiles = w.getTilesArr();
        for (int i = 0; i < tiles.length; i++) {
            if (vertical) {
                if (bonus[row + i][col] == null || bonus[row + i][col].equals("DW") || bonus[row + i][col].equals("TW") || bonus[row+ i][col].equals("DS"))
                 if(tiles[i] != null)
                    score += tiles[i].getScore();
                else if (bonus[row + i][col].equals("TL")) {
                    if (tiles[i] != null)
                       score += tiles[i].getScore() * 3;
                } else if (bonus[row + i][col].equals("DL")) {
                    if (tiles[i] != null)
                       score += tiles[i].getScore() * 2;
                }
            } else {
                if (bonus[row][col + i] == null || bonus[row][col + i].equals("DW") || bonus[row][col + i].equals("TW") 
                        || bonus[row + i][col].equals("DS"))
                   if(tiles[i] != null)
                      score += tiles[i].getScore();
                else if (bonus[row][col + i].equals("TL")) {
                    if (tiles[i] != null)
                      score += tiles[i].getScore() * 3;
                } else if (bonus[row][col + i].equals("DL")) {
                    if (tiles[i] != null)
                       score += tiles[i].getScore() * 2;
                }

            }
        }
        for (int i = 0; i < tiles.length; i++) {
            if (vertical) {
                if (bonus[row + i][col] == null)
                    continue;
                else if (bonus[row + i][col].equals("DS") && wordsCount == 1) {
                    score *= 2;
                }
                else if (bonus[row + i][col].equals("DW")) {
                    score *= 2;
                } else if (bonus[row + i][col].equals("TW")) {
                    score *= 3;
                }
            } else {
                if (bonus[row][col + i] == null)
                    continue;
                else if (bonus[row][col + i].equals("DS") && wordsCount == 1) {
                    score *= 2;
                }    
                else if (bonus[row][col + i].equals("DW")) {
                    score *= 2;
                } else if (bonus[row][col + i].equals("TW")) {
                    score *= 3;
                }
            }
        }
        return score;
    }

    public int tryPlaceWord(Word w) {
        int score = 0;
        int col = w.getCol();
        int row = w.getRow();
        boolean vertical = w.getVertical();
        Tile[] tiles = w.getTilesArr();

        if (!boardLegal(w))
            return 0;

        ArrayList<Word> words = getWords(w);
        wordsCount+=words.size();
        for (Word words2 : words) {
            if (!dictionaryLegal(words2))
                return 0;
        }

        score += getScore(w);

        for (int i = 0; i < tiles.length; i++) {
            if (vertical) {
                if (boardArr[row + i][col] == null) {
                    boardArr[row + i][col] = tiles[i];
                }
            } else {
                if (boardArr[row][col + i] == null) {
                    boardArr[row][col + i] = tiles[i];
                }
            }
        }
        return score;
    }

}
