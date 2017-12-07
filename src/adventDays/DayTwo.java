package adventDays;

import helpers.MappingHelper;

//Day two of Advert of Coding
//http://adventofcode.com/2017/day/2
public class DayTwo {
    private static String inputLocation = "src/inputs/DayTwoInput";

    //Add all the differences of the minimum and maximum value of each row
    public int adventChallengeOne(int[][] input) {
        int total = 0;
        for (int i = 0; i < input.length; i++) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < input.length; j++) {
                if (input[i][j] > max) max = input[i][j];
                if (input[i][j] < min) min = input[i][j];
            }
            total += (max - min);
        }

        return total;
    }

    //find the two evenly divisible numbers in each row, divide them and add them all together
    public int adventChallengeTwo(int[][] input) {
        int total = 0;

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                for(int k = j + 1; k < input.length; k++){
                    if(input[i][j] % input[i][k] == 0) total += (input[i][j] / input[i][k]);
                    if(input[i][k] % input[i][j] == 0) total += (input[i][k] / input[i][j]);
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        MappingHelper mapper = new MappingHelper();

        int[][] inputArray = mapper.stringTo2DIntArray(inputLocation, "\t");

        DayTwo dayTwo = new DayTwo();
        System.out.println("Day two Challenge 1: " + dayTwo.adventChallengeOne(inputArray));
        System.out.println("Day two Challenge 2: " + dayTwo.adventChallengeTwo(inputArray));
    }
}
