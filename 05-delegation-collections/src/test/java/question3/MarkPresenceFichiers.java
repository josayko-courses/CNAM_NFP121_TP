package question3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MarkPresenceFichiers {
  @Test
  public void test_présence_fichiers_question3() {
    try {
      Class.forName("question3.Factory");
      Class.forName("question3.TreeSetFactory");
      Class.forName("question3.HashSetFactory");
    } catch (ClassNotFoundException e) {
      fail("classe absente " + e.getMessage());
    }
  }
}
