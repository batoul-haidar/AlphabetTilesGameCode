/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AlphabetTilesGame;

/**
 *
 * @author YAROU
 */
public class EnglishTileFactory implements TileFactory {
    
    @Override
    public Tile createTile(char letter, int tileID) {
        int value = getLetterValue(letter); // Use your existing method to determine the letter value
        return new Tile(letter, value, tileID);
    }

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
    }
    
}
