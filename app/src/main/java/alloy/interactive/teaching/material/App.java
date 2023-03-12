package alloy.interactive.teaching.material;

import alloy.interactive.teaching.material.helper.FileHelper;
import alloy.interactive.teaching.material.config.LessonConfigManager;
import alloy.interactive.teaching.material.helper.ConsoleHelper;
import alloy.interactive.teaching.material.validator.CommandLineValidator;
import alloy.interactive.teaching.material.validator.alloy.AlloyValidator;

public class App {
    interface Condition { boolean doesNotSatisfy(String input); }
    interface Part { String execute(String message, String partName, String lessonNumber, boolean useLessonNumberFromInput, boolean copyFile, Condition condition); }

    public static void main(String[] args) {
        if (args.length > 0) { jumpToThePoint(args); }
        else { walkThroughTheLesson(); }
    }

    private static void jumpToThePoint(String[] args) {
        if (!CommandLineValidator.isValidArguments(args)) { ConsoleHelper.response("Invalid command. Please check the command syntax on our welcome page.", false ,0); }
        else if (args[3].equals("exercise-submit")) { AlloyValidator.execute(args[5]); }
        else { FileHelper.readFile(args[1], args[3], true, true); }
    }

    private static void walkThroughTheLesson() {     
        FileHelper.readFile("", "welcome", true, false);
        
        Part part = (message, partName, lessonNumber, useLessonNumberFromInput, copyFile, condition) -> {
            String input = "";
            while (condition.doesNotSatisfy(input)) {
                ConsoleHelper.response(message, true, -1);
                input = System.console().readLine().toLowerCase();
            }
            
            lessonNumber = useLessonNumberFromInput ? input : lessonNumber;
            FileHelper.readFile(lessonNumber, partName, true, copyFile);
            return lessonNumber;
        };

        String lesson = part.execute("What lesson are you interested in learning today? (1-3): ", 
            "explanation", "", true, true, input -> !LessonConfigManager.isValidLessonNumber(input));
        part.execute("Would you like to see syntax example? (y/n): ",
            "example", lesson, false, true, input -> !input.equals("y"));
        part.execute("It's time for an exercise. Shall we start? (y/n): ",
            "exercise", lesson, false, true, input -> !input.equals("y"));
        part.execute("The exercise can be completed at your own pace, saved in a separate file, and submitted to our app when completed. Let's proceed for now. (y/n): ",
            "farewell", "", false, false, input -> !input.equals("y"));
    }
}
