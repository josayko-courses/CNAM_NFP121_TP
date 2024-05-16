package question1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestsACompleter {
  private Memoire m;
  private Variable i, j, k, l;
  private VisiteurExpression<Integer> ve;
  private VisiteurExpression<String> vp, vi;

  @BeforeEach
  public void setUp() {
    m = new Memoire();
    i = new Variable(m, "i", 3);
    j = new Variable(m, "j", 5);
    k = new Variable(m, "k", 6);
    l = new Variable(m, "l", 2);
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
    Expression expr1 = new Division(new Constante(3), new Constante(2));
    Expression expr2 = new Division(new Constante(3), new Constante(2));
    Expression expr3 = new Division(new Constante(3), new Constante(2));
    Expression expr4 = new Division(k, l);
    assertEquals(1, expr1.accepter(ve).intValue(), " 3/2 != 1 ?, curieux ");
    assertEquals(1, expr2.accepter(ve).intValue(), " 3/2 != 1 ?, curieux ");
    assertEquals(1, expr3.accepter(ve).intValue(), " 3/2 != 1 ?, curieux ");
    assertEquals(3, expr4.accepter(ve).intValue(), " k/l != 2 ?, curieux ");
  }

  @Test
  public void testUneSoustraction3() {
    Expression expr = new Soustraction(new Constante(3), new Constante(2));
    assertEquals(1, expr.accepter(ve).intValue(), " 3-2 != 1 ?, curieux ");
  }

  @Test
  public void testVisiteurEvaluation() {
    Expression expr = new Addition(new Constante(3), new Constante(2));
    assertEquals(5, expr.accepter(ve), "3 + 2 != 5 ?");
  }

  @Test
  public void testFactorial() {
    Expression expr = new Addition(new Factoriel(new Constante(3)), new Factoriel(new Constante(2)));
    assertEquals(8, expr.accepter(ve), "!(3) + !(2) != 8 ?");
  }

  @Test
  public void testVisiteurInfixe() {
    Expression expr = new Addition(new Constante(3), new Constante(2));
    assertEquals(expr.accepter(vi), "(3 + 2)");
  }

  @Test
  public void testVisiteurPostfixe() {
    Expression expr = new Addition(new Constante(3), new Constante(2));
    assertEquals(expr.accepter(vp), "(3,2)+");
  }
}
