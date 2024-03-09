package question2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MarkMemeComportement {

  private question2.PileI p1;
  private question2.PileI p2;
  private question2.PileI p3;
  private question2.PileI p4;

  @BeforeEach
  protected void setUp() // throws java.lang.Exception
  {
    p1 = new question2.Pile(PileI.CAPACITE_PAR_DEFAUT);
    p2 = new question2.Pile2(PileI.CAPACITE_PAR_DEFAUT);
    p3 = new question2.Pile3(PileI.CAPACITE_PAR_DEFAUT);
    p4 = new question2.Pile4(PileI.CAPACITE_PAR_DEFAUT);
  }

  @AfterEach
  protected void tearDown() // throws java.lang.Exception
  {
    // Liberez ici les ressources engagees par setUp()
  }

  @Test
  public void test_Sommaire() throws Exception {

    p4.empiler("b");
    p4.empiler("a");
    p3.empiler("b");
    p3.empiler("a");
    p2.empiler("b");
    p2.empiler("a");
    p1.empiler("b");
    p1.empiler("a");

    assertEquals(p1.capacite(), p2.capacite());
    assertEquals(p2.capacite(), p3.capacite());
    assertEquals(p3.capacite(), p4.capacite());

    assertEquals("[a, b]", p1.toString());
    assertEquals(p1.toString(), p2.toString());
    assertEquals(p2.toString(), p3.toString());
    assertEquals(p3.toString(), p4.toString());

    assertEquals(p1.sommet(), p2.sommet());
    assertEquals(p2.sommet(), p3.sommet());
    assertEquals(p3.sommet(), p4.sommet());

    String s = (String) p1.depiler();
    assertEquals(s, (String) p2.depiler());
    assertEquals(s, (String) p3.depiler());
    assertEquals(s, (String) p4.depiler());
  }

  @Test
  public void test_meme_comportement() throws Exception {
    p4.empiler("aze");
    p3.empiler("aze");
    p2.empiler("aze");
    p1.empiler("aze");

    assertEquals(p1.capacite(), p2.capacite(), "p1.capacite() == p2.capacite() ??");
    assertEquals(p2.capacite(), p3.capacite(), "p2.capacite() == p3.capacite() ??");
    assertEquals(p3.capacite(), p4.capacite(), "p3.capacite() == p4.capacite() ??");

    // À compléter
  }

  // À compléter
}
