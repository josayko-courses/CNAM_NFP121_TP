package question1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import util.InfoClass;
import util.InfoMethod;

@TestInstance(Lifecycle.PER_CLASS) // Une seule instance pour tous les tests.
public class TestPresenceAppelsDeMethodes {

  private static final String Q1TESTPAT = "/question1/TestPatternObservateur.class";

  private static class T {
    private static final String TEST1 = "public void test1() [RuntimeVisibleAnnotations]";
    private static final String TEST2 = "public void test2() [RuntimeVisibleAnnotations]";
    private static final String TEST3 = "public void test3() [RuntimeVisibleAnnotations]";
  }

  private static class S {
    private static final String EMPTY = "java.util.Stack.empty()Z";
    private static final String POP = "java.util.Stack.pop()Ljava/lang/Object;";
  }

  private static class CS {
    private static final String INSERT = "question1.ConcreteSubject.insert(Ljava/lang/String;)V";
    private static final String ADDOBS =
        "question1.ConcreteSubject.addObserver(Ljava/util/Observer;)V";
    private static final String COUNTOBS = "question1.ConcreteSubject.countObservers()I";
    private static final String DELETEOBS =
        "question1.ConcreteSubject.deleteObserver(Ljava/util/Observer;)V";
    private static final String DELETEALLOBS = "question1.ConcreteSubject.deleteObservers()V";
  }

  InfoClass info;
  InfoMethod test1, test2, test3;

  @BeforeAll
  public void setUp2() throws Exception {
    // On lance la collecte d'infos (une seule fois avant les tests)
    // On regarde toutes les méthodes et pas seulement celles de test.
    info = new InfoClass(Q1TESTPAT);
    // System.out.println("INFO = " + info.toString());
    test1 = info.get(T.TEST1);
    test2 = info.get(T.TEST2);
    test3 = info.get(T.TEST3);
  }

  @Test
  public void test_presence_empty_dans_test1() {
    test1.assertNbCallsGreater(
        S.EMPTY, 4, "Les assertions à la fin de test1 ont-elles été modifiées ?");
  }

  @Test
  public void test_presence_cs_insert_dans_test1() {
    test1.assertNbCallsGreater(CS.INSERT, 2, "Avez vous modifié les 2 insertions?");
  }

  @Test
  public void test_presence_cs_addobs_dans_test1() {
    test1.assertNbCallsGreater(CS.ADDOBS, 2, "Avez vous modifié les 2 ajouts d'observateurs ?");
  }

  @Test
  public void test_presence_pop_dans_test1() {
    test1.assertNbCallsGreater(S.POP, 8, "Plusieurs appels de pop sont requis...");
  }

  @Test
  public void test_presence_empty_dans_test2() {
    test2.assertNbCallsGreater(
        S.EMPTY, 2, "Les assertions à la fin de test2 ont-elles été modifiées ?");
  }

  @Test
  public void test_presence_cs_insert_dans_test2() {
    test2.assertNbCallsGreater(CS.INSERT, 4, "Avez vous modifié les 4 insertions?");
  }

  @Test
  public void test_presence_cs_addobs_dans_test2() {
    test2.assertNbCallsGreater(CS.ADDOBS, 2, "Avez vous modifié les 2 ajouts d'observateurs ?");
  }

  @Test
  public void test_presence_pop_dans_test2() {
    test2.assertNbCallsGreater(S.POP, 8, "Plusieurs appels de pop sont requis...");
  }

  @Test
  public void test_presence_empty_dans_test3() {
    test3.assertNbCallsGreater(
        S.EMPTY, 2, "Les assertions à la fin de test3 ont-elles été modifiées ?");
  }

  @Test
  public void test_presence_countobs_dans_test3bis() {
    test3.assertNbCallsGreater(
        CS.COUNTOBS, 3, "Au moins un appel de countObservers en plus est requis...");
  }

  @Test
  public void test_presence_countobs_dans_test3() {
    test3.assertNbCallsGreater(
        CS.COUNTOBS, 2, "Avez vous modifié les appels finaux à countObservers?");
  }

  @Test
  public void test_presence_deleteobs_dans_test3() {
    test3.assertNbCallsGreater(
        CS.DELETEOBS, 1, "Au moins un appel de deleteObserver est requis...");
  }

  @Test
  public void test_presence_deleteallobs_dans_test3() {
    test3.assertNbCallsGreater(
        CS.DELETEALLOBS, 1, "Au moins un appel de deleteObservers est requis...");
  }
}
