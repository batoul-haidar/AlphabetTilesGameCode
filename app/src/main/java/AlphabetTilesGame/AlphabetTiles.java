package AlphabetTilesGame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class AlphabetTiles {

	private Board board = new Board();
	private Player currentPlayer;
	private TilesBag tilesBag;
	private ScoreCalculator scoreCalculator = new ScoreCalculator ();
        HumanPlayer humanPlayer; ComputerPlayer computerPlayer;
        int humanPlayerID, computerPlayerID, emptySquaresCount;
        private Map<Player, Integer> playersList = new HashMap();
        private Map<Integer, Integer> playersScores= new HashMap();

        
        
        public AlphabetTiles(HumanPlayer human, ComputerPlayer computer ) {
        this.humanPlayer = human;
        this.computerPlayer = computer;
       // this.computerPlayer = new ComputerPlayer();
    }
        
        public AlphabetTiles( ) {}
        
        
        public void initiateGame (){
            setPlayersID();
            initiateTilesBag();
            initiateBoard();
            initiatePlayersScore();
        //    this.playersScores = scoreCalculator.initiatePlayersScore();
        }
        
        
         public void initiatePlayersScore (){  

            for (int i=1; i<3; i++){
             //   Map<Integer, Integer> map = new HashMap();
                if (i==1){
                   // map.put(94, 0);
                    this.playersScores.put(94, 0);
                }
                else if (i==2){
                    this.playersScores.put(95, 0);
                }
                else
                    System.out.println("Error while initiating players scorse");
            }
            
            scoreCalculator.setTotalScores(this.playersScores);
            
            
          //  System.out.println("totalScore size: " + this.totalScores.size()); // Test
         //   return this.totalScores;
        }
  
    
        public void setPlayersID(){
            Random random = new Random();
            Map<Player, Integer> playersList = new HashMap();
         
            
            try{
                
                for (int i=94; i<96; i++){
                    if (i==94){
                        playersList.put(humanPlayer, 94);
                        humanPlayer.setID(94);
                      
                    }
                    else if (i==95){
                        playersList.put(computerPlayer, 95);
                        computerPlayer.setID(95);
                       
                    }
                }
                humanPlayerID = humanPlayer.getID();
                computerPlayerID = computerPlayer.getID();
                System.out.println("Human ID IS : " + humanPlayerID);
                System.out.println("Computer ID IS : " + computerPlayerID);
                
            
            this.playersList = playersList;
         //   computerPlayer.printTest();  // Test
         //   System.out.println (playersList); // Test

            }
            catch (Error e){
            throw new UnsupportedOperationException("Error while generating ID");
            }
            
        }
        
        
        public Map<Player, Integer> getPlayersID (){
            return playersList;
        }

	public void initiateBoard() {
		// TODO - implement AlphabetTiles.initiateBoard
		try{
                    Board board = new Board ();
                    board.initiateSquares();
                }
                catch (Error e){
                    throw new UnsupportedOperationException("Error while initiating Board");
                }
	}

	public void initiateTilesBag() {
		// TODO - implement AlphabetTiles.initiateTilesBag
		try{
                    TileFactory englishFactory = new EnglishTileFactory();
                    TilesBag tilesBag = new TilesBag (englishFactory);
                    tilesBag.initiateTiles();
                    tilesBag.initiateTilesRack(humanPlayer, computerPlayer, tilesBag.getUpdatedTilesBag_Tiles());
                    this.tilesBag = tilesBag;
                
              /*
             //    System.out.println("TilesBagCount is: " + tilesBag.tilesBagCount); //Test
             System.out.println("Human Tiles: ");
                    TilesRack humanRack = humanPlayer.getTilesRack();
                    ArrayList<Tile> tilesTestHuman = humanRack.getTilesRack_Tiles();                 
                    for (Tile tile: tilesTestHuman){
                        System.out.println("Tile:  " + tile.getLetter() + "  ID:  " + tile.getTileID());
                    }
                    
                    System.out.println("Computer Tiles: ");
                     TilesRack computerRack = computerPlayer.getTilesRack();
                    ArrayList<Tile> tilesTestComputer = computerRack.getTilesRack_Tiles();                 
                    for (Tile tile: tilesTestComputer){
                        System.out.println("Tile:  " + tile.getLetter() + "  ID:  " + tile.getTileID());
                    } 
                    
                    
                     
                 //Test
                 System.out.println("TilesBag Tiles from AlpgabetTiles: ");               
                    for (Tile tile: tilesBag.getUpdatedTilesBag_Tiles()){
                        System.out.println("Tile:  " + tile.getLetter() + "  ID:  " + tile.getTileID());
                    }
                    System.out.println("total size : " + tilesBag.getUpdatedTilesBag_Tiles().size()); 
                 
                 */
                }
                catch (Error e){
                    throw new UnsupportedOperationException("Error while initiaring TilesBag");
                }
	}
        
          
        public boolean isEndGameConditionsMet (){
            boolean isScoreReached = false;
            this.playersScores = scoreCalculator.getTotlalScores();
            for (Map.Entry<Integer, Integer> entry : this.playersScores.entrySet()){
                if(entry.getValue().equals(40)){
                    System.out.println ("Total Score is reached");
                    isScoreReached = true;
                    
                }
                System.out.println ("ID: " + entry.getKey() + "  score" + entry.getValue());
            }
            
            int emptySquareCount = 225 - board.getTotalOccupiedSquaresPositions().size();
            
            int tilesBagCount = tilesBag.getUpdatedTilesBag_Count();
            
            System.out.println("emptySquareCount : " + emptySquareCount +"  tilesBagCount : "+ tilesBagCount);
            
            if (isScoreReached || tilesBagCount == 0 || emptySquareCount == 0)
                return true;
            else
                return false;
        }        
                
    
	
        
        public String whoIsWinner() {
		String winner;
                
                        
                int humanScore = this.playersScores.get(94);
                int computerScore = this.playersScores.get(95);
                
                if (humanScore > computerScore){
                    winner = "Congratulations! You are the winner";
                }
                else if (computerScore > humanScore){
                    winner = "Game Over.. Computer is the Winner";
                }
                else
                    winner = "Game over.. The result is Tie";
                
                return winner;
	}
        
       
           
      

}