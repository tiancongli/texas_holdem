import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Poker {

    public static void main(String[] args) {
//        if (args.length != 5) {
//            System.out.println("wrong input number");
//            System.exit(1);
//        }

//        for (int i = 0; i < 5; i++) {
//            hand.setCard(i, new Card(args[i]));
//        }

        Hand hand1 = new Hand();
        Hand hand2 = new Hand();
        Hand hand3 = new Hand();

        String[] args1 = {"3H", "3S", "4D", "4H", "2H"};
        String[] args2 = {"3H", "3S", "2D", "4H", "2H"};
        String[] args3 = {"AH", "AS", "2D", "4H", "2H"};

        int i = 0;
        for (String s : args1) {
            hand1.setCard(i, new Card(s));
            i++;
        }

        i = 0;
        for (String s : args2) {
            hand2.setCard(i, new Card(s));
            i++;
        }

        i = 0;
        for (String s : args3) {
            hand3.setCard(i, new Card(s));
            i++;
        }
        System.out.println(hand1.getClassification());
        System.out.println(hand2.getClassification());
        System.out.println(hand3.getClassification());
        System.out.println("****************************");


        Hand[] hands = new Hand[3];
        hands[0] = hand1;
        hands[1] = hand2;
        hands[2] = hand3;
        Arrays.sort(hands);

        for (Hand hand : hands) {
            System.out.println(hand.getClassification());
        }

    }
}
