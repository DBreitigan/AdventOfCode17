package adventDays;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//Day eight of Advert of Coding
//http://adventofcode.com/2017/day/8
public class DayEight {
    private static String inputLocation = "src/inputs/DayEightInput";

    private class Instruction {
        private String registry;
        private String command;
        private int commandValue;
        private String compareRegistry;
        private String compareOperation;
        private int valueToCompare;

        int getCommandValue() { return commandValue; }
        void setCommandValue(int commandValue) { this.commandValue = commandValue; }

        String getCommand() { return command; }
        void setCommand(String command) { this.command = command; }

        String getRegistry() { return registry; }
        void setRegistry(String registry) { this.registry = registry; }

        String getCompareRegistry() { return compareRegistry; }
        void setCompareRegistry(String compareInstruction) { this.compareRegistry = compareInstruction; }

        String getCompareOperation() { return compareOperation; }
        void setCompareOperation(String compareOperation) { this.compareOperation = compareOperation; }

        int getValueToCompare() { return valueToCompare; }
        void setValueToCompare(int valueToCompare) { this.valueToCompare = valueToCompare; }
    }

    private int adventChallengeOne(List<Instruction> instructionList) {
        Map<String, Integer> registryValues = new HashMap<>();
        for (int i = 0; i < instructionList.size(); i++) {
            Instruction current = instructionList.get(i);
            compareOperation(registryValues, current);
        }

        int max = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : registryValues.entrySet()) {
            if (entry.getValue() > max) max = entry.getValue();
        }

        return max;
    }

    private int adventChallengeTwo(List<Instruction> instructionList){
        int maxEver = Integer.MIN_VALUE;

        Map<String, Integer> registryValues = new HashMap<>();
        for (int i = 0; i < instructionList.size(); i++) {
            Instruction current = instructionList.get(i);
            int value = compareOperation(registryValues, current);
            if(value > maxEver) maxEver = value;
        }
        return maxEver;
    }

    private List<Instruction> mapStringsToInstructionList(String inputLocation, String delimiter) {
        List<Instruction> instructionList = new ArrayList<>();

        BufferedReader bufRdr = null;
        try {
            bufRdr = new BufferedReader(new FileReader(inputLocation));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;

        try {
            while ((line = bufRdr.readLine()) != null) {
                int counter = 0;

                DayEight.Instruction newInstruction = new Instruction();

                StringTokenizer st = new StringTokenizer(line, delimiter);

                while (st.hasMoreTokens()) {
                    switch (counter) {
                        case 0:
                            newInstruction.setRegistry(st.nextToken());
                            break;
                        case 1:
                            newInstruction.setCommand(st.nextToken());
                            break;
                        case 2:
                            newInstruction.setCommandValue(Integer.parseInt(st.nextToken()));
                            break;
                        case 3:
                            st.nextToken();
                            break;
                        case 4:
                            newInstruction.setCompareRegistry(st.nextToken());
                        case 5:
                            newInstruction.setCompareOperation(st.nextToken());
                        case 6:
                            newInstruction.setValueToCompare(Integer.parseInt(st.nextToken()));
                    }
                    counter++;
                }
                instructionList.add(newInstruction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return instructionList;
    }

    private int compareOperation(Map<String, Integer> registryValues, Instruction current) {
        int value = 0;
        switch (current.getCompareOperation()) {
            case ">":
                if (registryValues.getOrDefault(current.getCompareRegistry(), 0) > current.getValueToCompare()) {
                    value = checkRegistryValue(registryValues, current);
                    registryValues.put(current.getRegistry(),value);
                }
                break;
            case "<":
                if (registryValues.getOrDefault(current.getCompareRegistry(), 0) < current.getValueToCompare()) {
                    value = checkRegistryValue(registryValues, current);
                    registryValues.put(current.getRegistry(), value);
                }
                break;
            case ">=":
                if (registryValues.getOrDefault(current.getCompareRegistry(), 0) >= current.getValueToCompare()) {
                    value = checkRegistryValue(registryValues, current);
                    registryValues.put(current.getRegistry(), value);
                }
                break;
            case "<=":
                if (registryValues.getOrDefault(current.getCompareRegistry(), 0) <= current.getValueToCompare()) {
                    value = checkRegistryValue(registryValues, current);
                    registryValues.put(current.getRegistry(), value);
                }
                break;
            case "==":
                if (registryValues.getOrDefault(current.getCompareRegistry(), 0) == current.getValueToCompare()) {
                    value = checkRegistryValue(registryValues, current);
                    registryValues.put(current.getRegistry(), value);
                }
                break;
            case "!=":
                if (registryValues.getOrDefault(current.getCompareRegistry(), 0) != current.getValueToCompare()) {
                    value = checkRegistryValue(registryValues, current);
                    registryValues.put(current.getRegistry(), value);
                }
                break;
        }
        return value;
    }

    private int checkRegistryValue(Map<String, Integer> registryValues, Instruction current) {
        if (current.getCommand().equals("dec")) return registryValues.getOrDefault(current.getRegistry(), 0) - current.getCommandValue();
        else return registryValues.getOrDefault(current.getRegistry(), 0) + current.getCommandValue();
    }

    public static void main(String[] args) {
        DayEight dayEight = new DayEight();
        List<Instruction> instructionList = dayEight.mapStringsToInstructionList(inputLocation, " ");
        System.out.println("Day eight Challenge 1: " + Integer.toString(dayEight.adventChallengeOne(instructionList)));
        System.out.println("Day eight Challenge 2: " + Integer.toString(dayEight.adventChallengeTwo(instructionList)));
    }

}
