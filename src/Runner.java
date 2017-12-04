import adventDays.DayOne;
import adventDays.DayThree;
import adventDays.DayTwo;

public class Runner {
    public static void main(String[] args) {
        DayOne dayOne = new DayOne();
        System.out.println("Day one Challenge 1: " + Integer.toString(dayOne.adventChallengeOne()));
        System.out.println("Day one Challenge 2: " + Integer.toString(dayOne.adventChallengeTwo()));

        DayTwo dayTwo = new DayTwo();
        System.out.println("Day two Challenge 1: " + dayTwo.adventChallengeOne());
        System.out.println("Day two Challenge 2: " + dayTwo.adventChallengeTwo());

        DayThree dayThree = new DayThree();
        System.out.println("Day three Challenge 1: " + dayThree.adventChallengeOne());
        System.out.println("Day three Challenge 2: " + dayThree.adventChallengeTwo());
    }
}
