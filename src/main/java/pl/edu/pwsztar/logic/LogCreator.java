package pl.edu.pwsztar.logic;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.io.FileWriter;

public class LogCreator {

    public String crateLog(String log,String fileName)  {
        String createdLog = LocalDateTime.now()+" " +log;
        writeTextToFille(createdLog,fileName);
        return LocalDateTime.now()+" " +log;
    }

 private void writeTextToFille(String text,String fileName)  {
     try {
     FileWriter myWriter = new FileWriter(fileName);
         myWriter.write(text);
         myWriter.close();
         System.out.println("Successfully wrote logger to the file.");
     } catch (IOException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
     }
 }
}
