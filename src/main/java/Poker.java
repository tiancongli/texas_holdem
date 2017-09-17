import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The class of the entry of the poker game.
 * Parse the command line input, start the game and print the game result.
 *
 * @author Tiancong Li
 * @loginId tiancongl
 */
public class Poker {

    /**
     * The main entry of the program.
     */
    public static void main(String[] args) {
        // Check the number of args.
        if (args.length == 0 | args.length % Hand.CARDS_NUM != 0) {
            System.out.println("Error: wrong number of arguments;" +
                    " must be a multiple of 5");
            System.exit(1);
        }

        // init the list of rankSuit as input
        List<String> rankSuits = new ArrayList<>(Arrays.asList(args));

        try {
            // create the game.
            Game game = new Game(rankSuits);

            // print all players.
            for (int i = 0; i < game.getPlayersNumber(); i++) {
                System.out.println(game.getPlayer(i));
            }

            // print the game result.
            if (game.getPlayersNumber() > 1) {
                System.out.println(game.getResult());
            }

        } catch (CardException e) {
            // catch the exception when card name is invalid.
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
