/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AlphabetTilesGame;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author YAROU
 */
public class EnglishNormalRoundState implements ComputerMoveState {
    
    private IDictionary dictionary = DictionaryFactory.getDictionary("English");
    private Board board = new Board ();
    Integer difficultyLevel;
    
    @Override
    public void setDifficultyLevel (Integer level){
        this.difficultyLevel = level;
    }
    
    @Override
    public Map<Position, Tile> makeMove(ArrayList<Tile> tiles, ArrayList<Position> positions) {
        Map<Position, Tile> playerMoves = new LinkedHashMap();
        
        ArrayList<Square> squares = new ArrayList<>(board.getSquares());
            Map<Integer, List<Pair<Integer, Character>>> charsByX = new HashMap<>();
            Map<Integer, List<Pair<Integer, Character>>> charsByY = new HashMap<>();
            
            // search for Characters with same X
            for (Square square: squares){
                if (square.getIsSquareisOccupied()){                  
                    Position position = square.getPosition();
                    int x = position.getX();
                    int y = position.getY();
                    
                    List<Pair<Integer, Character>> diffCharsByY = new ArrayList<>(); // Initialize outside of the loop

                    // for same X :
                    for (int j = 0; j<15; j++){
                    //    List<Pair<Integer,Character>> diffCharsByY = new ArrayList<>();
                        Position tempPosition = new Position(x,j);
                      //  tempPosition.setX(x);
                      //  tempPosition.setY(j);
                        
                        for (Square testSquare: squares){
                            Position testPos = testSquare.getPosition();
                            if (testPos.getX() == tempPosition.getX() && testPos.getY()==tempPosition.getY() && testSquare.getIsSquareisOccupied()){
                             //   if (testSquare.getIsSquareisOccupied()){
                                    Pair<Integer,Character> pair= new Pair<>(testPos.getY(),testSquare.getSquareValue());
                                    if(!diffCharsByY.contains(pair)){
                                        diffCharsByY.add(pair);
                                     //   if(!charsByX.containsValue(diffCharsByY))
                                            
                                    }
  
                            //    }
                            }
                        }
                    }
                    charsByX.put(x, diffCharsByY);     
                
                }
            }
            
            
            
            // search for Characters with same Y
            for (Square square: squares){
                if (square.getIsSquareisOccupied()){                  
                    Position position = square.getPosition();
                    int x = position.getX();
                    int y = position.getY();
                    
                    List<Pair<Integer,Character>> diffCharsByX = new ArrayList<>();
                    
                    for (int i = 0; i<15; i++){
                        
                        Position tempPosition = new Position(i,y);
                      //  tempPosition.setX(i);
                      //  tempPosition.setY(y);
                        
                        for (Square testSquare: squares){
                            Position testPos = testSquare.getPosition();
                            if (testPos.getX() == tempPosition.getX() && testPos.getY()==tempPosition.getY() && testSquare.getIsSquareisOccupied()){
                               // if (testSquare.getIsSquareisOccupied()){
                                    Pair<Integer,Character> pair= new Pair<>(testPos.getX(),testSquare.getSquareValue());
                                    if(!diffCharsByX.contains(pair)){
                                        diffCharsByX.add(pair);
                                      //  if(!charsByY.containsValue(diffCharsByX))
                                            
                                    }
  
                                //}
                            }
                        }
                    }   
                    charsByY.put(y, diffCharsByX);
  // <editor-fold defaultstate="collapsed" desc="other way">                  
/*
                    for (int i = x; i<15; i++){
                        
                        Position tempPosition = new Position();
                        tempPosition.setX(i);
                        tempPosition.setY(y);
                        
                        for (Square testSquare: squares){
                            Position testPos = testSquare.getPosition();
                            if (testPos.getX() == tempPosition.getX() && testPos.getY()==tempPosition.getY() && testSquare.getIsSquareisOccupied()){
                               // if (testSquare.getIsSquareisOccupied()){
                                    Pair<Integer,Character> pair= new Pair<>(testPos.getY(),testSquare.getSquareValue());
                                  //  if(!diffCharsByX.contains(pair)){
                                        diffCharsByX.add(pair);
                                        if(!charsByY.containsValue(diffCharsByX))
                                            charsByY.put(y, diffCharsByX);
                                 //   }
  
                                //}
                            }
                        }
                    }     
                    for (int i = x; i>=0; i--){
                     //   List<Pair<Integer,Character>> diffCharsByX = new ArrayList<>();
                        Position tempPosition = new Position();
                        tempPosition.setX(i);
                        tempPosition.setY(y);
                        
                        for (Square testSquare: squares){
                            Position testPos = testSquare.getPosition();
                            if (testPos.getX() == tempPosition.getX() && testPos.getY()==tempPosition.getY()&&testSquare.getIsSquareisOccupied()){
                              //  if (testSquare.getIsSquareisOccupied()){
                                    Pair<Integer,Character> pair= new Pair<>(testPos.getY(),testSquare.getSquareValue());
                                 //   if(!diffCharsByX.contains(pair)){
                                        diffCharsByX.add(pair);
                                        if(!charsByY.containsValue(diffCharsByX))
                                            charsByY.put(y, diffCharsByX);
                                  //  }
  
                              //  }
                            }
                        }
                        
                    }
                */
    //</editor-fold>            
                }
                
                
            }
            
              List<WordPosition> wordsByXWithPositions = new ArrayList<>();
              wordsByXWithPositions = getWordsByX(charsByX);
              
              
              List<WordPosition> wordsByYWithPositions = new ArrayList<>();
              wordsByYWithPositions = getWordsByY(charsByY);
        //    ArrayList<String> wordsListX = new ArrayList<>(getWordsByX(charsByX));
          //  ArrayList<String> wordsListY = new ArrayList<>(getWordsByY(charsByY));
            
            System.out.println("WordListX Size: " + wordsByXWithPositions.size());
            for (WordPosition wordsX : wordsByXWithPositions){
                System.out.println("word from x :  " + wordsX.getWord() +  "  X: " + wordsX.getCommon() +
                        "  startY: " + wordsX.getStart() +  "  lastY: " + wordsX.getEnd());
            }
            
            System.out.println("WordListY Size: " + wordsByYWithPositions.size());
            for (WordPosition wordsY : wordsByYWithPositions){
                System.out.println("word from x :  " + wordsY.getWord() +  "  Y: " + wordsY.getCommon() +
                        "  startX: " + wordsY.getStart() +  "  lastX: " + wordsY.getEnd());
            }
            
           
            
            Map<WordPosition,ArrayList<Tile>> finalWord = searchNormalWord(wordsByXWithPositions, wordsByYWithPositions, tiles);
            
            System.out.println("finalWord Size: " + finalWord.size());
            for (WordPosition wordPosition : finalWord.keySet()) {
            System.out.println(wordPosition.getWord());}
    
        
        return playerMoves;
    }
    
    
    
   
        
        
        private Map<WordPosition, ArrayList<Tile>> searchNormalWord(List<WordPosition> wordsByXWithPositions, 
                List<WordPosition> wordsByYWithPositions, ArrayList<Tile> remainingTiles) {
            Map<WordPosition, ArrayList<Tile>> wordToTilesMap = new HashMap<>();
            
            // Process words by X position
            for (WordPosition xWord : wordsByXWithPositions) {
                searchWordWithTiles(xWord, remainingTiles, wordToTilesMap, new ArrayList<>());
                
            }
            
            // Process words by Y position
            for (WordPosition yWord : wordsByYWithPositions) {
                searchWordWithTiles(yWord, remainingTiles, wordToTilesMap, new ArrayList<>());
            }
            return wordToTilesMap;
        }
        
