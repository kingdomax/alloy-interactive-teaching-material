package alloy.interactive.teaching.material.validator.alloy;

import edu.mit.csail.sdg.alloy4.A4Reporter;
import edu.mit.csail.sdg.translator.A4Options;

public class AlloyOption {
    public static A4Options getOptions() {
        A4Options options = new A4Options();
        options.solver = A4Options.SatSolver.SAT4J;
        options.symmetry = 12; // Anti-lexicographic: explore solutions in a deterministic order that prioritizes smaller relations over larger ones
        return options;
    }

    public static A4Reporter getReporter() {
        var reporter = new A4Reporter();
        return reporter;
    }
}
