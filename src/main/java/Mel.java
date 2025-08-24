import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Mel {
    private static TaskList taskList = new TaskList();

    public static void printOut(String input) {
        String line = "_______________________________________________________\n";
        System.out.println(line + " " + input + "\n" + line);

    }

    public static int handleIndex(String argument) throws MelException {
        if (argument == "") {
            throw new MelException.NoArgumentFoundException("index");

        }

        int index;
        try {
            index = parseInt(argument) - 1;

        } catch (NumberFormatException e) {
            throw new MelException.InvalidIndexException("Please input a number instead :)");

        }

        if (!taskList.validIndex(index)) {
            throw new MelException.InvalidIndexException(String.format("It is out of range! Please put a number from 1 to %d.", taskList.size()));

        }

        return index;

    }

    public static void main(String[] args) {
        String line = "_______________________________________________________\n";
        String greeting = "Hello! I'm Mel\n "
                + "What can I do for you?";

        String exit_message = " Bye! Hope to see you again soon!";
        printOut(greeting);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] words = input.split(" ", 2);
            String command = words[0];
            String argument = words.length > 1 ? words[1].trim() : "";
            try {
                if (command.equals("bye")) {
                    if (argument != "") {
                        throw new MelException.ExtraArgumentException("bye");

                    } else {
                        break;

                    }

                } else if (command.equals("list")) {
                    if (argument != "") {
                        throw new MelException.ExtraArgumentException("list");

                    } else {
                        printOut(taskList.toString());

                    }

                } else if (command.equals("mark")) {
                    Task task = taskList.get(handleIndex(argument));
                    task.markAsDone();
                    String output = String.format("Nice! I've marked this task as done:\n  %s", task.toString());
                    printOut(output);


                } else if (command.equals("unmark")) {
                    Task task = taskList.get(handleIndex(argument));
                    task.undo();
                    String output = String.format("OK, I've marked this task as not done yet:\n  %s", task.toString());
                    printOut(output);

                } else if (command.equals("todo")) {
                    String desc = input.length() > 5 ? input.substring(5) : "";
                    if (desc.isEmpty()) {
                        throw new MelException.NoArgumentFoundException("todo");

                    }

                    Task task = new Todo(desc);
                    printOut(taskList.add(task));

                } else if (command.equals("deadline")) {

                    String[] desc_and_time = argument.split("/by", 2);
                    if (desc_and_time.length < 2 || desc_and_time[0] == "" || desc_and_time[1] == "") {
                        throw new MelException.NoArgumentFoundException("deadline");

                    }

                    String desc = desc_and_time[0].trim();
                    String by = desc_and_time[1].trim();

                    Task task = new Deadline(desc, by);
                    printOut(taskList.add(task));

                } else if (command.equals("event")) {
                    String[] desc_and_time = argument.split("/from", 2);
                    if (desc_and_time.length < 2 || desc_and_time[0] == "" || desc_and_time[1] == "") {
                        throw new MelException.NoArgumentFoundException("event");

                    }

                    String[] fromandto = desc_and_time[1].split("/to", 2);
                    if (fromandto.length < 2 || fromandto[0] == "" || fromandto[1] == "") {
                        throw new MelException.NoArgumentFoundException("event");

                    }

                    String desc = desc_and_time[0].trim();
                    String from = fromandto[0].trim();
                    String to = fromandto[1].trim();

                    Task task = new Event(desc, from, to);
                    printOut(taskList.add(task));

                } else {
                    printOut("Please use the following commands: list, mark, unmark, todo, deadline, event, bye.");

                }

            } catch (MelException e) {
                printOut(e.getMessage());

            }

        }

        printOut(exit_message);
    }

}
