package alloy.interactive.teaching.material.validator.alloy;

import java.util.stream.Collectors;
import com.jakewharton.fliptables.FlipTable;

import edu.mit.csail.sdg.ast.Sig;
import edu.mit.csail.sdg.ast.Sig.Field;
import edu.mit.csail.sdg.translator.A4Tuple;
import edu.mit.csail.sdg.translator.A4TupleSet;
import edu.mit.csail.sdg.translator.A4Solution;

public class AlloyVisualizer {
    public static void printRelationTable(A4Solution sol) {
        try {
            var sigs = sol.getAllReachableSigs().makeCopy().stream().filter(s -> s.getFields().size() > 0).collect(Collectors.toList());

            for (Sig sig : sigs) { //System.out.println("sig: " + sig.label);            
                for (Field field : sig.getFields()) { //System.out.println(" => field: " + field.label);
                    A4TupleSet ts = (A4TupleSet)(sol.eval(field));
                    String[] headers = { sig.label, field.label };
                    String[][] data = new String[ts.size()][2];;
                    var i = 0;
    
                    for(A4Tuple t: ts){
                        //System.out.println("  => tuple: " + t.toString());
                        //System.out.println("  => tuple left: " + t.atom(0));
                        //System.out.println("  => tuple right: " + t.atom(1));
                        if (t.arity() >= 2){
                            data[i][0] = t.atom(0);
                            data[i][1] = t.atom(1);
                        }
                        i++;
                    }
    
                    System.out.println(headers[0]+"."+headers[1]);
                    System.out.println(FlipTable.of(headers, data));
                }
            }
        } catch (Exception e) {
            System.out.println("Printing the relations has some errors, so we are skipping it for now. ("+e+")");
        }
    }
}
