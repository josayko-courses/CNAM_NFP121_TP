package question3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import question1.*;
import question2.*;

public class MarkEvalPour {
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

  public static int pour1(int n) {
    int r = 0;
    for (int i = n; (i > 0) || (i == 0); i--) {
      r++;
    }
    return r;
  }

  @Test
  public void testPour1_10_itérations() {
    final int N = 10;
    Variable r = new Variable(m, "r", 0);
    Variable i = new Variable(m, "i", N);
    Instruction inst =
        new Pour(
            new Affectation(i, new Constante(N)),
            new Ou(new Sup(i, new Constante(0)), new Egal(i, new Constante(0))),
            new Affectation(r, new Addition(r, new Constante(1))),
            new Affectation(i, new Soustraction(i, new Constante(1))));

    inst.accepter(vi);
    // System.out.println(vi.contexte());
    assertTrue(
        m.lire("r") == pour1(N), inst.accepter(vs) + " ne donne pas le résultat attendu ...");
  }

  @Test
  public void testPour1_0_itération() {
    final int N = 0;
    Variable r = new Variable(m, "r", 0);
    Variable i = new Variable(m, "i", N);

    Instruction inst =
        new Pour(
            new Affectation(i, new Constante(N)),
            new Ou(new Sup(i, new Constante(0)), new Egal(i, new Constante(0))),
            new Affectation(r, new Addition(r, new Constante(1))),
            new Affectation(i, new Soustraction(i, new Constante(1))));

    inst.accepter(vi);
    // System.out.println(vi.contexte());
    assertTrue(
        m.lire("r") == pour1(N),
        (String) inst.accepter(vs) + " ne donne pas le résultat attendu ...");
  }

  @Test
  public void testPour1_0_itération_bis() {
    final int N = -1;
    Variable r = new Variable(m, "r", 0);
    Variable i = new Variable(m, "i", N);

    Instruction inst =
        new Pour(
            new Affectation(i, new Constante(N)),
            new Ou(new Sup(i, new Constante(0)), new Egal(i, new Constante(0))),
            new Affectation(r, new Addition(r, new Constante(1))),
            new Affectation(i, new Soustraction(i, new Constante(1))));

    inst.accepter(vi);
    // System.out.println(vi.contexte());
    assertTrue(
        m.lire("r") == pour1(N),
        (String) inst.accepter(vs) + " ne donne pas le résultat attendu ...");
  }

  @Test
  public void testPour1_1_itération() {
    final int N = 1;
    Variable r = new Variable(m, "r", 0);
    Variable i = new Variable(m, "i", N);

    Instruction inst =
        new Pour(
            new Affectation(i, new Constante(N)),
            new Ou(new Sup(i, new Constante(0)), new Egal(i, new Constante(0))),
            new Affectation(r, new Addition(r, new Constante(1))),
            new Affectation(i, new Soustraction(i, new Constante(1))));

    inst.accepter(vi);
    // System.out.println(vi.contexte());
    assertTrue(
        m.lire("r") == pour1(N),
        (String) inst.accepter(vs) + " ne donne pas le résultat attendu ...");
  }

  public static int pour2(int n) {
    int r = 0;
    for (int i = 0; (i < n); i++) {
      r++;
    }
    return r;
  }

  @Test
  public void testPour2_10_itérations() {
    final int N = 10;
    Variable r = new Variable(m, "r", 0);
    Variable i = new Variable(m, "i", N);

    Instruction inst =
        new Pour(
            new Affectation(i, new Constante(0)),
            new Inf(i, new Constante(N)),
            new Affectation(r, new Addition(r, new Constante(1))),
            new Affectation(i, new Addition(i, new Constante(1))));

    inst.accepter(vi);
    // System.out.println(vi.contexte());
    assertTrue(
        m.lire("r") == pour2(N),
        (String) inst.accepter(vs) + " ne donne pas le résultat attendu ...");
  }

  @Test
  public void testPour2_0_itération() {
    final int N = 0;
    Variable r = new Variable(m, "r", 0);
    Variable i = new Variable(m, "i", N);

    Instruction inst =
        new Pour(
            new Affectation(i, new Constante(0)),
            new Inf(i, new Constante(N)),
            new Affectation(r, new Addition(r, new Constante(1))),
            new Affectation(i, new Addition(i, new Constante(1))));

    inst.accepter(vi);
    // System.out.println(vi.contexte());
    assertTrue(
        m.lire("r") == pour2(N),
        (String) inst.accepter(vs) + " ne donne pas le résultat attendu ...");
  }

  public static int pour3(int n) {
    int r = 0;
    for (int i = 0; (i < n); i++) {
      for (int j = 0; j < n; j++) {
        r++;
      }
    }
    return r;
  }

  @Test
  public void testPourPour_5_itérations() {
    final int N = 5;
    Variable r = new Variable(m, "r", 0);
    Variable i = new Variable(m, "i", 0);
    Variable j = new Variable(m, "j", 0);

    Instruction inst =
        new Pour(
            new Affectation(i, new Constante(0)),
            new Inf(i, new Constante(N)),
            new Pour(
                new Affectation(j, new Constante(0)),
                new Inf(j, new Constante(N)),
                new Affectation(r, new Addition(r, new Constante(1))),
                new Affectation(j, new Addition(j, new Constante(1)))),
            new Affectation(i, new Addition(i, new Constante(1))));

    inst.accepter(vi);
    // System.out.println(vi.contexte());
    assertTrue(
        m.lire("r") == pour3(N),
        (String) inst.accepter(vs) + " ne donne pas le résultat attendu ...");
  }
}
