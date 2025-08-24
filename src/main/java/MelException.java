public class MelException extends Exception {

    public MelException(String msg) {
        super(msg);

    }

    public static class NoArgumentFoundException extends MelException {
        public NoArgumentFoundException(String command) {
            super(messager(command));

        }

        private static String messager(String command) {
            String message = "You are missing an argument!";
            if (command.equals("index")) {
                message = "You need a valid index after the command.";

            } else if (command.equals("todo")) {
                message = "You need a task description after \"todo\".";

            } else if (command.equals("deadline")) {
                message = "You are missing the deadline and/or /by after \"deadline\".";
            } else if (command.equals("event")) {
                message = "Use the event command like this: \"(event) /from (start) /to (end)\".";
            }
            return message;

        }
    }

    public static class InvalidIndexException extends MelException {
        public InvalidIndexException(String msg) {
            super(msg);

        }
    }

    public static class ExtraArgumentException extends MelException {
        public ExtraArgumentException(String command) {
            super("Are you trying to type " + command +  "? Just type " + "\"" + command + "\"" + "!");

        }
    }

    public static class EmptyListException extends MelException {
        public EmptyListException() {
            super("List is empty!");

        }
    }

}
