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
        outerLoop:
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] words = input.split(" ", 2);
            String command = words[0];
            Command commandE = Command.convert(command);
            String argument = words.length > 1 ? words[1].trim() : "";
            try {
                switch (commandE) {
                    case BYE: {
                        if (argument != "") {
                            throw new MelException.ExtraArgumentException("bye");

                        } else {
                            break outerLoop;

                        }


                    }
                    case LIST: {
                        if (argument != "") {
                            throw new MelException.ExtraArgumentException("list");

                        } else {
                            printOut(taskList.toString());

                        }
                        break;
                    }

                    case MARK: {
                        printOut(taskList.mark(handleIndex(argument)));
                        break;
                    }

                    case UNMARK: {
                        printOut(taskList.unmark(handleIndex(argument)));
                        break;
                    }

                    case TODO: {
                        String desc = input.length() > 5 ? input.substring(5) : "";
                        if (desc.isEmpty()) {
                            throw new MelException.NoArgumentFoundException("todo");

                        }

                        printOut(taskList.add(new Todo(desc)));
                        break;

                    }
                    case DEADLINE: {

                        String[] desc_and_time = argument.split("/by", 2);
                        if (desc_and_time.length < 2 || desc_and_time[0] == "" || desc_and_time[1] == "") {
                            throw new MelException.NoArgumentFoundException("deadline");

                        }

                        String desc = desc_and_time[0].trim();
                        String by = desc_and_time[1].trim();

                        printOut(taskList.add(new Deadline(desc, by)));
                        break;

                    }

                    case EVENT: {
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

                        printOut(taskList.add(new Event(desc, from, to)));
                        break;
                    }

                    case DELETE: {
                        printOut(taskList.remove(handleIndex(argument)));
                        break;

                    }

                    case NULL: default: {
                        printOut("Please use the following commands: list, mark, unmark, todo, deadline, event, bye.");
                        break;

                    }

                }
            } catch (MelException e) {
                printOut(e.getMessage());

            }

        }
        printOut(exit_message);

    }

}
