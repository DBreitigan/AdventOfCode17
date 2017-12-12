package adventDays;

import helpers.MappingHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Day eleven of Advert of Coding
//http://adventofcode.com/2017/day/11
public class DayEleven {
    private static String inputLocation = "src/inputs/DayElevenInput";

    private class Coordinates {
        private int x;
        private int y;
        private int z;

        public int getZ() { return z; }
        public void setZ(int z) { this.z = z; }
        public int getX() { return x; }
        public void setX(int x) { this.x = x; }
        public int getY() { return y; }
        public void setY(int y) { this.y = y; }
    }

    private int adventChallengeOne(List<String> directions) {
        Coordinates coords = new Coordinates();

        for (int i = 0; i < directions.size(); i++) {
            coords = getNewCoordinates(coords, directions.get(i));
        }

        return ((Math.abs(coords.getX()) + Math.abs(coords.getY()) + Math.abs(coords.getZ())) / 2);
    }

    private int adventChallengeTwo(List<String> directions) {
        int max = Integer.MIN_VALUE;
        Coordinates coords = new Coordinates();

        for (int i = 0; i < directions.size(); i++) {
            coords = getNewCoordinates(coords, directions.get(i));

            double currentDistance = ((Math.abs(coords.getX()) + Math.abs(coords.getY()) + Math.abs(coords.getZ())) / 2);
            if (currentDistance > max) max = (int) currentDistance;
        }

        return max;
    }

    private Coordinates getNewCoordinates(Coordinates coords, String direction) {
        switch (direction) {
            case "n":
                coords.setY(coords.getY() + 1);
                coords.setZ(coords.getZ() - 1);
                break;
            case "ne":
                coords.setZ(coords.getZ() - 1);
                coords.setX(coords.getX() + 1);
                break;
            case "se":
                coords.setY(coords.getY() - 1);
                coords.setX(coords.getX() + 1);
                break;
            case "s":
                coords.setY(coords.getY() - 1);
                coords.setZ(coords.getZ() + 1);
                break;
            case "nw":
                coords.setY(coords.getY() + 1);
                coords.setX(coords.getX() - 1);
                break;
            case "sw":
                coords.setZ(coords.getZ() + 1);
                coords.setX(coords.getX() - 1);
                break;
        }
        return coords;
    }

    public static void main(String[] args) {
        MappingHelper mapper = new MappingHelper();
        DayEleven dayEleven = new DayEleven();

        List<String> input = mapper.stringToStringList(inputLocation, ",");
        System.out.println("Day eleven Challenge 1: " + dayEleven.adventChallengeOne(input));
        System.out.println("Day eleven Challenge 2: " + dayEleven.adventChallengeTwo(input));
    }

}
