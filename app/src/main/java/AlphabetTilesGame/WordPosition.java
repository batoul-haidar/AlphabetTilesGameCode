/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AlphabetTilesGame;

/**
 *
 * @author YAROU
 */
public class WordPosition {
    
    private String word;
    private int commonPoint;
    private int startPoint;
    private int endPoint;

    public WordPosition(String word, int commonPoint, int startPoint, int endPoint) {
        this.word = word;
        this.commonPoint = commonPoint;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    // Getters
    public String getWord() {
        return word;
    }

    public int getCommon() {
        return commonPoint;
    }

    public int getStart() {
        return startPoint;
    }

    public int getEnd() {
        return endPoint;
    }

  
    
}
