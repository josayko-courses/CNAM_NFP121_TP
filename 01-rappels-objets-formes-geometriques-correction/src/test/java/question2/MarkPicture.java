package question2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MarkPicture {

  public void ignore_test_présence_fichiers() {
    try {
      Class.forName("question2.Picture");
    } catch (ClassNotFoundException e) {
      fail("classe absente " + e.getMessage());
    }
  }

  @Test
  public void test_Picture() {
    question2.Picture p = new question2.Picture();
    assertNotNull(p);
  }
}
