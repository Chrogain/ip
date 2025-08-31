package apps;

import static java.lang.Integer.parseInt;
import exceptions.MelException;
import commands.Command;
import commands.*;

public class Parser {

    public static Command parse(String input) throws MelException {
        String[] words = input.split(" ", 2);
        String command = words[0];
        String argument = words.length > 1 ? words[1].trim() : "";
        switch (command) {
            case "bye": return new ByeCommand(argument);
            case "list": return new ListCommand(argument);
            case "mark": return new MarkCommand(argument);
            case "unmark": return new UnmarkCommand(argument);
            case "todo": return new TaskCommand(argument, "T");
            case "deadline": return new TaskCommand(argument, "D");
            case "event": return new TaskCommand(argument, "E");
            case "delete": return new DeleteCommand(argument);
            default: throw new MelException("Please use the following commands: list, mark, unmark, todo, deadline, event, bye.");

        }

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


        return index;

    }


}
