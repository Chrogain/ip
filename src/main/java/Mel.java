import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

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
            String[] words = input.split(" ");
            String command = words[0];
            String argument = words.length > 1 ? words[1] : "";

            if (command.equals("bye")) {
                break;

            } else if (command.equals("list")) {
                int index = 1;
                StringBuilder output = new StringBuilder();
                output.append("Here are the tasks in your list:\n ");
                for (Task task : taskList) {
                    String taskString = String.format("%d.[%s] %s\n ", index, task.getStatusIcon() ,task.toString());
                    output.append(taskString);
                    index++;

                }
                System.out.println(addLines(output.toString()));

            } else if (command.equals("mark")) {
                int index = parseInt(argument) - 1;
                Task task = taskList.get(index);
                task.markAsDone();
                String output = String.format("Nice! I've marked this task as done:\n  [%s] %s\n ", task.getStatusIcon(), task.toString());
                System.out.println(addLines(output));

            } else if (command.equals("unmark")) {
                int index = parseInt(argument) - 1;
                Task task = taskList.get(index);
                task.undo();
                String output = String.format("OK, I've marked this task as not done yet:\n  [%s] %s\n ", task.getStatusIcon(), task.toString());
                System.out.println(addLines(output));

            } else {
                taskList.add(new Task(input));
                String output = String.format("added: %s", input);
                System.out.println(addLines(output));

            }
        }

        System.out.println(line + exit_message + line);
    }
}
