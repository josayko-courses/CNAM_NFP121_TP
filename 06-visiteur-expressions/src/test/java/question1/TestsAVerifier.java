package question1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestsAVerifier {
  private Memoire m;
  private Variable i, j;
  private VisiteurExpression<Integer> ve;
  private VisiteurExpression<String> vp, vi;

  @BeforeEach
  public void setUp() {
    m = new Memoire();
    i = new Variable(m, "i", 3);
    j = new Variable(m, "j", 5);
    ve = new VisiteurEvaluation(m);
    vi = new VisiteurInfixe(m);
    vp = new VisiteurPostfixe(m);
    assertNotNull(i);
    assertNotNull(j);
    assertNotNull(ve);
    assertNotNull(vp);
    assertNotNull(vi);
  }

  @Test
  public void testUneConstante() {
    assertEquals(3, m.lire("i").intValue(), m + "lecture mémoire ?, i==3 ?");
    assertEquals(5, m.lire("j").intValue(), m + "lecture mémoire ?, j==5 ?");
    Expression expr = new Constante(3);
    assertEquals(3, expr.accepter(ve).intValue(), " une Constante : 3 ?");
  }

  @Test
  public void testUneVariable() {
    assertEquals(3, m.lire("i").intValue(), m + "lecture mémoire ?, i==3 ?");
    assertEquals(5, m.lire("j").intValue(), m + "lecture mémoire ?, j==5 ?");
    m.ecrire("z", 10);
    assertEquals(10, m.lire("z").intValue(), m + "écriture mémoire ?, z==10 ?");
  }

  @Test
  public void testUneAddition() {
    try { // i = 3; (i + 2) + i
      Expression expr = new Addition(new Addition(i, new Constante(2)), i);
      assertEquals(8, expr.accepter(ve).intValue(), m + " (i + 2) + i != 8? ?? ");
    } catch (StackOverflowError e) {
      fail(" StackOverflowError, appel récursif sans fin de " + m + " (i + 2) + i ???");
    }
  }

  @Test
  public void testMultiplication() {
    try {
      assertEquals(15, new Multiplication(i, j).accepter(ve).intValue(), m + " i * j != 15 ?");
    } catch (StackOverflowError e) {
      fail(" StackOverflowError, appel récursif sans fin de " + m + " i * j ???");
    }
  }

  @Test
  public void testSoustraction() {
    try {
      assertEquals(-2, new Soustraction(i, j).accepter(ve).intValue(), m + " i - j != -2 ?");
    } catch (StackOverflowError e) {
      fail(" StackOverflowError, appel récursif sans fin de " + m + " i - j ???");
    }
  }

  @Test
  public void testFactorielDe4() {
    try {
      Expression expr = new Factoriel(new Constante(4));
      assertEquals(24, expr.accepter(ve).intValue(), " !4 != 24 ?, curieux ");
    } catch (StackOverflowError e) {
      fail(" exception StackOverflowError, appel récursif sans fin ???");
    } catch (Exception e) {
      fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
    }
  }

  @Test
  public void testDivision() {
    try {
      assertEquals(m.lire("j"), Integer.valueOf(5));
      assertEquals(m.lire("i"), Integer.valueOf(3));
      assertEquals(
          5,
          new Division(new Multiplication(i, j), i).accepter(ve).intValue(),
          m + " i*j/i != 5 ???");
    } catch (StackOverflowError e) {
      fail(" StackOverflowError, appel récursif sans fin de " + m + " i*j/i ???");
    }
    assertThrows(
        ArithmeticException.class,
        () -> new Division(i, new Constante(0)).accepter(ve),
        "division par zéro ? possible");
  }

  @Test
  public void testVisiteurInfixe() {
    Expression expr = new Addition(new Constante(3), new Constante(2));
    assertEquals(expr.accepter(vi), "(3 + 2)");
    expr = new Addition(expr, new Constante(2));
    assertEquals(expr.accepter(vi), "((3 + 2) + 2)");
    assertEquals(m.toString(), "{i=3, j=5}");
    expr = new Soustraction(expr, expr);
    // System.out.println(expr.accepter(vi));
    assertEquals(expr.accepter(vi), "(((3 + 2) + 2) - ((3 + 2) + 2))");
  }

  @Test
  public void testVisiteurPostfixe() {
    Expression expr = new Addition(new Constante(3), new Constante(2));
    assertEquals(expr.accepter(vp), "(3,2)+");
    expr = new Addition(expr, new Constante(2));
    assertEquals(expr.accepter(vp), "((3,2)+,2)+");
    assertEquals(m.toString(), "{i=3, j=5}");
    expr = new Soustraction(expr, expr);
    assertEquals(expr.accepter(vp), "(((3,2)+,2)+,((3,2)+,2)+)-");
  }
}
