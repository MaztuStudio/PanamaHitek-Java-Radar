/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examples.arduino;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.sql.Timestamp;
import static org.apache.poi.hssf.usermodel.HeaderFooter.date;

public class lll {
  public static void main(String[] args) throws IOException {
      
        FileReader fr=null;
         Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
        try
        {
            fr = new FileReader("C:/Users/marti/Desktop/Nuevo Documento de texto.txt");
        }
        catch (FileNotFoundException fe)
        {
            System.out.println("File not found");
        }
        int j=0;
        int ch;
        String todo="";
        
        while ((ch=fr.read())!=-1){
        todo=todo+(char)ch;
        }
      
      
    try {
      FileWriter myWriter = new FileWriter("C:/Users/marti/Desktop/Nuevo Documento de texto.txt");
      myWriter.write(todo+"\n"+todo+timestamp);
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}