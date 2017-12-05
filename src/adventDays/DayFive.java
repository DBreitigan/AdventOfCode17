package adventDays;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DayFive {
    private static String inputLocation = "src/inputs/DayFiveInput";

    private List<Integer> stringToIntegerList(String fileLocation) {
        List<Integer> integerList = new ArrayList<>();

        BufferedReader bufRdr = null;
        try {
            bufRdr = new BufferedReader(new FileReader(fileLocation));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        try {
            while ((line = bufRdr.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, " ");
                integerList.add(Integer.parseInt(st.nextToken()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return integerList;
    }

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
        DayFive dayFive = new DayFive();

        List<Integer> inputList = dayFive.stringToIntegerList(inputLocation);

        System.out.println("Day five Challenge 1: " + dayFive.adventChallengeOne(inputList));
        System.out.println("Day five Challenge 2: " + dayFive.adventChallengeTwo(inputList));
    }

}
