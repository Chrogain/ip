public enum Command {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, NULL;

    public static Command convert(String s) {
        switch (s) {
            case "bye": return BYE;
            case "list": return LIST;
            case "mark": return MARK;
            case "unmark": return UNMARK;
            case "todo": return TODO;
            case "deadline": return DEADLINE;
            case "event": return EVENT;
            case "delete": return DELETE;
            default: return NULL;

        }
    }
}
