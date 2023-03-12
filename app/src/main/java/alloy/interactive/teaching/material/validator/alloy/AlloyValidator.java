package alloy.interactive.teaching.material.validator.alloy;

import java.util.List;
import java.util.ArrayList;
import edu.mit.csail.sdg.ast.Sig;
import edu.mit.csail.sdg.ast.Module;
import edu.mit.csail.sdg.ast.Command;
import edu.mit.csail.sdg.parser.CompUtil;
import edu.mit.csail.sdg.alloy4.ConstList;
import edu.mit.csail.sdg.ast.ExprUnary.Op;
import edu.mit.csail.sdg.alloy4viz.VizGUI;
import edu.mit.csail.sdg.alloy4.A4Reporter;
import edu.mit.csail.sdg.translator.A4Options;
import edu.mit.csail.sdg.translator.A4Solution;
import edu.mit.csail.sdg.translator.TranslateAlloyToKodkod;

import alloy.interactive.teaching.material.helper.FileHelper;
import alloy.interactive.teaching.material.helper.MeasurementHelper;
import alloy.interactive.teaching.material.helper.ConsoleHelper;

public class AlloyValidator {
    public static void execute(String filename) {
        FileHelper.readFile("", "analyzer", false, false);
        if (filename.endsWith(".xml")) { visualize(filename); }
        else { analyze(filename); }
    }

    private static void visualize(String filename) {
        ConsoleHelper.response("Visualizing instance: " + filename + "\n", true, 1000);
        new VizGUI(true, filename, null).doShowViz();
    }

    private static void analyze(String filename) {
        // 0) Setup
        A4Options options = AlloyOption.getOptions();
        A4Reporter reporter = AlloyOption.getReporter();

        // 1) Parse & Typecheck the model
        ConsoleHelper.response("Parsing model: " + filename + "\n", true, 1000);
        final Module[] moduleWrapper = new Module[1];
        MeasurementHelper.measureAndReport(() -> {
            moduleWrapper[0] = CompUtil.parseEverything_fromFile(reporter, null, filename);
        }, "No syntax error, parsed sucessful", "\n");

        // 2) Execute first command in Alloy Model
        final A4Solution[] solutionWrapper = new A4Solution[1];
        var firstCommand = moduleWrapper[0].getAllCommands().get(0);
        var allSignatures = moduleWrapper[0].getAllReachableUserDefinedSigs();
        var commandTime = MeasurementHelper.measure(() -> {
            solutionWrapper[0] = TranslateAlloyToKodkod.execute_command(reporter, moduleWrapper[0].getAllReachableSigs(), firstCommand, options);
        });

        // 3) Check dead signatures
        ConsoleHelper.response("Determining dead signatures, signatures that do not have atoms in any instance\n", true, 1000);
        MeasurementHelper.measureAndReport(() -> {
            getSignatureListThatNotSatisfyPredefinedExpression(Op.SOME, firstCommand, allSignatures, options, reporter);
        }, "", "\n");

        // 4) Check core signatures
        ConsoleHelper.response("Determining core signatures, signatures that always have atoms except in the empty instance\n", true, 1000);
        MeasurementHelper.measureAndReport(() -> {
            getSignatureListThatNotSatisfyPredefinedExpression(Op.NO, firstCommand, allSignatures, options, reporter);
        }, "", "\n");

        // 5) Iterate through solutions and display informative output
        ConsoleHelper.response("Executing command: " + firstCommand + "\n", true, 1000); 
        if (solutionWrapper[0].satisfiable()) {
            System.out.println("Instances found, predicate is consistent ("+commandTime+"ms)\n");
            generatingInstances(solutionWrapper[0], filename);
        } else {
            System.out.println("No instance found, predicate may be inconsistent ("+commandTime+"ms)\n");
        }
    }

	private static void getSignatureListThatNotSatisfyPredefinedExpression(Op operation,
                                                                        Command command,
                                                                        ConstList<Sig> signatures,
                                                                        A4Options options,
                                                                        A4Reporter reporter) {
        // Modify run command with predefined expression
		List<String> results = new ArrayList<>();
        for (Sig signature : signatures) {
			if (!TranslateAlloyToKodkod.execute_command(reporter,
                                                    signatures,
													command.change(command.formula.and(operation == Op.SOME ? signature.some() : signature.no())),
													options).satisfiable()) { results.add(signature.label); }
		}
		
        // Print the result
        if (results.size() < 1) {
            System.out.print("none");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < results.size(); i++) {
                stringBuilder.append(results.get(i));
                if (i != results.size() - 1) { stringBuilder.append(", "); }
            }
            System.out.print(stringBuilder.toString());
        }
	}

    private static void generatingInstances(A4Solution solutions, String inputFile) {
        var instance = 1;
        var nextInstance = false;
        
        while (instance == 1 || nextInstance) {
            System.out.println("######################### [INSTANCE "+instance+"] ######################### \n");
            System.out.println("["+instance+".1] Informative Text");
            System.out.println(solutions); 
            System.out.println("["+instance+".2] Relations");
            AlloyVisualizer.printRelationTable(solutions);
            System.out.println("["+instance+".3] Graphical Instance");
            System.out.println("You can view a visual representation as graph in another opened window.\n");
            System.out.println("["+instance+".4] Output");
            var outputFile = createOutputFile(inputFile, instance);
            solutions.writeXML(outputFile);
            new VizGUI(false, outputFile, null).doShowViz();
            System.out.println("The instance is generated and is saved as " + outputFile + ". To view this file later on, please use the command below.");
            System.out.println("'java -jar alloy-interactive-teaching-material.jar --lesson <lesson-number> --part exercise-submit --partParam " + outputFile + "'\n");
            System.out.println("#################################################################");

            // Next instance ??
            instance++;
            nextInstance = false;
            solutions = solutions.next();
            if (solutions.satisfiable()) {
                ConsoleHelper.response("Would you like to see next instance (y/n): ", true, -1);
                nextInstance = System.console().readLine().toLowerCase().equals("y") ? true : false;
            }
        }
    }

    private static String createOutputFile(String inputFile, int instanceNo) {
        return (inputFile.lastIndexOf('.') != -1 ? inputFile.substring(0, inputFile.lastIndexOf('.')) : inputFile) + "_instance" + instanceNo + ".xml";
    }
}
