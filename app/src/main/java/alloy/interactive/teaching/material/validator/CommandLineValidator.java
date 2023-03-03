package alloy.interactive.teaching.material.validator;

public class CommandLineValidator {
    // Synopsis
    // java -cp alloy-interactive-teaching-material.jar alloy.interactive.teaching.material.App --lesson <lesson-number> --part <explanation||example||exercise||exercise-solution||exercise-submit> [--partParam <solution-file>]
    public static boolean isValidArguments(String[] args) {
        // minimum number of parameters
        if (args.length < 4) {
            System.out.println(">> Invalid command. Please check the command syntax on our welcome page.");
            return false;
        }

        // todo-moch: use config instead
        if (!args[0].equals("--lesson") ||
            (!args[1].equals("1") && !args[1].equals("2") && !args[1].equals("3")) || 
            !args[2].equals("--part") ||
            (!args[3].equals("explanation") && !args[3].equals("example") && !args[3].equals("exercise") && !args[3].equals("exercise-solution") && !args[3].equals("exercise-submit"))) {
            System.out.println(">> Invalid command. Please check the command syntax on our welcome page.");
            return false;
        }

        // make sure "exercise-submit" commmand is in correct structure
        if (args[3].equals("exercise-submit") && (args.length <= 5 || !args[4].equals("--partParam"))) {
            System.out.println(">> Invalid command. Please check the command syntax on our welcome page.");
            return false;
        }

        return true;
    }
}
