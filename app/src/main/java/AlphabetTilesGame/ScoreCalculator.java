package AlphabetTilesGame;


import java.util.*;

public class ScoreCalculator {

	private static Map<Integer, Integer> totalScores = new HashMap();


        
        public ScoreCalculator(){

     //       this.totalScores = new ArrayList <> ();
     
        }

        
        
        
        public Map<Integer, Integer> calculateScore(Map<Position, Tile> playerMoves, int playerID, ArrayList<Square> squares) {
            int sum = 0;
            
         //   System.out.println ("Player ID is :  "+ playerID);
         //   System.out.println ("size of playerMoves Array: " + playerMoves.size());
        //    ArrayList<Integer> tilesValues = new ArrayList<>();
            ArrayList<Integer> totalValues = new ArrayList<>();
            Map<Integer, Integer> playersScore = new HashMap();
            
            
                for (Map.Entry<Position,Tile> entry: playerMoves.entrySet()){
                    Tile tile = entry.getValue();    // we can add position for position points when we want to add square points to score
                    char letter = tile.getLetter();
                  //  int letterValue = tile.getLetterValuebyInfo(letter);
                  //  System.out.println("Letter is :  " + letter + "  Letter value is :  " + getLetterValue(letter) );
                    int tileValue = tile.getLetterValue(); //getLetterValue(letter);
                    int squarePoint = 1;
                   // tilesValues.add(value);
                    
                    Position position = entry.getKey();
                    
                    for (Square square : squares){
                        if(square.getPosition().getX() == position.getX() && square.getPosition().getY() == position.getY()){
                            String squarePointType = square.getSquareType();
                            if (null != squarePointType)
                                switch (squarePointType) {
                                case "ordinary" -> squarePoint = 1;
                                case "double" -> squarePoint = 2;
                                case "triple" -> squarePoint = 3;
                                default -> {
                                }                               
                            }
                            int totalValue = tileValue * squarePoint;
                            totalValues.add(totalValue);
                            System.out.println("Tile Value: "+ tileValue +"  Square point: " + squarePoint + "  Total Value: " + totalValue);
                        }
                    }
                     
                }
            
            
            for (int i=0; i< totalValues.size(); i++){
                sum= sum + totalValues.get(i);
            }
            System.out.println ("Your score for this round : " + sum);
            playersScore.put(playerID, sum);

            updateTotalScores (playersScore);
            
            return playersScore;
	}
        
       
	/**
	 * 
     * @param currentScore
	 * @param score
	 * @param playerID
	 */
	public void updateTotalScores(Map<Integer, Integer> currentScore) {
            int currentPlayerID = 0, currentPlayerScore = 0;
           // ArrayList <Map<Integer, Integer>
            for (Map.Entry<Integer, Integer> entry : currentScore.entrySet()){
                currentPlayerID = entry.getKey();
                currentPlayerScore = entry.getValue();
            
            
          //  for (Map.Entry<Integer, Integer> entry : this.totalScores.entrySet()) {
                if (totalScores.containsKey(currentPlayerID)) {
                    int playerScoreValue = totalScores.get(currentPlayerID);
                    int newScore = playerScoreValue + currentPlayerScore;
                    totalScores.put(currentPlayerID, newScore); // Directly update the score in the map
                    System.out.println ("Your total score : " + newScore);
               //     break; // Exit the loop once the player's score is updated
                }
                
            }
         //   }
            
            
            
            
            /*   // Test
            for (Map<Integer, Integer> map : this.totalScores){
                for (Map.Entry<Integer, Integer> entry : map.entrySet()){
                    System.out.println("player ID: " + entry.getKey()+ "  Total Score: " + entry.getValue());
                }
            }
            
            */


	}
        
        
        public void setTotalScores (Map<Integer, Integer> updatedTotalScore){
               totalScores = updatedTotalScore;
           }
        
        
        
        public Map<Integer, Integer> getTotlalScores (){
            return totalScores;
        }
        
        
        
        
        
        /*
          private int getLetterValue(char letter) {
            return switch (letter) {
                case 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'S', 'T', 'R' -> 1;
                case 'D', 'G' -> 2;
                case 'B', 'C', 'M', 'P' -> 3;
                case 'F', 'H', 'V', 'W', 'Y' -> 4;
                case 'K' -> 5;
                case 'J', 'X' -> 8;
                case 'Q', 'Z' -> 10;
                default -> 0;
            }; // For empty tiles or any other characters
         
        }*/

}