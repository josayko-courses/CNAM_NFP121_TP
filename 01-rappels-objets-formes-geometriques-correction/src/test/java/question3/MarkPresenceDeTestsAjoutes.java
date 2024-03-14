package question3;

import java.io.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import util.InfoClass;
import util.InfoMethod;

@TestInstance(Lifecycle.PER_CLASS) // Une seule instance pour tous les tests.
public class MarkPresenceDeTestsAjoutes {
  private static final String Q3TESTS = "/question3/AuditeurCNAMTest.class";

  private class A {
    private static final String MATRICULE = "question3.AuditeurCNAM.matricule()Ljava/lang/String;";
    private static final String LOGIN = "question3.AuditeurCNAM.login()Ljava/lang/String;";
    private static final String PRENOM = "question3.AuditeurCNAM.prenom()Ljava/lang/String;";
    private static final String NOM = "question3.AuditeurCNAM.nom()Ljava/lang/String;";
    private static final String TOSTRING = "question3.AuditeurCNAM.toString()Ljava/lang/String;";
  }

  // contient une map stockant le nb d'appels à chaque méthode dans chaque méthode de test.
  InfoClass info = null;
  // Vue en un seul infomethod
  InfoMethod all; // pseudo -méthode avec tous les appels de la classe
  InfoMethod allTests; // avec tous les appels dans des méthodes de test

  @BeforeAll
  public void setUp() throws IOException {
    // On lance la collecte d'infos (une seule fois avant les tests)
    info = new InfoClass(Q3TESTS);
    // union de tous les appels de méthodes dans la classe
    all = info.get("#all#");
    // pareil mais seulement les méthodes de test
    allTests = info.get("#alltests#");
  }

  // Attention les appels de méthodes (ici à nom()) ne sont détectés que dans des méthodes annotées
  // avec @Test.
  @Test
  public void test_presence_appels_nom() {
    allTests.assertNbCallsGreater(A.NOM, 7);
  }

  @Test
  public void test_presence_appels_prenom() {
    allTests.assertNbCallsGreater(A.PRENOM, 7);
  }

  @Test
  public void test_presence_appels_login() {
    allTests.assertNbCallsGreater(A.LOGIN, 7);
  }

  @Test
  public void test_presence_appels_matricule() {
    allTests.assertNbCallsGreater(A.MATRICULE, 1);
  }

  @Test
  public void test_presence_appels_toString() {
    allTests.assertNbCallsGreater(A.TOSTRING, 2);
  }
}
