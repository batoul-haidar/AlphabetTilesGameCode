package AlphabetTilesGame;

import static java.awt.PageAttributes.MediaType.C;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;



public class Dictionary implements IDictionary {
    
    private static Dictionary instance;
    private boolean isValidWord;
 //   private String word;
    private Set<String> validWords;
    private TrieNode root;

    // Private constructor so no instances can be made outside this class
    private Dictionary() {
        validWords = new HashSet<>();
        root = new TrieNode();
        loadWordList("C:\\Users\\YAROU\\Documents\\NetBeansProjects\\AlphabetTilesGame\\extracted_words.txt");
    }
    
     // Static method to get the single instance of the class
    public static Dictionary getInstance() {
        if (instance == null) {
            instance = new Dictionary();
        }
        return instance;
    }

    
    @Override
    public void loadWordList(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\YAROU\\Documents\\NetBeansProjects\\AlphabetTilesGame\\extracted_words.txt"))) {
            String word;
            while ((word = reader.readLine()) != null) {
                validWords.add(word.toLowerCase());
                addWord(word.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }
        node.setEndOfWord();
    }

    @Override
    public boolean isWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                return false;
            }
            node = node.get(currentChar);
        }
        return node.isEndOfWord();
    }

    @Override
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char currentChar = prefix.charAt(i);
            if (!node.containsKey(currentChar)) {
                return false;
            }
            node = node.get(currentChar);
        }
        return true;
    }
    
    
    @Override
    public boolean checkValidity(String word) {
        for(String line: validWords){
            if(word.toLowerCase().equals(line)){
                return true;
            }
        }        
        return false;
		//return validWords.contains(word.toLowerCase());
	}
 
    
    public boolean checkValidity(ArrayList<String> wordList) {
        
        // Iterate through each word in the wordList
        for (String word : wordList) {
            boolean isValid = false; // Flag to check if the current word is valid
            
            // Check if the current word is in the validWords list
            for (String validWord : validWords) {
                if (word.toLowerCase().equals(validWord.toLowerCase())) {
                    isValid = true; // The word is valid, so set the flag to true
                    break; // No need to check the rest of the validWords for this word
                }
            }
            
            // If the current word was not found in the validWords list, it's invalid
            if (!isValid) {
                return false; // Return false immediately if any word is invalid
            }
        }
        
        // If the method hasn't returned false yet, all words are valid
        return true;
    }

 
    class TrieNode {
    private TrieNode[] children = new TrieNode[26]; // Assuming only lowercase English letters
    private boolean isEndOfWord;

    public boolean containsKey(char ch) {
        if (ch < 'a' || ch > 'z') {
        return false; // Return false or handle the character appropriately
    }
    return children[ch - 'a'] != null;
    }

    public TrieNode get(char ch) {
        return children[ch - 'a'];
    }

    public void put(char ch, TrieNode node) {
        if (ch < 'a' || ch > 'z') {
        return; // Ignore or handle the character appropriately
    }
    children[ch - 'a'] = node;
    }

    public void setEndOfWord() {
        isEndOfWord = true;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }
}
    
    
    // <editor-fold defaultstate="collapsed" desc="Comments">
        /*
    public boolean checkValidity(ArrayList<String> wordList) {
        for (String word: wordList){
           for(String line: validWords){
            if(word.toLowerCase().equals(line)){
                return true;
            }
        } 
        }
                
        return false;
		//return validWords.contains(word.toLowerCase());
	}*/
    //</editor-fold>


}