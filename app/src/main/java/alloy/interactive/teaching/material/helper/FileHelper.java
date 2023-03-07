package alloy.interactive.teaching.material.helper;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileHelper {
    private static final String RESOURCES_PATH = "app/bin/main/";
    private static final String WELCOME_MSG = "welcome.txt";
    private static final String FAREWELL_MSG = "farewell.txt";
    private static final String ANALYZER_MSG = "analyzer.txt";
    private static final String EXPLANATION_FILE = "explanation.txt";
    private static final String EXAMPLE_FILE = "example.als";
    private static final String EXERCISE_FILE = "exercise.als";
    private static final String SOLUTION_FILE = "solution.als";
    private static final String LINE = "############################################################################################";

    public static void readFile(String lesson, String file, boolean printLine, boolean copyFile) {
        BufferedReader reader = null;
        var fullPath = getAbsolutePath(lesson, file);
        
        try {
            System.out.println(printLine ? LINE : "");
            
            String line;
            reader = new BufferedReader(new FileReader(fullPath));
            while ((line = reader.readLine()) != null) { System.out.println(line); }
            if (copyFile) { copyFile(lesson, file, fullPath); }
            
            System.out.println(printLine ? LINE : "");
        } catch (IOException e) { 
            e.printStackTrace();
        } finally {
            try { reader.close(); } 
            catch (IOException e) { e.printStackTrace(); }
        }
    }

    private static void copyFile(String lesson, String file, String sourcePath) {
        var outputFileName = lesson + "_" + getFileName(file);
        if (new File(outputFileName).exists()) { return; }
        
        try {
            FileInputStream inputStream = new FileInputStream(sourcePath);
            FileOutputStream outputStream = new FileOutputStream(outputFileName);

            // Read from the input stream and write to the output stream until the end of the file is reached
            byte[] buffer = new byte[1024]; // Create a byte array to hold the data read from the input stream
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) > 0) { outputStream.write(buffer, 0, bytesRead); }

            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFileName(String file) {
        switch (file) {
            case "welcome":             return WELCOME_MSG;
            case "farewell":            return FAREWELL_MSG;
            case "analyzer":            return ANALYZER_MSG;
            case "explanation":         return EXPLANATION_FILE;
            case "example":             return EXAMPLE_FILE;
            case "exercise":            return EXERCISE_FILE;
            case "exercise-solution":   return SOLUTION_FILE;
        }
        return "";
    }

    private static String getAbsolutePath(String lesson, String file) {
        var filePath = RESOURCES_PATH + (lesson.equals("") ? "" : lesson + "/") + getFileName(file);
        return Paths.get(filePath).toAbsolutePath().toString();
    }
}
