package control;

import java.io.*;

public class SafetyController {
	
	public static String readTextFile() throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        File file = new File("safety_instructions.txt");

        if (!file.exists()) {
            throw new IOException("File not found: safety_instructions.txt " );
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }

        return contentBuilder.toString();
    }

}
