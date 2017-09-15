import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Game {
    private List<Player> players;
    private List<Player> winners;

    public Game() {
        this.players = new ArrayList<>();
        this.winners = new ArrayList<>();
    }

    public void addPlayer(Player player) {
       players.add(player);
    }

    public List<Player> getWinners() {
        return winners;
    }

    public String getResult() {
        findWinners();

        StringBuilder result = new StringBuilder();
        if (winners.size() == 1){
            result.append(String.format("Player %d wins.", winners.get(0).getIndex()));
        } else {
            result.append("Players ");
            for (int i = 0; i < winners.size() - 2; i++) {
                result.append(String.format("%d, ", winners.get(i).getIndex()));

            }
            result.append(String.format("%d and %d draw.",
                    winners.get(winners.size()-2).getIndex(), winners.get(winners.size()-1).getIndex()));
        }
        return result.toString();
    }

    private void findWinners() {
        players.sort(Comparator.comparing(Player::getHand));
        winners.add(players.get(players.size() - 1));
        for (int i = players.size() - 2; i >= 0; i--) {
            if (winners.get(0).getHand().compareTo(players.get(i).getHand()) == 0) {
                winners.add(players.get(i));
            }
        }
        winners.sort(Comparator.comparing(Player::getIndex));
    }
}
