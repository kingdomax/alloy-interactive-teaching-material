package alloy.interactive.teaching.material.validator;

import alloy.interactive.teaching.material.config.LessonConfigManager;

// Synopsis
// java -jar alloy-interactive-teaching-material.jar --lesson <lesson-number> --part <explanation||example||exercise||exercise-solution||exercise-submit> [--partParam <solution-file.als||instance.xml>]
public class CommandLineValidator {
    public static boolean isValidArguments(String[] args) {
        // minimum number of parameters
        if (args.length < 4) {
            return false;
        }

        // make sure "lesson-number" is legit and "--part" has correct value
        if (!args[0].equals("--lesson") ||
            !LessonConfigManager.isValidLessonNumber(args[1]) || 
            !args[2].equals("--part") ||
            (!args[3].equals("explanation") && !args[3].equals("example") && !args[3].equals("exercise") && !args[3].equals("exercise-solution") && !args[3].equals("exercise-submit"))) {
            return false;
        }

        // make sure "exercise-submit" commmand is in correct structure
        if (args[3].equals("exercise-submit") &&
            (args.length <= 5 ||
            !args[4].equals("--partParam") || 
            (!args[5].endsWith(".als") && !args[5].endsWith(".xml")))) {
            return false;
        }

        return true;
    }
}
