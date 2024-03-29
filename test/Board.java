package test;
import java.util.*;

public class Board {
    private static Tile boardArr[][] = new Tile[15][15];
    private Word wordsArr[] = new Word[15];
    private int wordsCount = 0;
    private static Board gameBoard = null;
    private String bonusArr[][] = new String[15][15];

    private Board() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                boardArr[i][j] = null;
            }
        }
        bonusArr[0][0] = "TW";
        bonusArr[0][7] = "TW";
        bonusArr[0][14] = "TW";
        bonusArr[7][0] = "TW";
        bonusArr[7][14] = "TW";
        bonusArr[14][0] = "TW";
        bonusArr[14][7] = "TW";
        bonusArr[14][14] = "TW";
        bonusArr[1][1] = "DW";
        bonusArr[1][13] = "DW";
        bonusArr[2][2] = "DW";
        bonusArr[2][12] = "DW";
        bonusArr[3][3] = "DW";
        bonusArr[3][11] = "DW";
        bonusArr[4][4] = "DW";
        bonusArr[4][10] = "DW";
        bonusArr[7][7] = "DW";
        bonusArr[10][4] = "DW";
        bonusArr[10][10] = "DW";
        bonusArr[11][3] = "DW";
        bonusArr[11][11] = "DW";
        bonusArr[12][2] = "DW";
        bonusArr[12][12] = "DW";
        bonusArr[13][1] = "DW";
        bonusArr[13][13] = "DW";
        bonusArr[1][5] = "TL";
        bonusArr[1][9] = "TL";
        bonusArr[5][1] = "TL";
        bonusArr[5][5] = "TL";
        bonusArr[5][9] = "TL";
        bonusArr[5][13] = "TL";
        bonusArr[9][1] = "TL";
        bonusArr[9][5] = "TL";
        bonusArr[9][9] = "TL";
        bonusArr[9][13] = "TL";
        bonusArr[13][5] = "TL";
        bonusArr[13][9] = "TL";
        bonusArr[0][3] = "DL";
        bonusArr[0][11] = "DL";
        bonusArr[2][6] = "DL";
        bonusArr[2][8] = "DL";
        bonusArr[3][0] = "DL";
        bonusArr[3][7] = "DL";
        bonusArr[3][14] = "DL";
        bonusArr[6][2] = "DL";
        bonusArr[6][6] = "DL";
        bonusArr[6][8] = "DL";
        bonusArr[6][12] = "DL";
        bonusArr[7][3] = "DL";
        bonusArr[7][11] = "DL";
        bonusArr[8][2] = "DL";
        bonusArr[8][6] = "DL";
        bonusArr[8][8] = "DL";
        bonusArr[8][12] = "DL";
        bonusArr[11][0] = "DL";
        bonusArr[11][7] = "DL";
        bonusArr[11][14] = "DL";
        bonusArr[12][6] = "DL";
        bonusArr[12][8] = "DL";
        bonusArr[14][3] = "DL";
        bonusArr[14][11] = "DL";
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
        int RowL = w.getRow();
        int ColL = w.getCol();
        boolean legal = false;
        boolean isVertical = w.getVertical();
        Tile[] tilesArr = w.getTilesArr();

        if (RowL < 0 || RowL > 14 || ColL < 0 || ColL > 14) {
            return false;
        }
        if (isVertical) {
            if (RowL + tilesArr.length > 14)
                return false;
            
            if(boardArr[7][7] == null)
            {
                if(ColL != 7)
                    return false;
                if(RowL + tilesArr.length < 7 || RowL > 7)
                    return false;      
            }
            else{
                for (int i = 0; i < tilesArr.length; i++){
                    if((ColL-1>-1 && boardArr[RowL+i][ColL-1]!=null) || (RowL+i+1 < 15 && boardArr[RowL+i+1][ColL]!=null) || (RowL+i-1>-1 && boardArr[RowL+i-1][ColL]!=null) || boardArr[RowL+i][ColL]!=null || (ColL+1<15 && boardArr[RowL+i][ColL+1] != null))
                       legal = true;

                    if (boardArr[RowL + i][ColL] != null && boardArr[RowL + i][ColL].getLetter() != tilesArr[i].getLetter())
                        return false;
                }
            
                if(!legal)
                   return false;
            }
        }
        else{
        if (ColL + tilesArr.length > 14)
            return false;

        if (boardArr[7][7] == null) {
            if (RowL != 7)
                return false;
            if (ColL + tilesArr.length < 7 || ColL > 7)
                return false;
        } else {
                 for (int i = 0; i < tilesArr.length; i++) {
                     if (boardArr[RowL][ColL+i] != null || (RowL + 1 < 15 && boardArr[RowL + 1][ColL + i] != null) || (RowL - 1 > -1 && boardArr[RowL - 1][ColL + i] != null) || (ColL + i + 1 < 15 && boardArr[RowL ][ColL + i + 1] != null) || (ColL + i - 1 > -1 && boardArr[RowL][ColL + i - 1] != null))
                        legal = true;

                     if (boardArr[RowL][ColL + i] != null && boardArr[RowL][ColL + i].getLetter() != tilesArr[i].getLetter())
                        return false;
                    }

            if (!legal)
                return false;
           }
        }
        return true;
    }

    public boolean dictionaryLegal(Word w) {
        return true;
    }

    public ArrayList<Word> getWords(Word w) {
        ArrayList<Word> words = new ArrayList<Word>();
        int RowW = w.getRow();
        int ColW = w.getCol();
        boolean isVertical = w.getVertical();
        Tile[] tiles = w.getTilesArr();
        int startWord,endWord;
        ArrayList<Tile> tArray = new ArrayList<Tile>();
        words.add(w);
        for (int i = 0; i < tiles.length; i++) {
            if (isVertical) {
                startWord = ColW;
                endWord = ColW;
                while (startWord - 1 > -1 && boardArr[RowW + i][startWord - 1] != null) {
                    startWord--;
                }
                while (endWord + 1 < 15 && boardArr[RowW + i][endWord + 1] != null) {
                    endWord++;
                }
                if (startWord != ColW || endWord != ColW) {
                    for (int j = startWord; j <= endWord; j++) {
                        tArray.add(boardArr[RowW + i][j]);
                    }
                    Word word = new Word(tArray.toArray(new Tile[tArray.size()]), RowW + i, startWord, false);
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
                startWord = RowW;
                endWord = RowW;
                while (startWord - 1 > -1 && boardArr[startWord - 1][ColW + i] != null) {
                    startWord--;
                }
                while (endWord + 1 < 15 && boardArr[endWord + 1][ColW + i] != null) {
                    endWord++;
                }
                if (startWord != RowW || endWord != RowW) {
                    tArray.clear();
                    for (int j = startWord; j <= endWord; j++) {
                        tArray.add(boardArr[j][ColW + i]);
                    }
                    if (tArray.size() > 0) {
                        Word word = new Word(tArray.toArray(new Tile[tArray.size()]), startWord, ColW + i, true);
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
        if (wordsCount > 0) {
            bonusArr[7][7] = null;
        }
        int score = 0;
        int col = w.getCol();
        int row = w.getRow();
        boolean vertical = w.getVertical();
        Tile[] tiles = w.getTilesArr();
        for (int i = 0; i < tiles.length; i++) {
            if (vertical) {
                if (bonusArr[row + i][col] == null || bonusArr[row + i][col].equals("DW") || bonusArr[row + i][col].equals("TW"))
                    score += tiles[i].getScore();
                else if (bonusArr[row + i][col].equals("TL")) {
                       score += tiles[i].getScore() * 3;
                } 
                else if (bonusArr[row + i][col].equals("DL")) {
                       score += tiles[i].getScore() * 2;
                }
            } else {
                if (bonusArr[row][col + i] == null || bonusArr[row][col + i].equals("DW") || bonusArr[row][col + i].equals("TW"))
                      score += tiles[i].getScore();
                else if (bonusArr[row][col + i].equals("TL")) {
                      score += tiles[i].getScore() * 3;
                } 
                else if (bonusArr[row][col + i].equals("DL")) {
                       score += tiles[i].getScore() * 2;
                }

            }
        }
        for (int i = 0; i < tiles.length; i++) {
            if (vertical) {
                if (bonusArr[row + i][col] == null)
                    continue;
                else if (bonusArr[row + i][col].equals("DW")) {
                    score *= 2;
                } 
                else if (bonusArr[row + i][col].equals("TW")) {
                    score *= 3;
                }
            } else {
                if (bonusArr[row][col + i] == null)
                    continue;   
                else if (bonusArr[row][col + i].equals("DW")) {
                    score *= 2;
                } 
                else if (bonusArr[row][col + i].equals("TW")) {
                    score *= 3;
                }
            }
        }
        return score;
    }

    public int tryPlaceWord(Word w) {
        int scoreW = 0;
        int RowP = w.getRow();
        int ColP = w.getCol();
        boolean isVertical = w.getVertical();
        Tile[] tilesW = w.getTilesArr();

        for(int i = 0 ; i<tilesW.length; i++){
            if(isVertical){
                if(tilesW[i]==null){
                    tilesW[i] = boardArr[RowP+i][ColP];
                }
            }
            else{
                if(tilesW[i]==null){
                    tilesW[i] = boardArr[RowP][ColP+i];
                }
            }
        }

       w.setTiles(tilesW);
        if(boardLegal(w) && dictionaryLegal(w)){
            this.placeWord(w);
            ArrayList<Word> words = getWords(w);
            for(int i = 0; i < words.size(); i++){
                if(dictionaryLegal(words.get(i))){
                    scoreW += getScore(words.get(i));
                }
                else{
                    return 0;
                }
            }
            for(int i = 0; i < words.size(); i++){
                wordsArr[wordsCount] = words.get(i);
                wordsCount++;
                this.placeWord(words.get(i));
            }
            return scoreW;
        }
        else{
            return -1;
        }
      }

    public void placeWord (Word w) {
        int RowP = w.getRow();
        int ColP = w.getCol();
        boolean isVertical = w.getVertical();
        Tile[] tilesW = w.getTilesArr();

        for (int i = 0; i < tilesW.length; i++) {
            if(isVertical)
                boardArr[RowP + i][ColP] = tilesW[i];
                
            else
                boardArr[RowP][ColP + i] = tilesW[i];
                
         }
    }
}