        private void searchWordWithTiles(WordPosition currentWordPosition, ArrayList<Tile> remainingTiles, 
                Map<WordPosition, ArrayList<Tile>> wordToTilesMap, ArrayList<Tile> usedTiles) {
            
            String currentWord = currentWordPosition.getWord();
            
            // Check if the current word is valid
            if (!currentWord.isEmpty() && dictionary.checkValidity(currentWord)) {
                wordToTilesMap.put(currentWordPosition, new ArrayList<>(usedTiles));
            }
            
            // Pruning: Stop if the current word cannot form a valid word
            if (!dictionary.startsWith(currentWord)) {
                return;
           }
            
            // Try adding tiles before and after the current word
            for (int i = 0; i < remainingTiles.size(); i++) {
                Tile tile = remainingTiles.get(i);
                
                // Tiles + Word
                String newWordPrepend = tile.getLetter() + currentWord;
             //   System.out.println("New word append : Tiles + Word" + newWordPrepend);
                updateWordPositionAndSearch(newWordPrepend, currentWordPosition, -1, usedTiles, tile,
                        remainingTiles, i, wordToTilesMap);
                
                // Word + Tiles
                String newWordAppend = currentWord + tile.getLetter();
              //  System.out.println("New word append : Word + Tiles" + newWordPrepend);
                
                
                updateWordPositionAndSearch(newWordAppend, currentWordPosition, 1, usedTiles, tile,
                        remainingTiles, i, wordToTilesMap);
            }
        }
        
    
        
