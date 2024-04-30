package question2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import question1.*;

public class TestsDuVisiteurBoolEvaluation {

  protected Contexte m;
  protected VisiteurExpressionBooleenne<Boolean> veb;

  @BeforeEach
  public void setUp() {
    m = new Memoire();
    veb = new VisiteurBoolEvaluation(new VisiteurEvaluation(m));
  }

  @Test
  public void testVisiteurBoolEvaluation() {
    assertTrue(new Vrai().accepter(veb));
    assertFalse(new Faux().accepter(veb));
    assertTrue(new Sup(new Constante(5), new Constante(3)).accepter(veb));

    // etc ...
  }
}
