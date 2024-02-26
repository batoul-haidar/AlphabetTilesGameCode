package AlphabetTilesGame;
import java.util.ArrayList;

public class TilesRack {

	private ArrayList<Tile> tiles;
        private int tilesRackID;
	private int tilesCount;

	/**
	 * 
	 * @param tiles
	 */
	public int getTilesCount(ArrayList<Tile> tiles) {
		return this.tilesCount;
	}

	
        
        
        public void setTilesRack (ArrayList<Tile> tiles,int ID){
            this.tiles = tiles;
           // this.tilesCount = count;
            this.tilesRackID = ID;
        }
        
        
        public ArrayList<Tile> getTilesRack_Tiles (){
            return this.tiles;
        }
        
        public int getTilesRack_ID (){
            return this.tilesRackID;
        }
        
       
        
       

}