package question1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import util.InfoClass;
import util.InfoMethod;

@TestInstance(Lifecycle.PER_CLASS) // Une seule instance pour tous les tests.
public class MarkPresenceDeTestsNouveaux {

  private static final String Q1TESTS = "/question1/FahrenheitCelsiusTest.class";
  private static final String FAHRENHEITCELSIUS =
      "question1.FahrenheitCelsius.fahrenheitEnCelsius(I)F";

  // contient une map stockant le nb d'appels à chaque méthode dans chaque méthode de test.
  InfoClass info = null;
  // Vue en un seul infomethod
  InfoMethod all; // pseudo -méthode avec tous les appels de la classe
  InfoMethod allTests; // avec tous les appels dans des méthodes de test

  @BeforeAll
  public void setUp() throws IOException {
    // On lance la collecte d'infos (une seule fois avant les tests)
    info = new InfoClass(Q1TESTS);
    // System.out.println("infoclass: " + info.callMap);
    // union de tous les appels de méthodes dans la classe
    all = info.get("#all#");
    // pareil mais seulement les méthodes de test
    allTests = info.get("#alltests#");
    //    for (Map.Entry<String, InfoMethod> e : info.callMap.entrySet()) {
    //      System.out.println("clé = [" + e.getKey() + "]");
    //    }
  }

  @Test
  public void test_presence_de_methodes_de_test_dans_FahrenheitCelsiusTest() {
    assertFalse(
        info.getNbTestsMethods() == 1,
        "Avez vous ajouté vos méthodes de tests (dans FahrenheitCelsiusTest) ?");
    info.assertNbTestGreater(4);
  }

  @Test
  public void test_presence_d_appels_a_lamethode_dans_FahrenheitCelsiusTest() {
    allTests.assertNbCallsGreater(FAHRENHEITCELSIUS, 12);
  }
}
