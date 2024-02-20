package question2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MarkPresenceFichiers {
  @Test
  public void test_presence_fichiers_question2() {
    try {
      Class.forName("question2.PileI");
      Class.forName("question2.Pile");
      Class.forName("question2.Pile2");
      Class.forName("question2.Pile3");
      Class.forName("question2.Pile4");
      Class.forName("question2.MarkMemeComportement");
    } catch (ClassNotFoundException e) {
      fail("classe absente " + e.getMessage());
    }
  }
}
