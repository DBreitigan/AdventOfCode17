package adventDays;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//Day seven of Advert of Coding
//http://adventofcode.com/2017/day/7
public class DaySeven {
    private static String inputLocation = "src/inputs/DaySevenInput";

    private class ProgramDisk {
        private String programName;
        private int weight;
        private List<String> programs;

        String getProgramName() { return programName; }
        void setProgramName(String programName) { this.programName = programName; }

        int getWeight() { return weight; }
        void setWeight(int weight) { this.weight = weight; }

        List<String> getPrograms() { return programs; }
        void setPrograms(List<String> programs) { this.programs = programs; }
    }

    private List<ProgramDisk> stringToProgramDiskList(String fileLocation, String delimiter) {
        List<ProgramDisk> inputMap = new ArrayList<>();

        BufferedReader bufRdr = null;
        try {
            bufRdr = new BufferedReader(new FileReader(fileLocation));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;

        try {
            while ((line = bufRdr.readLine()) != null) {
                int counter = 0;

                ProgramDisk newDisk = new ProgramDisk();
                List<String> programs = new ArrayList<>();

                StringTokenizer st = new StringTokenizer(line, delimiter);

                while (st.hasMoreTokens()) {
                    switch (counter) {
                        case 0:
                            newDisk.setProgramName(st.nextToken());
                            break;
                        case 1:
                            int weight = Integer.parseInt(st.nextToken().replace("(", "").replace(")", ""));
                            newDisk.setWeight(weight);
                            break;
                        case 2:
                            st.nextToken();
                            break;
                        default:
                            programs.add(st.nextToken().replace(",", ""));
                            break;
                    }
                    counter++;
                }

                newDisk.setPrograms(programs);
                inputMap.add(newDisk);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputMap;
    }

    private String adventChallengeOne(List<ProgramDisk> programDisks) {
        Map<String, Integer> referenceMap = new HashMap<>();


        for (int i = 0; i < programDisks.size(); i++) {
            referenceMap.put(programDisks.get(i).getProgramName(), referenceMap.getOrDefault(programDisks.get(i).getProgramName(), 0) + 1);
            for (int j = 0; j < programDisks.get(i).getPrograms().size(); j++) {
                referenceMap.put(programDisks.get(i).getPrograms().get(j), referenceMap.getOrDefault(programDisks.get(i).getPrograms().get(j), 0) + 1);
            }
        }

        String baseProgram = null;


        for (int i = 0; i < programDisks.size(); i++) {
            if (referenceMap.get(programDisks.get(i).getProgramName()) == 1) {
                baseProgram = programDisks.get(i).getProgramName();
            }
        }

        return baseProgram;
    }

    private int adventChallengeTwo(List<ProgramDisk> programDisks, String baseProgram) {
        Map<String, Integer> StringToWeightMap = new HashMap<>();
        Map<String, List<String>> StringToProgramsMap = new HashMap<>();

        for (int i = 0; i < programDisks.size(); i++) {
            ProgramDisk currentProgram = programDisks.get(i);
            StringToWeightMap.put(currentProgram.getProgramName(), currentProgram.getWeight());
            if (currentProgram.getPrograms().size() > 0)
                StringToProgramsMap.put(currentProgram.getProgramName(), currentProgram.getPrograms());
        }

        return findBadProgam(baseProgram, StringToWeightMap, StringToProgramsMap, baseProgram, 0);
    }

    private int findBadProgam(String baseProgram, Map<String, Integer> stringToWeightMap, Map<String, List<String>> stringToProgramsMap, String Parent, int result) {
        List<String> rootPrograms = stringToProgramsMap.get(baseProgram);

        boolean allEqual = true;
        List<Integer> towerWeights = new ArrayList<>();

        Map<Integer, Integer> findOddStack = new HashMap<>();

        for (int i = 0; i < rootPrograms.size(); i++) {
            towerWeights.add(findTowerWeights(stringToWeightMap, stringToProgramsMap, rootPrograms.get(i)));
            findOddStack.put(towerWeights.get(i), findOddStack.getOrDefault(towerWeights.get(i), 0) + 1);
        }

        for (int i = 0; i < towerWeights.size(); i++) {
            if (findOddStack.get(towerWeights.get(i)) == 1) {
                allEqual = false;
                result += findBadProgam(rootPrograms.get(i), stringToWeightMap, stringToProgramsMap, baseProgram, result);
            }
        }

        if (allEqual) {
            List<String> parentPrograms = stringToProgramsMap.get(Parent);
            List<Integer> parentWeights = new ArrayList<>();
            for (int i = 0; i < parentPrograms.size(); i++) {
                parentWeights.add(findTowerWeights(stringToWeightMap, stringToProgramsMap, parentPrograms.get(i)));
            }

            int minValue = Integer.MAX_VALUE;
            int maxValue = Integer.MIN_VALUE;
            Map<Integer, Integer> findIncorrectProgram = new HashMap<>();

            for (int i = 0; i < parentWeights.size(); i++) {
                if (parentWeights.get(i) > maxValue) maxValue = parentWeights.get(i);
                if (parentWeights.get(i) < minValue) minValue = parentWeights.get(i);
                findIncorrectProgram.put(parentWeights.get(i), findIncorrectProgram.getOrDefault(towerWeights.get(i), 0) + 1);
            }

            for (int i = 0; i < parentWeights.size(); i++) {
                if (findIncorrectProgram.get(parentWeights.get(i)) == 1) {
                    int badProgram = stringToWeightMap.get(parentPrograms.get(i));
                    if (parentWeights.get(i) == minValue) result = badProgram + maxValue - minValue;
                    else result = badProgram - (maxValue - minValue);
                }
            }
        }

        return result;
    }

    private int findTowerWeights(Map<String, Integer> StringToWeightMap, Map<String, List<String>> StringToProgramsMap, String currentProgram) {
        int total = 0;
        total += StringToWeightMap.get(currentProgram);

        List<String> stackedPrograms = StringToProgramsMap.getOrDefault(currentProgram, Collections.emptyList());
        for (int i = 0; i < stackedPrograms.size(); i++) {
            total += findTowerWeights(StringToWeightMap, StringToProgramsMap, stackedPrograms.get(i));
        }

        return total;
    }

    public static void main(String[] args) {
        DaySeven daySeven = new DaySeven();
        List<ProgramDisk> inputList = daySeven.stringToProgramDiskList(inputLocation, " ");
        System.out.println("Day seven Challenge 1: " + daySeven.adventChallengeOne(inputList));
        System.out.println("Day seven Challenge 2: " + daySeven.adventChallengeTwo(inputList, daySeven.adventChallengeOne(inputList)));
    }
}
