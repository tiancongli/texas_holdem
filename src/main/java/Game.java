import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The implementation of Game.
 *
 * @author Tiancong Li
 */
public class Game {
    /**
     * A list stores the players.
     */
    private List<Player> players;

    /**
     * A list stores the winners.
     */
    private List<Player> winners;

    /**
     * Constructs the game and init the players and winners.
     */
    public Game() {
        players = new ArrayList<>();
        winners = new ArrayList<>();
    }

    /**
     * Add a player to the players
     */
    public void addPlayer(Player player) {
       players.add(player);
    }

    /**
     * Find the winners of the game.
     */
    private void findWinners() {
        // sort the players by hands firstly, see the hand.compareTo.
        players.sort(Comparator.comparing(Player::getHand));

        // add the last player in the list as the first winner.
        winners.add(players.get(players.size() - 1));

        for (int i = players.size() - 2; i >= 0; i--) {
            if (winners.get(0).getHand().compareTo(players.get(i).getHand())
                    == 0) {
                // add other winners if they have same hand.
                winners.add(players.get(i));
            }
        }

        // sort the winner by the player index.
        winners.sort(Comparator.comparing(Player::getIndex));
    }

    /**
     * Get the game result.
     */
    public String getResult() {
        // find the winners firstly.
        findWinners();

        StringBuilder result = new StringBuilder();

        // get the result based on the number of winners.
        if (winners.size() == 1){
            result.append(String.format("Player %d wins.", winners.get(0)
                    .getIndex()));
        } else {
            result.append("Players ");
            for (int i = 0; i < winners.size() - 2; i++) {
                result.append(String.format("%d, ", winners.get(i).getIndex()));

            }
            result.append(String.format("%d and %d draw.",
                    winners.get(winners.size()-2).getIndex(), winners.get
                            (winners.size()-1).getIndex()));
        }
        return result.toString();
    }

}
