package postliminaire;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import question1.*;
import question2.*;
import question3.*;

public class TestsGenerationEtExecutionDeBytecodeAExecuter {

  private static final boolean JASMIN = true; // false; // assemblé et exécuté
  private static final boolean DELETE = true && JASMIN; // suppression des fichiers.j et .class

  public void ignore_testPour() throws Exception { // Pour une fois implémentée en q3
    Contexte m = new Memoire();
    Variable x = new Variable(m, "x", 3);
    Variable y = new Variable(m, "y", 5);
    // Variable z = new Variable(m, "z", 2);

    Code code = new Code("TestsPour", m);
    VisiteurExprJasmin vej = new VisiteurExprJasmin(m, code);
    VisiteurBoolJasmin vbj = new VisiteurBoolJasmin(vej);
    VisiteurInstJasmin vij = new VisiteurInstJasmin(vej, vbj);

    Instruction i1 =
        new Sequence(
            new Pour(
                new Affectation(x, new Constante(0)),
                new Inf(x, new Constante(10)),
                new Affectation(x, new Addition(x, new Constante(1))),
                new Sequence(
                    new Affectation(y, new Addition(y, new Constante(1))), new Afficher(x))),
            new Sequence(
                new Assertion(new Egal(x, new Constante(10))),
                new Assertion(new Egal(y, new Constante(15)))));

    code.setLimitStack(i1.accepter(vij));
    System.out.println(code.toString());

    if (JASMIN) Jasmin.assemble(code.toString(), code.nomDeClasse());
    if (JASMIN) Jasmin.exec(code.nomDeClasse());
    if (DELETE) new java.io.File(code.nomDeClasse() + ".j").delete();
    if (DELETE) new java.io.File(code.nomDeClasse() + ".class").delete();
  }

  @Test
  public void testFactoriel() throws Exception {
    Contexte m = new Memoire();
    Variable x = new Variable(m, "x", 5);
    Variable fact = new Variable(m, "fact", 1);

    Code code = new Code("TestsFactoriel", m);
    VisiteurExprJasmin vej = new VisiteurExprJasmin(m, code);
    VisiteurBoolJasmin vbj = new VisiteurBoolJasmin(vej);
    VisiteurInstJasmin vij = new VisiteurInstJasmin(vej, vbj);

    Instruction i =
        new Sequence(
            new TantQue(
                new Sup(x, new Constante(1)),
                new Sequence(
                    new Affectation(fact, new Multiplication(fact, x)),
                    new Affectation(x, new Soustraction(x, new Constante(1))))),
            new Sequence(
                new Assertion(new Egal(fact, new Constante(fact(5))), " 5! != 120 ??? "),
                new Afficher(fact)));

    code.setLimitStack(i.accepter(vij));
    System.out.println(code.nomDeClasse() + " : \n" + code.toString());

    if (JASMIN) Jasmin.assemble(code.toString(), code.nomDeClasse());
    if (JASMIN) Jasmin.exec(code.nomDeClasse());
    if (DELETE) new java.io.File(code.nomDeClasse() + ".j").delete();
    if (DELETE) new java.io.File(code.nomDeClasse() + ".class").delete();
  }

  public static int fact(int n) {
    if (n == 0) return 1;
    else return n * fact(n - 1);
  }

  public static void print(int x, int y) {
    System.out.println(" x : " + x + " , y : " + y);
  }

  public static int somme(int n) {
    int s = 0;
    int i = 0;
    while (i < n) {
      i = i + 1;
      s = s + i;
    }
    return s;
  }

  @Test
  public void testSomme() throws Exception {
    Contexte m = new Memoire();
    Variable n = new Variable(m, "n", 100);
    Variable s = new Variable(m, "s", 0);
    Variable i = new Variable(m, "i", 0);

    Code code = new Code("TestSomme", m);
    VisiteurExprJasmin vej = new VisiteurExprJasmin(m, code);
    VisiteurBoolJasmin vbj = new VisiteurBoolJasmin(vej);
    VisiteurInstJasmin vij = new VisiteurInstJasmin(vej, vbj);

    Instruction inst =
        new Sequence(
            new TantQue(
                new Inf(i, n),
                new Sequence(
                    new Affectation(i, new Addition(i, new Constante(1))),
                    new Affectation(s, new Addition(s, i)))),
            new Sequence(
                new Assertion(new Egal(s, new Constante(somme(100))), " somme(100) != 5050 ??? "),
                new Afficher(s)));

    code.setLimitStack(inst.accepter(vij));
    System.out.println(code.nomDeClasse() + " : \n" + code.toString());
    try {
      if (JASMIN) Jasmin.assemble(code.toString(), code.nomDeClasse());
      if (JASMIN) Jasmin.exec(code.nomDeClasse());
      if (DELETE) new java.io.File(code.nomDeClasse() + ".j").delete();
      if (DELETE) new java.io.File(code.nomDeClasse() + ".class").delete();
    } catch (Exception e) {
      fail("erreur jasmin :" + e.getMessage());
      throw e;
    }
  }

