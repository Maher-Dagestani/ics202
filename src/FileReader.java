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
  private Queue<String> tokens;

  public FileReader(String filename) {
    this.file = new File(filename);
    this.tokens = new Queue<>();

    try (Scanner reader = new Scanner(this.file)) {
      while (reader.hasNextLine()) {
        String line = reader.nextLine();
        int st = 0;
        int end = line.indexOf(',');
        while (end != -1) {
          String token = line.substring(st, end);
          tokens.enqueue(token.strip());
          st = end + 1;
          end = line.indexOf(',', st);
        }
        tokens.enqueue(line.substring(st, line.length()).strip());

      }
    } catch (FileNotFoundException e) {
      System.out.printf("File (%s) not found.\n", filename);
    }
  }

  public String next() { return tokens.dequeue(); }
  public boolean hasNext() { return !tokens.isEmpty(); }
  public String peek() {return tokens.peek(); }
}
