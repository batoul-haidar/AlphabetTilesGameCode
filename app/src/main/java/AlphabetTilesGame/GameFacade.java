/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AlphabetTilesGame;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author YAROU
 */
public class GameFacade {
    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;
    private AlphabetTiles game;
    private int levelNum;
  //  private int lettersCount;
    Scanner scanner = new Scanner (System.in);

    public GameFacade() {
        this.humanPlayer = new HumanPlayer();
        this.computerPlayer = new ComputerPlayer();
        this.game = new AlphabetTiles(humanPlayer, computerPlayer);
        
    }

    public void startGame() {
        
        // Initialize game components
        humanPlayer.setGame(game);
        computerPlayer.setGame(game);

        // Start the game
        System.out.println("Game is starting...");
        Scanner scanner = new Scanner(System.in);
                String userStart = scanner.nextLine();
                if (userStart.equals("start")) {
                    game.initiateGame();
                    System.out.println("Let's Start Playing");
                    System.out.println ("Choose difficulty leve \n 1 for easy \n 2 for medium \n 3 for hard");
                    while (true) { // Loop indefinitely until a valid integer is entered
                        if (scanner.hasNextInt()) {
                            this.levelNum = scanner.nextInt();
                            if (this.levelNum == 1 || this.levelNum == 2 || this.levelNum ==3 ){
                                scanner.nextLine(); // Consume the newline character after the integer input
                                break; // Exit the loop once an integer is received 
                            }
                            else
                                System.out.println("That's not a righr level \n Choose difficulty leve \n 1 for easy \n 2 for medium \n 3 for hard");
                        } 
                        else {
                            System.out.println("That's not an integer. Please enter an integer:");
                            scanner.nextLine(); // Consume the non-integer input to clear the buffer
                        }
                    }
                    this.computerPlayer.setDifficultyLevel(levelNum);
                    
                } 
                else {
                    System.out.println("You should type 'start' to start the game");
                    startGame();
                }
    }
      
    
    public String playFirstRound (){
            // First Round
            
             String[] players = {"human", "computer"};
             Random random = new Random();
             HumanMakeMoveStrategy humanStrategy = new HumanMakeMoveStrategy();
             
             // Randomly choose an index between 0 and 1
             int index = random.nextInt(players.length);
             
             // Use the index to select either "human" or "computer"
           //  String firstPlayer = players[index];
           String firstPlayer = "computer";
             
             System.out.println ("First player will be : " + firstPlayer);
             
          //   computerPlayer.playFisrtRound();
             
             
             if ("human".equals(firstPlayer)){
                 System.out.println("Remember you should cover middle cell: position (7,7)");                
                 humanPlayer.playFisrtRound();
                 
             }
             else if ("computer".equals(firstPlayer)){
                 computerPlayer.playFisrtRound();
             }
             else
                 System.out.println ("Error while choosing first player");
             
             
             return firstPlayer;
             
        }
        
   
    
    public void playNormalRound(String currentPlayer){
        Board board = new Board ();
        HumanMakeMoveStrategy humanStrategy = new HumanMakeMoveStrategy();
          //  int i=1;
            try{
              while (!game.isEndGameConditionsMet()){
                  switch (currentPlayer) {
                      case "human" -> {
                          System.out.println ("....Computer Turn....");                          
                          computerPlayer.playNormalRound();
                          currentPlayer = "computer";
                          // break;
                      }
                      case "computer" -> {
                        //  board.printGrid();
                          System.out.println ("....Human Turn....");
                          humanPlayer.playNormalRound();                   
                          currentPlayer = "human";
                          // break;
                      }
                      default -> System.out.println ("Error while defining turn");
                      //  i++;
                  }
              }
              
              endGame();
              
              
             //  System.out.println ("End Game");
            }
           
            catch (Error e){
                System.out.println ("Error while defining turn");
            }

        }
    
    
     
    public int getLettersCount (){
         int count;
        System.out.println ("How many chars do you need?");
        while (true) { // Loop indefinitely until a valid integer is entered
            if (scanner.hasNextInt()) {
                count = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character after the integer input
                break; // Exit the loop once an integer is received
            } 
            else {
                System.out.println("That's not an integer. Please enter an integer:");
                scanner.nextLine(); // Consume the non-integer input to clear the buffer
            }
        }
        
      
        return count;
    }
    
    
    public Map<Character, Integer> getTile (){
       Map<Character, Integer> inputTile = new HashMap();
       System.out.println("Enter Letter (Remember to add space when you want empty tile)");
       String input = scanner.nextLine();
       char letter = input.charAt(0);
       
       System.out.println("Enter Tile ID for your chosen tile");
       int tileID = scanner.nextInt();
       scanner.nextLine();
       
       inputTile.put(letter, tileID);
       
       return inputTile;
    }
    
    public Position getPosition (){
        System.out.println("Enter ' Row ' position for the square on the board");
        int  x = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Enter ' Column ' position for the square on the board");
        int  y = scanner.nextInt();
        scanner.nextLine();
        
        Position position = new Position (x,y);
        return position;
    }
    

    public void endGame() {
        // Handle end game logic, such as calculating final scores and declaring a winner
        System.out.println("Game End!");
        System.out.println (game.whoIsWinner());

    }

    
}
