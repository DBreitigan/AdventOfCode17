package adventDays;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//Day four of Advert of Coding
//http://adventofcode.com/2017/day/4
public class DayFour {
    private static String inputLocation = "src/inputs/DayFourInput";

    private Map<Integer, ArrayList> stringTo2DStringMap(String fileLocation) {
        Map<Integer, ArrayList> inputMap = new HashMap<>();

        BufferedReader bufRdr = null;
        try {
            bufRdr = new BufferedReader(new FileReader(fileLocation));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        int row = 0;
        try {
            while ((line = bufRdr.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, " ");
                ArrayList stringRow = new ArrayList();
                while (st.hasMoreTokens()) {
                    stringRow.add(st.nextToken());
                }

                inputMap.put(row, stringRow);
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputMap;
    }

    private int adventChallengeOne() {
        int total = 0;
        Map<Integer, ArrayList> input = stringTo2DStringMap(inputLocation);

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

    private int adventChallengeTwo() {
        int total = 0;
        Map<Integer, ArrayList> input = stringTo2DStringMap(inputLocation);

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
        DayFour dayFour = new DayFour();
        System.out.println("Day four Challenge 1: " + dayFour.adventChallengeOne());
        System.out.println("Day four Challenge 2: " + dayFour.adventChallengeTwo());
    }
}
