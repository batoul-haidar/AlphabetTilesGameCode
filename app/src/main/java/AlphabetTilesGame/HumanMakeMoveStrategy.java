/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AlphabetTilesGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author YAROU
 */
public class HumanMakeMoveStrategy implements MakeMoveStrategy {
    Map<Position, Tile> playersMove = new LinkedHashMap();
    GameFacade gameFacade = new GameFacade ();
     
    
    @Override
    public Map<Position, Tile> makeMove(ArrayList<Tile> tiles, ArrayList<Position> positions){
        
         
             // <editor-fold defaultstate="collapsed" desc="Variables & Objects">
         
            ArrayList <Tile> tempTiles = new ArrayList <> (tiles);  
          //  Iterator<Tile> iterator = tempTiles.iterator();
            
  
            ArrayList <Position> tempPositions = new ArrayList<> (positions);
         //   System.out.println("tempPosition size: " + tempPositions.size());
         //   System.out.println("totalPosition size: " + board.getTotalOccupiedSquaresPositions().size());
            if (tempPositions == null) {
                tempPositions = new ArrayList<>(); // Initialize as an empty list if null
            }
            
            ArrayList <Tile> movedTiles = new ArrayList<> ();
            ArrayList <Position> movedSquaresPositions = new ArrayList <>();
            Map<Position, Tile> playerMoves = new LinkedHashMap();
    
    
            Scanner scanner = new Scanner (System.in);


            String input;
            char letter = 0;
            boolean isExisted, isOccupiedSquare;
            int x,y, tileID = 0; //lettersCount=0 ;
   
         //   </editor-fold>
         
         
         
             // <editor-fold defaultstate="collapsed" desc="Input Number Of Letters in the word">
     /*       
            System.out.println ("How many chars do you need?");
            
            while (true) { // Loop indefinitely until a valid integer is entered
                if (scanner.hasNextInt()) {
                    lettersCount = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after the integer input
                    break; // Exit the loop once an integer is received
                } 
                else {
                    System.out.println("That's not an integer. Please enter an integer:");
                    scanner.nextLine(); // Consume the non-integer input to clear the buffer
                }
            }*/       // new
            
            
            System.out.println("Now you will choose Tiles from your TilesRack "
                    + "and input tile with id");
            
            //   </editor-fold>
            
            
            int lettersCount = gameFacade.getLettersCount();
            
             for (int i=0 ; i< lettersCount; i++){
                Tile movedTile = new Tile (' ',0,0);

                Position position = new Position (0,0) ;

                
                
                for (Tile tile : tempTiles){
                    System.out.println ( "tile: " + tile.getLetter() + "  with ID : " + tile.getTileID()+ "  // Points: " + tile.getLetterValue());
                }
                
               /* 
                System.out.println("Enter Letter (Remember to add space when you want empty tile)");
                input = scanner.nextLine();
                letter = input.charAt(0);
                                
                System.out.println("Enter Tile ID for your chosen tile");
                tileID = scanner.nextInt();
                scanner.nextLine();
                */       // new
                
                Map <Character, Integer> tempMap = new HashMap (gameFacade.getTile());
                for (Map.Entry<Character, Integer> entry : tempMap.entrySet()){
                    letter = entry.getKey();
                    tileID = entry.getValue();
                }
                
                // check if letter exists
                isExisted = checkLetterInTilesRack(tempTiles, letter, tileID);
                if (isExisted == false){
                    System.out.println ("The tile you entered is not existed in your TilesRack, Try again");
                    i = i-1;
                    continue;
                }
                else{
                    int letterValue=0;
                    for (Tile tile : tiles) {
                        if (tile.getTileID() == tileID) {
                            letterValue = tile.getLetterValue();
                            break; // Exit the loop once the matching tile is found
                        }
                    }
                    movedTile.setTile(letter,letterValue , tileID); 
            }
                    
                
                // Position   
 /*   test               
                System.out.println("Enter ' Row ' position for the square on the board");
                x = scanner.nextInt();
                scanner.nextLine();
                
                System.out.println("Enter ' Column ' position for the square on the board");
                y = scanner.nextInt();
                scanner.nextLine();
 */               
             //   position.setPosition(x, y);


             position = gameFacade.getPosition();
                
                isOccupiedSquare = isOccupiedSquareTemp(tempPositions, position);
              
                if (isOccupiedSquare == true){
                    System.out.println ("The Square you choosed is occupied, Try again");
                    i = i-1;
                    continue;
                }
                else{
                    movedSquaresPositions.add(position); // only moved positions
                    tempPositions.add(position);             // total positions


                    
                    
                    // Add to list player want to use & delete form temp TilesRack
                    movedTiles.add(movedTile);
                     
                    
                    // Delete Moved_Tiles from Temp_Tiles
                    for (Tile tile: tempTiles){
                        if(tile.getTileID() == movedTile.getTileID()){
                            tempTiles.remove(tile);
                            break;
                        }
                    }

       
                }
                
              //  playerMove.put(position, movedTile);
                playerMoves.put(position, movedTile);
                    
               
            }
             
             
             return playerMoves;
            
            
            
             // <editor-fold defaultstate="collapsed" desc="Print to Test Tiles & Positions">
            /* 
            System.out.println("Moved Tiles are : ");
            for (Tile tile : movedTiles){
                System.out.println ("Tile: " + tile.getLetter() + "  ID: " + tile.getTileID());
            }
            
            
            System.out.println("Occupied positions are : ");
            for (Position position1 : this.movedSquaresPositions){
                System.out.println ("X: " + position1.getX() + " Y: " + position1.getY());
            }
            */
            // </editor-fold>
    }
    
  //  public void setLettersCount (int count){
  //      this.lettersCount = count;
 //   }
     
    public boolean checkLetterInTilesRack (ArrayList <Tile> tempTiles, char letter, int id){
             boolean isExisted = false;
             char tileLetter; int tileID;
            // this.tiles = this.getTilesRack().getTilesRack_Tiles();
             for (Tile tile: tempTiles){
                 tileLetter = tile.getLetter();
                 tileID = tile.getTileID();
                 if (tileLetter == letter && tileID == id){
                     isExisted = true;
                     break;
                 }
                 else
                     isExisted= false;
             }
             
             return isExisted;
         }
     
     
     public boolean isOccupiedSquareTemp(ArrayList<Position> positions ,Position tempPosition) {
             boolean isOccupied = false;
           // System.out.println ("positins size" + positions.size());
            for (Position position: positions){
                if (position.getX() == tempPosition.getX() && position.getY()==tempPosition.getY()){
                    isOccupied = true;
                    break;
                }
                else
                    isOccupied = false;

                
            }  
            System.out.println("isOccupied? " + isOccupied);
            return isOccupied;

	}
        
}
