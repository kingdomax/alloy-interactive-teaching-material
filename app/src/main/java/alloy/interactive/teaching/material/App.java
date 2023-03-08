package alloy.interactive.teaching.material;

import alloy.interactive.teaching.material.helper.FileHelper;
import alloy.interactive.teaching.material.helper.ConsoleHelper;
import alloy.interactive.teaching.material.validator.CommandLineValidator;
import alloy.interactive.teaching.material.validator.alloy.AlloyValidator;

public class App {
    interface Condition { boolean test(String input); }
    interface Part { String execute(String message, String partName, boolean useLessonNumberFromInput, String lessonNumber, Condition condition); }
    
    public static void main(String[] args) {
        if (args.length > 0) { jumpToThePoint(args); }
        else { walkThroughTheLesson(); }
    }

    private static void jumpToThePoint(String[] args) {
        if (!CommandLineValidator.isValidArguments(args)) { ConsoleHelper.response("Invalid command. Please check the command syntax on our welcome page.", false ,0); }
        else if (args[3].equals("exercise-submit")) { AlloyValidator.execute(args[5]); }
        else { FileHelper.readFile(args[1], args[3], true, false); }
    }

    private static void walkThroughTheLesson() {     
        Part part = (message, partName, useLessonNumberFromInput, lessonNumber, condition) -> {
            String input = "";
            while (condition.test(input)) {
                ConsoleHelper.response(message, true, -1);
                input = System.console().readLine().toLowerCase();
            }
            
            lessonNumber = useLessonNumberFromInput ? input : lessonNumber;
            FileHelper.readFile(lessonNumber, partName, true, true);
            return lessonNumber;
        };
        
        FileHelper.readFile("", "welcome", true, false);
        String lesson = part.execute("What lesson are you interested in learning today? (1-3): ", "explanation", true, "", input -> !input.equals("1") && !input.equals("2") && !input.equals("3"));
        part.execute("Would you like to see syntax example? (y/n): ", "example", false, lesson, input -> !input.equals("y"));
        part.execute("It's time for an exercise. Shall we start? (y/n): ", "exercise", false, lesson, input -> !input.equals("y"));
        part.execute("The exercise can be completed at your own pace, saved in a separate file, and submitted to our app when completed. Let's proceed for now. (y/n): ", "farewell", false, "", input -> !input.equals("y"));
    }
}
