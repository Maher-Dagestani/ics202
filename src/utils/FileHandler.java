/* 
   Usage:
     FileHandler f = new FileHandler("filename");
     while (f.hasNext()) {
       String token = f.next();
     }

     f.write("Hello\n", false);
     write(Data, true if add to the file, false if rewrite the whole file)
*/

package utils;

import DS.Queue;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class FileHandler {
  private File file;
  private String filename;
  private Queue<String> tokens;

  public FileHandler(String filename) {
    this.file = new File(filename);
    this.filename = filename;
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
  public String peek() { return tokens.peek(); }
  public boolean hasNext() { return !tokens.isEmpty(); }
  public boolean isFileEmpty() { return file.length() == 0; }
  public void write(String text, boolean append) {
    try {
      FileWriter writer = new FileWriter(this.filename, append);
      writer.write(text);
      writer.close();
    } catch(IOException e) {
      System.out.println("Couldn't write to the leaderboard.");
    }
  }
}
