/* 
   Usage:
     FileReader f = new FileReader("filename");
     while (f.hasNext()) {
       String token = f.next();
     }
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FileReader {
  private File file;
  private Stack<String> tokens;

  public FileReader(String filename) {
    this.file = new File(filename);
    this.tokens = new Stack<>();

    try (Scanner reader = new Scanner(this.file)) {
      while (reader.hasNextLine()) {
        String line = reader.nextLine();
        int st = 0;
        int end = line.indexOf(',');
        while (end != -1) {
          String token = line.substring(st, end);
          tokens.push(token.strip());
          st = end + 1;
          end = line.indexOf(',', st);
        }
      }
    } catch (FileNotFoundException e) {
      System.out.printf("File (%s) not found.\n", filename);
    }
  }

  public String next() { return tokens.pop(); }
  public boolean hasNext() { return !tokens.isEmpty(); }
}
