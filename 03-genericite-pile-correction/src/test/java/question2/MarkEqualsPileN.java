package question2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MarkEqualsPileN {

  @Test
  public void test_equals_capacite_differente() throws Exception {
    PileI p1 = new Pile(6);
    PileI p11 = new Pile(4);
    PileI p2 = new Pile2(6);
    PileI p22 = new Pile2(4);
    PileI p3 = new Pile3(6);
    PileI p33 = new Pile3(4);
    PileI p4 = new Pile4(6);
    PileI p44 = new Pile4(4);

    assertFalse(p1.equals(p11), " equals classe Pile ???");
    assertFalse(p11.equals(p1), " equals classe Pile ???");
    assertFalse(p2.equals(p22), " equals classe Pile2 ???");
    assertFalse(p22.equals(p2), " equals classe Pile2 ???");
    assertFalse(p3.equals(p33), " equals classe Pile3 ???");
    assertFalse(p33.equals(p3), " equals classe Pile3 ???");
    assertFalse(p4.equals(p44), " equals classe Pile4 ???");
    assertFalse(p44.equals(p4), " equals classe Pile4 ???");
  }

  @Test
  public void test_equals_taille_egale() throws Exception {
    PileI p1 = new Pile(6);
    PileI p11 = new Pile(6);
    PileI p2 = new Pile2(6);
    PileI p22 = new Pile2(6);
    PileI p3 = new Pile3(6);
    PileI p33 = new Pile3(6);
    PileI p4 = new Pile4(6);
    PileI p44 = new Pile4(6);

    assertTrue(p1.equals(p11), " equals classe Pile ???");
    assertTrue(p11.equals(p1), " equals classe Pile ???");
    assertTrue(p2.equals(p22), " equals classe Pile2 ???");
    assertTrue(p22.equals(p2), " equals classe Pile2 ???");
    assertTrue(p3.equals(p33), " equals classe Pile3 ???");
    assertTrue(p33.equals(p3), " equals classe Pile3 ???");
    assertTrue(p4.equals(p44), " equals classe Pile4 ???");
    assertTrue(p44.equals(p4), " equals classe Pile4 ???");
    assertEquals(p1.hashCode(), p11.hashCode(), " equals=true avec hashCode() != (Pile) ???");
    assertEquals(p11.hashCode(), p1.hashCode(), " equals=true avec hashCode() != (Pile) ???");
    assertEquals(p2.hashCode(), p22.hashCode(), " equals=true avec hashCode() != (Pile) ???");
    assertEquals(p22.hashCode(), p2.hashCode(), " equals=true avec hashCode() != (Pile) ???");
    assertEquals(p3.hashCode(), p33.hashCode(), " equals=true avec hashCode() != (Pile) ???");
    assertEquals(p33.hashCode(), p3.hashCode(), " equals=true avec hashCode() != (Pile) ???");
    assertEquals(p4.hashCode(), p44.hashCode(), " equals=true avec hashCode() != (Pile) ???");
    assertEquals(p44.hashCode(), p4.hashCode(), " equals=true avec hashCode() != (Pile) ???");

    p1.empiler(3);
    p1.empiler(3);
    p1.empiler(3);
    assertFalse(p1.equals(p11), " equals classe Pile ???");
    assertFalse(p11.equals(p1), " equals classe Pile ???");
    p2.empiler(3);
    p2.empiler(3);
    p2.empiler(3);
    assertFalse(p2.equals(p22), " equals classe Pile2 ???");
    assertFalse(p22.equals(p2), " equals classe Pile2 ???");
    p3.empiler(3);
    p3.empiler(3);
    p3.empiler(3);
    assertFalse(p3.equals(p33), " equals classe Pile3 ???");
    assertFalse(p33.equals(p3), " equals classe Pile3 ???");
    p4.empiler(3);
    p4.empiler(3);
    p4.empiler(3);
  }

  @Test
  public void test_equals_meme_contenu() throws Exception {
    PileI p1 = new Pile(6);
    PileI p11 = new Pile(6);
    PileI p2 = new Pile2(6);
    PileI p22 = new Pile2(6);
    PileI p3 = new Pile3(6);
    PileI p33 = new Pile3(6);
    PileI p4 = new Pile4(6);
    PileI p44 = new Pile4(6);

    p1.empiler(3);
    p1.empiler(2);
    p1.empiler(5);
    p11.empiler(3);
    p11.empiler(2);
    p11.empiler(5);

    p2.empiler(3);
    p2.empiler(2);
    p2.empiler(5);
    p22.empiler(3);
    p22.empiler(2);
    p22.empiler(5);

    p3.empiler(3);
    p3.empiler(2);
    p3.empiler(5);
    p33.empiler(3);
    p33.empiler(2);
    p33.empiler(5);

    p4.empiler(3);
    p4.empiler(2);
    p4.empiler(5);
    p44.empiler(3);
    p44.empiler(2);
    p44.empiler(5);

    assertTrue(p1.equals(p11), " equals classe Pile ???");
    assertTrue(p11.equals(p1), " equals  classe Pile ???");
    assertTrue(p2.equals(p22), " equals  classe Pile2 ???");
    assertTrue(p22.equals(p2), " equals  classe Pile2 ???");
    assertTrue(p3.equals(p33), " equals  classe Pile3 ???");
    assertTrue(p33.equals(p3), " equals  classe Pile3 ???");
    assertTrue(p4.equals(p44), " equals  classe Pile4 ???");
    assertTrue(p44.equals(p4), " equals  classe Pile4 ???");
  }

  @Test
  public void test_equals_null() throws Exception {
    PileI p1 = new Pile(6);
    PileI p11 = null;
    PileI p2 = new Pile2(6);
    PileI p22 = null;
    PileI p3 = new Pile3(6);
    PileI p33 = null;
    PileI p4 = new Pile4(6);
    PileI p44 = null;

    assertFalse(p1.equals(p11), " Une pile.equals(null) ???");
    assertFalse(p2.equals(p22), " Une pile2.equals(null) ???");
    assertFalse(p3.equals(p33), " Une pile3.equals(null) ???");
    assertFalse(p4.equals(p44), " Une pile4.equals(null) ???");
  }

  @Test
  public void test_toString_equals_contenu_different() throws Exception {
    PileI p1 = new Pile(6);
    PileI p11 = new Pile(6);
    PileI p2 = new Pile2(6);
    PileI p22 = new Pile2(6);
    PileI p3 = new Pile3(6);
    PileI p33 = new Pile3(6);
    PileI p4 = new Pile4(6);
    PileI p44 = new Pile4(6);

    p1.empiler(3);
    p1.empiler(2);
    p1.empiler(5);
    p11.empiler("3");
    p11.empiler("2");
    p11.empiler("5");

    p2.empiler(3);
    p2.empiler(2);
    p2.empiler(5);
    p22.empiler("3");
    p22.empiler("2");
    p22.empiler("5");

    p3.empiler(3);
    p3.empiler(2);
    p3.empiler(5);
    p33.empiler("3");
    p33.empiler("2");
    p33.empiler("5");

    p4.empiler(3);
    p4.empiler(2);
    p4.empiler(5);
    p44.empiler("3");
    p44.empiler("2");
    p44.empiler("5");

    assertEquals(p1.toString(), p11.toString(), " equals Pile ???");
    assertEquals(p11.toString(), p1.toString(), " equals Pile ???");
    assertEquals(p2.toString(), p22.toString(), " equals Pile ???");
    assertEquals(p22.toString(), p2.toString(), " equals Pile ???");
    assertEquals(p3.toString(), p33.toString(), " equals Pile ???");
    assertEquals(p33.toString(), p3.toString(), " equals Pile ???");
    assertEquals(p4.toString(), p44.toString(), " equals Pile ???");
    assertEquals(p44.toString(), p4.toString(), " equals Pile ???");
  }

  @Test
  public void test_equals_contenu_different() throws Exception {
    PileI p1 = new Pile(6);
    PileI p11 = new Pile(6);
    PileI p2 = new Pile2(6);
    PileI p22 = new Pile2(6);
    PileI p3 = new Pile3(6);
    PileI p33 = new Pile3(6);
    PileI p4 = new Pile4(6);
    PileI p44 = new Pile4(6);

    p1.empiler(3);
    p1.empiler(2);
    p1.empiler(5);
    p11.empiler("3");
    p11.empiler("2");
    p11.empiler("5");

    p2.empiler(3);
    p2.empiler(2);
    p2.empiler(5);
    p22.empiler("3");
    p22.empiler("2");
    p22.empiler("5");

    p3.empiler(3);
    p3.empiler(2);
    p3.empiler(5);
    p33.empiler("3");
    p33.empiler("2");
    p33.empiler("5");

    p4.empiler(3);
    p4.empiler(2);
    p4.empiler(5);
    p44.empiler("3");
    p44.empiler("2");
    p44.empiler("5");

    assertEquals(p1.hashCode(), p1.hashCode(), " equals Pile ???");
    assertFalse(
        p1.equals(p11), " une pile d'entiers(1,2,3) == une pile de String(\"1\",\"2\",\"3\") ???");
    assertFalse(
        p11.equals(p1), " une pile d'entiers(1,2,3) == une pile de String(\"1\",\"2\",\"3\") ???");
    assertFalse(
        p2.equals(p22),
        " une pile2 d'entiers(1,2,3) == une pile2 de String(\"1\",\"2\",\"3\") ???");
    assertFalse(
        p22.equals(p2),
        " une pile2 d'entiers(1,2,3) == une pile2 de String(\"1\",\"2\",\"3\") ???");
    assertFalse(
        p3.equals(p33),
        " une pile3 d'entiers(1,2,3) == une pile3 de String(\"1\",\"2\",\"3\") ???");
    assertFalse(
        p33.equals(p3),
        " une pile3 d'entiers(1,2,3) == une pile3 de String(\"1\",\"2\",\"3\") ???");
    assertFalse(
        p4.equals(p44),
        " une pile4 d'entiers(1,2,3) == une pile4 de String(\"1\",\"2\",\"3\") ???");
    assertFalse(
        p44.equals(p4),
        " une pile4 d'entiers(1,2,3) == une pile4 de String(\"1\",\"2\",\"3\") ???");
  }

  //     assertFalse(p1.equals(p11));
  //         assertFalse(p1.equals(p11));
  //             assertFalse(p1.equals(p11));
  //     assertFalse(p.equals(p1));
  //     assertTrue(p1.equals(p2) && p2.equals(p1)); // etc ...
  //     assertTrue(p1.equals(p2) && p2.equals(p3) && p1.equals(p3));
  // 	  assertTrue(p2.equals(p3) && p3.equals(p4) && p2.equals(p4));// etc ...
  //
  //
  // p1.empiler(3);p1.empiler(33);p2.empiler(3);p2.empiler(33);p3.empiler(3);p3.empiler(33);p4.empiler(3);p4.empiler(33);
  // 	  assertTrue(p1.equals(p2) && p2.equals(p1)); // etc ...
  //     assertTrue(p1.equals(p2) && p2.equals(p3) && p1.equals(p3));
  // 	  assertTrue(p2.equals(p3) && p3.equals(p4) && p2.equals(p4));
  //
  // 	  p1.empiler(4);p1.empiler(44);p2.empiler(4);p2.empiler(44);
  // 	  assertTrue(p1.equals(p2) && p2.equals(p1)); // etc ...
  //     assertTrue(p1.equals(p2) && !p2.equals(p3) && !p1.equals(p3));
  // 	  assertTrue(!p2.equals(p3) && p3.equals(p4) && !p2.equals(p4));
  //
  // 	  p3.empiler(4);p3.empiler(44);p4.empiler(4);p4.empiler(44);
  // 	  assertTrue(p1.equals(p2) && p2.equals(p1)); // etc ...
  //     assertTrue(p1.equals(p2) && p2.equals(p3) && p1.equals(p3));
  // 	  assertTrue(p2.equals(p3) && p3.equals(p4) && p2.equals(p4));
  //
  // 	  p1.depiler();p2.depiler();
  // 	  assertTrue(p1.equals(p2) && p2.equals(p1)); // etc ...
  //     assertTrue(p1.equals(p2) && !p2.equals(p3) && !p1.equals(p3));
  // 	  assertTrue(!p2.equals(p3) && p3.equals(p4) && !p2.equals(p4));
  //
  // 	  p3.depiler();p4.depiler();
  // 	  assertTrue(p1.equals(p2) && p2.equals(p1)); // etc ...
  //     assertTrue(p1.equals(p2) && p2.equals(p3) && p1.equals(p3));
  // 	  assertTrue(p2.equals(p3) && p3.equals(p4) && p2.equals(p4));
  //
  // 	  p1.depiler();p2.depiler();p3.depiler();p4.depiler();
  // 	  assertTrue(p1.equals(p2) && p2.equals(p1)); // etc ...
  //     assertTrue(p1.equals(p2) && p2.equals(p3) && p1.equals(p3));
  // 	  assertTrue(p2.equals(p3) && p3.equals(p4) && p2.equals(p4));
  // 	 }
}
