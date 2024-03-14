package question3;

import question1.PolygoneRegulier;

public class UneUtilisation {

  public static void main(String[] args) throws Exception {
    // déclarer p1
    /* SOLUTION (rien) */
    PileI<PolygoneRegulier> p1 = new Pile2<PolygoneRegulier>();
    /* FIN SOLUTION */
    // déclarer p2
    /* SOLUTION (rien) */
    PileI<PileI<PolygoneRegulier>> p2 = new Pile2<PileI<PolygoneRegulier>>();
    PileI<String> p21 = new Pile2<String>();
    PileI<PileI<String>> p22 = new Pile2<PileI<String>>();
    /* FIN SOLUTION */
    // p1 est ici une pile de polygones réguliers PolygoneRegulier.java
    p1.empiler(new PolygoneRegulier(4, 100));
    p1.empiler(new PolygoneRegulier(5, 100));

    System.out.println(" la pile p1 = " + p1); // Quel est le résultat ?

    p2.empiler(p1);
    System.out.println(" la pile p2 = " + p2); // Quel est le résultat ?
    /* SOLUTION (rien) */
    p2.empiler(p1);
    p2.empiler(p1);
    p2.empiler(p1);

    p21.empiler("premier");
    p21.empiler("deuxième");

    p22.empiler(p21);

    /* FIN SOLUTION */
    try {
      // p1.empiler(Boolean.valueOf(true));      // désormais une erreur de compilation
      // ....
      // @SuppressWarnings("unused")
      // String s = (String)p1.depiler();    // désormais une erreur de compilation
    } catch (Exception e) {
      e.printStackTrace();
    }

    /* SOLUTION (rien) */
    @SuppressWarnings("unused")
    Object o = p2.depiler();
    o = p2.depiler();
    o = p2.depiler();
    o = p2.depiler();
    p21.depiler();
    p21.depiler();
    // o = p2.depiler(); // lève une exception
    /* FIN SOLUTION */
  }
}
