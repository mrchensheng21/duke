import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Store the tasks list into a .txt file after User exits the Duke application.
 * It also loads up the tasks list from the selected .txt file when User starts up
 * the Duke application.
 */
public class Storage {
    private String filepath;
    private ArrayList<Task> taskList;
    /**
     * Load the file contents of the .txt file and to input the saved tasks into
     * an Arraylist.
     * @param filePath Directory of the .txt file.
     * @param storingList An Arraylist to store the tasks in the .txt save file.
     * @throws FileNotFoundException if there are no .txt file found in the directory
     * @throws DukeException if the .txt file is an empty file
     */
    private static void loadFileContents(String filePath, ArrayList<Task> storingList)
            throws FileNotFoundException, DukeException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNextLine()) {
            String currentLine = s.nextLine();
            processTaskFile(currentLine, storingList);
        }
    }

    /**
     * Method from processing the information from each Task to instantiate
     * each Task object instance.
     * @param currentLine Selected line of task.
     * @param storingList ArrayList to store the task.
     */
    private static void processTaskFile (String currentLine, ArrayList<Task> storingList) {
        String[] splitCurrentLine = currentLine.split("-");
        if (splitCurrentLine[0].equals("D")) {
            storingList.add(new Deadline(splitCurrentLine[2],
                    "by " + splitCurrentLine[3]));
            storingList.get(storingList.size()-1).recoverStatus(splitCurrentLine[1]);
        } else if (splitCurrentLine[0].equals("E")) {
            storingList.add(new Events(splitCurrentLine[2],
                    "at " + splitCurrentLine[3]));
            storingList.get(storingList.size()-1).recoverStatus(splitCurrentLine[1]);
        } else if (splitCurrentLine[0].equals("T"))  {
            storingList.add(new ToDos(splitCurrentLine[2]));
            storingList.get(storingList.size()-1).recoverStatus(splitCurrentLine[1]);
        }
    }

    /**
     * Save the tasks into the .txt file allocated in the directory.
     * @param filepath Directory for the .txt file.
     * @param taskList ArrayList of tasks that the User inputs.
     * @throws IOException if there is an error of copying the list to the file.
     */
    private static void WriteTextToFile(String filepath, ArrayList<Task> taskList)
            throws IOException {
        FileWriter fileOut = new FileWriter(filepath);
        storeTask(fileOut, taskList);
    }

    /**
     * Process of storing each tasks into the .txt file
     * @param fileOut File storing the tasks.
     * @param taskList ArrayList of tasks User inputs.
     * @throws IOException If there is an error closing the file.
     */
    private static void storeTask(FileWriter fileOut, ArrayList<Task> taskList)
            throws IOException {
        for (int i = 0; i < taskList.size(); i++) {
            writeTask(fileOut, i, taskList);
        }
        fileOut.close();
    }

    /**
     * Write the formatted task into the .txt file.
     * @param fileOut File storing the tasks.
     * @param index of the task.
     * @param taskList ArrayList of tasks User inputs.
     * @throws IOException If there is an error writing the file.
     */
    private static void writeTask (FileWriter fileOut,int index, ArrayList<Task> taskList)
            throws IOException {
            if (index == taskList.size() - 1) {
                fileOut.write(taskList.get(index).formatString());
            } else {
                fileOut.write(taskList.get(index).formatString() + "\n");
            }
    }


    /**
     * Instantiate a Storage object.
     * @param filepath the directory for the allocated .txt file.
     */
    protected Storage(String filepath) {
        this.filepath = filepath;
        this.taskList = new ArrayList<Task>();
    }

    /**
     * A method to save the tasks that is being keyed in by the User
     * @param taskList the list of tasks User inputs
     */
    protected void save(ArrayList<Task> taskList) {
        try {
            Storage.WriteTextToFile(this.filepath, taskList);
        } catch (IOException e) {
            System.err.println("Something went wrong!");
        }
    }

    /**
     * A method to load the .txt file and to store the tasks into
     * an ArrayList.
     * @return the Arraylist of tasks.
     * @throws DukeException if there are no tasks found in the .txt file.
     */
    protected ArrayList<Task> load() throws DukeException {
        try {
            Storage.loadFileContents(this.filepath, this.taskList);
        } catch (FileNotFoundException e) {
            System.err.println("No file found, creating new file");
            File file = new File(this.filepath);
        }
        return this.taskList;
    }
}
