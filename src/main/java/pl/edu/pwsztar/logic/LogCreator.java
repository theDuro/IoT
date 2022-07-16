package pl.edu.pwsztar.logic;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.io.FileWriter;

public class LogCreator {
    private final String LOGGER_TXT_REFERENCE ="src/main/resources/logs.txt";
    public String crateLog(String log)  {
        String createdLog = LocalDateTime.now()+" " +log;
        writeTextToFille(createdLog);
        return LocalDateTime.now()+" " +log;
    }

 private void writeTextToFille(String text)  {
     try {
     FileWriter myWriter = new FileWriter(LOGGER_TXT_REFERENCE);
         myWriter.write(text);
         myWriter.close();
         System.out.println("Successfully wrote logger to the file.");
     } catch (IOException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
     }
 }
}
