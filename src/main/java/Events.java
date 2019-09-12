import java.text.ParseException;

/**
 * Creates a Events object which extends from the Task class.
 * A <code>description</code> is passed into this class to
 * instantiate a Events task.
 */
public class Events extends Task {
    private String event[];
    private String[] datetime;

    /**
     * Instantiate a Events object by passing a String of description and time
     * @param description Description of the event task.
     * @param at The date and time of the event.
     */
    protected Events(String description, String at) {
        super(description);
        this.event = at.split(" ", 2);
        this.datetime = event[1].split(" ");
    }

    @Override
    public String formatString() {
        return "E-" + super.checkStatus() + "-" + super.getDescription().trim() + "-" + this.event[1];
    }

    @Override
    public String toString() {
        try {
            return "[E]" + super.toString() +
                    "(at: " + super.getDate(this.datetime) + ", " + super.getTime(this.datetime) + ")";
        } catch (ParseException parseError) {
            return parseError.toString();
        }

    }
}
