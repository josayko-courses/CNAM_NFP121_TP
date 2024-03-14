package question1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MarkTriangle {

  public void ignore_test_présence_fichiers() {
    try {
      Class.forName("question1.Triangle");
    } catch (ClassNotFoundException e) {
      fail("classe absente " + e.getMessage());
    }
  }

  @Test
  public void test_création() {
    question1.Triangle t = new question1.Triangle();
    assertNotNull(t);
  }
}
