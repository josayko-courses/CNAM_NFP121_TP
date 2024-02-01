package question1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MarkFormes {
  // 	public void test_toujours_FAUX(){
  // 		assertTrue("tjs faux, juste pour les tests",false);
  // 	}

  public void ignore_test_présence_fichiers() {
    try {
      Class.forName("question1.Triangle");
      Class.forName("question1.Square");
      Class.forName("question1.Circle");
      Class.forName("question1.Canvas");
    } catch (ClassNotFoundException e) {
      fail("classe absente " + e.getMessage());
    }
  }

  @Test
  public void test_triangle() {
    question1.Triangle t = new question1.Triangle();
    assertNotNull(t);
  }

  @Test
  public void test_Square() {
    question1.Square t = new question1.Square();
    assertNotNull(t);
  }

  @Test
  public void test_Circle() {
    question1.Circle t = new question1.Circle();
    assertNotNull(t);
  }
}
