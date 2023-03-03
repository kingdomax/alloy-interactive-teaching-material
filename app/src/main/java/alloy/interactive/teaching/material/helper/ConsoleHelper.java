package alloy.interactive.teaching.material.helper;

public class ConsoleHelper {
    private static final int PRINT_TIME_MS = 2000;

    public static void response(String message, boolean slowMode) {
        System.out.print(">> ");

        if (!slowMode) {
            System.out.print(message);
        } else {
            var elapseTime = PRINT_TIME_MS / message.length();
            
            for (int i = 0; i < message.length(); i++) {
                System.out.print(message.charAt(i));
                try { Thread.sleep(elapseTime); }
                catch (InterruptedException e) { e.printStackTrace(); }
            }
        }

        System.out.print(" ");
    }
}
