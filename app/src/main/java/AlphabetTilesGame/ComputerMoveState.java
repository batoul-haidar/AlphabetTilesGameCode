/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package AlphabetTilesGame;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author YAROU
 */
public interface ComputerMoveState {
    Map<Position, Tile> makeMove(ArrayList<Tile> tiles, ArrayList<Position> positions);
    void setDifficultyLevel (Integer difficultyLevel);
}