  public static int mult(int a, int b) {
    int res = 0;
    while (b > 0) {
      b = b - 1;
      res = res + a;
    }
    return res;
  }

  @Test
  public void testMult() throws Exception {
    // System.out.println("mult(23,17) == " + mult(23,17));
    Contexte m = new Memoire();
    Variable a = new Variable(m, "a", 23);
    Variable b = new Variable(m, "b", 17);
    Variable res = new Variable(m, "res", 0);

    Code code = new Code("TestsMult", m);
    VisiteurExprJasmin vej = new VisiteurExprJasmin(m, code);
    VisiteurBoolJasmin vbj = new VisiteurBoolJasmin(vej);
    VisiteurInstJasmin vij = new VisiteurInstJasmin(vej, vbj);

    Instruction inst =
        new Sequence(
            new TantQue(
                new Sup(b, new Constante(0)),
                new Sequence(
                    new Affectation(b, new Soustraction(b, new Constante(1))),
                    new Affectation(res, new Addition(res, a)))),
            new Sequence(
                new Afficher(res),
                new Assertion(
                    new Egal(res, new Constante(mult(23, 17))), " mult(23,17) != 391 ??? ")));

    code.setLimitStack(inst.accepter(vij));
    System.out.println(code.nomDeClasse() + " : \n" + code.toString());
    try {
      if (JASMIN) Jasmin.assemble(code.toString(), code.nomDeClasse());
      if (JASMIN) Jasmin.exec(code.nomDeClasse());
      if (DELETE) new java.io.File(code.nomDeClasse() + ".j").delete();
      if (DELETE) new java.io.File(code.nomDeClasse() + ".class").delete();
    } catch (Exception e) {
      fail("erreur jasmin :" + e.getMessage());
      throw e;
    }
  }

  public static int pgcd(int a, int b) {
    while (a != b) {
      if (a < b) b = b - a;
      else a = a - b;
    }
    return b;
  }

  // https://fr.wikipedia.org/wiki/Technique_de_multiplication_dite_russe
  public static int multRusse(int x, int y) {
    int r = 0;
    while (x > 0) { // Tant que x est différent de 0
      if ((x - (x / 2) * 2) == 1) { // Si x est impair alors
        r = r + y;
        x = x - 1;
      } // fin si
      x = x / 2;
      y = y * 2;
    }
    return r;
  }

  @Test
  public void test_VerificationMultRusse_7_85() throws Exception {
    assertEquals(7 * 85, mult(7, 85));
    assertEquals(7 * 85, multRusse(7, 85));
  }

  @Test
  public void testMultRusse_7_85() throws Exception {
    Contexte m = new Memoire();
    Variable x = new Variable(m, "x", 7);
    Variable y = new Variable(m, "y", 85);
    Variable r = new Variable(m, "r", 0);
    Code code = new Code("MultRusse", m);

    Instruction inst =
        new Sequence(
            new TantQue(
                new Sup(x, new Constante(0)),
                new Sequence(
                    new Selection(
                        new Egal(
                            new Soustraction(
                                x,
                                new Multiplication(
                                    new Division(x, new Constante(2)), new Constante(2))),
                            new Constante(1)),
                        new Sequence(
                            new Affectation(r, new Addition(r, y)),
                            new Affectation(x, new Soustraction(x, new Constante(1))))),
                    new Sequence(
                        new Affectation(y, new Multiplication(y, new Constante(2))),
                        new Affectation(x, new Division(x, new Constante(2)))))),
            new Sequence(
                new Sequence(
                    new Assertion(new Egal(r, new Constante(multRusse(7, 85)))),
                    new Assertion(new Egal(r, new Constante(mult(7, 85))))),
                new Afficher(r)));

    VisiteurExprJasmin vej = new VisiteurExprJasmin(m, code);
    VisiteurBoolJasmin vbj = new VisiteurBoolJasmin(vej);
    VisiteurInstJasmin vij = new VisiteurInstJasmin(vej, vbj);

    code.setLimitStack(inst.accepter(vij));
    System.out.println(code.nomDeClasse() + " : \n" + code.toString());
    try {
      if (JASMIN) Jasmin.assemble(code.toString(), code.nomDeClasse());
      if (JASMIN) Jasmin.exec(code.nomDeClasse());
      if (DELETE) new java.io.File(code.nomDeClasse() + ".j").delete();
      if (DELETE) new java.io.File(code.nomDeClasse() + ".class").delete();
    } catch (Exception e) {
      fail("erreur jasmin :" + e.getMessage());
      throw e;
    }
  }

