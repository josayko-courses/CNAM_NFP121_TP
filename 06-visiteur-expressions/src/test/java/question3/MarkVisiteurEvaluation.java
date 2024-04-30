package question3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import question1.*;
import question2.*;

public class MarkVisiteurEvaluation {

  private VisiteurInstruction<Contexte> vi;
  private VisiteurInstruction<String> vs;

  @SuppressWarnings("unused")
  private Variable x, y, z;
  private Contexte m; // ici uniquement pour le 1er test

  @BeforeEach
  public void setUp() {
    m = new Memoire();
    x = new Variable(m, "x", 3);
    y = new Variable(m, "y", 5);
    z = new Variable(m, "z", 2);

    VisiteurExpression<Integer> ve = new VisiteurEvaluation(m);
    VisiteurExpressionBooleenne<Boolean> vb = new VisiteurBoolEvaluation(ve);
    vi = new VisiteurInstEvaluation(ve, vb);

    VisiteurExpression<String> ves = new VisiteurInfixe(m);
    VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToString(ves);
    vs = new VisiteurInstToString(ves, vbs);
  }

  @Test
  public void test_ContexteEnRetourDe_accepter() {
    assertEquals(3, m.lire("x"), " lecture erronée de la memoire ...");
    assertEquals(5, m.lire("y"), " lecture erronée de la memoire ...");
    assertEquals(2, m.lire("z"), " lecture erronée de la memoire ...");
    assertThrows(
        Exception.class,
        () -> m.lire("inconnu"),
        "une exception est attendue, lors d'une lecture d'une variable inconnue...");
    // x := 12
    Instruction i = new Affectation(x, new Constante(12));
    // System.out.println(vi.contexte().toString() + i.accepter(vs) + " " + i.accepter(vi));
    Contexte ctxt = i.accepter(vi);
    assertEquals(12, m.lire("x"), m + " x := 12; {x != 12} ???");
    assertTrue(ctxt instanceof Contexte, " un \"Contexte\" est attendu ???");
  }

  @Test
  public void testAffectation() {
    // x := 12
    Instruction i = new Affectation(x, new Constante(12));
    // System.out.println(vi.contexte().toString() + i.accepter(vs) + " " + i.accepter(vi));
    i.accepter(vi);
    assertEquals(12, vi.contexte().lire("x"), i.accepter(vs) + vi.contexte().toString() + " ??? ");
    assertEquals(5, vi.contexte().lire("y"));

    // x := x + x1;
    i = new Affectation(x, new Addition(x, y));
    i.accepter(vi);
    assertEquals(17, vi.contexte().lire("x"), "x + y == 17 " + vi.contexte().toString() + "??? ");
  }

  @Test
  public void testSequence() {
    Instruction i =
        new Sequence( //
            new Affectation(x, new Constante(1)), //
            new Affectation(x, new Addition(x, new Constante(1))));
    // System.out.println(m + i.toString() + i.accepter(v));
    i.accepter(vi);
    assertEquals(2, vi.contexte().lire("x"), i.accepter(vs) + vi.contexte().toString() + " ??? ");
  }

  @Test
  public void testSelection() {
    // if(x<10) x = 1; else x = 2;
    assertTrue(vi.contexte().lire("x") < 10);
    Instruction i =
        new Selection( //
            new Inf(x, new Constante(10)), //
            new Affectation(x, new Constante(1)), //
            new Affectation(x, new Constante(2)));
    i.accepter(vi);
    assertEquals(1, vi.contexte().lire("x"), i.accepter(vs) + vi.contexte().toString() + " ??? ");

    // if(x>10) x = 1; else x = 2;
    Instruction i1 =
        new Selection(
            new Sup(x, new Constante(10)),
            new Affectation(x, new Constante(1)),
            new Affectation(x, new Constante(2)));
    i1.accepter(vi);
    assertEquals(2, vi.contexte().lire("x"), i1.accepter(vs) + vi.contexte().toString() + " ??? ");

    Instruction i2 =
        new Selection(new Sup(x, new Constante(10)), new Affectation(x, new Constante(1)));
    i2.accepter(vi);
    assertEquals(2, vi.contexte().lire("x"), i2.accepter(vs) + vi.contexte().toString() + " ??? ");

    Instruction i3 =
        new Selection(new Inf(x, new Constante(10)), new Affectation(x, new Constante(1)));
    i3.accepter(vi);
    assertEquals(1, vi.contexte().lire("x"), i3.accepter(vs) + vi.contexte().toString() + " ??? ");
  }

  @Test
  public void testTantQue() {
    Instruction i =
        new Sequence( //
            new Affectation(x, new Constante(1)), //
            new TantQue(
                new Inf(x, new Constante(10)), //
                new Affectation(x, new Addition(x, new Constante(1)))));
    i.accepter(vi);
    assertEquals(10, vi.contexte().lire("x"), i.accepter(vs) + vi.contexte().toString() + " ??? ");
  }

  // // 	public void testRepeterJusque(){
  // //     Instruction i = new Affectation(x,new Constante(10));
  // //     i.accepter(vi);
  // // 	  Instruction i1 = new RepeterJusque(
  // // 	    new Sequence(
  // //           new Affectation(x,new Soustraction(x,new Constante(1))),
  // //           new Afficher(x)),
  // //       new Egal(x,new Constante(0))
  // //     );
  // //     //System.out.println(m + i1.toString() + i1.accepter(v));
  // //     i1.accepter(vi);
  // //  	  assertTrue(vi.contexte().toString() + i.accepter(vs) + " ???
  // ",vi.contexte().lire("x")==0);
  // //  	}

  @Test
  public void testPour() {
    Instruction i = new Affectation(y, new Constante(1));
    i.accepter(vi);

    Instruction i1 =
        new Pour( //
            new Affectation(x, new Constante(0)), //
            new Inf(x, new Constante(10)), //
            new Affectation(x, new Addition(x, new Constante(1))), //
            new Affectation(y, new Addition(y, new Constante(1))));
    i1.accepter(vi);

    assertEquals(10, vi.contexte().lire("x"), i.accepter(vs) + vi.contexte().toString() + " ??? ");
    assertEquals(11, vi.contexte().lire("y"), i.accepter(vs) + vi.contexte().toString() + " ??? ");
  }

  @Test
  public void testAssertion() {
    vi.contexte().ecrire("x", 10);
    try {
      Instruction i = new Assertion(new Egal(x, new Constante(10)));
      i.accepter(vi);
    } catch (AssertionError ae) {
      fail("assert, ne doit pas lever d'exception !!!");
    }
    Instruction i1 = new Assertion(new Inf(x, new Constante(10)));
    assertThrows(
        AssertionError.class, () -> i1.accepter(vi), "assert, doit ici lever une exception !!!");
  }

  // 	public void testAfficher(){
  // 	  try{
  //  	    Instruction i = new Afficher(new Constante(10));
  //  	    i.accepter(vi);
  // 	}

}
