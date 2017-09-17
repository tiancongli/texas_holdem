import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The class of the entry of the poker game.
 * Parse the command line input, start the game and print the game result.
 *
 * @author Tiancong Li
 */
public class Poker {

    /**
     * The main entry of the program.
     */
    public static void main(String[] args) {
        // Check the number of args.
        if (args.length == 0 | args.length % 5 != 0) {
            System.out.println("Error:_wrong_number_of_arguments;" +
                    "_must_be_a_multiple_of_5");
            System.exit(1);
        }

        // init the list of rankSuit as input
        List<String> rankSuits = new ArrayList<>(Arrays.asList(args));

        // create the game.
        Game game = new Game(rankSuits);

        // print all players.
        for (int i = 0; i < game.getPlayersNumber(); i++) {
            System.out.println(game.getPlayer(i));
        }

        // print the game result.
        System.out.println(game.getResult());

        /*
        Game game = new Game();
        Hand hand = new Hand();
        Player player;

        for (int i = 0; i < args.length; i++) {
            // every time a new 5 cards occurs, init a new hand for it.
            if (i % Hand.CARDS_NUM == 0) hand = new Hand();

            // init a card and add it to the hand.
            hand.addCard(new Card(args[i]));

            // every time when a hand gets 5 cards, init a player for it and
            // add the player to the game.
            if (i % Hand.CARDS_NUM == Hand.CARDS_NUM - 1) {
                player = new Player(i/Hand.CARDS_NUM + 1, hand);
                game.addPlayer(player);

                // print the player's hand's description(classification).
                System.out.println(player);
            }
        }

        // print the game result.
        System.out.println(game.getResult());

        /*Hand hand1 = new Hand();
        Hand hand2 = new Hand();
        Hand hand3 = new Hand();

        String[] args1 = {"AH", "AS", "2D", "4H", "2H"};
        String[] args2 = {"AH", "QS", "2D", "4H", "2H"};
        String[] args3 = {"AH", "KS", "2D", "4H", "2H"};

        int i = 0;
        for (String s : args1) {
            hand1.addCard(new Card(s));
            i++;
        }

        i = 0;
        for (String s : args2) {
            hand2.addCard(new Card(s));
            i++;
        }

        i = 0;
        for (String s : args3) {
            hand3.addCard(new Card(s));
            i++;
        }
        System.out.println(hand1.getClassification());
        System.out.println(hand2.getClassification());
        System.out.println(hand3.getClassification());
        System.out.println("****************************");


        Player player1 = new Player(1, hand1);
        Player player2 = new Player(2, hand2);
        Player player3 = new Player(3, hand3);

        Game game = new Game();
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        System.out.println(game.getResult());


        /*Game game = new Game(players);
        game.findWinners();
        List<Player> winners = game.getWinners();
        for (Player winner : winners)
            System.out.println(winner);
        System.out.println(game.getResult());
        */


        /*Hand[] hands = new Hand[3];
        hands[0] = hand1;
        hands[1] = hand2;
        hands[2] = hand3;
        Arrays.sort(hands);

        for (Hand hand : hands) {
            System.out.println(hand.getClassification());
        }*/

    }
}
