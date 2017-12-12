package helpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MappingHelper {

    public List<Integer> stringToIntegerList(String fileLocation, String delimiter) {
        List<Integer> integerList = new ArrayList<>();

        BufferedReader bufRdr = null;
        try {
            bufRdr = new BufferedReader(new FileReader(fileLocation));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        try {
            while ((line = bufRdr.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, delimiter);
                while (st.hasMoreTokens()) {
                    integerList.add(Integer.parseInt(st.nextToken()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return integerList;
    }

    public List<String> stringToStringList(String fileLocation, String delimiter) {
        List<String> stringList = new ArrayList<>();

        BufferedReader bufRdr = null;
        try {
            bufRdr = new BufferedReader(new FileReader(fileLocation));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        try {
            while ((line = bufRdr.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, delimiter);
                while (st.hasMoreTokens()) {
                    stringList.add(st.nextToken());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringList;
    }

    public int[][] stringTo2DIntArray(String fileLocation, String delimiter) {
        int[][] inputArray = new int[16][16];
        BufferedReader bufRdr = null;
        try {
            bufRdr = new BufferedReader(new FileReader(fileLocation));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        int row = 0, col = 0;
        try {
            while ((line = bufRdr.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, delimiter);
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

    public Map<Integer, ArrayList> stringTo2DStringMap(String fileLocation, String delimiter) {
        Map<Integer, ArrayList> inputMap = new HashMap<>();

        BufferedReader bufRdr = null;
        try {
            bufRdr = new BufferedReader(new FileReader(fileLocation));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        int row = 0;
        try {
            while ((line = bufRdr.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, delimiter);
                ArrayList stringRow = new ArrayList();
                while (st.hasMoreTokens()) {
                    stringRow.add(st.nextToken());
                }

                inputMap.put(row, stringRow);
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputMap;
    }


}
