import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * An abstract class to instantiate all the Task objects.
 */
public abstract class Task {
    protected String description;
    protected Boolean isdone;

    /**
     * Task object is instantiated when User enters the description of task.
     * @param description Description of tasks.
     */
    protected Task (String description) {
        this.description = description;
        this.isdone = false;
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
        return (isdone ? "\u2713" : "\u2718");
    }

    /**
     * A method to check the status when saving the tasks into the .txt file.
     * @return Status of task, "1" means its done and "0" means its yet to be completed.
     */
    protected String checkStatus() {
        if (isdone == true) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * A method to revert the numerical status back to the boolean.
     * @param status The number "1" or "0".
     */
    protected void recoverStatus(String status) {
        if (status.equals("1")) {
            isdone = true ;
        } else {
            isdone = false;
        }
    }

    /**
     * A flag to toggle when a task is done.
     */
    protected void markAsDone() {
        isdone = true;
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

        return this.getDay(day) + " of " + this.getMonth(month) + " " + year;
    }

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
     * 
     * @param month
     * @return
     */
    private String getMonth(String month) {
        String editedMonth = "";

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

        return editedMonth;
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
