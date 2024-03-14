package question3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import org.junit.jupiter.api.Test;

public class MarkPresenceDuFichier {
  @Test
  public void test_présence_question3_FahrenheitCelsius() {
    try {
      Class.forName("question3.IHMFahrenheit");
    } catch (ClassNotFoundException e) {
      fail("classe absente ??? " + e.getMessage()); // + " ( présentes " + directory() + ")"
    }
  }

  public String directory() {
    File dir = new File("question3");

    String[] children = dir.list();
    if (children == null) {
      // Either dir does not exist or is not a directory
    } else {
      for (int i = 0; i < children.length; i++) {
        // Get filename of file or directory
        @SuppressWarnings("unused")
        String filename = children[i];
      }
    }
    String res = new String();
    for (String s : children) {
      if (!s.startsWith("Z_")) // filtre au cas où
      res = res + s + " ";
    }
    return res;
  }
}
