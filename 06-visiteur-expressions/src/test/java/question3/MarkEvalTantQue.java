package question3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import question1.*;
import question2.*;

public class MarkEvalTantQue {

  Contexte m;
  VisiteurExpression<Integer> ve;
  VisiteurExpressionBooleenne<Boolean> vb;
  VisiteurInstruction<Contexte> vi;

  VisiteurExpression<String> ves;
  VisiteurExpressionBooleenne<String> vbs;
  VisiteurInstruction<String> vs;

  @BeforeEach
  public void setUp() {
    m = new Memoire();
    ve = new VisiteurEvaluation(m);
    vb = new VisiteurBoolEvaluation(ve);
    vi = new VisiteurInstEvaluation(ve, vb);

    ves = new VisiteurInfixe(m);
    vbs = new VisiteurBoolToString(ves);
    vs = new VisiteurInstToString(ves, vbs);
  }

  @SuppressWarnings("unused")
  private static int fact(int n) {
    if (n == 0) return 1;
    else return n * fact(n - 1);
  }

  private static int factIter(int x) {
    int fact = 1;
    while (x > 1) {
      fact = fact * x;
      x = x - 1;
    }
    return fact;
  }

  @Test
  public void testFactoriel() {
    Variable x = new Variable(m, "x", 5);
    Variable fact = new Variable(m, "fact", 1);

    Instruction i =
        new TantQue(
            new Sup(x, new Constante(1)),
            new Sequence(
                new Affectation(fact, new Multiplication(fact, x)),
                new Affectation(x, new Soustraction(x, new Constante(1)))));

    i.accepter(vi);
    assertEquals(factIter(5), m.lire("fact"), (String) i.accepter(vs));
  }

  private static int somme(int n) {
    int s = 0;
    int i = 0;
    while (i < n) {
      i = i + 1;
      s = s + i;
    }
    return s;
  }

  @Test
  public void testSomme() {
    Variable n = new Variable(m, "n", 100);
    Variable s = new Variable(m, "s", 0);
    Variable i = new Variable(m, "i", 0);

    Instruction inst =
        new TantQue(
            new Inf(i, n),
            new Sequence(
                new Affectation(i, new Addition(i, new Constante(1))),
                new Affectation(s, new Addition(s, i))));

    inst.accepter(vi);
    assertEquals(somme(100), m.lire("s"), (String) inst.accepter(vs));
  }

  private static int mult(int a, int b) {
    int z = 0;
    while (b > 0) {
      if ((b - (b / 2) * 2) == 1) {
        z = z + a;
        b = b - 1;
      } else {
        a = 2 * a;
        b = b / 2;
      }
    }
    return z;
  }

  @Test
  public void testMult() {
    Variable a = new Variable(m, "a", 7);
    Variable b = new Variable(m, "b", 85);
    Variable z = new Variable(m, "z", 0);

    Instruction i =
        new TantQue(
            new Sup(b, new Constante(0)),
            new Selection(
                new Egal(
                    new Soustraction(
                        b, new Multiplication(new Division(b, new Constante(2)), new Constante(2))),
                    new Constante(1)),
                new Sequence(
                    new Affectation(z, new Addition(z, a)),
                    new Affectation(b, new Soustraction(b, new Constante(1)))),
                new Sequence(
                    new Affectation(a, new Multiplication(a, new Constante(2))),
                    new Affectation(b, new Division(b, new Constante(2))))));

    i.accepter(vi);
    assertTrue(m.lire("z") == mult(7, 85), (String) i.accepter(vs));
  }
}
