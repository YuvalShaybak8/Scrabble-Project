public class Tile {
    char letter;
    int value;

    public Tile(char letter, int value) {
        this.letter = letter;
        this.value = value;
    }

    public char getLetter() {
        return this.letter;
    }

    public int getValue() {
        return this.value;
    }

    public String toString(){
        return "" + letter + "{" + value + "}";
    }

    public boolean equals(Object obj) {
        Tile other;
        if (!(obj instanceof Tile)) {
            return false;
        } else {
            other = (Tile) obj;

        }
        if (this.letter == other.letter) {
            return true;
        } else {
            return false;
        }
    }

}
