package question1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS) // Une seule instance pour tous les tests.
public class MarkFahrenheitCelsius {

  private Z_ReferenceFahrenheitCelsiusTest test;

  @BeforeAll
  public void setUp() {
    test = new Z_ReferenceFahrenheitCelsiusTest();
  }

  @Test
  public void test_présence_question1_FahrenheitCelsius() {
    test.test_présence_question1_FahrenheitCelsius();
  }

  @Test
  public void test_fahrenheitEnCelsius() {
    test.test_fahrenheitEnCelsius();
  }

  @Test
  public void test_sortie_console_conforme() throws Exception {
    test.test_sortie_console_conforme();
  }
}
