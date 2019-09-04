import java.util.ArrayList;

/**
 * This class is created to make sense of the User commands and input.
 */
public class Parser {
    private TaskList tasksList;
    private Ui ui;

    /**
     * Parser class is instantiated by passing TaskList and Ui class parameters.
     * @param tasksList TaskList class.
     * @param ui Ui class.
     */
    protected Parser(TaskList tasksList, Ui ui) {
        this.tasksList = tasksList;
        this.ui = ui;
    }

    /**
     * Making sense of the User's input, and to throw an IllegalCommandException when User enter an invalid
     * command.
     * @param command Inputs of the User.
     */
    protected void parse(String command) {
        try {
            if (command.contains("todo") || command.contains("deadline") || command.contains("event")) {
                this.tasksList.addTask(command);
                ui.getAddedMessage(this.tasksList.getTaskList());
            } else if (command.contains("delete")) {
                String deletedTask = this.tasksList.deleteTask(command); // retrieve the deleted task.
                ui.getDeletedMessage(this.tasksList.getTaskList(), deletedTask);
            } else if (command.contains("done")) {
                String taskDoneStr = this.tasksList.doneTask(command);  // retrieve the task that is done.
                ui.getDoneMessage(taskDoneStr);
            } else if (command.contains("list")) {
                ui.showList(this.tasksList.getTaskList());
            } else if (command.contains("find")) {
                ArrayList<Task> foundTask = this.tasksList.findTasks(command);
                ui.showFoundMessage(foundTask);
            } else {
                throw new IllegalCommandException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (IllegalCommandException errorMsg) {
            ui.getIllegalCommandError(errorMsg);
        }
    }

    /**
     * To retrieve the taskList.
     * @return The stored taskList.
     */
    protected ArrayList<Task> retrieveTasks() {
        return this.tasksList.getTaskList();
    }
}