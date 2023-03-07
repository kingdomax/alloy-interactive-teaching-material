package alloy.interactive.teaching.material.helper;

public class MeasurementHelper {
    public static long measure(Runnable function) {
        var startTime = System.currentTimeMillis();
        function.run();
        return System.currentTimeMillis() - startTime;
    }

    public static void measureAndReport(Runnable function, String prefix, String suffix) {
        var elapsedTime = measure(function);
        System.out.println(prefix + " ("+elapsedTime+"ms)" + suffix);
    }
}
