package question3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import question1.*;
import question2.*;

public class TestsDesAssertionsAVerifier {

  @Test
  public void testAssertionSucces() {
    Contexte m = new Memoire();
    Variable x = new Variable(m, "x", 5);
    VisiteurExpression<Integer> ve = new VisiteurEvaluation(m);
    VisiteurExpressionBooleenne<Boolean> vb = new VisiteurBoolEvaluation(ve);
    VisiteurInstruction<Contexte> vi = new VisiteurInstEvaluation(ve, vb);

    VisiteurExpression<String> ves = new VisiteurInfixe(m);
    VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToString(ves);
    VisiteurInstruction<String> vs = new VisiteurInstToString(ves, vbs);

    Instruction i = new Assertion(new Egal(x, new Constante(5)));
    try {
      i.accepter(vi);
    } catch (AssertionError ae) {
      fail("attention, " + i.accepter(vs) + " ne devrait pas lever une exception ???");
    }
  }

  @Test
  public void testAssertionError() {
    Contexte m = new Memoire();
    Variable x = new Variable(m, "x", 5);
    VisiteurExpression<Integer> ve = new VisiteurEvaluation(m);
    VisiteurExpressionBooleenne<Boolean> vb = new VisiteurBoolEvaluation(ve);
    VisiteurInstruction<Contexte> vi = new VisiteurInstEvaluation(ve, vb);
    VisiteurExpression<String> ves = new VisiteurInfixe(m);
    VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToString(ves);
    VisiteurInstruction<String> vs = new VisiteurInstToString(ves, vbs);

    Instruction i = new Assertion(new Egal(x, new Constante(6)));
    assertThrows(
        AssertionError.class,
        () -> i.accepter(vi), //
        () -> "attention, " + i.accepter(vs) + " devrait échouer ???");
  }
}
