public class Todo extends Task {

    public Todo(String description) {
        super(description);

    }

    @Override
    public String toString() {
        String output = String.format("[T]%s", super.toString());
        return output;

    }

}
