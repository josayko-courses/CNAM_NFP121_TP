package question3;

import static org.junit.jupiter.api.Assertions.*;

import compilation.ClasseJava;
import compilation.CompilationFailed;
import compilation.CompileString;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import org.junit.jupiter.api.Test;
import question1.*;
import question2.*;

public class MarkVisiteurToJava {

  private ClasseJava createClasse(Instruction inst, Contexte m, String className) {
    VisiteurExpression<String> ves = new question1.VisiteurInfixe(m);
    VisiteurExpressionBooleenne<String> vbs = new question2.VisiteurBoolToJava(ves);
    VisiteurInstruction<String> vs = new question3.VisiteurInstToJava(ves, vbs, 4);
    ClasseJava cj = new ClasseJava(className, inst, vs);
    System.out.println("code compilé:\n" + cj.sourceComplet());
    return cj;
  }

  private void testCompile(ClasseJava cj)
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    List<Diagnostic<? extends JavaFileObject>> diags =
        CompileString.compileSeulement(cj).getDiagnostics();
    assertEquals(0, diags.size(), "There are some error/warning messages:\n" + diags);
  }

  private void testCompileAndExec(ClasseJava cj)
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    List<Diagnostic<? extends JavaFileObject>> diags =
        CompileString.compileSeulement(cj).getDiagnostics();
    assertEquals(0, diags.size(), "There are some error/warning messages:\n" + diags);
    CompileString.compileEtExecute(cj);
  }

  // Un test de code incorrect (variable non déclarée) pour vérifier que la
  // compilation échoue effectivement.
  @Test
  public void test_CompilationDeFactorielFailing() throws CompilationFailed {
    Contexte m = new Memoire();
    Variable x = new Variable(m, "x", 5);
    Variable fact = new Variable(m, "fact", 1);
    Contexte m2 = new Memoire(); // Pour déclarer de fausses variables
    Variable dummy = new Variable(m2, "dummy", 1); // Cette variable n'existe pas dans m
    Instruction inst = //
        new TantQue( //
            new Sup(x, new Constante(1)), //
            new Sequence( //
                new Affectation(fact, new Multiplication(dummy, x)), //
                new Affectation(x, new Soustraction(x, new Constante(1)))));
    ClasseJava cj = createClasse(inst, m, "X_FactBug");
    assertThrows(CompilationFailed.class, () -> testCompile(cj));
  }

  @Test
  public void test_CompilationDeFactoriel()
      throws CompilationFailed, IOException, ReflectiveOperationException {
    Contexte m = new Memoire();
    Variable x = new Variable(m, "x", 5);
    Variable fact = new Variable(m, "fact", 1);
    Instruction inst =
        new TantQue(
            new Sup(x, new Constante(1)),
            new Sequence(
                new Affectation(fact, new Multiplication(fact, x)),
                new Affectation(x, new Soustraction(x, new Constante(1)))));
    ClasseJava cj = createClasse(inst, m, "X_Fact");
    testCompile(cj);
  }

  @Test
  public void test_CompilationDeSomme()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    Contexte m = new Memoire();
    Variable n = new Variable(m, "n", 100);
    Variable s = new Variable(m, "s", 0);
    Variable i = new Variable(m, "i", 0);

    Instruction inst =
        new Sequence(
            new Sequence(
                new TantQue(
                    new Inf(i, n), //
                    new Sequence(
                        new Affectation(i, new Addition(i, new Constante(1))), //
                        new Affectation(s, new Addition(s, i)))), //
                new Afficher(s)), //
            new Assertion(new Egal(s, new Constante(5050))) // à voir ........
            );
    ClasseJava cj = createClasse(inst, m, "X_Somme");
    testCompile(cj);
  }

  private static int somme(int n) {
    int s = 0;
    int i = 0;
    while (i < n) {
      i = i + 1;
      s = s + i;
    }
    return s;
  }

  @Test
  public void test_CompilationExecutionDeSomme_100()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    Contexte m = new Memoire();
    Variable n = new Variable(m, "n", 100);
    Variable s = new Variable(m, "s", 0);
    Variable i = new Variable(m, "i", 0);

    Instruction inst =
        new Sequence(
            new Sequence(
                new TantQue(
                    new Inf(i, n),
                    new Sequence(
                        new Affectation(i, new Addition(i, new Constante(1))),
                        new Affectation(s, new Addition(s, i)))),
                new Afficher(s)),
            new Assertion(new Egal(s, new Constante(somme(100)))) // à voir ........
            );
    ClasseJava cj = createClasse(inst, m, "X_Somme2");
    testCompileAndExec(cj);
  }

  private static int fact(int n) {
    if (n == 0) return 1;
    else return n * fact(n - 1);
  }

  @Test
  public void test_CompilationExecutionDeFactoriel_5()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    Contexte m = new Memoire();
    Variable x = new Variable(m, "x", 5);
    Variable fact = new Variable(m, "fact", 1);

    Instruction inst =
        new Sequence(
            new TantQue(
                new Sup(x, new Constante(1)),
                new Sequence(
                    new Affectation(fact, new Multiplication(fact, x)),
                    new Affectation(x, new Soustraction(x, new Constante(1))))),
            new Assertion(new Egal(fact, new Constante(fact(5)))));
    ClasseJava cj = createClasse(inst, m, "X_Fact2");
    testCompileAndExec(cj);
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
  public void test_VerificationMultRusse_7_85()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    assertEquals(7 * 85, mult(7, 85));
    assertEquals(7 * 85, multRusse(7, 85));
  }

  @Test
  public void test_CompilationExecutionDeMultRusse_7_85()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    Contexte m = new Memoire();
    Variable x = new Variable(m, "x", 7);
    Variable y = new Variable(m, "y", 85);
    Variable r = new Variable(m, "r", 0);

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
                new Assertion(new Egal(r, new Constante(multRusse(7, 85)))),
                new Assertion(new Egal(r, new Constante(mult(7, 85))))));

    ClasseJava cj = createClasse(inst, m, "X_MultRusse");
    testCompileAndExec(cj);
  }

  private static int mult(int a, int b) {
    int z = 0;
    while (b > 0) {
      if ((b - (b / 2) * 2) == 1) {
        z = z + a;
        b = b - 1;
      } else {
        a = 2 * a;
        b = b / 2;
      }
    }
    return z;
  }

  @Test
  public void test_CompilationExecutionDeMult_7_85()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    Contexte m = new Memoire();
    Variable a = new Variable(m, "a", 7);
    Variable b = new Variable(m, "b", 85);
    Variable z = new Variable(m, "z", 0);

    Instruction inst =
        new Sequence(
            new TantQue(
                new Sup(b, new Constante(0)),
                new Selection(
                    new Egal(
                        new Soustraction(
                            b,
                            new Multiplication(
                                new Division(b, new Constante(2)), new Constante(2))),
                        new Constante(1)),
                    new Sequence(
                        new Affectation(z, new Addition(z, a)),
                        new Affectation(b, new Soustraction(b, new Constante(1)))),
                    new Sequence(
                        new Affectation(a, new Multiplication(a, new Constante(2))),
                        new Affectation(b, new Division(b, new Constante(2)))))),
            new Assertion(new Egal(z, new Constante(mult(7, 85)))));
    ClasseJava cj = createClasse(inst, m, "X_Mult");
    testCompileAndExec(cj);
  }

  // Quelle différence avec celui d'au-dessus?
  @Test
  public void test_CompilationDeMult()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    Contexte m = new Memoire();
    Variable a = new Variable(m, "a", 7);
    Variable b = new Variable(m, "b", 85);
    Variable z = new Variable(m, "z", 0);

    Instruction inst =
        new Sequence(
            new TantQue(
                new Sup(b, new Constante(0)),
                new Selection(
                    new Egal(
                        new Soustraction(
                            b,
                            new Multiplication(
                                new Division(b, new Constante(2)), new Constante(2))),
                        new Constante(1)),
                    new Sequence(
                        new Affectation(z, new Addition(z, a)),
                        new Affectation(b, new Soustraction(b, new Constante(1)))),
                    new Sequence(
                        new Affectation(a, new Multiplication(a, new Constante(2))),
                        new Affectation(b, new Division(b, new Constante(2)))))),
            new Assertion(new Egal(z, new Constante(7 * 85))));

    ClasseJava cj = createClasse(inst, m, "X_MultZ");
    testCompileAndExec(cj);
  }

  @Test
  public void test_CompilationExecutionDUneBouclePour()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    Contexte m = new Memoire();
    Variable i = new Variable(m, "i");
    Variable j = new Variable(m, "j", 1);

    Instruction inst =
        new Sequence(
            new Pour(
                new Affectation(i, new Constante(0)),
                new Inf(i, new Constante(5)),
                new Affectation(i, new Addition(i, new Constante(1))),
                new Affectation(j, new Addition(j, new Constante(1)))),
            new Assertion(new Egal(j, new Constante(6))));
    ClasseJava cj = createClasse(inst, m, "X_BouclePour");
    testCompileAndExec(cj);
  }

  // Quelle différence avec celui d'au-dessus?
  @Test
  public void test_CompilationDUneBouclePour()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    Contexte m = new Memoire();
    Variable i = new Variable(m, "i");
    Variable j = new Variable(m, "j", 1);

    Instruction inst =
        new Sequence(
            new Pour(
                new Affectation(i, new Constante(0)),
                new Inf(i, new Constante(5)),
                new Affectation(i, new Addition(i, new Constante(1))),
                new Affectation(j, new Addition(j, new Constante(1)))),
            new Afficher(j));
    ClasseJava cj = createClasse(inst, m, "X_BouclePour");
    testCompileAndExec(cj);
  }

  @Test
  public void test_CompilationDUneBouclePourAvecAfficher()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    Contexte m = new Memoire();
    Variable i = new Variable(m, "i");
    Variable j = new Variable(m, "j", 1);

    Instruction inst =
        new Sequence(
            new Pour(
                new Affectation(i, new Constante(0)),
                new Inf(i, new Constante(5)),
                new Sequence(
                    new Affectation(i, new Addition(i, new Constante(1))), new Afficher(i)),
                new Affectation(j, new Addition(j, new Constante(1)))),
            new Afficher(j));
    ClasseJava cj = createClasse(inst, m, "X_BouclePourEtAfficher");
    testCompileAndExec(cj);
  }

  @Test
  public void test_CompilationDeBouclesTantQueImbriquées()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    Contexte m = new Memoire();
    Variable i = new Variable(m, "i");
    Variable j = new Variable(m, "j", 1);

    Instruction inst =
        new TantQue(
            new Inf(i, new Constante(10)),
            new Sequence(
                new TantQue(
                    new Inf(j, new Constante(10)),
                    new Affectation(j, new Addition(j, new Constante(1)))),
                new Sequence(
                    new Affectation(i, new Addition(i, new Constante(1))),
                    new Affectation(j, new Constante(1)))));
    ClasseJava cj = createClasse(inst, m, "X_BoucleBoucle");
    testCompileAndExec(cj);
  }

  @Test
  public void test_CompilationDeBouclesPourImbriquées()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    question1.Contexte m = new Memoire();
    Variable x = new Variable(m, "x", Integer.valueOf(5));
    Variable k = new Variable(m, "k", Integer.valueOf(0));
    Variable n = new Variable(m, "n", Integer.valueOf(0));
    Variable f = new Variable(m, "f", Integer.valueOf(1));
    Instruction inst =
        new Pour(
            new Affectation(k, n),
            new Inf(k, x),
            new Pour(
                new Affectation(k, n),
                new Inf(k, x),
                new Pour(
                    new Affectation(k, n),
                    new Inf(k, x),
                    new Affectation(k, new Addition(k, f)),
                    new Affectation(k, new Addition(k, f))),
                new Affectation(k, new Addition(k, f))),
            new Affectation(k, new Addition(k, f)));
    ClasseJava cj = createClasse(inst, m, "X_PourClasseTest");
    testCompile(cj);
  }

  @Test
  public void test_CompilationDeBouclesTantQuePourImbriquées()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    Contexte m = new Memoire();
    Variable i = new Variable(m, "i");
    Variable j = new Variable(m, "j", 1);

    Instruction inst =
        new TantQue(
            new Inf(i, new Constante(10)),
            new Sequence(
                new TantQue(
                    new Inf(j, new Constante(10)),
                    new Pour(
                        new Affectation(i, new Constante(0)),
                        new Inf(i, new Constante(5)),
                        new Sequence(
                            new Affectation(i, new Addition(i, new Constante(1))), new Afficher(i)),
                        new Affectation(j, new Addition(j, new Constante(1))))),
                new Sequence(
                    new Affectation(i, new Addition(i, new Constante(1))),
                    new Affectation(j, new Constante(1)))));
    ClasseJava cj = createClasse(inst, m, "X_BoucleTantQuePour");
    testCompile(cj);
  }

  @Test
  public void test_CompilationDeBouclesTantQuePourImbriquées_bis()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    Contexte m = new Memoire();
    Variable i = new Variable(m, "i");
    Variable j = new Variable(m, "j", 1);

    Instruction inst =
        new TantQue(
            new Inf(i, new Constante(10)),
            new Sequence(
                new TantQue(
                    new Inf(j, new Constante(10)),
                    new Pour(
                        new Affectation(i, new Constante(0)),
                        new Inf(i, new Constante(5)),
                        new Sequence(
                            new Affectation(i, new Addition(i, new Constante(1))),
                            new Pour(
                                new Affectation(i, new Constante(0)),
                                new Inf(i, new Constante(5)),
                                new Sequence(
                                    new Affectation(i, new Addition(i, new Constante(1))),
                                    new Afficher(i)),
                                new Affectation(j, new Addition(j, new Constante(1))))),
                        new Affectation(j, new Addition(j, new Constante(1))))),
                new Sequence(
                    new Affectation(i, new Addition(i, new Constante(1))),
                    new Affectation(j, new Constante(1)))));
    ClasseJava cj = createClasse(inst, m, "X_BoucleTantQuePour2");
    testCompile(cj);
  }

  @Test
  public void test_CompilationDeBouclesPourTantQueImbriquées_bis()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    Contexte m = new Memoire();
    Variable i = new Variable(m, "i");
    Variable j = new Variable(m, "j", 1);

    Instruction inst =
        new Pour(
            new Affectation(i, new Constante(0)),
            new Inf(i, new Constante(5)),
            new TantQue(
                new Inf(i, new Constante(10)),
                new Sequence(
                    new TantQue(
                        new Inf(j, new Constante(10)),
                        new Pour(
                            new Affectation(i, new Constante(0)),
                            new Inf(i, new Constante(5)),
                            new Sequence(
                                new Affectation(i, new Addition(i, new Constante(1))),
                                new Pour(
                                    new Affectation(i, new Constante(0)),
                                    new Inf(i, new Constante(5)),
                                    new Sequence(
                                        new Affectation(i, new Addition(i, new Constante(1))),
                                        new Afficher(i)),
                                    new Affectation(j, new Addition(j, new Constante(1))))),
                            new Affectation(j, new Addition(j, new Constante(1))))),
                    new Sequence(
                        new Affectation(i, new Addition(i, new Constante(1))),
                        new Affectation(j, new Constante(1))))),
            new Affectation(j, new Addition(j, new Constante(1))));
    ClasseJava cj = createClasse(inst, m, "X_BoucleTantQuePour3");
    testCompile(cj);
  }

  // public void test_CompilationDUneBoucleRepeter() throws Exception{
  // Contexte m = new Memoire();
  // Variable i = new Variable(m,"i");
  // Variable j = new Variable(m,"j",1);
  //
  // Instruction inst =
  // new Sequence(
  // new RepeterJusque(
  // new Sequence(
  // new Affectation(i,new Addition(i,new Constante(1))),
  // new Affectation(j,new Addition(j,new Constante(1)))
  // ),
  // new Ou(new Sup(i,new Constante(10)),new Egal(i,new Constante(10)))),
  // new Afficher(j)
  // );
  //
  // VisiteurExpression ves = new VisiteurInfixe(m);
  // VisiteurExpressionBooleenne vbs = new VisiteurBoolToJava(ves);
  // VisiteurInstruction vs = new VisiteurInstToJava(ves,vbs,4);
  //
  // Z_ClasseJava cj = new Z_ClasseJava("BoucleRepeter", inst, vs);
  // //System.out.println(cj.sourceComplet());
  // //cj.enFichier();
  // try{
  // cj.compiler();
  // }catch(Exception e){
  // fail(e.getMessage());
  // }
  // }

}
