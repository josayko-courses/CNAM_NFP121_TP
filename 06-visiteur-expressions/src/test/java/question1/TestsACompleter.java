package question1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestsACompleter {
  private Memoire m;
  private Variable i, j;
  private VisiteurExpression<Integer> ve;
  private VisiteurExpression<String> vp, vi;

  @BeforeEach
  public void setUp() {
  }

  @Test
  public void testUneAddition() {
    Expression expr = new Addition(new Constante(3), new Constante(2));
    assertEquals(5, expr.accepter(ve).intValue(), " 3+2 != 5 ?, curieux ");
  }

  @Test
  public void testUneMultiplication() {
    Expression expr = new Multiplication(new Constante(3), new Constante(2));
    assertEquals(6, expr.accepter(ve).intValue(), " 3*2 != 6 ?, curieux ");
  }

  @Test
  public void testUneDivision() {
    Expression expr = new Division(new Constante(3), new Constante(2));
    assertEquals(1, expr.accepter(ve).intValue(), " 3/2 != 1 ?, curieux ");
  }

  @Test
  public void testUneSoustraction3() {
    Expression expr = new Soustraction(new Constante(3), new Constante(2));
    assertEquals(1, expr.accepter(ve).intValue(), " 3-2 != 1 ?, curieux ");
  }

  @Test
  public void testVisiteurInfixe() {
    Expression expr = new Addition(new Constante(3), new Constante(2));
    assertEquals(expr.accepter(vi), "(3 + 2)");
  }

  // À compléter
}
