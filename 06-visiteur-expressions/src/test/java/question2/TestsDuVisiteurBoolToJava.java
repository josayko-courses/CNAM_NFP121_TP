package question2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import question1.*;

public class TestsDuVisiteurBoolToJava {

  protected Contexte m;
  protected VisiteurExpressionBooleenne<String> vbs;

  @BeforeEach
  public void setUp() {
    m = new Memoire();
    vbs = new VisiteurBoolToJava(new VisiteurInfixe(m));
  }

  @Test
  public void testVisiteurBoolString() {
    assertEquals("true", new Vrai().accepter(vbs));
    assertEquals("false", new Faux().accepter(vbs));
    assertEquals("(5 > 8)", new Sup(new Constante(5), new Constante(8)).accepter(vbs));
    // Etc
  }
}
