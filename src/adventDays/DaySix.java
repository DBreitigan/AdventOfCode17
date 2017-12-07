package adventDays;

import helpers.MappingHelper;

import java.util.*;

//Day six of Advert of Coding
//http://adventofcode.com/2017/day/6
public class DaySix {
    private static String inputLocation = "src/inputs/DaySixInput";

    private int adventChallengeOne(List<Integer> input) {
        int counter = 0;

        Map<Integer, List<Integer>> previousStates = new HashMap<>();

        boolean foundMatch = false;
        while (!foundMatch) {
            for (int i = 0; i < previousStates.size(); i++) {
                if (Arrays.equals(previousStates.get(i).toArray(), input.toArray())) {
                    foundMatch = true;
                    break;
                }
            }

            List<Integer> newList = new ArrayList<>();
            newList.addAll(input);

            previousStates.put(counter, newList);

            input = reallocateBlocks(input);

            counter++;
        }
        return counter;
    }

    private int adventChallengeTwo(List<Integer> input) {
        int location = 0;
        int counter = 0;

        Map<Integer, List<Integer>> previousStates = new HashMap<>();

        boolean foundMatch = false;
        while (!foundMatch) {
            for (int i = 0; i < previousStates.size(); i++) {
                if (Arrays.equals(previousStates.get(i).toArray(), input.toArray())) {
                    location = counter - i;
                    foundMatch = true;
                    break;
                }
            }

            List<Integer> newList = new ArrayList<>();
            newList.addAll(input);

            previousStates.put(counter, newList);

            input = reallocateBlocks(input);
            counter++;
        }
        return location;
    }

    private int locationOfHighestInt(List<Integer> input) {
        if (input == null || input.size() == 0) return -1;
        int location = 0;
        int highest = Integer.MIN_VALUE;

        for (int i = 0; i < input.size(); i++) {
            if (input.get(i) > highest) {
                highest = input.get(i);
                location = i;
            }
        }

        return location;
    }

    private List<Integer> reallocateBlocks(List<Integer> input) {
        int highestMemoryLocation = locationOfHighestInt(input);
        int memoryAmount = input.get(highestMemoryLocation);
        input.set(highestMemoryLocation, 0);

        int location = highestMemoryLocation;
        while (memoryAmount > 0) {
            if (location == input.size() - 1) location = 0;
            else location++;

            input.set(location, input.get(location) + 1);

            memoryAmount--;
        }
        return input;
    }

    public static void main(String[] args) {
        MappingHelper mapper = new MappingHelper();

        DaySix daySix = new DaySix();
        List<Integer> inputList = mapper.stringToIntegerList(inputLocation, "\t");

        System.out.println("Day six Challenge 1: " + daySix.adventChallengeOne(inputList));
        System.out.println("Day six Challenge 2: " + daySix.adventChallengeTwo(inputList));
    }

}
