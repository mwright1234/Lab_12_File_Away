import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInspector {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                System.out.println("Selected file: " + file.getAbsolutePath());

                try (Scanner fileScanner = new Scanner(file)) {
                    int lineCount = 0;
                    while (fileScanner.hasNextLine()) {
                        String line = fileScanner.nextLine();
                        System.out.println(line);
                        lineCount++;
                    }
                    System.out.println("Total lines: " + lineCount);
                } catch (FileNotFoundException e) {
                    System.out.println("Error reading the file: " + e.getMessage());
                }
            } else {
                System.out.println("No file selected. Exiting program.");
            }
        });
    }
}

