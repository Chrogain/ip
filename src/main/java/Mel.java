import java.util.ArrayList;
import java.util.Scanner;

public class Mel {
    public static String addLines(String input) {
        String line = "_______________________________________________________\n";
        return line + " " + input + "\n" + line;
    }
    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();
        String line = "_______________________________________________________\n";
        String greeting = addLines(
                "Hello! I'm Mel\n "
                + "What can I do for you?\n");

        String exit_message = " Bye! Hope to see you again soon!\n";
        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;

            } else if (input.equals("list")) {
                int index = 1;
                StringBuilder output = new StringBuilder();
                for (Task task : taskList) {
                    String taskString = String.format("%d. %s\n ", index, task.toString());
                    output.append(taskString);
                    index++;

                }
                System.out.println(addLines(output.toString()));
            } else {
                taskList.add(new Task(input));
                String output = String.format("added: %s", input);
                System.out.println(addLines(output));

            }
        }

        System.out.println(line + exit_message + line);
    }
}
