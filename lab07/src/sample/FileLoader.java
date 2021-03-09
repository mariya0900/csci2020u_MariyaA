package sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileLoader {
    private String filename;

    public FileLoader(String filename){ this.filename = filename;}

    public Map<String, Integer> loadFile() {

        String line = "";
        Map<String, Integer> weatherCount = new TreeMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.filename));

            while ((line = br.readLine()) != null) {

                String[] column = line.split(",");
                String weatherType = column[5];

                //filling our weatherCount map
                if (weatherCount.containsKey(weatherType)) {
                    int previous = weatherCount.get(weatherType);
                    weatherCount.put(weatherType, previous + 1);
                } else {
                    weatherCount.put(weatherType, 1);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherCount;
    }
}
