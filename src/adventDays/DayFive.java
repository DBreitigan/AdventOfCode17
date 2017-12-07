package adventDays;

import helpers.MappingHelper;
import java.util.List;

public class DayFive {
    private static String inputLocation = "src/inputs/DayFiveInput";

    private int adventChallengeOne(List<Integer> input) {
        int counter = 0;
        int location = 0;

        while (location >= 0 && location < input.size()) {
            counter++;

            int currentLocation = input.get(location);
            input.set(location, currentLocation + 1);
            location = location + currentLocation;
        }

        return counter;
    }

    private int adventChallengeTwo(List<Integer> input) {
        int counter = 0;
        int location = 0;

        while (location >= 0 && location < input.size()) {
            counter++;
            int currentLocation = input.get(location);

            if (currentLocation > 2) input.set(location, currentLocation - 1);
            else input.set(location, currentLocation + 1);

            location = location + currentLocation;
        }

        return counter;
    }

    public static void main(String[] args) {
        MappingHelper mapper = new MappingHelper();
        DayFive dayFive = new DayFive();

        List<Integer> inputList = mapper.stringToIntegerList(inputLocation, " ");

        System.out.println("Day five Challenge 1: " + dayFive.adventChallengeOne(inputList));
        System.out.println("Day five Challenge 2: " + dayFive.adventChallengeTwo(inputList));
    }

}
