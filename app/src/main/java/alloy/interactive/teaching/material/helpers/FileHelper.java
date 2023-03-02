package alloy.interactive.teaching.material.helpers;

import java.io.FileReader;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.BufferedReader;

public class FileHelper {
    public static final String LINE = "############################################################################################";

    public static void readFile(String filePath) throws IOException {
        BufferedReader reader = null;
        
        try {
            reader = new BufferedReader(new FileReader(Paths.get(filePath).toAbsolutePath().toString()));
            
            System.out.println(LINE);
            String line;
            while ((line = reader.readLine()) != null) { System.out.println(line); }
            System.out.println(LINE);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }

    public static void saveFile() {

    }
}
