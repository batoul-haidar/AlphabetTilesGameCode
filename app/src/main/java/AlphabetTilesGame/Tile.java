package AlphabetTilesGame;


public class Tile {

	private char letter;
	private int letterValue;
        private int tileID;
        
        public Tile (char letter, int value, int tileID){
            this.letter = letter;
            this.letterValue = value;
            this.tileID = tileID;
        }
        
        public void setTile(char letter, int value, int tileID){
            this.letter = letter;
            this.letterValue = value;
            this.tileID = tileID;
        }
        
        public char getLetter (){
            return this.letter;
        }
        
        public int getLetterValue(){   
            return this.letterValue;
        }
       
        
        
        public int getTileID(){   
            return this.tileID;
        }
        
        
        /*
        private int getLetterValue(char letter) {
            switch (letter) {
                case 'A': case 'E': case 'I': case 'O': case 'U': case 'L': case 'N': case 'S': case 'T': case 'R':
                    return 1;
                case 'D': case 'G':
                    return 2;
                case 'B': case 'C': case 'M': case 'P':
                    return 3;
                case 'F': case 'H': case 'V': case 'W': case 'Y':
                    return 4;
                case 'K':
                    return 5;
                case 'J': case 'X':
                    return 8;
                case 'Q': case 'Z':
                    return 10;
                default: // For empty tiles or any other characters
                    return 0;
    }
         
        } */

}