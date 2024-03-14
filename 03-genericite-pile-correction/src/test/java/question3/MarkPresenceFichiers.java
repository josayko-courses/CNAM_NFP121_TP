package question3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MarkPresenceFichiers {
  @Test
  public void test_presence_fichiers_question3() {
    try {
      Class.forName("question3.PileI");
      Class.forName("question3.Pile2");
      Class.forName("question3.UneUtilisation");
    } catch (ClassNotFoundException e) {
      fail("classe absente " + e.getMessage());
    }
  }
}
