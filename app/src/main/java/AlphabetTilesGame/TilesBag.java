package AlphabetTilesGame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class TilesBag {

	public static ArrayList<Tile> tiles = new ArrayList <> ();
	public int tilesBagCount;
        private TileFactory tileFactory;
        
        
       /* public TilesBag (){
            
        }*/
        
        public TilesBag(TileFactory factory) {
            this.tileFactory = factory;
        }
        
        
        public void initiateTiles() {
        int tileID = 1;
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            for (int i = 0; i < 4; i++) {
                Tile tile = tileFactory.createTile(letter, tileID++);
                tiles.add(tile);
            }
        }
        
        
        
        for (int i = 0; i < 20; i++) {
                Tile tile = tileFactory.createTile(' ', tileID++);
                tiles.add(tile);
            }
        
        
        Collections.shuffle(tiles); // Shuffle the tiles after adding them
        tilesBagCount = tiles.size();
        
       /* //Test
        for ( Tile tile : tiles){    
            System.out.println ("All Tiles in TilesBag are : " + tile.getLetter() + " has value : " + tile.getLetterValue()+ "and has ID: " +tile.getTileID()); // Test
        }*/
        
        System.out.println("Counts of Tiles in TilesBag is: " + tiles.size());
        
        
        
    }



	public void initiateTilesRack(HumanPlayer human, ComputerPlayer computer, ArrayList<Tile> totalTiles) {
		// TODO - implement TilesBag.initiateTilesRack
                try{
                    TilesRack tilesRackHuman = new TilesRack();
                    TilesRack tilesRackComputer = new TilesRack();
                    
                  //  ArrayList<Tile> addedTiles = new ArrayList <> ();
                    ArrayList<Tile> addedHumanTiles = new ArrayList <> ();
                    ArrayList<Tile> addedComputeTiles = new ArrayList <> ();
                                         
                    for (int i=1; i<3; i++){
                        for (int j=0; j<7; j++){
                      
                        if (i==1){
                            Tile tile = totalTiles.get(j);
                            addedHumanTiles.add(tile);
                            totalTiles.remove(j);
                            tilesRackHuman.setTilesRack(addedHumanTiles, i);
                            human.setTilesRack(tilesRackHuman);
                        }
                        else if (i==2){
                            Tile tile = totalTiles.get(j);
                            addedComputeTiles.add(tile);
                            totalTiles.remove(j);
                            tilesRackComputer.setTilesRack(addedComputeTiles, i);
                            computer.setTilesRack(tilesRackComputer);
                        }
                        }
                        setUpdatedTilesBag (totalTiles, totalTiles.size());
                        totalTiles = getUpdatedTilesBag_Tiles();
                        //addedTiles.clear();
                    }
            
                       
                }
                catch (Error e) {
		throw new UnsupportedOperationException();
                }
	}
       
        
        public TilesRack tilesRackHandling (TilesRack oldTilesRack){
            TilesRack newTilesRack = new TilesRack();
            ArrayList<Tile> currentTiles = oldTilesRack.getTilesRack_Tiles();
            ArrayList<Tile> totalTilesBag_Tiles = new ArrayList<>(this.getUpdatedTilesBag_Tiles());
            int count = currentTiles.size();
            System.out.println("  total tiles in TilesBag: "+ totalTilesBag_Tiles.size());
            
            if (count < 7) {
                int tilesNeeded = 7 - count; // Calculate the number of tiles needed to add.
                Iterator<Tile> iterator = totalTilesBag_Tiles.iterator();
                while (iterator.hasNext() && tilesNeeded > 0) {
                    Tile tile = iterator.next();
                    currentTiles.add(tile);
                    iterator.remove(); // Remove the tile from 'this.tiles' using the iterator.
                    tilesNeeded--; // Decrement the number of tiles needed.
                }
                setUpdatedTilesBag(totalTilesBag_Tiles, totalTilesBag_Tiles.size());
            }
            
            newTilesRack.setTilesRack(currentTiles, oldTilesRack.getTilesRack_ID());
            return newTilesRack;
        }


        
        public void setUpdatedTilesBag (ArrayList<Tile> tiles, int count){
           this.tiles = tiles;
           this.tilesBagCount = count;   
        }
        
        public ArrayList<Tile> getUpdatedTilesBag_Tiles (){
         //   System.out.println("total size in getUpdtae func : " + this.tiles.size());
            return this.tiles;
        }
        
        public int getUpdatedTilesBag_Count (){
            return this.tilesBagCount;
        }
        
        

}