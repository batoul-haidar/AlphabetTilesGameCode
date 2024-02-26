/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package AlphabetTilesGame;

public class App {

   
    public String getGreeting() {
        return "Welcme to Alphabet Tiles Game "
                + "type 'start' to start the game"
                + "";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        
        GameFacade gameFacade = new GameFacade();

        gameFacade.startGame();

        String currentPlayer = gameFacade.playFirstRound();
        gameFacade.playNormalRound(currentPlayer);

    }
}