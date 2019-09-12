import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * An abstract class to instantiate all the Task objects.
 */
public abstract class Task {
    protected String description;
    protected Boolean taskIsDone;

    /**
     * Task object is instantiated when User enters the description of task.
     * @param description Description of tasks.
     */
    protected Task (String description) {
        this.description = description;
        this.taskIsDone = false;
    }

    /**
     * For other classes to retrieve the description information.
     * @return Description of task.
     */
    protected String getDescription() {
        return this.description;
    }

    /**
     * For other classes to retrieve the status of task whether if its
     * completed or not.
     * @return Status of task.
     */
    protected String getStatusIcon() {
        return (taskIsDone ? "\u2713" : "\u2718");
    }

    /**
     * A method to check the status when saving the tasks into the .txt file.
     * @return Status of task, "1" means its done and "0" means its yet to be completed.
     */
    protected String checkStatus() {
        String statusWhenSavingTask = "";
        if (taskIsDone == true) {
            statusWhenSavingTask = "1";
        } else {
            statusWhenSavingTask = "0";
        }

        return statusWhenSavingTask;
    }

    /**
     * A method to revert the numerical status back to the boolean.
     * @param status The number "1" or "0".
     */
    protected void recoverStatus(String status) {
        if (status.equals("1")) {
            taskIsDone = true;
        } else {
            taskIsDone = false;
        }
    }

    /**
     * A flag to toggle when a task is done.
     */
    protected void markAsDone() {
        taskIsDone = true;
    }

    /**
     * Get the date from a numeric format eg. (23/05/2019) and convert it
     * into words (23rd of May 2019).
     * @return a String consisting the date in wording format
     */
    protected String getDate(String[] datetime) {
        String[] splitDates = datetime[0].split("/");
        String day = splitDates[0];
        String month = splitDates[1];
        String year = splitDates[2];

        try {
            return this.getDay(day) + " of " + this.getMonth(month) + " " + year;
        } catch (ParseException parseError) {
            return parseError.toString();
        }

    }

    /**
     * Get the formatted day.
     * @param day The numerical format of day.
     * @return The formatted word of day.
     */
    private String getDay(String day) {
        String editedDay = "";

        if (day.equals("1") || day.equals("01")) {
            editedDay = "1st";
        } else if (day.equals("2") || day.equals("02")) {
            editedDay = "2nd";
        } else if (day.equals("3") || day.equals("03")) {
            editedDay = "3rd";
        } else if (day.equals("21")) {
            editedDay = "21st";
        } else if (day.equals("22")) {
            editedDay = "22nd";
        } else if (day.equals("23")) {
            editedDay = "23rd";
        } else if (day.equals("31")) {
            editedDay = "31st";
        } else if (Integer.valueOf(day) < 31) {
            editedDay = day + "th";
        } else {
            assert false : "No such day";
        }

        return editedDay;
    }

    /**
     * Get the formatted month.
     * @param month Numerical month.
     * @return Formatted word of month.
     */
    private String getMonth(String month) throws ParseException {

        DateFormat df = new SimpleDateFormat("MM");
        DateFormat outputFormat = new SimpleDateFormat("MMMM");
        try {
            Date strToMonth = df.parse(month);
            String monthFormat = outputFormat.format(strToMonth);
            return monthFormat;
        } catch (ParseException parseError) {
            return parseError.toString();
        }

    }

    /**
     * Get the time format from 24Hr eg.(2300) to a 12Hr HH:MM format
     * eg.(11.00pm).
     * @return Time in 12Hr HH:MM format.
     */
    protected String getTime(String[] datetime) throws ParseException {
        DateFormat df = new SimpleDateFormat("HHmm");
        DateFormat outputFormat = new SimpleDateFormat("h:mm a");
        try {
            Date strToTime = df.parse(datetime[1]);
            String timeFormat = outputFormat.format(strToTime);
            return timeFormat;
        } catch (ParseException parseError) {
            return parseError.toString();
        }
    }


    /**
     * Format the String into a save file format.
     * @return Formatted String for the .txt file.
     */
    public abstract String formatString();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

}
