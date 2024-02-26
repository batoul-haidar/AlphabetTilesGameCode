package AlphabetTilesGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HumanPlayer extends Player {

    private TilesRack tilesRack = this.getTilesRack();
    private AlphabetTiles game;

      



    

        public HumanPlayer (){
        }
        
        
        public void setGame(AlphabetTiles game) {
            this.game = game;
        }
        
        
        
        public void setTilesRack(TilesRack tilesRack){
            this.tilesRack = tilesRack;
        }
        
        public TilesRack getTilesRack (){
            return this.tilesRack;
        }
      
        /*
        public void startGame() {
            try {

                Scanner scanner = new Scanner(System.in);
                String userStart = scanner.nextLine();
                if (userStart.equals("start")) {
                    System.out.println("Let's Start Playing");
                    game.initiateGame();
                    
                    // initiate game moved to AlhpabetTiles Class
                    
           //         System.out.print(game.getPlayersID()); // Test
                //    System.out.println("Ensure that players get ID  " + this.humanPlayerID);   // Test
                    
                } 
                else {
                    System.out.println("You should type 'start' to start the game");
                    startGame();
                }
            } 
            catch (NoSuchElementException e) {
                System.out.println("No input found. Please run the application in an interactive console.");
            } finally {
        // Consider commenting out or removing this line
        // scanner.close(); 
    }
}
        
        */
        

        public void playFisrtRound (){
            boolean isValid = false;
          //  System.out.println("tilesRackID " + this.tilesRack.getTilesRack_ID());    // Test
            TilesRack tempTilesRack = new TilesRack();
            tempTilesRack =  this.tilesRack; 
            ArrayList<Tile> tempTiles = new ArrayList<>(tempTilesRack.getTilesRack_Tiles());
            
            
          //  tiles = tempTilesRack.getTilesRack_Tiles();
            System.out.println ("Your Tiles are: ");
            
            for (Tile tile : tempTiles){
                System.out.println ( "tile: " + tile.getLetter() + "  with ID : " + tile.getTileID() + "  // Points: " + tile.getLetterValue()  );
            }  
            
            
            ArrayList <Position> tempPositions = new ArrayList<> (board.getTotalOccupiedSquaresPositions());

            this.makeMoveStrategy = new HumanMakeMoveStrategy();
               this.setMakeMoveStrategy(makeMoveStrategy);
            

            this.playerMoves = this.makeMoveStrategy.makeMove(tempTiles,tempPositions);


            isValid = board.checkMove("First", this.playerMoves, this.getID());
            
            if (isValid == true){
                System.out.println("Word is Valid");
                removeTiles(this.playerMoves);
                this.playerMoves.clear();

                
            }
                
            
            else{
                System.out.println("Word is not Valid. Try again");
                this.playerMoves.clear();
                playFisrtRound ();
                
            }
                

            
            // <editor-fold defaultstate="collapsed" desc="Print for Testing">
            /*
            for (Map<Position,Tile> map : this.playerMoves){
                for (Map.Entry<Position, Tile> entry : map.entrySet()) {
                Tile tile = entry.getValue();
                Position position = entry.getKey();
                System.out.println ("Letter: " + tile.getLetter() + "  on position: X= " + position.getX() 
                        + " Y= "+ position.getY());
                }

            }
            */
            // </editor-fold>

            
        }
        
        
        

        public void playNormalRound (){
            
            boolean isValid = false;
            TilesRack newTilesRack = tilesRackCountHandling(this.tilesRack);

            
            while (!isValid){
                
                TilesRack tempTilesRack = new TilesRack ();
                tempTilesRack = newTilesRack;
                
                ArrayList<Tile> tempTiles = new ArrayList<>(tempTilesRack.getTilesRack_Tiles());
                
                board.printGrid();
                System.out.println ( "Your Tiles are ");
                
                for (Tile tile: tempTiles){
                    System.out.println ( "tile: " + tile.getLetter() + "  with ID : " + tile.getTileID() + "  // Points: " + tile.getLetterValue()  );
                }
                
                
                ArrayList <Position> tempPositions = new ArrayList<> (board.getTotalOccupiedSquaresPositions());
                
                this.makeMoveStrategy = new HumanMakeMoveStrategy();
               this.setMakeMoveStrategy(makeMoveStrategy);

                
                this.playerMoves = this.makeMoveStrategy.makeMove(tempTiles,tempPositions);
                
                isValid = board.checkMove("Normal", this.playerMoves, this.getID());
                
                if (!isValid){   
                    this.playerMoves.clear();
                }
            }
            
            
            System.out.println("Word is Valid");
            removeTiles(this.playerMoves);
            this.playerMoves.clear();
        
        }
        
        
        public TilesRack tilesRackCountHandling (TilesRack oldTilesRack){
            int count = 0;
            ArrayList <Tile> oldTiles = oldTilesRack.getTilesRack_Tiles();
            count = oldTiles.size();
            
            System.out.println ("Old TilesRack count is: " + count);
            
            if (count < 7){
                this.tilesRack = this.tilesBag.tilesRackHandling(oldTilesRack);
            }
            else
                this.tilesRack = oldTilesRack;
            
            
            return this.tilesRack;
        }
        

     
       
        public void removeTiles(Map<Position, Tile> currentPlayerMove) { 
            ArrayList<Tile> playedTiles = new ArrayList<>();
            ArrayList<Tile> baseTiles = this.tilesRack.getTilesRack_Tiles();
            
            
            // collect played tiles
            for (Map.Entry<Position, Tile> entry : currentPlayerMove.entrySet()){
                    Tile tile = entry.getValue();
                    playedTiles.add(tile);
                }
            
            
            // Use Iterator to safely remove tiles from baseTiles
            Iterator<Tile> baseTilesIterator = baseTiles.iterator();
            while (baseTilesIterator.hasNext()) {
                Tile tile = baseTilesIterator.next();
                for (Tile playedTile : playedTiles) {
                    if (tile.getTileID() == playedTile.getTileID()) {
                        baseTilesIterator.remove(); // Remove tile using iterator
                        //        break; // Break inner loop once tile is removed
                        
                        
                        this.tilesRack.setTilesRack(baseTiles, this.tilesRack.getTilesRack_ID());
                    }
                }
            }
        }
        
        
}