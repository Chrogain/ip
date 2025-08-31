import static java.lang.Integer.parseInt;

public class Parser {

    public static Command parse(String input) {
        String[] words = input.split(" ", 2);
        String command = words[0];
        Command commandE = Command.convert(command);

        return commandE;

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
