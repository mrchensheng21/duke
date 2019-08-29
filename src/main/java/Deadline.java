public class Deadline extends Task {
    protected String by;
    protected String deadline[];

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        deadline = by.split(" ", 2);
    @Override
    public String formatString() {
        return "D-" + super.checkStatus() + "-" + super.getDescription().trim() + "-" + this.deadline[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline[1] + ")";
    }
}
