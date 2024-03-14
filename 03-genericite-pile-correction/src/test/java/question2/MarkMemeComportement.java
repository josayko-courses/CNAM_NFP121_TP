package question2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import question1.PilePleineException;

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

    /* SOLUTION:// À compléter */
    assertEquals("[aze]", p1.toString());
    assertEquals(p1.toString(), p2.toString(), "p1.toString() != p2.toString() ?? ");
    assertEquals(p2.toString(), p3.toString(), "p2.toString()!= p3.toString() ?? ");
    assertEquals(p3.toString(), p4.toString(), "p3.toString()!= p4.toString() ?? ");

    assertEquals(p1.sommet(), p2.sommet(), "p1.sommet() != p2.sommet() ??? ");
    assertEquals(p2.sommet(), p3.sommet(), "p2.sommet() != p3.sommet() ??? ");
    assertEquals(p1.estVide(), p2.estVide(), "p1.estVide() != p2.estVide() ??? ");
    assertEquals(p2.estVide(), p3.estVide(), "p2.estVide()!= p3.estVide() ??? ");
    assertEquals(p1.estPleine(), p2.estPleine(), "p1.estPleine() != p2.estPleine() ??? ");
    assertEquals(p2.estPleine(), p3.estPleine(), "p2.estPleine()!=  p3.estPleine() ??? ");

    String s = (String) p1.depiler();
    assertEquals(s, (String) p2.depiler(), " difference apres avoir depile ?? ");
    assertEquals(s, (String) p3.depiler(), " difference apres avoir depile ?? ");
    assertEquals(s, (String) p4.depiler(), " difference apres avoir depile ?? ");

    assertEquals(p1.estVide(), p2.estVide(), "p1.estVide() != p2.estVide() ??? ");
    assertEquals(p2.estVide(), p3.estVide(), "p2.estVide()!= p3.estVide() ??? ");
    assertEquals(p1.estPleine(), p2.estPleine(), "p1.estPleine() != p2.estPleine() ??? ");
    assertEquals(p2.estPleine(), p3.estPleine(), "p2.estPleine()!=  p3.estPleine() ??? ");

    assertEquals(p1.taille(), p2.taille(), " difference sur la taille() ?? ");
    assertEquals(p2.taille(), p3.taille(), " difference sur la taille() ?? ");
    assertEquals(p3.taille(), p4.taille(), " difference sur la taille() ?? ");

    p4.empiler("azerty");
    p3.empiler("azerty");
    p2.empiler("azerty");
    p1.empiler("azerty");

    assertEquals(p1.sommet(), p2.sommet(), "p1.sommet() != p2.sommet() ??? ");
    assertEquals(p2.sommet(), p3.sommet(), "p2.sommet() != p3.sommet() ??? ");
    assertEquals(p1.estVide(), p2.estVide(), "p1.estVide() != p2.estVide() ??? ");
    assertEquals(p2.estVide(), p3.estVide(), "p2.estVide()!= p3.estVide() ??? ");
    assertEquals(p1.estPleine(), p2.estPleine(), "p1.estPleine() != p2.estPleine() ??? ");
    assertEquals(p2.estPleine(), p3.estPleine(), "p2.estPleine()!=  p3.estPleine() ??? ");
    assertEquals(p1.taille(), p2.taille(), " difference sur la taille() ?? ");
    assertEquals(p2.taille(), p3.taille(), " difference sur la taille() ?? ");
    assertEquals(p3.taille(), p4.taille(), " difference  sur la taille() ?? ");

    p4.empiler("azer");
    p3.empiler("azer");
    p2.empiler("azer");
    p1.empiler("azer");

    assertEquals(p1.toString(), p2.toString(), "p1.toString() != p2.toString() ?? ");
    assertEquals(p2.toString(), p3.toString(), "p2.toString() != p3.toString() ?? ");
    assertEquals(p3.toString(), p4.toString(), "p3.toString() != p4.toString() ?? ");

    assertEquals(p1.sommet(), p2.sommet(), "p1.sommet() != p2.sommet() ??? ");
    assertEquals(p2.sommet(), p3.sommet(), "p2.sommet() != p3.sommet() ??? ");

    assertEquals(p1.estVide(), p2.estVide(), "p1.estVide() != p2.estVide() ??? ");
    assertEquals(p2.estVide(), p3.estVide(), "p2.estVide()!= p3.estVide() ??? ");
    assertEquals(p1.estPleine(), p2.estPleine(), "p1.estPleine() != p2.estPleine() ??? ");
    assertEquals(p2.estPleine(), p3.estPleine(), "p2.estPleine()!=  p3.estPleine() ??? ");

    assertEquals(p1.taille(), p2.taille(), " difference sur la taille() ?? ");
    assertEquals(p2.taille(), p3.taille(), " difference sur la taille() ?? ");
    assertEquals(p3.taille(), p4.taille(), " difference  sur la taille() ?? ");

    p4.empiler("azer");
    p3.empiler("azer");
    p2.empiler("azer");
    p1.empiler("azer");

    assertEquals(p1.toString(), p2.toString(), "p1.toString() != p2.toString() ?? ");
    assertEquals(p2.toString(), p3.toString(), "p2.toString() != p3.toString() ?? ");
    assertEquals(p3.toString(), p4.toString(), "p3.toString() != p4.toString() ?? ");

    assertEquals(p1.sommet(), p2.sommet(), "p1.sommet() != p2.sommet() ??? ");
    assertEquals(p2.sommet(), p3.sommet(), "p2.sommet() != p3.sommet() ??? ");

    assertEquals(p1.estVide(), p2.estVide(), "p1.estVide() != p2.estVide() ??? ");
    assertEquals(p2.estVide(), p3.estVide(), "p2.estVide()!= p3.estVide() ??? ");
    assertEquals(p1.estPleine(), p2.estPleine(), "p1.estPleine() != p2.estPleine() ??? ");
    assertEquals(p2.estPleine(), p3.estPleine(), "p2.estPleine()!=  p3.estPleine() ??? ");

    assertEquals(p1.taille(), p2.taille(), " difference sur la taille() ?? ");
    assertEquals(p2.taille(), p3.taille(), " difference sur la taille() ?? ");
    assertEquals(p3.taille(), p4.taille(), " difference  sur la taille() ?? ");

    p4.depiler();
    p3.depiler();
    p2.depiler();
    p1.depiler();

    assertEquals(p1.toString(), p2.toString(), "p1.toString() != p2.toString() ?? ");
    assertEquals(p2.toString(), p3.toString(), "p2.toString() != p3.toString() ?? ");
    assertEquals(p3.toString(), p4.toString(), "p3.toString() != p4.toString() ?? ");

    assertEquals(p1.sommet(), p2.sommet(), "p1.sommet() != p2.sommet() ??? ");
    assertEquals(p2.sommet(), p3.sommet(), "p2.sommet() != p3.sommet() ??? ");

    assertEquals(p1.estVide(), p2.estVide(), "p1.estVide() != p2.estVide() ??? ");
    assertEquals(p2.estVide(), p3.estVide(), "p2.estVide()!= p3.estVide() ??? ");
    assertEquals(p1.estPleine(), p2.estPleine(), "p1.estPleine() != p2.estPleine() ??? ");
    assertEquals(p2.estPleine(), p3.estPleine(), "p2.estPleine()!=  p3.estPleine() ??? ");

    assertEquals(p1.taille(), p2.taille(), " difference sur la taille() ?? ");
    assertEquals(p2.taille(), p3.taille(), " difference sur la taille() ?? ");
    assertEquals(p3.taille(), p4.taille(), " difference  sur la taille() ?? ");
    /* FIN SOLUTION */
  }

  /* SOLUTION:// À compléter */
  @Test
  public void test_meme_comportement_avec_equals() throws PilePleineException {
    PileI p_ = new question2.Pile(5);
    PileI p2_ = new question2.Pile2(5);
    PileI p3_ = new question2.Pile3(5);
    PileI p4_ = new question2.Pile4(5);
    try {
      p4.empiler("aze");
      p4_.empiler("aze");
      p3.empiler("aze");
      p3_.empiler("aze");
      p2.empiler("aze");
      p2_.empiler("aze");
      p1.empiler("aze");
      p_.empiler("aze");
      assertEquals(p1.equals(p_), p3.equals(p3_), "p1.equals() et p4.equals ??");
      assertEquals(p1.equals(p_), p3.equals(p3_), "p1.equals() et p3.equals ??");
      assertEquals(p1.equals(p_), p2.equals(p2_), "p1.equals() et p2.equals ??");
    } catch (StackOverflowError e) {
      fail(" récursivité ?? " + e.getClass().getName());
    }
  }

  @Test
  public void test_meme_comportement_avec_les_exceptions() {
    // On n'utilise pas assertThow parce-qu'on se contente de vérifier que les deux
    // exceptions sont identiques
    try {
      p4.depiler();
      fail("p4 est vide --> levée d'une exception!! ");
    } catch (Exception e) {
      try {
        p3.depiler();
        fail("p3 est vide --> levée d'une exception!! ");
      } catch (Exception e1) {
        assertEquals(e.getClass().getName(), e1.getClass().getName());
      }
    }

    try {
      p1.depiler();
      fail("p est vide --> levée d'une exception!! ");
    } catch (Exception e) {
      try {
        p2.depiler();
        fail("p2 est vide --> levée d'une exception!! ");
      } catch (Exception e1) {
        assertEquals(e.getClass().getName(), e1.getClass().getName());
      }
    }
  }
  /* FIN SOLUTION */
}
