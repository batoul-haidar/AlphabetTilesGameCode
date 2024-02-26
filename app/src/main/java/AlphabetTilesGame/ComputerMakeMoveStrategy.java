/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AlphabetTilesGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author YAROU
 */
public class ComputerMakeMoveStrategy implements MakeMoveStrategy {
    
    private ComputerMoveState currentState;
    
    public ComputerMakeMoveStrategy (ComputerMoveState state){
        this.currentState = state;
    }
    
    
    
    @Override
    public Map<Position, Tile> makeMove(ArrayList<Tile> tiles, ArrayList<Position> positions) {
        // Delegate the makeMove call to the current state
        return currentState.makeMove(tiles, positions);
    }   
    
    
   // public abstract Map<Position, Tile> makeMove(ArrayList<Tile> tiles, ArrayList<Position> position);
}
