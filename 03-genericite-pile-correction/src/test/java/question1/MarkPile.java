package question1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MarkPile {

  private question1.Z_ReferencePileTest test;

  @BeforeEach
  public void setUp() {
    test = new Z_ReferencePileTest();
    test.setUp();
  }

  @Test
  public void test_toString() throws PilePleineException {
    test.test_toString();
  }

  @Test
  public void test_PilePleine() throws PilePleineException {
    test.test_PilePleine();
  }

  @Test
  public void test_PileVide() {
    test.test_PileVide();
  }

  @Test
  public void test_estVide() throws Exception {
    test.test_estVide();
  }

  @Test
  public void test_TailleNegative() throws PilePleineException {
    test.test_TailleNegative();
  }
}
