package adventDays;

import helpers.MappingHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Day ten of Advert of Coding
//http://adventofcode.com/2017/day/10
public class DayTen {
    private static String inputLocation = "src/inputs/DayTenInput";

    private List<Integer> arrayListInitializer(int size) {
        List<Integer> elements = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            elements.add(i);
        }
        return elements;
    }

    private List<Integer> arrayListASCIIConverter(String input) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            integerList.add((int) input.charAt(i));
        }
        return integerList;
    }

    private int adventChallengeOne(List<Integer> inputLengths, int arraySize) {
        List<Integer> elements = arrayListInitializer(arraySize);
        int total = 0;
        int skipSize = 0;

        for (int i = 0; i < inputLengths.size(); i++) {
            List<Integer> positions = new ArrayList<>();
            List<Integer> subList = new ArrayList<>();
            for (int j = 0; j < inputLengths.get(i); j++) {
                int currentPosition = total % elements.size();
                positions.add(currentPosition);
                subList.add(elements.get(currentPosition));
                total++;
            }

            Collections.reverse(subList);
            for (int j = 0; j < positions.size(); j++) {
                elements.set(positions.get(j), subList.get(j));
            }

            total += skipSize;
            skipSize++;
        }

        return (elements.get(0) * elements.get(1));
    }

    private String adventChallengeTwo(String input, int arraySize) {
        List<Integer> elements = arrayListInitializer(arraySize);
        List<Integer> inputLengths = arrayListASCIIConverter(input);
        inputLengths.addAll(Arrays.asList(17, 31, 73, 47, 23));

        int total = 0;
        int skipSize = 0;

        for (int k = 0; k < 64; k++) {
            for (int i = 0; i < inputLengths.size(); i++) {
                List<Integer> positions = new ArrayList<>();
                List<Integer> subList = new ArrayList<>();
                for (int j = 0; j < inputLengths.get(i); j++) {
                    int currentPosition = total % elements.size();
                    positions.add(currentPosition);
                    subList.add(elements.get(currentPosition));
                    total++;
                }

                Collections.reverse(subList);
                for (int j = 0; j < positions.size(); j++) {
                    elements.set(positions.get(j), subList.get(j));
                }

                total += skipSize;
                skipSize++;
            }
        }

        //create Dense Hash from Sparse Hash
        List<Integer> denseHash = new ArrayList<>();
        int offset = 0;
        for (int i = 0; i < 16; i++) {
            int value = elements.get(offset);
            for (int j = 1; j < 16; j++) {
                value = value ^ elements.get(j + offset);
            }
            denseHash.add(value);
            offset += 16;
        }

        //Make Dense Hash into a string of hexidecimal values
        String output = "";
        for (int i = 0; i < denseHash.size(); i++) {
            String hexValue = Integer.toHexString(denseHash.get(i));
            if (hexValue.length() < 2) {
                hexValue = "0" + hexValue;
            }
            output += hexValue;
        }

        return output;
    }

    public static void main(String[] args) throws IOException {
        MappingHelper mapper = new MappingHelper();
        DayTen dayTen = new DayTen();
        List<Integer> input = mapper.stringToIntegerList(inputLocation, ",");
        System.out.println("Day ten Challenge 1: " + Integer.toString(dayTen.adventChallengeOne(input, 256)));
        String inputString = new String(Files.readAllBytes(Paths.get(inputLocation)));
        System.out.println("Day ten Challenge 2: " + dayTen.adventChallengeTwo(inputString, 256));
    }

}
