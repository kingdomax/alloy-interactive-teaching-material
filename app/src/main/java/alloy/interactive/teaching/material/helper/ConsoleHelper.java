package alloy.interactive.teaching.material.helper;

public class ConsoleHelper {
    private static final int DEFAULT_PRINT_TIME_MS = 2000;

    public static void response(String message, boolean slowMode, int printTime) {
        System.out.print(">> ");

        if (!slowMode) {
            System.out.print(message);
        } else {
            var elapseTime = (printTime != -1 ? printTime : DEFAULT_PRINT_TIME_MS) / message.length();
            
            for (int i = 0; i < message.length(); i++) {
                System.out.print(message.charAt(i));
                try { Thread.sleep(elapseTime); }
                catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }
}
