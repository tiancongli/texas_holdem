public class PokerTest {
    public static void main(String[] args) {
//        String[] a1 = {"3G"};
//        Poker.main(a1);

//        String[] a2 = {"3G", "3H", "4H", "AH", "HH"};
//        Poker.main(a2);

        String[] a3 = {
                "3D", "3H", "4H", "4S", "AC",  //2 pair
                "3D", "3H", "4H", "5S", "AC",  //1 pair
                "3D", "5H", "4H", "7S", "6C",
                "3s", "3s", "4s", "4S", "As",
                "3D", "3H", "3c", "4S", "4C",
                "3s", "3H", "3H", "4h", "4C",
                "3s", "3H", "3H", "3h", "4C",
                "3s", "3H", "3H", "4h", "4C",

        };

        Poker.main(a3);
    }
}
