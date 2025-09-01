package mel.apps;

import java.util.Scanner;

import mel.exceptions.MelException;

public class Ui {

    private Scanner sc;

    public Ui (Scanner sc) {
        this.sc = sc;

    }

    public String readCommand() throws MelException {
        System.out.println("Next command:");
        if (sc.hasNext()) {
            return sc.nextLine();

        } else {
            throw new MelException("No more input");

        }

    }

    public void printOut(String input) {
        String LINE = "_______________________________________________________\n";
        System.out.println(LINE + " " + input + "\n" + LINE);

    }

    public void showGreeting() {
        String greeting = "Hello! I'm Mel\n "
                + "What can I do for you?";

        printOut(greeting);

    }

    public void showExit() {
        String exit_message = " Bye! Hope to see you again soon!";
        printOut(exit_message);
    }
}