  @Test
  public void testPGCD() throws Exception {

    Contexte m = new Memoire();
    Variable a = new Variable(m, "a", 25);
    Variable b = new Variable(m, "b", 15);

    Code code = new Code("TestsPGCD", m);
    VisiteurExprJasmin vej = new VisiteurExprJasmin(m, code);
    VisiteurBoolJasmin vbj = new VisiteurBoolJasmin(vej);
    VisiteurInstJasmin vij = new VisiteurInstJasmin(vej, vbj);

    Instruction inst =
        new Sequence(
            new TantQue(
                new Non(new Egal(a, b)),
                new Selection(
                    new Inf(a, b),
                    new Affectation(b, new Soustraction(b, a)),
                    new Affectation(a, new Soustraction(a, b)))),
            new Sequence(
                new Afficher(b),
                new Assertion(new Egal(b, new Constante(pgcd(25, 15))), "pgcd(25,15) != 5 ??? ")));

    code.setLimitStack(inst.accepter(vij));
    System.out.println(code.nomDeClasse() + " : \n" + code.toString());
    try {
      if (JASMIN) Jasmin.assemble(code.toString(), code.nomDeClasse());
      if (JASMIN) Jasmin.exec(code.nomDeClasse());
      if (DELETE) new java.io.File(code.nomDeClasse() + ".j").delete();
      if (DELETE) new java.io.File(code.nomDeClasse() + ".class").delete();
    } catch (Exception e) {
      fail("erreur jasmin :" + e.getMessage());
      throw e;
    }
  }

  // calcul  de la racine carrée à un près par défaut d'un entier. source jf dazy NFP120
  //          rac := 0 '.,' v := 1 '.,' car := 1 '.,'
  //           while car =< n
  //                     invariant {rac * rac =< n &
  //                               v = rac +rac +1 & w = (rac +1)*(rac +1)}
  //                     variant {n-car +1}
  //           do
  //           (         rac := rac + 1 '.,'
  //                     v := v + 2 '.,'
  //                     car := car + v )
  //           end '.,'
  //        {rac *rac =< n & (rac +1)*(rac +1) > n}

  @Test
  public void testRacine() {
    assertTrue(racineCarre(9) == 3, " racineCarre(9) ???");
    assertTrue(racineCarre(16) == 4, " racineCarre (16) ???");
    assertTrue(racineCarre(12) == 3, " racineCarre(12) ???");
  }

  public static int racineCarre(int n) {
    int rac = 0;
    int car = 1;
    int v = 1;
    while (car <= n) {
      rac = rac + 1;
      v = v + 2;
      car = car + v;
    }
    return rac;
  }

  // calcul  de la racine carrée à un près par défaut d'un entier. source jf dazy NFP120
  //          rac := 0 '.,' v := 1 '.,' car := 1 '.,'
  //           while car =< n
  //                     invariant {rac * rac =< n &
  //                               v = rac +rac +1 & w = (rac +1)*(rac +1)}
  //                     variant {n-car +1}
  //           do
  //           (         rac := rac + 1 '.,'
  //                     v := v + 2 '.,'
  //                     car := car + v )
  //           end '.,'
  //        {rac *rac =< n & (rac +1)*(rac +1) > n}
  @Test
  public void testRacineCarreeDe12() {
    try {
      Contexte m = new Memoire();
      Variable rac = new Variable(m, "rac", 0);
      Variable car = new Variable(m, "car", 1);
      Variable v = new Variable(m, "v", 1);
      Variable n = new Variable(m, "n", 0);
      Code code = new Code("RacineCarree", m);

      VisiteurExprJasmin vej = new VisiteurExprJasmin(m, code);
      VisiteurBoolJasmin vbj = new VisiteurBoolJasmin(vej);
      VisiteurInstJasmin vij = new VisiteurInstJasmin(vej, vbj);

      Instruction inst =
          new Sequence(
              new Affectation(n, new Constante(12)),
              new Sequence(
                  new TantQue(
                      new Ou(new Inf(car, n), new Egal(car, n)),
                      new Sequence(
                          new Affectation(rac, new Addition(rac, new Constante(1))),
                          new Sequence(
                              new Affectation(v, new Addition(v, new Constante(2))),
                              new Affectation(car, new Addition(car, v))))),
                  new Sequence(
                      new Assertion(new Egal(rac, new Constante(3)), "RacineCarre(12) != 3 ???"),
                      // rac *rac =< n & (rac +1)*(rac +1) > n en post-assertion
                      new Assertion(
                          new Et(
                              new Ou(
                                  new Inf(new Multiplication(rac, rac), n),
                                  new Egal(new Multiplication(rac, rac), n)),
                              new Sup(
                                  new Multiplication(
                                      new Addition(rac, new Constante(1)),
                                      new Addition(rac, new Constante(1))),
                                  n)),
                          "La post-assertion est en échec ???"))));

      code.setLimitStack(inst.accepter(vij));
      System.out.println(code.nomDeClasse() + " : \n" + code.toString());
      try {
        if (JASMIN) Jasmin.assemble(code.toString(), code.nomDeClasse());
        if (JASMIN) Jasmin.exec(code.nomDeClasse());
        if (DELETE) new java.io.File(code.nomDeClasse() + ".j").delete();
        if (DELETE) new java.io.File(code.nomDeClasse() + ".class").delete();
      } catch (Exception e) {
        fail("erreur jasmin :" + e.getMessage());
        throw e;
      }

    } catch (Exception e) {
      fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
    }
  }

