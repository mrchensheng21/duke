import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String welcome = "--------------------------\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "\n"
                + "--------------------------";

        System.out.println(welcome);

        Scanner scanner = new Scanner(System.in);

    }
}
