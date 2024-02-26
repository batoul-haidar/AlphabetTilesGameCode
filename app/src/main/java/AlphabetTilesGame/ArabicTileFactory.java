/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AlphabetTilesGame;

/**
 *
 * @author YAROU
 */
public class ArabicTileFactory implements TileFactory {
    
    @Override
    public Tile createTile(char letter, int tileID) {
        int value = getLetterValue(letter); // Use your existing method to determine the letter value
        return new Tile(letter, value, tileID);
    }

    private int getLetterValue(char letter) {
        
        return switch (letter) {
        case 'أ', 'ب', 'ت', 'ث', 'ج' -> 1;  
        case 'ح', 'خ', 'د', 'ذ', 'ر' -> 2;  
        case 'ز', 'س', 'ش', 'ص', 'ض' -> 3;  
        case 'ط', 'ظ', 'ع', 'غ' -> 4;
        case 'ف', 'ق' -> 5;
        case 'ك', 'ل' -> 6;
        case 'م', 'ن' -> 7;
        case 'ه', 'و' -> 8;
        case 'ي' -> 10;
        default -> 0;  // For any non-Arabic characters or special cases
    };
    }
}