  // public class SuiteFibonacciMethod1{
  // extrait de https://waytolearnx.com/2018/11/lalgorithme-de-fibonacci-en-java.html
  // public static void main(String args[])
  // {
  // int nbr1=0, nbr2=1, nbr3, i, count=10;
  // //afficher 0 et 1
  // System.out.print(nbr1+" "+nbr2);

  // //La boucle commence par 2 car 0 et 1 sont deja affiches
  // for(i=2; i<count; ++i)
  // {
  // nbr3 = nbr1 + nbr2;
  // nbr1 = nbr2;
  // nbr2 = nbr3;
  // System.out.print(" "+nbr3);
  // }
  // }
  // }

  public static int fibonacci() {
    int nbr1 = 0, nbr2 = 1, nbr3 = 0, count = 10;
    // for(int i=2; i<count; ++i){
    // nbr3 = nbr1 + nbr2;
    // nbr1 = nbr2;
    // nbr2 = nbr3;
    // }
    int i = 2;
    while (i < count) {
      nbr3 = nbr1 + nbr2;
      nbr1 = nbr2;
      nbr2 = nbr3;
      i++;
    }
    return nbr3;
  }

  @Test
  public void testFibonacci() {
    try {
      Contexte m = new Memoire();
      Variable nbr1 = new Variable(m, "nbr1", 0);
      Variable nbr2 = new Variable(m, "nbr2", 1);
      Variable nbr3 = new Variable(m, "nbr3", 10);
      Variable i = new Variable(m, "i", 0);
      Variable count = new Variable(m, "count", 10);

      Code code = new Code("Fibonacci", m);

      VisiteurExprJasmin vej = new VisiteurExprJasmin(m, code);
      VisiteurBoolJasmin vbj = new VisiteurBoolJasmin(vej);
      VisiteurInstJasmin vij = new VisiteurInstJasmin(vej, vbj);

      Instruction inst =
          new Sequence(
              new Sequence(new Affectation(i, new Constante(2)), new Afficher(i)),
              new Sequence(
                  new TantQue(
                      new Inf(i, count),
                      new Sequence(
                          new Affectation(nbr3, new Addition(nbr1, nbr2)),
                          new Sequence(
                              new Affectation(nbr1, nbr2),
                              new Sequence(
                                  new Affectation(nbr2, nbr3),
                                  new Sequence(
                                      new Affectation(i, new Addition(i, new Constante(1))),
                                      new Afficher(nbr3)))))),
                  new Assertion(
                      new Egal(nbr3, new Constante(fibonacci())),
                      "Fibonacci en erreur, 2 3 5 8 13 21 34")));

      code.setLimitStack(inst.accepter(vij));
      System.out.println(code.nomDeClasse() + " : \n" + code.toString());
      try {
        if (JASMIN) Jasmin.assemble(code.toString(), code.nomDeClasse());
        if (JASMIN) Jasmin.exec(code.nomDeClasse());
        if (DELETE) new java.io.File(code.nomDeClasse() + ".j").delete();
        if (DELETE) new java.io.File(code.nomDeClasse() + ".class").delete();
      } catch (Exception e) {
        fail("erreur jasmin :" + e.getMessage());
        throw e;
      }

    } catch (Exception e) {
      fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
    }
  }
}
