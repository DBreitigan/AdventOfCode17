package adventDays;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//Day twelve of Advert of Coding
//http://adventofcode.com/2017/day/12
public class DayTwelve {
    private static String inputLocation = "src/inputs/DayTwelveInput";

    private class Pipes {
        private String first;
        private String second;

        Pipes(String first, String second){
            this.first = first;
            this.second = second;
        }

        public String getSecond() { return second; }
        public String getFirst() { return first; }
    }

    private List<Pipes> MapStringToPipes(String inputLocation, String delimiter) {
        List<Pipes> list = new ArrayList<>();

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
                String mainString = null;

                while (st.hasMoreTokens()) {
                    switch (counter) {
                        case 0:
                            mainString = st.nextToken();
                            break;
                        case 1:
                            st.nextToken();
                            break;
                        default:
                            String relation = st.nextToken().replace(",", "");
                            list.add(new Pipes(relation, mainString));
                            list.add(new Pipes(mainString, relation));
                            break;
                    }
                    counter++;
                }
            }
        } catch (IOException e) { e.printStackTrace(); }

        return list;
    }


    private int adventChallengeOne(List<Pipes> pipes, List<String> alreadyUsedPipes, String location) {
        int counter = 0;
        for (Pipes pipe : pipes) {
            if (pipe.getFirst().equals(location) && !alreadyUsedPipes.contains(pipe.getSecond())) {
                counter++;
                alreadyUsedPipes.add(pipe.getSecond());
                counter += adventChallengeOne(pipes, alreadyUsedPipes, pipe.getSecond());
            }
        }

        return counter;
    }

    private int adventChallengeTwo(List<Pipes> pipes) {
        int groups = 0;
        List<String> alreadyFoundGroup = new ArrayList<>();
        String location = pipes.get(0).getFirst();
        while (true) {
            groups++;
            findGroup(pipes, alreadyFoundGroup, location);

            boolean allFound = true;
            for (Pipes pipe : pipes) {
                if (!alreadyFoundGroup.contains(pipe.getFirst())) {
                    location = pipe.getFirst();
                    allFound = false;
                }
            }

            if (allFound) break;
        }

        return groups;
    }

    private List<String> findGroup(List<Pipes> pipes, List<String> alreadyFoundGroup, String location) {
        for (Pipes pipe : pipes) {
            if (pipe.getFirst().equals(location) && !alreadyFoundGroup.contains(pipe.getSecond())) {
                alreadyFoundGroup.add(pipe.getSecond());
                List<String> foundGroups = findGroup(pipes, alreadyFoundGroup, pipe.getSecond());
                for (String group : foundGroups) {
                    if (!alreadyFoundGroup.contains(group)) alreadyFoundGroup.add(group);
                }
            }

        }
        return alreadyFoundGroup;
    }


    public static void main(String[] args) {
        DayTwelve dayTwelve = new DayTwelve();

        List<Pipes> input = dayTwelve.MapStringToPipes(inputLocation, " ");
        System.out.println("Day twelve Challenge 1: " + dayTwelve.adventChallengeOne(input, new ArrayList<>(), "0"));
        System.out.println("Day twelve Challenge 2: " + dayTwelve.adventChallengeTwo(input));
    }

}
