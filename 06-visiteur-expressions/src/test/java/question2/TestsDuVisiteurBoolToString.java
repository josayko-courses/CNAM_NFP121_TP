package question2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import question1.*;

public class TestsDuVisiteurBoolToString {

  protected Contexte m;
  protected VisiteurExpressionBooleenne<String> vbs;

  @BeforeEach
  public void setUp() {
    m = new Memoire();
    vbs = new VisiteurBoolToString(new VisiteurInfixe(m));
  }

  @Test
  public void testVisiteurBoolString() {
    assertEquals("vrai", new Vrai().accepter(vbs));
    assertEquals("faux", new Faux().accepter(vbs));
    assertEquals("(5 > 8)", new Sup(new Constante(5), new Constante(8)).accepter(vbs));
    assertEquals(
        "((i > j) et (i < j))",
        new Et(
                new Sup(new Variable(m, "i"), new Variable(m, "j")),
                new Inf(new Variable(m, "i"), new Variable(m, "j")))
            .accepter(vbs));
    // Etc
  }
}
