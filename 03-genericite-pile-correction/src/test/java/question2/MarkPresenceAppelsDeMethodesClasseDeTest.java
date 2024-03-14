package question2;

import java.io.IOException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import util.InfoClass;
import util.InfoMethod;

@TestInstance(Lifecycle.PER_CLASS) // Une seule instance pour tous les tests.
public class MarkPresenceAppelsDeMethodesClasseDeTest {
  private static final String Q2TESTS = "/question2/PileTestN.class";

  private class P {
    public static final String EMPILER = "question2.PileI.empiler(Ljava/lang/Object;)V";
    public static final String CAPACITE = "question2.PileI.capacite()I";
    public static final String ESTPLEINE = "question2.PileI.estPleine()Z";
    public static final String TAILLE = "question2.PileI.taille()I";
    public static final String DEPILER = "question2.PileI.depiler()Ljava/lang/Object;";
    public static final String ESTVIDE = "question2.PileI.estVide()Z";
    public static final String SOMMET = "question2.PileI.sommet()Ljava/lang/Object;";
  }

  // contient une map stockant le nb d'appels à chaque méthode dans chaque méthode de test.
  InfoClass info = null;
  // Vue en un seul infomethod
  InfoMethod all; // pseudo -méthode avec tous les appels de la classe
  InfoMethod allTests; // avec tous les appels dans des méthodes de test

  @BeforeAll
  public void setUp() throws IOException {
    // On lance la collecte d'infos (une seule fois avant les tests)
    info = new InfoClass(Q2TESTS);
    // System.out.println("infoclass: " + info.callMap);
    // union de tous les appels de méthodes dans la classe
    all = info.get("#all#");
    // pareil mais seulement les méthodes de test
    allTests = info.get("#alltests#");
  }

  @Test
  public void test_NbmethodesTest() {
    info.assertNbTestGreater(4);
  }

  @Test
  public void test_appels_capacite() {
    allTests.assertNbCallsGreater(P.CAPACITE, 1);
  }

  @Test
  public void test_appels_empiler() {
    allTests.assertNbCallsGreater(P.EMPILER, 1);
  }

  @Test
  public void test_appels_taille() {
    allTests.assertNbCallsGreater(P.TAILLE, 1);
  }

  @Test
  public void test_appels_estPleine() {
    allTests.assertNbCallsGreater(P.ESTPLEINE, 1);
  }

  @Test
  public void test_appels_depiler() {
    allTests.assertNbCallsGreater(P.DEPILER, 1);
  }

  @Test
  public void test_appels_estVide() {
    allTests.assertNbCallsGreater(P.ESTVIDE, 1);
  }

  @Test
  public void test_appels_sommet() {
    allTests.assertNbCallsGreater(P.SOMMET, 1);
  }
}
