package alloy.interactive.teaching.material.helper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class FileHelper {
    private static final String RESOURCES_PATH = "";
    private static final String WELCOME_MSG = "welcome.txt";
    private static final String FAREWELL_MSG = "farewell.txt";
    private static final String ANALYZER_MSG = "analyzer.txt";
    private static final String EXPLANATION_FILE = "explanation.txt";
    private static final String EXAMPLE_FILE = "example.als";
    private static final String EXERCISE_FILE = "exercise.als";
    private static final String SOLUTION_FILE = "solution.als";
    private static final String LINE = "################################################################################################";

    public static void readFile(String lesson, String file, boolean printLine, boolean copyFile) {
        InputStream inputStream = null;
        var fullPath = RESOURCES_PATH + (lesson.equals("") ? "" : lesson + "/") + getFileName(file);
        
        try {
            System.out.println(printLine ? LINE : "");
            inputStream = FileHelper.class.getClassLoader().getResourceAsStream(fullPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            
            String line;
            while ((line = reader.readLine()) != null) { System.out.println(line); }
            if (copyFile) { copyFile(lesson, file, fullPath); }
            
            System.out.println(printLine ? LINE : "");
        } catch (IOException e) { 
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) { inputStream.close(); }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void copyFile(String lesson, String file, String sourcePath) {
        var outputFileName = lesson + "_" + getFileName(file);
        if (new File(outputFileName).exists()) { return; }
    
        try (InputStream inputStream = FileHelper.class.getClassLoader().getResourceAsStream(sourcePath)) {
            if (inputStream == null) { throw new IOException("Resource not found: " + sourcePath); }

            FileOutputStream outputStream = new FileOutputStream(outputFileName);
            byte[] buffer = new byte[1024]; // Create a byte array to hold the data read from the input stream
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) > 0) { outputStream.write(buffer, 0, bytesRead); } // Read from the input stream and write to the output stream until the end of the file is reached
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
}
