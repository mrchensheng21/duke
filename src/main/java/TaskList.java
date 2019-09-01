import java.util.ArrayList;

/**
 * TaskList is a class to process all the commands and adds them into
 * the tasklist. It is to split the Strings and allocate them accordingly
 * to the different Task classes.
 */
public class TaskList {
    protected ArrayList<Task> taskList;

    /**
     * Instantiate the TaskList class by passing an Arraylist as a parameter.
     * @param taskList A list to store all the tasks written by the User.
     */
    protected TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Instantiate the TaskList class when there are no existing tasks in the .txt file.
     * A new Arraylist is created to store the tasks.
     */
    protected TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Add tasks to the tasks list
     * @param command The description of task and date/time(if applicable) from the User
     * @throws IllegalCommandException If the User inputs a wrong/invalid command.
     */
    protected void addTask(String command) throws IllegalCommandException {
        if (!command.contains(" ")) {
            throw new IllegalCommandException("The description of a todo cannot be empty.");
        } else if (command.contains("todo")) {
            String[] splitCommand = command.split(" ", 2);
            taskList.add(new ToDos(splitCommand[1]));
        } else if (command.contains("event")) {
            String[] splitCommand = command.split(" ", 2);
            String[] splitEvent = (splitCommand[1].split("/", 2));
            taskList.add(new Events(splitEvent[0], splitEvent[1]));
        } else if (command.contains("deadline")) {
            String[] splitCommand = command.split(" ", 2);
            String[] splitDeadline = (splitCommand[1].split("/", 2));
            taskList.add(new Deadline(splitDeadline[0], splitDeadline[1]));
        }
    }

    /**
     * Delete the task that the User inputs in number. Eg. (User types "delete 3" means to
     * delete the task 3 from the tasks list).
     * @param command The command delete and the number of the task User wants to delete
     * @return the updated tasks list after deletion
     * @throws IllegalCommandException if User did not enter a number after delete or invalid
     * command.
     */
    protected String deleteTask(String command) throws IllegalCommandException {
        if (!command.contains(" ")) {
            throw new IllegalCommandException("Give a task to delete.");
        } else  {
            String[] splitCommand = command.split(" ");
            int taskDelete = Integer.valueOf(splitCommand[1]);
            String deleted = taskList.get(taskDelete - 1).getDescription();
            taskList.remove(taskDelete-1);
            return deleted;
        }
    }

    /**
     * Set the status of the selected task to be done. Eg. (User inputs "done 3" means User
     * wants to indicate that task 3 in the list is completed).
     * @param command The command done and the number of the task User set as done.
     * @return Updated tasks list with the selected task being marked done.
     * @throws IllegalCommandException if User did not enter a number after delete or invalid
     * command.
     */
    protected String doneTask(String command) throws IllegalCommandException {
        if (!command.contains(" ")) {
            throw new IllegalCommandException("There must be a number after done.");
        } else {
            String[] splitString = command.split(" ");
            int taskDone = Integer.valueOf(splitString[1]);
            taskList.get(taskDone - 1).markAsDone();
            return taskList.get(taskDone - 1).getDescription();
        }
    }

    /**
     * Other classes can retrieve the tasks list
     * @return The tasks list.
     */
    protected ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    protected ArrayList<Task> findTasks(String command) throws IllegalCommandException {
        if (!command.contains(" ")) {
            throw new IllegalCommandException("There must be an input to find.");
        } else {
            String[] splitString = command.split(" ",  2);
            ArrayList<Task> foundTasks = new ArrayList<>();
            for (Task task : this.taskList) {
                if (task.toString().contains(splitString[1])) {
                    foundTasks.add(task);
                }
            }
            return foundTasks;
        }
    }

}
