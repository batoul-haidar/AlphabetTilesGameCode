package AlphabetTilesGame;
import java.util.ArrayList;
import java.util.List;

public class Square {

	
	private boolean isOccupied;
	private ArrayList<Position> currentOccupiedSquaresPositions;
	private List<Integer> occupiedSquaresPoints;
	//private Position position = new Position ();
        private Position position;
        private int squareID;
        private char squareValue;
        private String squareType;
        
        
        public void setSquarePosition (int x, int y){
            this.position = new Position (x,y);
//            position.setPosition(x, y);
          //  position.setX(x);
          //  position.setY(y);
        }
        
        public Position getPosition (){
            return this.position;
        }
        
        
        public void setSquare (Position position, Boolean isOccupied, char value ,int squareID, String squareType){
            this.position = position;
            this.isOccupied = isOccupied;
            this.squareValue = value;
            this.squareID = squareID;
            this.squareType = squareType;
        }
        
        
        public boolean getIsSquareisOccupied (){
            return this.isOccupied;
        }
        
        public int getIsSquareID (){
            return this.squareID;
        }
        
        
        public char getSquareValue (){
            return this.squareValue;
        }
        
        public String getSquareType (){
            return this.squareType;
        }



}