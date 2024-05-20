import java.io.*;

/**
 * This class is in charge of creating a file,
 * where it writes all the errors occurred during runtime.
 */

public class ErrorLogger_Quiz {

    /**
     * Writes an error message to the "errors.log" file.
     *
     * @param message The error message to be logged.
     */

    public void error(String message) {
        try {
            // Create a File object representing the error log file
            File errorFile = new File("errors.log");
            // Checking if the file already exists
            if (!errorFile.exists()) {
                // Create a new file if it doesn't exist
                errorFile.createNewFile();
            }
            // Create a PrintWriter object for writing to the file
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(errorFile,
                    true)));
            writer.write(message);
            writer.println();

            // Write the error message to the file with a newline character
            writer.close();
        } catch (IOException e) {
            // Handle potential exceptions during file operations
            System.out.println("Error: The errorLogs file could not be opened");
            // Consider logging this error as well
        }
    }
}