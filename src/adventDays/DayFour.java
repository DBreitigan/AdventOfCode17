package adventDays;

import helpers.MappingHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

//Day four of Advert of Coding
//http://adventofcode.com/2017/day/4
public class DayFour {
    private static String inputLocation = "src/inputs/DayFourInput";

    private int adventChallengeOne(Map<Integer, ArrayList> input) {
        int total = 0;

        for (int i = 0; i < input.size(); i++) {
            boolean duplicate = false;
            ArrayList stringRow = input.get(i);
            for (int j = 0; j < stringRow.size(); j++) {
                String currentString = stringRow.get(j).toString();
                stringRow.remove(j);
                if (stringRow.contains(currentString)) {
                    duplicate = true;
                    break;
                }
                stringRow.add(j, currentString);
            }
            if (!duplicate) total++;
        }

        return total;
    }

    private int adventChallengeTwo(Map<Integer, ArrayList> input) {
        int total = 0;

        for (int i = 0; i < input.size(); i++) {
            boolean isAnagram = false;
            ArrayList stringRow = input.get(i);
            for (int j = 0; j < stringRow.size(); j++) {
                char[] firstCharArray = stringRow.get(j).toString().toCharArray();
                for (int k = j + 1; k < stringRow.size(); k++) {
                    char[] secondCharArray = stringRow.get(k).toString().toCharArray();
                    if (firstCharArray.length == secondCharArray.length) {
                        Arrays.sort(firstCharArray);
                        Arrays.sort(secondCharArray);
                        if (Arrays.equals(firstCharArray, secondCharArray)) {
                            isAnagram = true;
                            break;
                        }
                    }
                }
            }
            if (!isAnagram) total++;
        }
        return total;
    }

    public static void main(String[] args) {
        MappingHelper mapper = new MappingHelper();
        DayFour dayFour = new DayFour();

        Map<Integer, ArrayList> inputMap = mapper.stringTo2DStringMap(inputLocation, " ");
        System.out.println("Day four Challenge 1: " + dayFour.adventChallengeOne(inputMap));
        System.out.println("Day four Challenge 2: " + dayFour.adventChallengeTwo(inputMap));
    }
}
