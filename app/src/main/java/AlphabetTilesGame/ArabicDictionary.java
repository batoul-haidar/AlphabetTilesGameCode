/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AlphabetTilesGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class ArabicDictionary implements IDictionary {
    
    private static ArabicDictionary instance;
    private boolean isValidWord;
 //   private String word;
    private Set<String> validWords;
    private TrieNode root;

    // Private constructor so no instances can be made outside this class
    private ArabicDictionary() {
        validWords = new HashSet<>();
        root = new TrieNode();
        loadWordList("C:\\Users\\YAROU\\Documents\\NetBeansProjects\\AlphabetTilesGame\\extracted_words.txt");
    }
    
     // Static method to get the single instance of the class
    public static ArabicDictionary getInstance() {
        if (instance == null) {
            instance = new ArabicDictionary();
        }
        return instance;
    }

    
    @Override
    public void loadWordList(String filePath) {
        
    }
    
    @Override
    public void addWord(String word) {
        
    }

    @Override
    public boolean isWord(String word) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean startsWith(String prefix) {
        throw new UnsupportedOperationException();
    }
    
    
    @Override
    public boolean checkValidity(String word) {
        throw new UnsupportedOperationException();
	}
 
    @Override
    public boolean checkValidity(ArrayList<String> wordList) {  
        throw new UnsupportedOperationException();
    }

 
    class TrieNode {
    private TrieNode[] children = new TrieNode[28]; // Assuming only lowercase English letters
    private boolean isEndOfWord;

    public boolean containsKey(char ch) {
        if (ch < 'ا' || ch > 'ي') {
        return false; // Return false or handle the character appropriately
    }
    return children[ch - 'ي'] != null;
    }

    public TrieNode get(char ch) {
        return children[ch - 'ا'];
    }

    public void put(char ch, TrieNode node) {
        if (ch < 'ا' || ch > 'ي') {
        return; // Ignore or handle the character appropriately
    }
    children[ch - 'ا'] = node;
    }

    public void setEndOfWord() {
        isEndOfWord = true;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }
}
    
    
}
