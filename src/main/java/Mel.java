import java.util.Scanner;

public class Mel {
    public static void main(String[] args) {
        String line = "_______________________________________________________\n";
        String greeting = line
                + "Hello! I'm Mel\n"
                + "What can I do for you?\n"
                + line;

        String exit_message = "Bye! Hope to see you again soon!\n";
        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.next();
            if (input.equals("bye")) {
                break;

            } else {
                System.out.println(line + input + "\n" + line);

            }
        }

        System.out.println(line + exit_message + line);
    }
}