        private void updateWordPositionAndSearch(String newWord, WordPosition currentPosition, int offset, ArrayList<Tile> usedTiles, 
                Tile tile, ArrayList<Tile> remainingTiles, int tileIndex, Map<WordPosition, ArrayList<Tile>> wordToTilesMap) {
            
            int newStart = currentPosition.getStart() + (offset < 0 ? offset : 0); // Adjust start position if adding tile before the word
            int newEnd = currentPosition.getEnd() + (offset > 0 ? offset : 0); // Adjust end position if adding tile after the word
            WordPosition newWordPosition = new WordPosition(newWord, currentPosition.getCommon() ,
                    newStart, newEnd);
            
            ArrayList<Tile> newRemainingTiles = new ArrayList<>(remainingTiles);
            newRemainingTiles.remove(tileIndex);
            
            ArrayList<Tile> newUsedTiles = new ArrayList<>(usedTiles);
            newUsedTiles.add(tile); // Add the used tile to the list
            
            searchWordWithTiles(newWordPosition, newRemainingTiles, wordToTilesMap, newUsedTiles);
        }

        
      
        
        public List<WordPosition> getWordsByX(Map<Integer, List<Pair<Integer, Character>>> charsByX) {
          //  Map<String, Pair<Integer, Integer>> wordsByXWithPositions = new HashMap<>();
            List<WordPosition> wordsByXWithPositions = new ArrayList<>();
            ArrayList<String> wordsByX = new ArrayList<>();
            for (Map.Entry<Integer, List<Pair<Integer, Character>>> entry : charsByX.entrySet()) {
                List<Pair<Integer, Character>> charsWithY = entry.getValue();
                
                // Sort by Y position
                charsWithY.sort(Comparator.comparing(Pair::getKey));
                Integer x = entry.getKey();
                StringBuilder sb = new StringBuilder();
                Integer startY = null;
                Integer lastY = null;
                for (Pair<Integer, Character> pair : charsWithY) {
                    int y = pair.getKey();
                    
                    if (startY == null) startY = y; // Set the start Y position for the new word
                    
                    // Check for discontinuity in Y positions or a space character
                    if ((lastY != null && y != lastY + 1) || pair.getValue() == ' ') {
                        if (sb.length() > 0) { // Add the string formed so far if it's not empty
                            String formedWord = sb.toString();
                            if (!formedWord.contains(" ")) {
                                wordsByXWithPositions.add(new WordPosition(formedWord, x, startY, lastY));
                             //   wordsByXWithPositions.put(formedWord, new Pair<>(startY, lastY));
                             //   wordsByX.add(formedWord);
                            } 
                            else {
                                 // Split by space and add parts
                                String[] parts = formedWord.split(" ");
                                int currentStartY = startY;
                                for (String part : parts) {
                                    if (!part.isEmpty()) { // Check to avoid adding empty strings
                                      //  wordsByX.add(part);
                                    //  wordsByXWithPositions.put(part, new Pair<>(currentStartY, currentStartY + part.length() - 1));
                                      wordsByXWithPositions.add(new WordPosition(part, x, currentStartY, currentStartY + part.length() - 1));
                                      currentStartY += part.length() + 1; // +1 for the space
                        
                                    }
                                }
                            }
                            sb = new StringBuilder(); // Reset StringBuilder for a new string
                            startY = y; // Reset startY for the next word
                        }
                        if (pair.getValue() != ' ') {
                            sb.append(pair.getValue()); // Append the current character if it's not a space
                        }
                    } 
                    else {
                        sb.append(pair.getValue());
                    }
                    lastY = y;
                }
                
                 // Process the last word
                if (sb.length() > 0) {
                    String formedWord = sb.toString();
                    if (!formedWord.contains(" ")) {
                        wordsByXWithPositions.add(new WordPosition(formedWord, x, startY, lastY));
                    //      wordsByXWithPositions.put(formedWord, new Pair<>(startY, lastY));
                       // wordsByX.add(formedWord);
                    } 
                    else {
                        String[] parts = formedWord.split(" ");
                        int currentStartY = startY;
                        for (String part : parts) {
                            if (!part.isEmpty()) {
                                wordsByXWithPositions.add(new WordPosition(part, x, currentStartY, currentStartY + part.length() - 1));
                              //  wordsByXWithPositions.put(part, new Pair<>(currentStartY, currentStartY + part.length() - 1));
                                currentStartY += part.length() + 1; // +1 for the space
                 
                            }
                        }
                    }
                }
            }
            return wordsByXWithPositions;
        }
       
        
        public List<WordPosition> getWordsByY(Map<Integer, List<Pair<Integer, Character>>> charsByY) {
            List<WordPosition> wordsByYWithPositions = new ArrayList<>();
          //  Map<String, Pair<Integer, Integer>> wordsByYWithPositions = new HashMap<>();
            ArrayList<String> wordsByY = new ArrayList<>();
            for (Map.Entry<Integer, List<Pair<Integer, Character>>> entry : charsByY.entrySet()) {
                List<Pair<Integer, Character>> charsWithX = entry.getValue();
                
                // Sort by Y position
                charsWithX.sort(Comparator.comparing(Pair::getKey));
                int y = entry.getKey();
                
                StringBuilder sb = new StringBuilder();
                Integer startX = null;
                Integer lastX = null;
                for (Pair<Integer, Character> pair : charsWithX) {
                    int x = pair.getKey();
                    
                    if (startX == null) startX = x; // Set the start Y position for the new word
                    
                    // Check for discontinuity in Y positions or a space character
                    if ((lastX != null && x != lastX + 1) || pair.getValue() == ' ') {
                        if (sb.length() > 0) { // Add the string formed so far if it's not empty
                            String formedWord = sb.toString();
                            if (!formedWord.contains(" ")) {
                                wordsByYWithPositions.add(new WordPosition(formedWord, y, startX, lastX));
                            //    wordsByYWithPositions.put(formedWord, new Pair<>(startX, startX));
                             //   wordsByX.add(formedWord);
                            } 
                            else {
                                 // Split by space and add parts
                                String[] parts = formedWord.split(" ");
                                int currentStartX = startX;
                                for (String part : parts) {
                                    if (!part.isEmpty()) { // Check to avoid adding empty strings
                                      //  wordsByX.add(part);
                                  //    wordsByYWithPositions.put(part, new Pair<>(currentStartX, currentStartX + part.length() - 1));
                                      wordsByYWithPositions.add(new WordPosition(part, y, currentStartX, currentStartX + part.length() - 1));
                                      currentStartX += part.length() + 1; // +1 for the space
                        
                                    }
                                }
                            }
                            sb = new StringBuilder(); // Reset StringBuilder for a new string
                            startX = x; // Reset startY for the next word
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
                        wordsByYWithPositions.add(new WordPosition(formedWord, y, startX, lastX));
                        //  wordsByYWithPositions.put(formedWord, new Pair<>(startX, lastX));
                       // wordsByX.add(formedWord);
                    } 
                    else {
                        String[] parts = formedWord.split(" ");
                        int currentStartX = startX;
                        for (String part : parts) {
                            if (!part.isEmpty()) {
                                wordsByYWithPositions.add(new WordPosition(part, y, currentStartX, currentStartX + part.length() - 1));
                            //    wordsByYWithPositions.put(part, new Pair<>(currentStartX, currentStartX + part.length() - 1));
                                currentStartX += part.length() + 1; // +1 for the space
                 
                        
                        // Split by space and add parts
                   /*     String[] parts = formedWord.split(" ");
                        for (String part : parts) {
                            if (!part.isEmpty()) { // Check to avoid adding empty strings
                                wordsByX.add(part); */
                            }
                        }
                    }
                }
            }
            return wordsByYWithPositions;
        }
        
        
        
}
