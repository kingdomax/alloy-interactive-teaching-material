package alloy.interactive.teaching.material.validator.alloy;

import edu.mit.csail.sdg.ast.Module;
import edu.mit.csail.sdg.ast.Command;
import edu.mit.csail.sdg.ast.ExprVar;
import edu.mit.csail.sdg.parser.CompUtil;
import edu.mit.csail.sdg.alloy4viz.VizGUI;
import edu.mit.csail.sdg.alloy4.A4Reporter;
import edu.mit.csail.sdg.alloy4.ErrorWarning;
import edu.mit.csail.sdg.alloy4.Util;
import edu.mit.csail.sdg.translator.A4Options;
import edu.mit.csail.sdg.translator.A4Solution;
import edu.mit.csail.sdg.translator.A4Tuple;
import edu.mit.csail.sdg.translator.A4TupleSet;
import edu.mit.csail.sdg.translator.TranslateAlloyToKodkod;

public class AlloyValidator {

    // todo-moch
    // - findDeadSignature() might be useful in case user forget to use it
    // - has explanation what is the output of compiler mean
    // - playaround with A4Reporter & A4 Options, find few example #done
    // - give 3 instances answer
    // - print table is easy to read
    // - check no instance found, what this program will print
    // - check error, what this program will print
    // - ask prof. for more useful response from API
    public static void execute(String filename) {
        System.out.println(">> RUN VALIDATOR..."); 

        // Alloy4 sends diagnostic messages and progress reports to the A4Reporter.
        // By default, the A4Reporter ignores all these events (but you can extend the A4Reporter to display the event for the user)
        A4Reporter reporter = new A4Reporter() {

            // For example, here we choose to display each "warning" by printing it to System.out
            @Override
            public void warning(ErrorWarning msg) {
                System.out.print("[Custom Message] Relevance Warning:\n" + (msg.toString().trim()) + "\n\n");
                System.out.flush();
            }
        };

        // Choose some default options for how you want to execute the commands
        A4Options options = new A4Options();
        options.solver = A4Options.SatSolver.SAT4J;
        options.symmetry = 12; // Anti-lexicographic: explore solutions in a deterministic order that prioritizes smaller relations over larger ones

        // Parse+typecheck the model
        System.out.println("=========== Parsing+Typechecking " + filename + " =============");
        Module world = CompUtil.parseEverything_fromFile(reporter, null, filename);

        for (Command command : world.getAllCommands()) { // todo-moch: maybe support only 1 command
            // Execute the command + Print the outcome
            System.out.println("============ Command " + command + ": ============");
            A4Solution ans = TranslateAlloyToKodkod.execute_command(reporter, world.getAllReachableSigs(), command, options);
            System.out.println("============ Before ans ============");
            System.out.println(ans); // You can query "ans" to find out the values of each set or type. This can be useful for debugging.




            // If satisfiable...
            if (ans.satisfiable()) {
                // You can also write the outcome to an XML file
                ans.writeXML("my_solution_instances.xml");

                // You can then visualize the XML file by calling the visualizer
                new VizGUI(false, "my_solution_instances.xml", null).doShowViz();
            }
        }
    }
}
