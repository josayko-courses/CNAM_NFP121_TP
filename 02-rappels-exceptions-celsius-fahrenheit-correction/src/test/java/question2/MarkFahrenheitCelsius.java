package question2;

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
  public void test_présence_question2_FahrenheitCelsius() {
    test.test_présence_question2_FahrenheitCelsius();
  }

  @Test
  public void test_fahrenheitEnCelsius() {
    test.test_fahrenheitEnCelsius();
  }

  @Test
  public void test_fahrenheitEnCelsius2() {
    test.test_fahrenheitEnCelsius2();
  }

  // analyse de la trace écran ? oui pour les excepions ???
  // ne fonctionne pas en ligne de commande, ... supprimé
  // pour le moment, cela fonctionnait avec un fichier ...
  @Test
  public void test_console() throws Exception {
    test.test_console();
  }

  // analyse de la trace écran ? oui pour les excepions ???
  public void ignore_test_3_conversions_une_erreur() throws Exception {
    test.ignore_test_3_conversions_une_erreur();
  }

  @Test
  public void test_6_conversions_sans_erreur() throws Exception {
    test.test_6_conversions_sans_erreur();
  }
}
