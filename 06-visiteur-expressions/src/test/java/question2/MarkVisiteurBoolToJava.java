package question2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import question1.*;

public class MarkVisiteurBoolToJava {

  protected Contexte m;
  protected VisiteurExpressionBooleenne<String> vbs;

  @BeforeEach
  public void setUp() {
    m = new Memoire();
    vbs = new VisiteurBoolToJava(new VisiteurInfixe(m));
  }

  @Test
  public void testVisiteurBoolToJava() {
    assertEquals("true", new Vrai().accepter(vbs));
    assertEquals("false", new Faux().accepter(vbs));
    assertEquals("(5 > 8)", new Sup(new Constante(5), new Constante(8)).accepter(vbs));
    assertEquals(
        "((5 + 3) == 8)",
        new Egal(new Addition(new Constante(5), new Constante(3)), new Constante(8)).accepter(vbs));
    assertEquals("(i < j)", new Inf(new Variable(m, "i"), new Variable(m, "j")).accepter(vbs));
    assertEquals(
        "(i < (j + 1))",
        new Inf(new Variable(m, "i"), new Addition(new Variable(m, "j"), new Constante(1)))
            .accepter(vbs));
    assertEquals(
        "((i > j) || (i < j))",
        new Ou(
                new Sup(new Variable(m, "i"), new Variable(m, "j")),
                new Inf(new Variable(m, "i"), new Variable(m, "j")))
            .accepter(vbs));
    assertEquals(
        "((i > j) && (i < j))",
        new Et(
                new Sup(new Variable(m, "i"), new Variable(m, "j")),
                new Inf(new Variable(m, "i"), new Variable(m, "j")))
            .accepter(vbs));
    assertEquals(
        "((true || true) && (true && true))",
        new Et(new Ou(new Vrai(), new Vrai()), new Et(new Vrai(), new Vrai())).accepter(vbs));
  }
}
