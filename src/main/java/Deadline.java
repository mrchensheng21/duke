import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Creates a Deadline object which extends from the Task class.
 * A <code>description</code> is passed into this class to
 * instantiate a Deadline task.
 */
public class Deadline extends Task {
    private String by;
    private String[] deadline;
    private String[] datetime;

    /**
     * Instantiate a Events object by passing a String of description and time
     * @param description Description of the deadline task.
     * @param by The date and time of the deadline.
     */
    protected Deadline(String description, String by) {
        super(description);
        this.by = by;
        deadline = by.split(" ", 2);
        this.datetime = deadline[1].split(" ");
    }

    /**
     * Get the date from a numeric format eg. (23/05/2019) and convert it
     * into words (23rd of May 2019).
     * @return a String consisting the date in wording format
     */
    private String getDate() {
        String[] splitDates = this.datetime[0].split("/");
        String day = splitDates[0];
        String month = splitDates[1];
        String year = splitDates[2];
        String editedDay = ""; //String to store the day from numerical to words
        String editedMonth = ""; //String to store the month from numerical to words

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

        switch (month) {
            case "1":
            case "01":
                editedMonth = "January";
                break;

            case "2":
            case "02":
                editedMonth = "Febuary";
                break;

            case "3":
            case "03":
                editedMonth = "March";
                break;

            case "4":
            case "04":
                editedMonth = "April";
                break;

            case "5":
            case "05":
                editedMonth = "May";
                break;

            case "6":
            case "06":
                editedMonth = "June";
                break;

            case "7":
            case "07":
                editedMonth = "July";
                break;

            case "8":
            case "08":
                editedMonth = "August";
                break;

            case "9":
            case "09":
                editedMonth = "September";
                break;

            case "10":
                editedMonth = "October";
                break;

            case "11":
                editedMonth = "November";
                break;

            case "12":
                editedMonth = "December";
                break;

            default:
                assert false : "Invalid month";
                break;

        }

        return editedDay + " of " + editedMonth + " " + year;
    }

    /**
     * Get the time format from 24Hr eg.(2300) to a 12Hr HH:MM format
     * eg.(11.00pm).
     * @return Time in 12Hr HH:MM format.
     */
    private String getTime() throws ParseException {
        DateFormat df = new SimpleDateFormat("HHmm");
        DateFormat outputFormat = new SimpleDateFormat("h:mm a");
        try {
            Date strToTime = df.parse(this.datetime[1]);
            String timeFormat = outputFormat.format(strToTime);
            return timeFormat;
        } catch (ParseException parseError) {
            return parseError.toString();
        }
    }


    @Override
    public String formatString() {
        return "D-" + super.checkStatus() + "-" + super.getDescription().trim() + "-" + this.deadline[1];
    }

    @Override
    public String toString() {
        try {
            return "[D]" + super.toString() +
                    "(by: " + this.getDate() + ", " + this.getTime() + ")";
        } catch (ParseException parseError) {
            return parseError.toString();
        }

    }
}
