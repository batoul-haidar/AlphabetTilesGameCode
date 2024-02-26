/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package AlphabetTilesGame;

/**
 *
 * @author YAROU
 */
public interface DictionaryFactory {
    public static IDictionary getDictionary(String language) {
        if ("Arabic".equalsIgnoreCase(language)) {
            return ArabicDictionary.getInstance();
        } else {
            // Default to English Dictionary
            return Dictionary.getInstance();
        }
    }
}
