package alloy.interactive.teaching.material;

import java.io.IOException;

import alloy.interactive.teaching.material.helpers.FileHelper;

public class App {
    public static final String RESOURCES_PATH = "app/bin/main/";
    public static final String WELCOME_MSG = "/welcome.txt";
    public static final String EXPLANATION_FILE = "/explanation.txt";
    public static final String EXAMPLE_FILE = "/example.als";
    public static final String EXERCISE_FILE = "/exercise.als";
    public static final String SOLUTION_FILE = "/solution.als";
    public static final String FAREWELL_MSG = "/farewell.txt";
    
    // java -cp build/libs/*-all.jar me.args.indexing.Index --input data/acquired/warcs/idebate.zip --inputstep acquisition 
    //                                                      --output index --overwrite --configuration properties.conf
    public static void main(String[] args) throws IOException {
        if (args.length <= 0) {
            walkThrough();
        } else {
            // PARAMS 
            // (lesson=>1-3 / part=>explanation||example||exercise||exercise-submit||exercise-solution / partParam=>filepath)
            // java -cp build/libs/*-all.jar me.args.indexing.Index --input data/acquired/warcs/idebate.zip --inputstep acquisition 
            //                                                      --output index --overwrite --configuration properties.conf
            // ---> jump to the point
            // ---> print invalid with correct format
            jumpToTheLesson();
        }

    }

    // --> explanation --> example --> exercise --> submit
    private static void walkThrough() throws IOException {
        FileHelper.readFile(RESOURCES_PATH + WELCOME_MSG);
        
        // explanation
        int lesson = 0;
        while (lesson < 1 || lesson > 3) { // remove this hardcode so it's flexible to add new lesson
            System.out.print(">> What lesson are you interested in learning today? (1-3): ");
            try { lesson = Integer.parseInt(System.console().readLine()); }
            catch (NumberFormatException e) { } 
        }
        FileHelper.readFile(RESOURCES_PATH + lesson + EXPLANATION_FILE);

        // example
        String ans = "n";
        while (!ans.equals("y")) {
            System.out.print(">> Would you like to see an example of syntax? (y/n): ");
            ans = System.console().readLine().toLowerCase();
        }
        FileHelper.readFile(RESOURCES_PATH + lesson + EXAMPLE_FILE);

        // exercise
        ans = "n";
        while (!ans.equals("y")) {
            System.out.print(">> It's time for an exercise. Shall we start? (y/n): ");
            ans = System.console().readLine().toLowerCase();
        }
        FileHelper.readFile(RESOURCES_PATH + lesson + EXERCISE_FILE);

        FileHelper.readFile(RESOURCES_PATH + FAREWELL_MSG);
    }

    private static void jumpToTheLesson() {

    }
}
