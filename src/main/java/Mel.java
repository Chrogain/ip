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
            String[] words = input.split(" ", 2);
            String command = words[0];
            String argument = words.length > 1 ? words[1] : "";

            if (command.equals("bye")) {
                break;

            } else if (command.equals("list")) {
                int index = 1;
                StringBuilder output = new StringBuilder();
                output.append("Here are the tasks in your list:\n ");
                for (Task task : taskList) {
                    if (index > 1) {
                        output.append("\n ");

                    }

                    String taskString = String.format("%d.%s", index, task.toString());
                    output.append(taskString);
                    index++;

                }
                System.out.println(addLines(output.toString()));

            } else if (command.equals("mark")) {
                int index = parseInt(argument) - 1;
                Task task = taskList.get(index);
                task.markAsDone();
                String output = String.format("Nice! I've marked this task as done:\n  %s\n ", task.toString());
                System.out.println(addLines(output));

            } else if (command.equals("unmark")) {
                int index = parseInt(argument) - 1;
                Task task = taskList.get(index);
                task.undo();
                String output = String.format("OK, I've marked this task as not done yet:\n  %s\n ", task.toString());
                System.out.println(addLines(output));

            } else if (command.equals("todo")) {
                Task task = new Todo(input.substring(5));
                taskList.add(task);
                String output = String.format("Got it. I've added this task:\n  %s\n Now you have %d tasks in the list.", task.toString(), taskList.size());
                System.out.println(addLines(output));

            } else if (command.equals("deadline")) {
                String[] desc_and_time = argument.split("/by", 2);
                String desc = desc_and_time[0].trim();
                String by = desc_and_time[1].trim();

                Task task = new Deadline(desc, by);
                taskList.add(task);
                String output = String.format("Got it. I've added this task:\n  %s\n Now you have %d tasks in the list.", task.toString(), taskList.size());
                System.out.println(addLines(output));

            } else if (command.equals("event")) {
                String[] desc_and_time = argument.split("/from", 2);
                String[] fromandto = desc_and_time[1].split("/to" , 2);
                String desc = desc_and_time[0].trim();
                String from = fromandto[0].trim();
                String to = fromandto[1].trim();

                Task task = new Event(desc, from, to);
                taskList.add(task);
                String output = String.format("Got it. I've added this task:\n  %s\n Now you have %d tasks in the list.", task.toString(), taskList.size());
                System.out.println(addLines(output));

            } else {
                System.out.println(addLines("Please use the following commands: list, mark, unmark, todo, deadline, event, bye."));

            }
        }

        System.out.println(line + exit_message + line);
    }
}
