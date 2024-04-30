package question3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import question1.*;
import question2.*;

public class MarkVisiteurToString {

  private Variable x, y;
  private VisiteurInstruction<String> vs;

  @BeforeEach
  public void setUp() {
    Contexte m = new Memoire();
    x = new Variable(m, "x", 3);
    y = new Variable(m, "y", 5);
    new Variable(m, "z", 2);

    VisiteurExpression<String> ves = new VisiteurInfixe(m);
    VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToString(ves);
    vs = new VisiteurInstToString(ves, vbs);
  }

  @Test
  public void testAffectation() {
    // x := 12
    Instruction i = new Affectation(x, new Constante(12));
    String str = vs.contexte().toString() + i.accepter(vs);
    assertTrue(str.endsWith("x := 12"));
    // x := x + x1;
    i = new Affectation(x, new Addition(x, y));
    str = vs.contexte().toString() + i.accepter(vs);
    assertTrue(str.endsWith("x := (x + y)"));
  }

  @Test
  public void testSequence() {
    Instruction i =
        new Sequence(
            new Affectation(x, new Constante(1)),
            new Affectation(x, new Addition(x, new Constante(1))));
    String str = vs.contexte().toString() + i.accepter(vs);
    assertTrue(str.endsWith("x := 1 ; x := (x + 1)"));
  }

  @Test
  public void testSelection() {
    // if(x<10) x = 1; else x = 2;
    assertTrue(vs.contexte().lire("x") < 10);
    Instruction i =
        new Selection( //
            new Inf(x, new Constante(10)), //
            new Affectation(x, new Constante(1)), //
            new Affectation(x, new Constante(2)));
    String str = vs.contexte().toString() + i.accepter(vs);
    assertTrue(str.endsWith("si(x < 10) alors x := 1 sinon x := 2 fsi"));

    Instruction i1 =
        new Selection( //
            new Sup(x, new Constante(10)), //
            new Affectation(x, new Constante(1)), //
            new Affectation(x, new Constante(2)));
    str = vs.contexte().toString() + i1.accepter(vs);
    assertTrue(str.endsWith("si(x > 10) alors x := 1 sinon x := 2 fsi"));

    Instruction i2 =
        new Selection(new Sup(x, new Constante(10)), new Affectation(x, new Constante(1)));
    str = vs.contexte().toString() + i2.accepter(vs);
    assertTrue(str.endsWith("si(x > 10) alors x := 1 fsi"));

    Instruction i3 =
        new Selection(new Inf(x, new Constante(10)), new Affectation(x, new Constante(1)));
    str = vs.contexte().toString() + i3.accepter(vs);
    assertTrue(str.endsWith("si(x < 10) alors x := 1 fsi"));
  }

  @Test
  public void testTantQue() {
    Instruction i =
        new Sequence( //
            new Affectation(x, new Constante(1)), //
            new TantQue(
                new Inf(x, new Constante(10)), //
                new Affectation(x, new Addition(x, new Constante(1)))));
    String str = vs.contexte().toString() + i.accepter(vs);
    assertTrue(str.endsWith("x := 1 ; tantque(x < 10) faire x := (x + 1) ftq"));
  }

  // 	public void testRepeterJusque(){
  //     Instruction i = new Affectation(x,new Constante(10));
  //     i.accepter(vs);
  // 	  Instruction i1 = new RepeterJusque(
  // 	    new Sequence(
  //           new Affectation(x,new Soustraction(x,new Constante(1))),
  //           new Afficher(x)),
  //       new Egal(x,new Constante(0))
  //     );
  //  	  String str = vs.contexte().toString() + i1.accepter(vs);
  //  	  assertTrue(str.endsWith("repeter x := (x - 1) ; afficher(x) jusque (x = 0)"));
  //  	}

  @Test
  public void testPour() {
    Instruction i = new Affectation(y, new Constante(1));
    i.accepter(vs);

    Instruction i1 =
        new Pour( //
            new Affectation(x, new Constante(0)), //
            new Inf(x, new Constante(10)), //
            new Affectation(x, new Addition(x, new Constante(1))), //
            new Affectation(y, new Addition(y, new Constante(1))));
    String str = vs.contexte().toString() + i1.accepter(vs);
    assertTrue(str.endsWith("pour(x := 0,(x < 10),y := (y + 1)) x := (x + 1) fpour"));
  }

  @Test
  public void testAfficher() {
    Instruction i = new Afficher(new Constante(10));
    String str = vs.contexte().toString() + i.accepter(vs);
    assertTrue(str.endsWith("afficher(10)"));
  }

  @Test
  public void testAssertion() {
    vs.contexte().ecrire("x", 10);
    Instruction i = new Assertion(new Egal(x, new Constante(10)));
    String str = vs.contexte().toString() + i.accepter(vs);
    assertTrue(str.endsWith("assertion((x = 10))"));
  }
}
