package adventDays;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

//Day thirteen of Advert of Coding
//http://adventofcode.com/2017/day/13
public class DayThirteen {
    private static String inputLocation = "src/inputs/DayThirteenInput";

    private class FireWall {
        private Map<Integer, Integer> scannerLocations;
        private Integer scannerDepth;

        FireWall(Map<Integer, Integer> scannerLocations, Integer scannerDepth) {
            this.scannerLocations = scannerLocations;
            this.scannerDepth = scannerDepth;
        }

        Map<Integer, Integer> getScannerLocations() { return scannerLocations; }
        Integer getScannerDepth() { return scannerDepth; }
    }

    private FireWall mapStringtoIntegerMap(String inputLocation, String delimiter) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxDepth = Integer.MIN_VALUE;

        BufferedReader bufRdr = null;
        try {
            bufRdr = new BufferedReader(new FileReader(inputLocation));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;

        try {
            while ((line = bufRdr.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, delimiter);
                int counter = 0;
                int mainInt = 0;

                while (st.hasMoreTokens()) {
                    switch (counter) {
                        case 0:
                            mainInt = Integer.parseInt(st.nextToken().replace(":", ""));
                            if (mainInt > maxDepth) maxDepth = mainInt;
                            break;
                        case 1:
                            map.put(mainInt, Integer.parseInt(st.nextToken()) - 1);
                            break;
                    }
                    counter++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new FireWall(map, maxDepth);
    }


    private int adventChallengeOne(FireWall input) {
        Map<Integer, Integer> fireWallLocations = input.getScannerLocations();
        int severity = 0;

        for (int i = 0; i <= input.getScannerDepth(); i++) {
            int currentDepthsRange = fireWallLocations.getOrDefault(i, -1);
            int currentLocation;

            if (currentDepthsRange == -1) continue;
            else if (i / currentDepthsRange % 2 == 0) currentLocation = i % currentDepthsRange;
            else currentLocation = currentDepthsRange - (i % currentDepthsRange);

            if (currentLocation == 0) severity += (currentDepthsRange + 1) * i;
        }
        return severity;
    }

    private int adventChallengeTwo(FireWall input) {
        Map<Integer, Integer> fireWallLocations = input.getScannerLocations();
        int offset = 0;

        while (true) {
            boolean found = false;
            for (int i = 0; i <= input.getScannerDepth(); i++) {
                int currentDepthsRange = fireWallLocations.getOrDefault(i, -1);
                int currentLocation;

                if (currentDepthsRange == -1) continue;
                else if ((i + offset) / currentDepthsRange % 2 == 0) currentLocation = (i + offset) % currentDepthsRange;
                else currentLocation = currentDepthsRange - ((i + offset) % currentDepthsRange);

                if (currentLocation == 0) {
                    found = true;
                    break;
                }
            }

            if (!found) break;
            offset++;
        }
        return offset;
    }

    public static void main(String[] args) {
        DayThirteen dayThirteen = new DayThirteen();

        FireWall input = dayThirteen.mapStringtoIntegerMap(inputLocation, " ");
        System.out.println("Day thirteen Challenge 1: " + dayThirteen.adventChallengeOne(input));
        System.out.println("Day thirteen Challenge 2: " + dayThirteen.adventChallengeTwo(input));
    }

}
