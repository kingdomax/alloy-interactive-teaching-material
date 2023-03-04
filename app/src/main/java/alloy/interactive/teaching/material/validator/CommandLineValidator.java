package alloy.interactive.teaching.material.validator;

// Synopsis
// java -jar alloy-interactive-teaching-material.jar --lesson <lesson-number> --part <explanation||example||exercise||exercise-solution||exercise-submit> [--partParam <solution-file>]
public class CommandLineValidator {
    public static boolean isValidArguments(String[] args) {
        // minimum number of parameters
        if (args.length < 4) {
            return false;
        }

        // todo-moch: use config instead
        if (!args[0].equals("--lesson") ||
            (!args[1].equals("1") && !args[1].equals("2") && !args[1].equals("3")) || 
            !args[2].equals("--part") ||
            (!args[3].equals("explanation") && !args[3].equals("example") && !args[3].equals("exercise") && !args[3].equals("exercise-solution") && !args[3].equals("exercise-submit"))) {
            return false;
        }

        // make sure "exercise-submit" commmand is in correct structure
        if (args[3].equals("exercise-submit") && (args.length <= 5 || !args[4].equals("--partParam"))) {
            return false;
        }

        return true;
    }
}
