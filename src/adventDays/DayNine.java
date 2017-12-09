package adventDays;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//Day nine of Advert of Coding
//http://adventofcode.com/2017/day/9
public class DayNine {
    private static String inputLocation = "src/inputs/DayNineInput";

    private int adventChallengeOne(String input) {
        int totalGroups = 0;
        int depth = 1;

        boolean inGarbage = false;
        for (int i = 0; i < input.length(); i++) {
            if (inGarbage) {
                char currentChar = input.charAt(i);
                if (currentChar == '!') i++;
                else if (currentChar == '>') inGarbage = false;
            } else {
                char currentChar = input.charAt(i);
                if (currentChar == '{') {
                    totalGroups += depth;
                    depth++;
                }
                if (currentChar == '}') depth--;
                if (currentChar == '<') inGarbage = true;
            }
        }
        return totalGroups;
    }

    private int adventChallengeTwo(String input) {
        int totalGarbageCharacters = 0;

        boolean inGarbage = false;
        for (int i = 0; i < input.length(); i++) {
            if (inGarbage) {
                char currentChar = input.charAt(i);
                if (currentChar == '!') i++;
                else if (currentChar == '>') inGarbage = false;
                else totalGarbageCharacters++;
            } else {
                if (input.charAt(i) == '<') inGarbage = true;
            }
        }

        return totalGarbageCharacters;
    }

    public static void main(String[] args) throws IOException {
        DayNine dayNine = new DayNine();
        String input = new String(Files.readAllBytes(Paths.get(inputLocation)));
        System.out.println("Day nine Challenge 1: " + Integer.toString(dayNine.adventChallengeOne(input)));
        System.out.println("Day nine Challenge 2: " + Integer.toString(dayNine.adventChallengeTwo(input)));
    }

}
