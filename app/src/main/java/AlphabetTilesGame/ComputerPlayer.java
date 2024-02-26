/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AlphabetTilesGame;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ComputerPlayer extends Player {

	public int computerPlayerID;
        private TilesRack tilesRack = this.getTilesRack();
        private ArrayList <Tile> tiles = new ArrayList <> ();
        private AlphabetTiles game;
        private int difficultyLevel;
      //  private IDictionary dictionary;
       // private Board board;
        
  //      private static TilesBag tilesBag = new TilesBag (); 
        
    
    

 
        public ComputerPlayer (){
           // this.dictionary = Dictionary.getInstance();
          // this.dictionary = DictionaryFactory.getDictionary("English");
          //  this.board = new Board ();
   
          //  this.game = game;
        }
        
        public void setDifficultyLevel (Integer level){
            this.difficultyLevel = level;
        }
        
        
        public void setTilesRack(TilesRack tilesRack){
            this.tilesRack = tilesRack;
        }
        
        public TilesRack getTilesRack (){
            return this.tilesRack;
        }
      
        
        public void setGame(AlphabetTiles game) {
            this.game = game;
        }
        
        
        
        public void printTest (){
            System.out.println ("computer ID: " +  this.computerPlayerID);
        }
        
        
        public void playFisrtRound (){
            TilesRack tempTilesRack = new TilesRack();
            tempTilesRack =  this.tilesRack;
            boolean isValid = false;
            
            ArrayList<Tile> tempTiles = new ArrayList<>(tempTilesRack.getTilesRack_Tiles());

            System.out.println ("Computer Tiles are: "); 
            for (Tile tile : tempTiles){
                System.out.println ( "tile: " + tile.getLetter() + "  with ID : " + tile.getTileID() + "  // Points: " + tile.getLetterValue()  );
            }  
            

            ArrayList <Position> tempPositions = new ArrayList<> (board.getTotalOccupiedSquaresPositions());
            

            ComputerMoveState state = new EnglishFirstRoundState();
            ComputerMakeMoveStrategy computerStrategy = new ComputerMakeMoveStrategy(state);
            state.setDifficultyLevel(this.difficultyLevel);
            this.playerMoves = computerStrategy.makeMove(tempTiles, tempPositions);
            
            for(Map.Entry<Position, Tile> entry : this.playerMoves.entrySet()){
                Tile tile = entry.getValue();
                Position position = entry.getKey();
                System.out.println ("Tile: " + tile.getLetter() + " ID: " + tile.getTileID() + " position X: "+ position.getX() + " position Y: "+ position.getY());
            }
            
            
            isValid = board.checkMove("First", this.playerMoves, this.getID());
            
            
            if (isValid == true){
                System.out.println("Word is Valid");
                removeTiles(this.playerMoves);
            }
        }
        
        
        
        public void playNormalRound (){
            
            boolean isValid = false;
            TilesRack newTilesRack = tilesRackCountHandling(this.tilesRack);
            
            while (!isValid){
                TilesRack tempTilesRack = new TilesRack ();
                tempTilesRack = newTilesRack;
                
                ArrayList<Tile> tempTiles = new ArrayList<>(tempTilesRack.getTilesRack_Tiles());
                
                board.printGrid();
                System.out.println ( "Computer Tiles are ");
                for (Tile tile: tempTiles){
                    System.out.println ( "tile: " + tile.getLetter() + "  with ID : " + tile.getTileID() + "  // Points: " + tile.getLetterValue()  );
                }
                
              
                ArrayList <Position> tempPositions = new ArrayList<> (board.getTotalOccupiedSquaresPositions());
                
                ComputerMakeMoveStrategy computerStrategy = new ComputerMakeMoveStrategy(new EnglishNormalRoundState());           
                this.playerMoves = computerStrategy.makeMove(tempTiles, tempPositions);
            
                
                
                isValid = true;
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
        
        

        
           // <editor-fold defaultstate="collapsed" desc="searchNormalWord other way"> 
        /*
        private Map<WordPosition, ArrayList<Tile>> searchNormalWord(List<WordPosition> wordsByXWithPositions, List<WordPosition> wordsByYWithPositions, List<Tile> remainingTiles) {
    Map<WordPosition, ArrayList<Tile>> wordToTilesMap = new HashMap<>();
    List<Tile> usedTiles = new ArrayList<>();

    processWordPositions(wordsByXWithPositions, remainingTiles, usedTiles, wordToTilesMap, true);
    processWordPositions(wordsByYWithPositions, remainingTiles, usedTiles, wordToTilesMap, false);

    return wordToTilesMap;
}

private void processWordPositions(List<WordPosition> wordPositions, List<Tile> remainingTiles, List<Tile> usedTiles, Map<WordPosition, ArrayList<Tile>> wordToTilesMap, boolean isXAxis) {
    for (WordPosition wordPosition : wordPositions) {
        processWordPosition(wordPosition, new ArrayList<>(remainingTiles), new ArrayList<>(usedTiles), wordToTilesMap, isXAxis);
    }
}

private void processWordPosition(WordPosition wordPosition, List<Tile> remainingTiles, List<Tile> usedTiles, Map<WordPosition, ArrayList<Tile>> wordToTilesMap, boolean isXAxis) {
    if (!dictionary.checkValidity(wordPosition.getWord())) {
        return;
    }

    wordToTilesMap.put(wordPosition, new ArrayList<>(usedTiles));

    for (int i = 0; i < remainingTiles.size(); i++) {
        Tile tile = remainingTiles.get(i);
        if (!dictionary.startsWith(wordPosition.getWord() + tile.getLetter())) {
            continue;
        }

        WordPosition newWordPosition = createNewWordPosition(wordPosition, tile, isXAxis);
        List<Tile> newRemainingTiles = new LinkedList<>(remainingTiles);
        newRemainingTiles.remove(i);

        List<Tile> newUsedTiles = new ArrayList<>(usedTiles);
        newUsedTiles.add(tile);

        processWordPosition(newWordPosition, newRemainingTiles, newUsedTiles, wordToTilesMap, isXAxis);
    }
}

private WordPosition createNewWordPosition(WordPosition wordPosition, Tile tile, boolean isXAxis) {
    String newWord = wordPosition.getWord().toLowerCase() + tile.getLetter();
    if (isXAxis) {
        return new WordPosition(newWord, wordPosition.getCommon(), wordPosition.getStart(), wordPosition.getEnd() + 1);
    } else {
        return new WordPosition(newWord, wordPosition.getCommon(), wordPosition.getStart() - 1, wordPosition.getEnd());
    }
}
*/
      //  </editor-fold>
        
        
        
        
        
       // <editor-fold defaultstate="collapsed" desc="getWordByY other way"> 
      /*  
        public ArrayList<String> getWordsByY(Map<Integer, List<Pair<Integer, Character>>> charsByY) {
            ArrayList<String> wordsByY = new ArrayList<>();
            for (Map.Entry<Integer, List<Pair<Integer, Character>>> entry : charsByY.entrySet()) {
                List<Pair<Integer, Character>> charsWithX = entry.getValue();
                
                // Sort by X position
                charsWithX.sort(Comparator.comparing(Pair::getKey));
                
                StringBuilder sb = new StringBuilder();
                Integer lastX = null;
                for (Pair<Integer, Character> pair : charsWithX) {
                    int x = pair.getKey();
                    
                    // Check for discontinuity in X positions or a space character
                    if ((lastX != null && x != lastX + 1) || pair.getValue() == ' ') {
                        if (sb.length() > 0) { // Add the string formed so far if it's not empty
                            String formedWord = sb.toString();
                            if (!formedWord.contains(" ")) {
                                wordsByY.add(formedWord);
                            } 
                            else {
                                // Split by space and add parts
                                String[] parts = formedWord.split(" ");
                                for (String part : parts) {
                                    if (!part.isEmpty()) { // Check to avoid adding empty strings
                                        wordsByY.add(part);
                                    }
                                }
                            }
                            
                            sb = new StringBuilder(); // Reset StringBuilder for a new string
                        }
                        if (pair.getValue() != ' ') {
                            sb.append(pair.getValue()); // Append the current character if it's not a space
                        }
                    } 
                    else {
                        sb.append(pair.getValue());
                    }
                    lastX = x;
                }
        
                // Process the last word
                if (sb.length() > 0) {
                    String formedWord = sb.toString();
                    if (!formedWord.contains(" ")) {
                        wordsByY.add(formedWord);
                    } 
                    else {
                        // Split by space and add parts
                        String[] parts = formedWord.split(" ");
                        for (String part : parts) {
                            if (!part.isEmpty()) { // Check to avoid adding empty strings
                                wordsByY.add(part);
                            }
                        }
                    }
                }
            }
            return wordsByY;
        }
      */
      //</editor-fold>  
        
        // <editor-fold defaultstate="collapsed" desc="searchNormalWord other way"> 
        /*
        private Map<WordPosition,ArrayList<Tile>> searchNormalWord (List<WordPosition> wordsByXWithPositions,List<WordPosition> wordsByYWithPositions, ArrayList<Tile> remainingTiles){    
               // Map<WordPosition,ArrayList<Tile>> finalWord = new HashMap();
              //  List<WordPosition> totalWords = new ArrayList<>();
                Map<WordPosition, ArrayList<Tile>> wordToTilesMap = new HashMap<>();
                ArrayList<Tile> usedTiles = new ArrayList<>();
             //   ArrayList<Tile> remainingTiles;
                
             // Loop for Word + Tiles case SameX
                for (WordPosition xWord : wordsByXWithPositions){
                    String word = xWord.getWord();
                    WordPosition currentWord = xWord;
                    int x = xWord.getCommon();
                    int startY = xWord.getStart();  int endY = xWord.getEnd();
                    ArrayList<Tile> tempRemainingTiles = new ArrayList<>(remainingTiles);
                    
                    // Loop for Tiles+Word case
                    while (!currentWord.getWord().isEmpty() && dictionary.checkValidity(currentWord.getWord())) {
                // Clone the usedTiles list to freeze its state at this point
                wordToTilesMap.put(currentWord, new ArrayList<>(usedTiles));
            
            
            if (!dictionary.startsWith(currentWord.getWord())) { // Pruning
                continue;
            }
            
            for (int i = 0; i < tempRemainingTiles.size(); i++) {
                Tile tile = tempRemainingTiles.get(i);
                WordPosition newWord = new WordPosition (currentWord.getWord().toLowerCase() + tile.getLetter(),x,startY,endY+1);
                ArrayList<Tile> newRemainingTiles = new ArrayList<>(tempRemainingTiles);
                newRemainingTiles.remove(i);
                
                ArrayList<Tile> newUsedTiles = new ArrayList<>(usedTiles);
                newUsedTiles.add(tile); // Add the used tile to the list
                
                currentWord = newWord;
                tempRemainingTiles = newRemainingTiles;
                usedTiles = newUsedTiles;
              //  searchFisrtWord(newWord.toLowerCase(), newRemainingTiles, wordToTilesMap, newUsedTiles);
            
            }
                }
                
                }
                
                
                // Loop for Tiles + word case  SameX
                for (WordPosition xWord : wordsByXWithPositions){
                    String word = xWord.getWord();
                    WordPosition currentWord = xWord;
                    int x = xWord.getCommon();
                    int startY = xWord.getStart();  int endY = xWord.getEnd();
                    ArrayList<Tile> tempRemainingTiles = new ArrayList<>(remainingTiles);
                    
                    // Loop for Tiles+Word case
                    while (!currentWord.getWord().isEmpty() && dictionary.checkValidity(currentWord.getWord())) {
                // Clone the usedTiles list to freeze its state at this point
                wordToTilesMap.put(currentWord, new ArrayList<>(usedTiles));
            
            
            if (!dictionary.startsWith(currentWord.getWord())) { // Pruning
                continue ;
            }
            
            for (int i = 0; i < tempRemainingTiles.size(); i++) {
                Tile tile = tempRemainingTiles.get(i);
                WordPosition newWord = new WordPosition ( tile.getLetter() + currentWord.getWord().toLowerCase(),x,startY-1,endY);
                ArrayList<Tile> newRemainingTiles = new ArrayList<>(tempRemainingTiles);
                newRemainingTiles.remove(i);
                
                ArrayList<Tile> newUsedTiles = new ArrayList<>(usedTiles);
                newUsedTiles.add(tile); // Add the used tile to the list
                
                currentWord = newWord;
                tempRemainingTiles = newRemainingTiles;
                usedTiles = newUsedTiles;
              //  searchFisrtWord(newWord.toLowerCase(), newRemainingTiles, wordToTilesMap, newUsedTiles);
            
            }
                }
                
                }
                
               
                
                
                
                   // Loop for Word + Tiles case SameX
                for (WordPosition yWord : wordsByYWithPositions){
                    String word = yWord.getWord();
                    WordPosition currentWord = yWord;
                    int y = yWord.getCommon();
                    int startX = yWord.getStart();  int endX = yWord.getEnd();
                    ArrayList<Tile> tempRemainingTiles = new ArrayList<>(remainingTiles);
                    
                    // Loop for Tiles+Word case
                    while (!currentWord.getWord().isEmpty() && dictionary.checkValidity(currentWord.getWord())) {
                // Clone the usedTiles list to freeze its state at this point
                wordToTilesMap.put(currentWord, new ArrayList<>(usedTiles));
            
            
            if (!dictionary.startsWith(currentWord.getWord())) { // Pruning
                continue ;
            }
            
            for (int i = 0; i < tempRemainingTiles.size(); i++) {
                Tile tile = tempRemainingTiles.get(i);
                WordPosition newWord = new WordPosition (currentWord.getWord().toLowerCase() + tile.getLetter(),y,startX,endX+1);
                ArrayList<Tile> newRemainingTiles = new ArrayList<>(tempRemainingTiles);
                newRemainingTiles.remove(i);
                
                ArrayList<Tile> newUsedTiles = new ArrayList<>(usedTiles);
                newUsedTiles.add(tile); // Add the used tile to the list
                
                currentWord = newWord;
                tempRemainingTiles = newRemainingTiles;
                usedTiles = newUsedTiles;
              //  searchFisrtWord(newWord.toLowerCase(), newRemainingTiles, wordToTilesMap, newUsedTiles);
            
            }
                }
                
                }
                
                
                // Loop for Tiles + word case  SameX
                for (WordPosition yWord : wordsByYWithPositions){
                    String word = yWord.getWord();
                    WordPosition currentWord = yWord;
                    int y = yWord.getCommon();
                    int startX = yWord.getStart();  int endX = yWord.getEnd();
                    ArrayList<Tile> tempRemainingTiles = new ArrayList<>(remainingTiles);
                    
                    // Loop for Tiles+Word case
                    while (!currentWord.getWord().isEmpty() && dictionary.checkValidity(currentWord.getWord())) {
                // Clone the usedTiles list to freeze its state at this point
                wordToTilesMap.put(currentWord, new ArrayList<>(usedTiles));
            
            
            if (!dictionary.startsWith(currentWord.getWord())) { // Pruning
                continue ;
            }
            
            for (int i = 0; i < tempRemainingTiles.size(); i++) {
                Tile tile = tempRemainingTiles.get(i);
                WordPosition newWord = new WordPosition ( tile.getLetter() + currentWord.getWord().toLowerCase(),y,startX-1,endX);
                ArrayList<Tile> newRemainingTiles = new ArrayList<>(tempRemainingTiles);
                newRemainingTiles.remove(i);
                
                ArrayList<Tile> newUsedTiles = new ArrayList<>(usedTiles);
                newUsedTiles.add(tile); // Add the used tile to the list
                
                currentWord = newWord;
                tempRemainingTiles = newRemainingTiles;
                usedTiles = newUsedTiles;
              //  searchFisrtWord(newWord.toLowerCase(), newRemainingTiles, wordToTilesMap, newUsedTiles);
            
            }
                }
                
                }
                
                
                
                
                
                
                
                
                return wordToTilesMap;
            
            
        }
        
        */
      //  </editor-fold>
        
        
        
        
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
