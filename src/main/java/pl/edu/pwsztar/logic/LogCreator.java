package pl.edu.pwsztar.logic;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Component
public class LogCreator {

    public String crateLog(String log,String fileName)  {
        String createdLog = LocalDateTime.now()+" " +log;
        writeTextToFille(createdLog,fileName);
        return LocalDateTime.now()+" " +log;
    }

 private void writeTextToFille(String text,String fileName)  {
     try {

         // Open given file in append mode by creating an
         // object of BufferedWriter class
         BufferedWriter out = new BufferedWriter(
                 new FileWriter(fileName, true));

         // Writing on output stream
         out.write(text);
         out.newLine();
         // Closing the connection
         out.close();
     }

     // Catch block to handle the exceptions
     catch (IOException e) {

         // Display message when exception occurs
         System.out.println("exception occurred" + e);
     }
 }
}
