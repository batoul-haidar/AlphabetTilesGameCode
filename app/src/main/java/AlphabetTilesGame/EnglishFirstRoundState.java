/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AlphabetTilesGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author YAROU
 */
public class EnglishFirstRoundState implements ComputerMoveState {
    private IDictionary dictionary = DictionaryFactory.getDictionary("English");
    // in Arabic Language it is easy now to get Arabic dictionary
    Integer difficultyLevel;
    
    @Override
    public void setDifficultyLevel (Integer level){
        this.difficultyLevel = level;
    }
    
    @Override
    public Map<Position, Tile> makeMove(ArrayList<Tile> tiles, ArrayList<Position> position) {
        // English-specific move logic
        Map<Position, Tile> playerMoves = new LinkedHashMap();
        
        ArrayList<Tile> movedTiles = makeFirstValidWord(tiles);
        
        
        int x=7, y=7;
            for (int i=0; i<movedTiles.size(); i++){
                Tile tile = movedTiles.get(i);
                Position tempPosition = new Position(x,y+i);
                playerMoves.put(tempPosition, tile);
            }
            
            
        
        return playerMoves;
    }
    
    public ArrayList<Tile> makeFirstValidWord(ArrayList<Tile> allTiles){ 
            // Map to keep track of words and the tiles used to form them
            Map<String, ArrayList<Tile>> wordToTilesMap = new HashMap<>();
            
            
            // Generate all valid words and their corresponding tiles
            searchFisrtWord("", allTiles, wordToTilesMap, new ArrayList<>());
            
            // Find the valid word and its tiles based on difficulty level
            // Difficult Level
            if (this.difficultyLevel == 3){
               // int longestWordLength = 0;
                int longestWordPoints = 0;
                String longestWord = ""; 
                ArrayList<Tile> longestWordTiles = new ArrayList<>();
                for (Map.Entry<String, ArrayList<Tile>> entry : wordToTilesMap.entrySet()) {
                    int sum = 0; ArrayList<Integer> listOfSum = new ArrayList<>();
                    String word = entry.getKey();
                    ArrayList<Tile> tiles = entry.getValue();
                    for (Tile tile : tiles){
                        int letterValue = tile.getLetterValue();
                        sum = sum + letterValue;    
                    }
                    listOfSum.add(sum);
                    if (sum > longestWordPoints) {
                        longestWordPoints = sum;
                     //   longestWordLength = word.length();
                        longestWordTiles = tiles;
                    } 
         
                 //   System.out.println ("Word is : " + word + "  sum : " + sum);        // print for test
                } 
                return longestWordTiles;
            }
            
            // Easy Level
            else if (this.difficultyLevel == 1){
                int shortestWordPoints = Integer.MAX_VALUE;
                String shortestWord = ""; 
                ArrayList<Tile> shortestWordTiles = new ArrayList<>();
                for (Map.Entry<String, ArrayList<Tile>> entry : wordToTilesMap.entrySet()) {
                    int sum = 0; ArrayList<Integer> listOfSum = new ArrayList<>();
                    String word = entry.getKey();
                    ArrayList<Tile> tiles = entry.getValue();
                    for (Tile tile : tiles){
                        int letterValue = tile.getLetterValue();
                        sum = sum + letterValue;    
                    }
                    listOfSum.add(sum);
                    if (sum < shortestWordPoints) {
                        shortestWordPoints = sum;
                     //   longestWordLength = word.length();
                        shortestWordTiles = tiles;
                    }         
                 //   System.out.println ("Word is : " + word + "  sum : " + sum);     // print for test
                }
                
                return shortestWordTiles;
            }
            
            // Medium Level
            else if (this.difficultyLevel == 2){
                Map<String, Integer> wordsWithSum = new LinkedHashMap();
                
                for (Map.Entry<String, ArrayList<Tile>> entry : wordToTilesMap.entrySet()) {
                    int sum=0;
                    String word = entry.getKey();
                    ArrayList<Tile> tiles = entry.getValue();
                    for (Tile tile : tiles){
                        int letterValue = tile.getLetterValue();
                        sum = sum + letterValue;    
                    }
                    wordsWithSum.put(word, sum);
               //     System.out.println("word : "+ word + "sum : " + sum);          // print for test
                }
                
               // Step 1: Collect all sums into a list
               List<Integer> sums = new ArrayList<>(wordsWithSum.values());
               
               // Step 2: Remove duplicates
               List<Integer> distinctSums = sums.stream().distinct().collect(Collectors.toList());
               
               // Step 3: Sort the list of distinct sums
               Collections.sort(distinctSums);
               
               
               // Step 4: Find the middle sum from the sorted list of distinct sums
               int middleIndex = distinctSums.size() / 2;
               int middleSum;
               if (distinctSums.size() % 2 == 0) {
                   middleSum = (distinctSums.get(middleIndex - 1) + distinctSums.get(middleIndex)) / 2;
               } 
               else {
                   middleSum = distinctSums.get(middleIndex);
               }
               
               // Step 5: Find the word(s) that have this middle sum
               List<String> mediumWords = new ArrayList<>();
               for (Map.Entry<String, Integer> entry : wordsWithSum.entrySet()) {
                   if (entry.getValue().equals(middleSum)) {
                       mediumWords.add(entry.getKey());
                   }
               }
               
               // Assuming you want to return tiles for the first word with the middle sum if there are multiple
               if (!mediumWords.isEmpty()) {
                   String mediumWord = mediumWords.get(0);
                   ArrayList<Tile> mediumWordTiles = wordToTilesMap.get(mediumWord);
                   /*  // print for test
                   System.out.println("Word with the middle sum is: " + mediumWord);   // print for test
                   
                   for (Tile tile : mediumWordTiles) {
                       System.out.println("Tile: " + tile.getLetter() + " ID: " + tile.getTileID());
                   }*/
                   
                   return mediumWordTiles;
               }
               
               return new ArrayList<>();
            }
            
            
            else
               throw new UnsupportedOperationException("You choosed invalid difficulty level");      
        }
    
    
    private void searchFisrtWord(String currentWord, ArrayList<Tile> remainingTiles, Map<String, ArrayList<Tile>> wordToTilesMap, ArrayList<Tile> usedTiles) {
            
            if (!currentWord.isEmpty() && dictionary.checkValidity(currentWord)) {
                // Clone the usedTiles list to freeze its state at this point
                wordToTilesMap.put(currentWord, new ArrayList<>(usedTiles));
            }
            
            if (!dictionary.startsWith(currentWord)) { // Pruning
                return;
            }
            
            for (int i = 0; i < remainingTiles.size(); i++) {
                Tile tile = remainingTiles.get(i);
                String newWord = currentWord + tile.getLetter();
                ArrayList<Tile> newRemainingTiles = new ArrayList<>(remainingTiles);
                newRemainingTiles.remove(i);
                
                ArrayList<Tile> newUsedTiles = new ArrayList<>(usedTiles);
                newUsedTiles.add(tile); // Add the used tile to the list
                
                
                searchFisrtWord(newWord.toLowerCase(), newRemainingTiles, wordToTilesMap, newUsedTiles);
            }
        }
}
