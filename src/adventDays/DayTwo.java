package adventDays;

import java.io.*;
import java.util.StringTokenizer;

//Day two of Advert of Coding
//http://adventofcode.com/2017/day/2
public class DayTwo {
    private static String inputLocation = "src/inputs/DayTwoInput";

    private int[][] stringTo2DIntArray() {
        int[][] inputArray = new int[16][16];
        BufferedReader bufRdr = null;
        try {
            bufRdr = new BufferedReader(new FileReader(inputLocation));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        int row = 0, col = 0;
        try {
            while ((line = bufRdr.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, "\t");
                while (st.hasMoreTokens()) {
                    inputArray[row][col] = Integer.parseInt(st.nextToken());
                    col++;
                }
                col = 0;
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputArray;
    }

    //Add all the differences of the minimum and maximum value of each row
    public int adventChallengeOne() {
        int[][] input = stringTo2DIntArray();
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
    public int adventChallengeTwo() {
        int[][] input = stringTo2DIntArray();
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
        DayTwo dayTwo = new DayTwo();
        System.out.println("Day two Challenge 1: " + dayTwo.adventChallengeOne());
        System.out.println("Day two Challenge 2: " + dayTwo.adventChallengeTwo());
    }
}
