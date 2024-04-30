package question3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import question1.*;
import question2.*;

public class MarkAssertions {

  Contexte m;
  VisiteurExpression<Integer> ve;
  VisiteurExpressionBooleenne<Boolean> vb;
  VisiteurInstruction<Contexte> vi;

  VisiteurExpression<String> ves;
  VisiteurExpressionBooleenne<String> vbs;
  VisiteurInstruction<String> vs;

  //  public void asserte() {
  //    try {
  //      assert false : "essai";
  //    } catch (AssertionError ae) {
  //    }
  //  }

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

  @Test
  public void testAssertionSucces() {
    Variable x = new Variable(m, "x", 5);

    Instruction i = new Assertion(new Egal(x, new Constante(5)));
    try {
      i.accepter(vi);
    } catch (AssertionError ae) {
      fail(i.accepter(vs) + "attention, assert a levé l'exception" + ae);
    }
  }

  @Test
  public void testAssertionError() {
    Variable x = new Variable(m, "x", 5);
    Instruction i = new Assertion(new Egal(x, new Constante(6)));
    assertThrows(
        AssertionError.class,
        () -> i.accepter(vi),
        "attention, " + i.accepter(vs) + " est-il sans effet ???");
  }
}
