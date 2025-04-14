import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class DataSaver {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        int idCounter = 1;
        boolean cont = true;

        while (cont) {
            System.out.print("First Name: ");
            String firstName = in.nextLine();

            System.out.print("Last Name: ");
            String lastName = in.nextLine();

            String id = String.format("%06d", idCounter++);

            System.out.print("Email: ");
            String email = in.nextLine();

            System.out.print("Year of Birth (e.g., 1978): ");
            String yob = in.nextLine();

            String record = String.join(", ", firstName, lastName, id, email, yob);
            records.add(record);

            System.out.print("Add another record? (Y/N): ");
            String response = in.nextLine().trim().toUpperCase();
            cont = response.equals("Y");
        }

        // Save to file
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\savedData.txt");

        try {
            OutputStream out = Files.newOutputStream(file, CREATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String rec : records) {
                writer.write(rec);
                writer.newLine();
            }

            writer.close();
            System.out.println("Data saved successfully to " + file.toString());
        } catch (IOException e) {
            System.out.println("Error writing to file.");
            e.printStackTrace();
        }
    }
}
