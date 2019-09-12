import java.text.ParseException;


/**
 * Creates a Deadline object which extends from the Task class.
 * A <code>description</code> is passed into this class to
 * instantiate a Deadline task.
 */
public class Deadline extends Task {
    private String[] deadline;
    private String[] datetime;

    /**
     * Instantiate a Events object by passing a String of description and time
     * @param description Description of the deadline task.
     * @param by The date and time of the deadline.
     */
    protected Deadline(String description, String by) {
        super(description);
        this.deadline = by.split(" ", 2);
        this.datetime = deadline[1].split(" ");
    }

    @Override
    public String formatString() {
        return "D-" + super.checkStatus() + "-" + super.getDescription().trim() + "-" + this.deadline[1];
    }

    @Override
    public String toString() {
        try {
            return "[D]" + super.toString() +
                    "(by: " + super.getDate(this.datetime) + ", " + super.getTime(this.datetime) + ")";
        } catch (ParseException parseError) {
            return parseError.toString();
        }

    }
}
