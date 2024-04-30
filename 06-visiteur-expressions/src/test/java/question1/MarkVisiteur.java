package question1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MarkVisiteur {

  private Z_ReferenceVisiteurTests test;

  @BeforeEach
  public void setUp() {
    test = new Z_ReferenceVisiteurTests();
    test.setUp();
  }

  @Test
  public void testNewVariable() {
    test.testNewVariable();
  }

  @Test
  public void testUneConstante() {
    test.testUneConstante();
  }

  @Test
  public void testUneVariable() {
    test.testUneVariable();
  }

  @Test
  public void testUneAddition() {
    test.testUneAddition();
  }

  @Test
  public void testUneAddition2() {
    test.testUneAddition2();
  }

  @Test
  public void testUneAddition3() {
    test.testUneAddition3();
  }

  @Test
  public void testUneMultiplication() {
    test.testUneMultiplication();
  }

  @Test
  public void testMultiplication1() {
    test.testMultiplication1();
  }

  @Test
  public void testMultiplication2() {
    test.testMultiplication2();
  }

  @Test
  public void testUneSoustraction3() {
    test.testUneSoustraction3();
  }

  @Test
  public void testSoustraction() {
    test.testSoustraction();
  }

  @Test
  public void testSoustraction2() {
    test.testSoustraction2();
  }

  @Test
  public void testFactorielDe4() {
    test.testFactorielDe4();
  }

  @Test
  public void testFactorielDe5() {
    test.testFactorielDe5();
  }

  @Test
  public void testFactorielDeZero() {
    test.testFactorielDeZero();
  }

  @Test
  public void testFactorielDeNegatif() {
    test.testFactorielDeNegatif();
  }

  @Test
  public void testDivision() {
    test.testDivision();
  }

  // Le test de division par séro ci-dessus doit rester visible aux étudiants: il est cité dans
  // le sujet pour illustrer le comportement attendu.
  @Test
  public void testDivision2() {
    test.testDivision2();
  }

  @Test
  public void testVisiteurInfixe() {
    test.testVisiteurInfixe();
  }

  @Test
  public void testVisiteurPostfixe() {
    test.testVisiteurPostfixe();
  }
}
