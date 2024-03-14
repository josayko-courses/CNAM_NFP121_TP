package question1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

// N'éditez pas cette classe. Vous devez écrire vos tests dans TestPatternObservateur.
public class MarkPatternObservateur {

  @Test
  public void test_présence_fichiers_question1() {
    try {
      Class.forName("question1.ConcreteSubject");
      Class.forName("question1.ConcreteObserver");
      Class.forName("question1.TestPatternObservateur");
    } catch (ClassNotFoundException e) {
      fail("classe absente " + e.getMessage());
    }
  }

  // Les tests suivants ont été cachés car il ressemblent trop aux tests que vous
  // devez écrire par ailleurs
  // une liste, 2 observateurs
  @Test
  public void test_UneListe_DeuxObservateurs() {
    Z_ReferenceTestPatternObservateur test = new Z_ReferenceTestPatternObservateur();
    test.test_UneListe_DeuxObservateurs();
  }

  // deux listes, 1 observateur
  @Test
  public void test_DeuxListes_UnObservateur() {
    Z_ReferenceTestPatternObservateur test = new Z_ReferenceTestPatternObservateur();
    test.test_DeuxListes_UnObservateur();
  }

  // deux listes, 2 observateurs
  @Test
  public void test_DeuxListes_DeuxObservateurs() {
    Z_ReferenceTestPatternObservateur test = new Z_ReferenceTestPatternObservateur();
    test.test_DeuxListes_DeuxObservateurs();
  }
}
