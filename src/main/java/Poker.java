import java.util.List;
import java.util.Map;

public class Poker {

    public static void main(String[] args) {

        Hand hand = new Hand();

        if (args.length != 5) {
            System.out.println("wrong input number");
            System.exit(1);
        }

        for (int i = 0; i < 5; i++) {
            hand.setCard(i, new Card(args[i]));
        }

        System.out.println(hand);
        System.out.println(hand.getClassification());



    }
}
