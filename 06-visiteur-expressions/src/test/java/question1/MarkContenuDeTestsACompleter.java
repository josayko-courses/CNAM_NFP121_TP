package question1;

import java.io.*;
import java.util.regex.Pattern;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import util.InfoClass;
import util.InfoMethod;

@TestInstance(Lifecycle.PER_CLASS) // Une seule instance pour tous les tests.
public class MarkContenuDeTestsACompleter {

  private static final String Q1TESTS = "/question1/TestsACompleter.class";

  private class T {
    private static final String ACCEPTER_RE = "question1[.][^.]+[.]accepter[(].*";
    private static final String ADDITION =
        "question1.Addition.<init>(Lquestion1/Expression;Lquestion1/Expression;)V";
    private static final String SOUSTRACTION =
        "question1.Soustraction.<init>(Lquestion1/Expression;Lquestion1/Expression;)V";
    private static final String MULTIPLICATION =
        "question1.Multiplication.<init>(Lquestion1/Expression;Lquestion1/Expression;)V";
    private static final String DIVISION =
        "question1.Division.<init>(Lquestion1/Expression;Lquestion1/Expression;)V";
    private static final String VARIABLE =
        "question1.Variable.<init>(Lquestion1/Contexte;Ljava/lang/String;Ljava/lang/Integer;)V";
    private static final String CONSTANTE = "question1.Constante.<init>(I)V";
    private static final String VISITINFIX =
        "question1.VisiteurInfixe.<init>(Lquestion1/Contexte;)V";
    private static final String VISITPOSTFIX =
        "question1.VisiteurPostfixe.<init>(Lquestion1/Contexte;)V";
    private static final String VISITEVALUATION =
        "question1.VisiteurEvaluation.<init>(Lquestion1/Contexte;)V";
  }

  // contient une map stockant le nb d'appels à chaque méthode dans chaque méthode de test.
  InfoClass info = null;
  // Vue en un seul infomethod
  InfoMethod all; // pseudo -méthode avec tous les appels de la classe
  InfoMethod allTests; // avec tous les appels dans des méthodes de test

  @BeforeAll
  public void setUp() throws IOException {
    // On lance la collecte d'infos (une seule fois avant les tests)
    info = new InfoClass(MarkContenuDeTestsACompleter.Q1TESTS);
    // union de tous les appels de méthodes dans la classe
    all = info.get("#all#");
    // pareil mais seulement les méthodes de test
    allTests = info.get("#alltests#");
  }

  private static Pattern pat(String regexp) {
    return Pattern.compile(regexp);
  }

  @Test
  public void test_nombre_de_accepter() {
    allTests.assertNbCallsGreater(pat(T.ACCEPTER_RE), 8);
  }

  @Test
  public void test_nombre_de_tests() {
    info.assertNbTestGreater(8);
  }

  @Test
  public void test_nombre_de_Addition() {
    allTests.assertNbCallsGreater(T.ADDITION, 1);
  }

  @Test
  public void test_nombre_de_Soustraction() {
    allTests.assertNbCallsGreater(T.SOUSTRACTION, 1);
  }

  @Test
  public void test_nombre_de_Multiplication() {
    allTests.assertNbCallsGreater(T.MULTIPLICATION, 1);
  }

  // les lambdas ne sont pas détectés comme étant dans leur méthode appelante, on cherche donc dans
  // toutes le méthodes (all) et pas seulement dans les @Tests (allTests)
  @Test
  public void test_nombre_de_Division() {
    all.assertNbCallsGreater(T.DIVISION, 4);
  }

  @Test
  public void test_nombre_de_Variable() {
    all.assertNbCallsGreater(T.VARIABLE, 4);
  }

  @Test
  public void test_nombre_de_VisitConstante() {
    all.assertNbCallsGreater(T.CONSTANTE, 4);
  }

  @Test
  public void test_nombre_de_VisitEval() {
    all.assertNbCallsGreater(T.VISITEVALUATION, 1);
  }

  @Test
  public void test_nombre_de_VisitPost() {
    all.assertNbCallsGreater(T.VISITPOSTFIX, 1);
  }

  @Test
  public void test_nombre_de_VisitInf() {
    all.assertNbCallsGreater(T.VISITINFIX, 1);
  }
}
