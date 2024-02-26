package AlphabetTilesGame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Player {

        protected int playerID;
        private HumanPlayer human;
        private ComputerPlayer computer;
        protected Board board = new Board ();
        protected TileFactory englishFactory = new EnglishTileFactory();
        protected TilesBag tilesBag = new TilesBag (englishFactory);
        protected Map<Position,Tile> playerMoves = new LinkedHashMap ();
//        protected MoveStrategy moveStrategy = new MoveStrategy();
        protected MakeMoveStrategy makeMoveStrategy;
        
        
        
        
        public void setMakeMoveStrategy(MakeMoveStrategy makeMoveStrategy) {
        makeMoveStrategy = makeMoveStrategy;
    }

    

        
       // protected int id;

        public Player (){

        }
        
       
        
        public void setHumanPlayer (HumanPlayer human){
            this.human = human;
        }
        
        public void setComputerPlayer (ComputerPlayer computer){
            this.computer = computer;
        }
        
        
        
        
        public void setID (int id){
            this.playerID = id;
        }
        
        public int getID (){
            return this.playerID;
        }
        
        
         public void setTilesRack(TilesRack tilesRack){

        }
        
        public TilesRack getTilesRack (){
            throw new UnsupportedOperationException();
        }
        

	public void RemoveTiles() {
		// TODO - implement Player.RemoveTiles
		//throw new UnsupportedOperationException();
	}
        
        
        
        public void playNormalRound (){
            
        }
       
        
            
        


}