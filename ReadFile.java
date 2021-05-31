import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {
   public static void main(String args[]) throws IOException {
      //Melist semua file yang ada di folder target
      File directoryPath = new File("D:\\yohan\\file");
      File filesList[] = directoryPath.listFiles();
     
      Scanner sc = null;
      for(File file : filesList) {
         System.out.println("File: "+file.getName());

         sc= new Scanner(file);
         String input;
         StringBuffer sb = new StringBuffer();
         while (sc.hasNextLine()) {
            input = sc.nextLine();
            sb.append(input+" ");
         }
         System.out.println("Content: "+sb.toString()+ "\n");
      }
   }
}
