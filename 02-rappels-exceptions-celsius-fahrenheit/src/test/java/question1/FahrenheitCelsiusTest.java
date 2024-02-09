package question1;

/**
 * Classe-test FahrenheitCelsiusTest.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FahrenheitCelsiusTest {
  // Définissez ici les variables d'instance nécessaires à vos engagements (fixtures);

  /** Constructeur de la classe-test FahrenheitCelsiusTest */
  public FahrenheitCelsiusTest() {}

  /**
   * Met en place les engagements.
   *
   * <p>Méthode appelée avant chaque appel de méthode de test.
   */
  @BeforeEach
  protected void setUp() // throws java.lang.Exception
      {
    // Initialisez ici vos engagements
  }

  /**
   * Supprime les engagements
   *
   * <p>Méthode appelée après chaque appel de méthode de test.
   */
  @AfterEach
  protected void tearDown() // throws java.lang.Exception
      {
    // Libérez ici les ressources engagées par setUp()
  }

  /**
   * Il ne vous reste plus qu'à définir une ou plusieurs méthodes de test. Ces méthodes doivent
   * vérifier les résultats attendus à l'aide d'assertions assertXXX. Par convention, leurs noms
   * devraient débuter par "test". Les IDE permettre d'ébaucher les méthodes de tests. Exemple: dans
   * eclipse: clique droit sur la classe / new / Junit test / s'assurer que la classe va bien être
   * mise dans le répertoire src/test/java/lebonpackage/.
   */
  @Test
  public void test_fahrenheitEnCelsius() {
    assertEquals(
        -17.7, question1.FahrenheitCelsius.fahrenheitEnCelsius(0), 0.1, "    0 °F -> -17.7 °C ? ");
    assertEquals(
        37.7, question1.FahrenheitCelsius.fahrenheitEnCelsius(100), 0.1, "  100 °F -> 37.7 °C ? ");
    assertEquals(
        1093.3,
        question1.FahrenheitCelsius.fahrenheitEnCelsius(2000),
        0.1,
        " 2000 °F -> 1093.3 °C ?");
    assertEquals(
        12.2, question1.FahrenheitCelsius.fahrenheitEnCelsius(54), 0.1, "   54 °F -> 12.2 °C ?");
  }

}
