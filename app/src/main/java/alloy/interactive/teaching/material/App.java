package alloy.interactive.teaching.material;

import alloy.interactive.teaching.material.helper.FileHelper;
import alloy.interactive.teaching.material.validator.CommandLineValidator;

public class App {    
    public static void main(String[] args) {
        if (args.length <= 0) { walkThroughTheLesson(); }
        else { jumpToThePoint(args); }
    }

    // todo-moch: re-write to be more cleaner
    private static void walkThroughTheLesson() {
        // welcome message
        FileHelper.readFile("", "welcome", false);
        
        // explanation
        String lesson = "";
        while (!lesson.equals("1") && !lesson.equals("2") && !lesson.equals("3")) { // todo-moch: remove this hardcode so it's flexible to add new lesson
            System.out.print(">> What lesson are you interested in learning today? (1-3): ");
            lesson = System.console().readLine().toLowerCase();
        }
        FileHelper.readFile(lesson, "explanation", true);

        // example
        String ans = "";
        while (!ans.equals("y")) {
            System.out.print(">> Would you like to see syntax example? (y/n): ");
            ans = System.console().readLine().toLowerCase();
        }
        FileHelper.readFile(lesson, "example", true);

        // exercise
        ans = "";
        while (!ans.equals("y")) {
            System.out.print(">> It's time for an exercise. Shall we start? (y/n): ");
            ans = System.console().readLine().toLowerCase();
        }
        FileHelper.readFile(lesson, "exercise", true);

        // farewell message
        ans = "";
        while (!ans.equals("y")) {
            System.out.print(">> The exercise can be completed at your own pace, saved in a separate file, and submitted to our app when completed. Let's proceed for now.. (y/n): ");
            ans = System.console().readLine().toLowerCase();
        }
        FileHelper.readFile("", "farewell", false);
    }

    private static void jumpToThePoint(String[] args) {
        if (!CommandLineValidator.isValidArguments(args)) { 
            return; 
        } else if (!args[3].equals("exercise-submit")) { 
            FileHelper.readFile(args[1], args[3], false); 
        } else { 
            System.out.print(">> RUN VALIDATOR !!"); 
        }
    }
}
