/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package AlphabetTilesGame;

import java.util.ArrayList;


public interface IDictionary {
    void loadWordList(String filePath);
    void addWord (String word);
    boolean isWord(String word);
    boolean startsWith(String prefix);
    boolean checkValidity(String word);
    boolean checkValidity(ArrayList<String> wordList);
    
    class TrieNode{
        
    }
}
