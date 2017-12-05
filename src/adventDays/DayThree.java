package adventDays;

import java.util.HashMap;
import java.util.Map;

//Day three of Advert of Coding
//http://adventofcode.com/2017/day/3
public class DayThree {
    private final int input = 312051;

    public int adventChallengeOne() {
        int distance;
        boolean Increase = true;
        int counter = 0;
        double layer;
        double lastLayer = 1;
        for (int i = 2; i <= input; i++) {
            layer = Math.ceil((Math.sqrt(i) - 1) / 2);

            if (lastLayer != layer) {
                lastLayer = layer;
                counter = (int) layer - 1;
                Increase = false;
            }

            distance = (int) layer + counter;

            counter = Increase ? counter + 1 : counter - 1;

            if (counter >= layer) Increase = false;
            if (counter == 0) Increase = true;

            if (i == input) return distance;
        }
        return 0;
    }

    public int adventChallengeTwo() {
        int value = 0;
        int x = 0, y = 0;
        String direction = "RIGHT";
        Map<String, Integer> matrix = new HashMap<>();
        matrix.put("[0,0]", 1);

        while (value < input) {
            switch (direction) {
                case "UP":
                    y++;
                    if (matrix.getOrDefault(generateLocation(x - 1, y), 0) == 0) direction = "LEFT";
                    break;
                case "LEFT":
                    x--;
                    if (matrix.getOrDefault(generateLocation(x, y - 1), 0) == 0) direction = "DOWN";
                    break;
                case "DOWN":
                    y--;
                    if (matrix.getOrDefault(generateLocation(x + 1, y), 0) == 0) direction = "RIGHT";
                    break;
                case "RIGHT":
                    x++;
                    if (matrix.getOrDefault(generateLocation(x, y + 1), 0) == 0) direction = "UP";

                    break;
            }
            value = generateValue(matrix, x, y);
            matrix.put(generateLocation(x, y), value);
        }

        return value;
    }

    //Generate the value by adding all adjacent cells in the matrix
    private int generateValue(Map<String, Integer> matrix, int x, int y) {
        int value = 0;

        value += matrix.getOrDefault(generateLocation(x + 1, y), 0);
        value += matrix.getOrDefault(generateLocation(x + 1, y + 1), 0);
        value += matrix.getOrDefault(generateLocation(x, y + 1), 0);
        value += matrix.getOrDefault(generateLocation(x - 1, y + 1), 0);
        value += matrix.getOrDefault(generateLocation(x - 1, y), 0);
        value += matrix.getOrDefault(generateLocation(x + 1, y - 1), 0);
        value += matrix.getOrDefault(generateLocation(x, y - 1), 0);
        value += matrix.getOrDefault(generateLocation(x - 1, y - 1), 0);

        return value;
    }

    //Make the coordinates a string for the map
    private String generateLocation(int x, int y) {
        return "[" + x + "," + y + "]";
    }

    public static void main(String[] args) {
        DayThree dayThree = new DayThree();
        System.out.println("Day three Challenge 1: " + dayThree.adventChallengeOne());
        System.out.println("Day three Challenge 2: " + dayThree.adventChallengeTwo());
    }
}
