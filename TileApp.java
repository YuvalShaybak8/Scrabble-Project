public class TileApp {
      
    /**
     * param args the command line arguments
     */  

    public static void main(String[] args){
        Tile myTile = new Tile('c', 3);
        Tile tile2 = new Tile('b', 3);
        Tile tile3 = new Tile('c', 3);

        System.out.println("The value of " + myTile.getLetter() + " is " + myTile.getValue());
        System.out.println(myTile);

        if( myTile.equals(tile2)){
            System.out.println("myTile and tile2 are equal");
        } else {
            System.out.println("The tiles are not equal");
        }
        if( myTile.equals(tile3)){
            System.out.println("myTile and tile3 are equal");
        } else {
            System.out.println("The tiles are not equal");
        }
    }
}
