package AlphabetTilesGame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Board {

	private ArrayList<String> wordList;
	private static ArrayList<Position> totalOccupiedSquaresPositions = new ArrayList<>();
        private static ArrayList <Square> squares = new ArrayList <> ();
        private char squareValue = ' ';
        private String direction = ""; 
        IDictionary dictionary ;
     //   private Dictionary dictionary;
        private ScoreCalculator scoreCalculator = new ScoreCalculator();
        private Map<Integer, Integer> playersScores = new HashMap ();
     //   ArrayList <Map<Position, Tile>> tempPlayerMoves = new ArrayList<>();
        
        
        public Board (){
            this.dictionary = DictionaryFactory.getDictionary("English");

          //  this.dictionary = Dictionary.getInstance();
        }



	public void initiateSquares() {
            
            try {
                int generateSquareID = 1;
                List<String> squareTypes = new ArrayList<>();
                
                // Assuming a 70% ordinary, 20% double, 10% triple distribution for 225 squares
                for (int i = 0; i < 158; i++) squareTypes.add("ordinary"); // 70% of 225
                for (int i = 0; i < 45; i++) squareTypes.add("double"); // 20% of 225
                for (int i = 0; i < 22; i++) squareTypes.add("triple"); // 10% of 225
                
                Collections.shuffle(squareTypes); // Randomize the list order
                
                int listIndex = 0;
                for (int i = 0; i < 15; i++) {
                    for (int j = 0; j < 15; j++) {
                        Square square = new Square();
                        square.setSquarePosition(i, j);
                        // Assign square type from the shuffled list
                        String squareType = squareTypes.get(listIndex % squareTypes.size());
                        square.setSquare(square.getPosition(), false, this.squareValue, generateSquareID, squareType);
                        squares.add(square);
                        generateSquareID++;
                        listIndex++;
                    }
                }
                
                setSquares(squares);
                System.out.println("Squares Size: " + squares.size());
                printGrid();
                
             /*   int generatequareID = 1;
                for (int i=0; i<15; i++){
                    for (int j=0; j<15; j++){
                        Square sqaure = new Square ();
                        sqaure.setSquarePosition(i, j);
                        sqaure.setSquare(sqaure.getPosition(),false,this.squareValue,generatequareID );
                        squares.add(sqaure);
                        generatequareID++;
                     
                    }
                }
                
                setSquares(squares);
                System.out.println("Squares Size: "+squares.size());
                printGrid (); */


            }
            
            catch (Error e){
                throw new UnsupportedOperationException();
            }
            
            


	}
        
        
        public void printGrid (){
             int rows = 15;
            int cols = 15;

            for (int i = 0; i < rows; i++) {
                // Print the top border of a cell
                for (int j = 0; j < cols; j++) {                   
                    System.out.print("+---"); // Top part of the cell
                }
                System.out.println("+"); // Rightmost border of the last cell in a row
                
                 // Print the left border of a cell
                for (int j = 0; j < cols; j++) {
                    Position position = new Position (i,j);
                  //  position.setX(i); position.setY(j);
                    System.out.print("| " + getSquareValue(position) + " "); // Left part of the cell
                }
                System.out.print("|"); // Rightmost border of the last cell in a row
                System.out.println(i);
            
            
            // Print the bottom border of the last row
            for (int j = 0; j < cols; j++) {
                System.out.print("+---");
            }

            System.out.println("+");

        }
            
             for (int j = 0; j < cols; j++) {
                 if(j==0)
                     System.out.print("  " + j);
                 if(j> 0 && j<10)
                     System.out.print("   " + j);
                 else if (j>=10)
                     System.out.print("  " + j);
            }
             System.out.println("\n");

        }
        
        
        public void setSquares (ArrayList <Square> squares){
            this.squares = squares;
        }
        
        public char getSquareValue (Position position){
          //  System.out.println("position X: " + position.getX() + "  position Y: " + position.getY()); 
            for (Square square : squares){
             //  System.out.println(square.getPosition().getX() +"  " + square.getPosition().getY()); 
                if (square.getPosition().getX() == position.getX() && square.getPosition().getY() == position.getY()){
                    this.squareValue = square.getSquareValue();
               //     System.out.println ("Square value is: " + this.squareValue + "  on position: X= " + position.getX() + " Y= " + position.getY());

                }
                
            }
            return this.squareValue;
        }
        
        public ArrayList<Square> getSquares (){
            return this.squares;
        }
        

        public boolean checkMove (String roundType, Map<Position, Tile> playerMoves, int playerID ){
            boolean isValid = false;

            boolean isCernralSquareCovered = false, isSquaresConnected = false, isDirectionValid = false, isValidWord;
            
         //   Map <Integer, Integer> playerScore = new HashMap();
            if (playerID == 94){
               if (roundType == "First"){
               isCernralSquareCovered = checkCentralSquare(playerMoves); 
               isDirectionValid = checkDirection (playerMoves);
              
               if (!isCernralSquareCovered){
                System.out.println("Central square isn't coversed");
                
                if (!isDirectionValid){
                System.out.println("Direction is invalid");
            }
                
               }
            }
            
            else if (roundType == "Normal"){   
                System.out.println("totalOccupiedSquares size: " + totalOccupiedSquaresPositions.size());
                isSquaresConnected = checkConnectivity(playerMoves);
                
                if (!isSquaresConnected){
                System.out.println("Squares you choosed aren't conntected to others");
                
                
                
                }
            }
            
            else
                System.out.println("Round Type is Error");
            
            
            
            
            
            wordList = FormWordAsString (playerMoves, roundType);
            
            isValidWord = dictionary.checkValidity(wordList);
           //  System.out.println("Is Valid Sord " + isValidWord );

            if (isCernralSquareCovered && isDirectionValid && isValidWord && roundType == "First" && playerMoves.size()>1){
                playersScores = scoreCalculator.calculateScore(playerMoves, playerID, squares);
                
                /*
                // Test
                for (Map.Entry<Integer, Integer> entry: playerScore.entrySet()){
                   System.out.println("Your score for this round is: " + entry.getValue() +"  and your ID is: "+ entry.getKey()); 
                }
                */
                updateTotalOccupiedPositions(playerMoves);
                updateSquares (playerMoves);
                
                
                // <editor-fold defaultstate="collapsed" desc="Test Comments">
                
             //   System.out.println("totalScore size: " + scoreCalculator.getTotlalScores().size());
                
                /*   // Test
                for (Position position : this.totalOccupiedSquaresPositions){
                    System.out.println("X  : " + position.getX()+ " Y  : " + position.getY());
                }
                */

               // ArrayList<Map<Integer, Integer>> totalScore = new ArrayList<>(scoreCalculator.getTotlalScore()) ;
               /*    // Test
                for (Map<Integer, Integer> map : scoreCalculator.getTotlalScores()){
                    for (Map.Entry<Integer, Integer> entry : map.entrySet()){
                        int totalPlayerID = entry.getKey();
                        int totalPlayerScore = entry.getValue();
                        switch (totalPlayerID) {
                            case 94 -> System.out.println("Player is Human and ID is: " + totalPlayerID + "  Score : " + totalPlayerScore);
                            case 95 -> System.out.println("Player is Computer and ID is: " + totalPlayerID + "  Score : " + totalPlayerScore);
                            default -> System.out.println("ID is error ");
                        }
                    }
                }
                */
              //  </editor-fold>
             //   return true;
             isValid = true;
             System.out.println("isValid in condition: " + isValid);
            }
            
            else if (isSquaresConnected && isValidWord && roundType == "Normal"){
                
                playersScores = scoreCalculator.calculateScore(playerMoves, playerID, squares);
              
                updateTotalOccupiedPositions(playerMoves);
                updateSquares (playerMoves);
                
             //   return true;
             isValid = true;
            }
            else
                isValid = false;
            
            
            
            if (!isValidWord){
                System.out.println("Word isn't existed in the dictionary");
            } 
            }
            
            else if (playerID == 95){
                isValid = true;
                 playersScores = scoreCalculator.calculateScore(playerMoves, playerID, squares);
              
                updateTotalOccupiedPositions(playerMoves);
                updateSquares (playerMoves);
            }
            else
                System.out.println("player ID is incorrect");
                
                
           // System.out.println("isValid in function: " + isValid);
            return isValid;
        }
        
        
        public boolean checkCentralSquare(Map<Position, Tile> playerMoves) {
           // boolean isInCentar = false;
                for (Map.Entry<Position, Tile> entry : playerMoves.entrySet()) {
                    Position position = entry.getKey();
                    if (position.getX() == 7 && position.getY() == 7){
                        return true;
                       // break;
                    }
             
                }
            
            return false;	
	}
        
        
        
        public boolean checkConnectivity(Map<Position, Tile> playerMoves) {
            return !getConnectedSquares(playerMoves).isEmpty(); 
            
            // <editor-fold defaultstate="collapsed" desc="Other Way">
            /*
            boolean isConnected = false;
            System.out.println("Size" + totalOccupiedSquaresPositions.size());
            for (Map<Position,Tile> map : playerMoves){
            for (Map.Entry<Position, Tile> entry : map.entrySet()) {
            Position position = entry.getKey();
            for (Position occupiedPosition : totalOccupiedSquaresPositions){
            if (// Horizontal and vertical neighbors
            (occupiedPosition.getX() == position.getX() - 1 && occupiedPosition.getY() == position.getY()) ||
            (occupiedPosition.getX() == position.getX() + 1 && occupiedPosition.getY() == position.getY()) ||
            (occupiedPosition.getY() == position.getY() - 1 && occupiedPosition.getX() == position.getX()) ||
            (occupiedPosition.getY() == position.getY() + 1 && occupiedPosition.getX() == position.getX()))
            {
            isConnected = true;
            break;
            }
            else
            isConnected = false;
            }
            }
            }
            System.out.println("ISConnected: " + isConnected);
            return isConnected; */
            
            // </editor-fold>
	}

        
        public boolean checkDirection(Map<Position, Tile> playerMoves) {
          //  boolean isRightDirection = false;
            if (playerMoves.size()==1){
                return true;
            }
            else{
            ArrayList<Integer> listX = new ArrayList<>();
            ArrayList<Integer> listY = new ArrayList<>();
                for (Map.Entry<Position, Tile> entry: playerMoves.entrySet()){
                    Position position = entry.getKey();
                    int x = position.getX();
                    listX.add(x);
                    int y = position.getY();
                    listY.add(y);
                }
            
            
            // Initial checks for horizontal or vertical by comparing the first two positions
            boolean isHorizontal = listX.get(0).equals(listX.get(1));
            boolean isVertical = listY.get(0).equals(listY.get(1));
            
            // Verify all subsequent positions to ensure they follow the determined direction
            for (int i = 1; i < listX.size(); i++) {
                if (isHorizontal) {
                    if (!listX.get(i).equals(listX.get(0)) || Math.abs(listY.get(i) - listY.get(i - 1)) != 1) {
                        System.out.println("Direction is Wrong");
                        return false;
                    }
                } 
                else if (isVertical) {
                    if (!listY.get(i).equals(listY.get(0)) || Math.abs(listX.get(i) - listX.get(i - 1)) != 1) {
                        System.out.println("Direction is Wrong");
                        return false;
                    }
                } 
                else { // This case is for when the first two positions do not set a clear horizontal or vertical direction
                    System.out.println("Direction is Wrong");
                    return false;
                }
            }
            
            // Print the direction based on the checks
            if (isHorizontal) {
                System.out.println("Direction is Horizontally");
                this.direction = "Horizontal";
            } 
            else if (isVertical) {
                System.out.println("Direction is Vertically");
                this.direction = "Vertical";
            }
            
            
            
            return true; // All checks passed, so the direction is consistent

            }
	}
       
      
        
                
       public ArrayList<String> FormWordAsString(Map<Position, Tile> playerMoves, String roundType) {
		ArrayList<String> wordList = new ArrayList<>();
                StringBuilder stringBuilder = new StringBuilder();
                ArrayList <Character> lettersList = new ArrayList <>();
                String word = "";
                int row=15, col=15;
                
                if ("First".equals(roundType)){
                        for (Map.Entry<Position, Tile> entry: playerMoves.entrySet()){
                            Tile tile = entry.getValue();
                            lettersList.add(tile.getLetter());
                        }
                    
                    for (Character letter :lettersList ){
                        stringBuilder.append(letter);
                        word = stringBuilder.toString(); 
                    }
                    wordList.add(word);
                }
                
                else if ("Normal".equals(roundType)){
                    ArrayList<Square> totalConnectedSquares = new ArrayList<>(getConnectedSquares(playerMoves));                   
                    for (Square square : totalConnectedSquares){
                        System.out.println("Square Values is :" + square.getSquareValue());    
                    }
                    Map<Position, Character> MapsSameX = new LinkedHashMap();
                    Map<Position, Character> MapsSameY = new LinkedHashMap();
                    
                    for (Square connectedSquare : totalConnectedSquares){
                        Position connectedPosition = connectedSquare.getPosition();
                        char connectedLetter = connectedSquare.getSquareValue(); 
                        int oldX = 0; int oldY = 0;
                        
                        for (Map.Entry<Position, Tile> entry : playerMoves.entrySet()){
                            Position playerPosition = entry.getKey();
                            Tile tile = entry.getValue();
                            char playerLetter = tile.getLetter();
                            
                            
                            if (connectedPosition.getX()==playerPosition.getX()){  
                                if (!MapsSameX.containsKey(connectedLetter))
                                    MapsSameX.put(connectedPosition, connectedLetter); 
                                if (!MapsSameX.containsKey(playerPosition))
                                    MapsSameX.put(playerPosition, playerLetter); 
                                
                                if(playerPosition.getX() != oldX){ 
                                    int i = connectedPosition.getX();
                                    
                                    for (int j = connectedPosition.getY(); j>=0 ; j--){ 
                                        if(j==connectedPosition.getY())
                                            continue;
                                        Position tempPosition = new Position(i,j);
                                        
                                        for (Square otherSquare: squares){
                                            Position otherPosition = otherSquare.getPosition();
                                            if (otherPosition.getX()==tempPosition.getX() && otherPosition.getY() == tempPosition.getY()){
                                                if (otherSquare.getIsSquareisOccupied()){         
                                                    if(!MapsSameX.containsKey(otherPosition))
                                                        MapsSameX.put(otherPosition, otherSquare.getSquareValue());
                                                }
                                                else
                                                    break;
                                            }
                                        }
                                    }
                                    
                                    for (int j = connectedPosition.getY(); j<15 ; j++){ 
                                        if(j==connectedPosition.getY())
                                            continue;
                                        Position tempPosition = new Position(i,j);
                                        for (Square otherSquare: squares){
                                            Position otherPosition = otherSquare.getPosition();
                                            if (otherPosition.getX()==tempPosition.getX() && otherPosition.getY() == tempPosition.getY()){
                                                if (otherSquare.getIsSquareisOccupied()){
                                                    if(!MapsSameX.containsKey(otherPosition))
                                                        MapsSameX.put(otherPosition, otherSquare.getSquareValue());
                                                //    MapsSameX.put(connectedPosition, connectedLetter); // new
                                                }
                                                else
                                                    break;
                                            }
                                        }
                                    }
                                }
                            }
                                
                                   
                            oldX = playerPosition.getX();
                            
                            if (connectedPosition.getY()==playerPosition.getY()){
                                if (!MapsSameY.containsKey(playerPosition))
                                    MapsSameY.put(playerPosition, playerLetter);
                                if (!MapsSameY.containsKey(connectedLetter))
                                    MapsSameY.put(connectedPosition, connectedLetter);
                                if(playerPosition.getY() != oldY){                                       
                                    int m= connectedPosition.getY();
                                    
                                for (int k = connectedPosition.getX(); k>=0 ; k--){ 
                                    if(k==connectedPosition.getX())
                                        continue;
                                    Position tempPosition = new Position(k,m);
                                    
                                    for (Square otherSquare: squares){
                                        Position otherPosition = otherSquare.getPosition();
                                        if (otherPosition.getY()==tempPosition.getY() && otherPosition.getX() == tempPosition.getX()){
                                            if (otherSquare.getIsSquareisOccupied()){
                                                if(!MapsSameY.containsKey(otherPosition))
                                                    MapsSameY.put(otherPosition, otherSquare.getSquareValue());
                                            }
                                            else
                                                break;
                                        }
                                    }
                                }
                                
                                

                                for (int k = connectedPosition.getX(); k<15 ; k++){ 
                                    if(k==connectedPosition.getX())
                                        continue;
                                    Position tempPosition = new Position(k,m);

                                    for (Square otherSquare: squares){
                                        Position otherPosition = otherSquare.getPosition();
                                        if (otherPosition.getY()==tempPosition.getY() && otherPosition.getX() == tempPosition.getX()){
                                            if (otherSquare.getIsSquareisOccupied()){
                                                if(!MapsSameY.containsKey(otherPosition))
                                                    MapsSameY.put(otherPosition, otherSquare.getSquareValue());
                                            }
                                            else
                                                break;
                                        }
                                    }
                                }
                                }
                            }
                            
                            oldY = playerPosition.getY();
                            
                            }
                        
                            List<Map.Entry<Position, Character>> allEntriesX = new ArrayList<>();
                                
                                // Extract all entries from each map and add them to the list
                                for (Map.Entry<Position, Character> mapTempX : MapsSameX.entrySet()) {
                                    allEntriesX.add(mapTempX);
                                }
                                
                                
                                // Sort the combined list by Position's Y value
                                allEntriesX.sort(Comparator.comparingInt(entryX -> entryX.getKey().getY()));
                                
                                // Build a word from the sorted characters
                                StringBuilder wordBuilderX = new StringBuilder();
                                
                                for (Map.Entry<Position, Character> entryX : allEntriesX) {
                                    wordBuilderX.append(entryX.getValue());
                                }
                                
                                // The final word formed from all characters
                                String finalWordX = wordBuilderX.toString();
                                wordList.add(finalWordX);
                            
                                
                                
                                List<Map.Entry<Position, Character>> allEntriesY = new ArrayList<>();
                                
                                // Extract all entries from each map and add them to the list
                                for (Map.Entry<Position, Character> mapTempY : MapsSameY.entrySet()) {
                                    allEntriesY.add(mapTempY);
                                }
                                
                                
                                // Sort the combined list by Position's Y value
                                allEntriesY.sort(Comparator.comparingInt(entryY -> entryY.getKey().getX()));
                                
                                // Build a word from the sorted characters
                                StringBuilder wordBuilderY = new StringBuilder();
                                
                                for (Map.Entry<Position, Character> entryY : allEntriesY) {
                                    wordBuilderY.append(entryY.getValue());
                                }
                                
                                // The final word formed from all characters
                                String finalWordY = wordBuilderY.toString();
                                wordList.add(finalWordY);           
                            
                        }
                    
                    wordList.removeIf(String::isEmpty);
                        
                        
                       
                    
              /*
                    System.out.println("totalMap size: " + totalCharsMaps.size());
                        for(Map.Entry<Position, Character> entry : totalCharsMaps.entrySet()){
                            System.out.println("Psoition X: " + entry.getKey().getX() +"  Psoition Y: "+
                                    entry.getKey().getY() +" Letter: " + entry.getValue());
                        }*/
                    
                    
                    

                    
                  
                    
   // <editor-fold defaultstate="collapsed" desc="Other Ways">
                    /*
                    for (int i=0; i<row; i++){
                        ArrayList<Map<Position, Character>> tempMapList = new ArrayList<>();
                        for(int j=0; j<col; j++){     
                          for (Map<Position, Tile> map: playerMoves){
                              for (Map.Entry<Position, Tile> entry: map.entrySet()){
                                  Position playerPosition = entry.getKey();
                                  Tile tile = entry.getValue();
                                  char playerLetter = tile.getLetter();
                                  if(playerPosition.getX()==i){
                                      Map<Position, Character> tempMap = new HashMap();
                                      tempMap.put(playerPosition, playerLetter);
                                      tempMapList.add(tempMap);
                                  }
                              }
                          }
                          
                          for (Square square:totalConnectedSquares){
                              Position occupiedPosition = square.getPosition();
                              char occupiedLetter = square.getSquareValue();
                              if(occupiedPosition.getX() == i){
                                  Map<Position, Character> tempMap = new HashMap();
                                      tempMap.put(occupiedPosition, occupiedLetter);
                                      tempMapList.add(tempMap);
                              }
                        }
                        }    
                        for (Map<Position, Character> map: tempMapList){
                            for (Map.Entry<Position, Character> entry: map.entrySet()){
                                Position position = entry.getKey();
                                System.out.println("Char: " + entry.getValue()+"  at position: X: " + position.getX() + "  Y: " + position.getY());
                            }
                        }  
                    }
                    */
                    
                    
                    /*
                    for (Map<Position, Tile> map: playerMoves){
                        for (Map.Entry<Position, Tile> entry: map.entrySet()){
                            Position playerPosition = entry.getKey();
                            Tile tile = entry.getValue();
                            char playerLetter = tile.getLetter();
                            for (Square square:totalConnectedSquares){
                                Position occupiedPosition = square.getPosition();
                                char occupiedLetter = square.getSquareValue();
                                if (playerPosition.getX() == occupiedPosition.getX()){
                                    if(playerPosition.getY()<occupiedPosition.getY()){
                                        stringBuilder.append(playerLetter);
                                        stringBuilder.append(occupiedLetter);
                                        word = stringBuilder.toString();
                                        wordList.add(word);
                                    }
                                    else{
                                        stringBuilder.append(occupiedLetter);
                                        stringBuilder.append(playerLetter);
                                        word = stringBuilder.toString();
                                        wordList.add(word);
                                    }
                                        
                                        
                                }
                                else if (playerPosition.getY() == occupiedPosition.getY()){
                                    if(playerPosition.getX()<occupiedPosition.getX()){
                                        stringBuilder.append(playerLetter);
                                        stringBuilder.append(occupiedLetter);
                                        word = stringBuilder.toString();
                                        wordList.add(word);
                                    }
                                    else{
                                        stringBuilder.append(occupiedLetter);
                                        stringBuilder.append(playerLetter);
                                        word = stringBuilder.toString();
                                        wordList.add(word);
                                    }
                                }
                                else
                                    System.out.println ("No conntected X or Y");
                            }
                        }
                    }
                    */
   //</editor-fold> 
                    
                    
               //    wordList.addAll(getWordsByX(totalCharsMaps));
               //    wordList.addAll(getWordsByY(totalCharsMaps));
                
                }
                
                System.out.println("WordList size: " + wordList.size() + "  ,they are: " + wordList);
                
                return wordList;
	}
        
        
        public ArrayList <String> getWordsByX (Map<Position, Character> totalCharsMaps){
            Map<Integer, List<Pair<Integer, Character>>> charsByX = new HashMap<>();
            
            System.out.println("totalMap size in getWordsByX: " + totalCharsMaps.size());
                    
            // Populate charsByX
                for (Map.Entry<Position, Character> entry : totalCharsMaps.entrySet()) {
                    Position position = entry.getKey();
                    int x = position.getX();
                    int y = position.getY();
                    char c = entry.getValue();
                //    charsByX.putIfAbsent(x, new ArrayList<>());
                //    charsByX.get(x).add(new Pair<>(y, c));
                    
                    charsByX.computeIfAbsent(x, k -> new ArrayList<>()).add(new Pair<>(y, c));

                }
            
            
            // Process each X position
            ArrayList <String> wordsByX  = new ArrayList<>();
            for (Map.Entry<Integer, List<Pair<Integer, Character>>> entry : charsByX.entrySet()) {
                int x = entry.getKey();
                List<Pair<Integer, Character>> charsWithY = entry.getValue();
                
                // Sort by Y position
                charsWithY.sort(Comparator.comparing(Pair::getKey));
                
                // Aggregate characters by continuous Y positions
                StringBuilder sb = new StringBuilder();
                Integer lastY = null;
                for (Pair<Integer, Character> pair : charsWithY) {
                    int y = pair.getKey();
                    if (lastY != null && y != lastY + 1) {
                        // Process the string formed so far if Y positions are not continuous
                      //  String formedString = sb.toString();
                    //    wordsByX.add(formedString);
                        // Do something with formedString for this X position
                        sb = new StringBuilder(); // Reset StringBuilder for new string
                    }
                    sb.append(pair.getValue());
                    lastY = y;
                }
                if (sb.length() > 1) {
                    String formedWord = sb.toString();
                    if(!formedWord.contains(" "))
                        wordsByX.add(formedWord); // Add the last word if sb is not empty
                    else{
                        String[] parts = formedWord.split(" ");
                        for (String part : parts) {
                            wordsByX.add(part);
                        }
                    }
                        
        }
            }
            
            
            
            return wordsByX;
        }
        
        public ArrayList <String> getWordsByY (Map<Position, Character> totalCharsMaps){
            Map<Integer, List<Pair<Integer, Character>>> charsByY = new HashMap<>();
            System.out.println("totalMap size in getWordsByY: " + totalCharsMaps.size());
            
            // Populate charsByX
                for (Map.Entry<Position, Character> entry : totalCharsMaps.entrySet()) {
                    Position position = entry.getKey();
                    int x = position.getX();
                    int y = position.getY();
                    char c = entry.getValue();
                    charsByY.computeIfAbsent(y, k -> new ArrayList<>()).add(new Pair<>(x, c));

                  //  charsByY.putIfAbsent(y, new ArrayList<>());
                   // charsByY.get(x).add(new Pair<>(x, c));
                }
            
            
            // Process each Y position
            ArrayList <String> wordsByY  = new ArrayList<>();
            for (Map.Entry<Integer, List<Pair<Integer, Character>>> entry : charsByY.entrySet()) {
                int y = entry.getKey();
                List<Pair<Integer, Character>> charsWithY = entry.getValue();
                
                // Sort by Y position
                charsWithY.sort(Comparator.comparing(Pair::getKey));
                
                // Aggregate characters by continuous Y positions
                StringBuilder sb = new StringBuilder();
                Integer lastX = null;
                for (Pair<Integer, Character> pair : charsWithY) {
                    int x = pair.getKey();
                    if (lastX != null && x != lastX + 1) {
                        // Process the string formed so far if Y positions are not continuous
                       // String formedString = sb.toString();
                       // wordsByY.add(formedString);
                        // Do something with formedString for this X position
                        sb = new StringBuilder(); // Reset StringBuilder for new string
                    }
                    sb.append(pair.getValue());
                    lastX = x;
                }
                if (sb.length() > 1) {
                    String formedWord = sb.toString();
                    if(!formedWord.contains(" "))
                        wordsByY.add(formedWord); // Add the last word if sb is not empty
                    else{
                        String[] parts = formedWord.split(" ");
                        for (String part : parts) {
                            wordsByY.add(part);
                        }
                    }
                        
                }
            }
            
            return wordsByY;
        }  
        
        public ArrayList<Square> getConnectedSquares (Map<Position, Tile> playerMoves){
            ArrayList<Square> directlyConnectedOccupiedSquares = new ArrayList<> ();   
            directlyConnectedOccupiedSquares.clear();
             // Iterate over each player move.
      
            
                for(Map.Entry<Position,Tile> entry: playerMoves.entrySet()){
                    Position position = entry.getKey();
                    for (Square square : this.getSquares()){
                        if (square.getIsSquareisOccupied()){
                            Position squarePosition = square.getPosition();
                            if (// Horizontal and vertical neighbors
                                (squarePosition.getX() == position.getX() - 1 && squarePosition.getY() == position.getY()) ||
                                (squarePosition.getX() == position.getX() + 1 && squarePosition.getY() == position.getY()) ||
                                (squarePosition.getY() == position.getY() - 1 && squarePosition.getX() == position.getX()) ||
                                (squarePosition.getY() == position.getY() + 1 && squarePosition.getX() == position.getX()))
                            {
                                // Add the square to the list if it's not already included.
                                if (!directlyConnectedOccupiedSquares.contains(square)) {
                                    directlyConnectedOccupiedSquares.add(square);
                                }
                            }
                        }
                    }
                }
            
            
            System.out.println ("Total Connected Squares size:  " + directlyConnectedOccupiedSquares.size());
            return directlyConnectedOccupiedSquares;
        }
      
        public void updateTotalOccupiedPositions (Map<Position, Tile> currentPlayerMoves){
            ArrayList<Position> tempTotalPosition = new ArrayList<>(this.getTotalOccupiedSquaresPositions());

                for (Map.Entry<Position, Tile> entry: currentPlayerMoves.entrySet()){
                    Position position = entry.getKey();
                    tempTotalPosition.add(position);          
                }
            
            setOccupiedSquaresPositions (tempTotalPosition);
            System.out.println ("total occupied positions size: " + tempTotalPosition.size());
        }
        
        public void updateSquares(Map<Position, Tile> currentPlayerMoves) {
            Iterator<Square> iterator = squares.iterator();
            while (iterator.hasNext()) {
                Square square = iterator.next();
                    for (Map.Entry<Position, Tile> entry : currentPlayerMoves.entrySet()) {
                        Position position = entry.getKey();
                        Tile tile = entry.getValue();
                        char letter = tile.getLetter();
                        
                        if (square.getPosition().getX() == position.getX() && square.getPosition().getY() == position.getY()) {
                            // Update the square
                            square.setSquare(position, Boolean.TRUE, letter, square.getIsSquareID(), square.getSquareType());
                            break; // Stop iterating over player moves if the square is found
                }
            }
        
    }
}

      
            
        public void setOccupiedSquaresPositions(ArrayList<Position> totalPositions){
            this.totalOccupiedSquaresPositions = totalPositions;
        }


	public ArrayList<Position> getTotalOccupiedSquaresPositions() {
		return this.totalOccupiedSquaresPositions;
	}

}