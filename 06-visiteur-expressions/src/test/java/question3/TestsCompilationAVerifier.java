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

// Dans cette classe on génère des code pour des classe Java (String), et on
// lance la compilation java dessus. Si la compilation échoue ou si elle
// génère un warning le test échoue.

public class TestsCompilationAVerifier {

  private ClasseJava createClasse(Instruction inst, Contexte m, String className) {
    VisiteurExpression<String> ves = new question1.VisiteurInfixe(m);
    VisiteurExpressionBooleenne<String> vbs = new question2.VisiteurBoolToJava(ves);
    VisiteurInstruction<String> vs = new question3.VisiteurInstToJava(ves, vbs, 4);
    ClasseJava cj = new ClasseJava(className, inst, vs);
    // On veut voir le code java généré pendant les tests.
    System.out.println("code compilé:\n" + cj.sourceComplet());
    return cj;
  }

  private void testCompile(ClasseJava cj)
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    List<Diagnostic<? extends JavaFileObject>> diags =
        CompileString.compileSeulement(cj).getDiagnostics();
    assertEquals(0, diags.size(), "There are some error/warning messages:\n" + diags);
    // diags.forEach(d -> System.out.println(String.valueOf(d)));
  }

  private void testCompileAndExec(ClasseJava cj)
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    List<Diagnostic<? extends JavaFileObject>> diags =
        CompileString.compileSeulement(cj).getDiagnostics();
    assertEquals(0, diags.size(), "There are some error/warning messages:\n" + diags);
    CompileString.compileEtExecute(cj);
    // diags.forEach(d -> System.out.println(String.valueOf(d)));
  }

  // Un test de code incorrect (variable non déclarée) pour vérifier que la
  // compilation échoue effectivement.
  @Test
  public void test_CompilationDeFactorielFailing()
      throws CompilationFailed, MalformedURLException, ReflectiveOperationException {
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
    ClasseJava cj = createClasse(inst, m, "FactBug");
    assertThrows(CompilationFailed.class, () -> testCompile(cj));
  }

  @Test
  public void test_CompilationDeFactoriel()
      throws CompilationFailed, IOException, ReflectiveOperationException {
    Contexte m = new Memoire();
    Variable x = new Variable(m, "x", 5);
    Variable fact = new Variable(m, "fact", 1);

    Instruction inst = //
        new TantQue( //
            new Sup(x, new Constante(1)), //
            new Sequence( //
                new Affectation(fact, new Multiplication(fact, x)), //
                new Affectation(x, new Soustraction(x, new Constante(1)))));
    ClasseJava cj = createClasse(inst, m, "Fact");
    testCompileAndExec(cj);
  }

  @Test
  public void test_CompilationDeSomme() throws Exception {
    Contexte m = new Memoire();
    Variable n = new Variable(m, "n", 100);
    Variable s = new Variable(m, "s", 0);
    Variable i = new Variable(m, "i", 0);

    Instruction inst = //
        new Sequence( //
            new Sequence( //
                new TantQue( //
                    new Inf(i, n), //
                    new Sequence( //
                        new Affectation(i, new Addition(i, new Constante(1))), //
                        new Affectation(s, new Addition(s, i)))), //
                new Afficher(s)), //
            new Assertion(new Egal(s, new Constante(5050))) // à voir ........
            );
    ClasseJava cj = createClasse(inst, m, "X_Somme");
    testCompileAndExec(cj);
  }

  @Test
  public void test_CompilationDUneBouclePour() throws Exception {
    Contexte m = new Memoire();
    Variable i = new Variable(m, "i");
    Variable j = new Variable(m, "j", 1);

    Instruction inst = //
        new Sequence( //
            new Pour( //
                new Affectation(i, new Constante(0)), //
                new Inf(i, new Constante(5)), //
                new Affectation(i, new Addition(i, new Constante(1))), //
                new Affectation(j, new Addition(j, new Constante(1)))), //
            new Assertion(new Egal(j, new Constante(6))));
    ClasseJava cj = createClasse(inst, m, "BouclePour");
    testCompileAndExec(cj);
  }

  @Test
  public void test_CompilationDeBouclesTantQueImbriquees() throws Exception {
    Contexte m = new Memoire();
    Variable i = new Variable(m, "i");
    Variable j = new Variable(m, "j", 1);

    Instruction inst = //
        new TantQue( //
            new Inf(i, new Constante(10)), //
            new Sequence( //
                new TantQue( //
                    new Inf(j, new Constante(10)), //
                    new Affectation(j, new Addition(j, new Constante(1)))), //
                new Sequence( //
                    new Affectation(i, new Addition(i, new Constante(1))), //
                    new Affectation(j, new Constante(1)))));
    ClasseJava cj = createClasse(inst, m, "BoucleBoucle");
    testCompileAndExec(cj);
  }

  @Test
  public void test_CompilationDeBouclesPourImbriquees() throws Exception {
    question1.Contexte m = new Memoire();
    Variable x = new Variable(m, "x", Integer.valueOf(5));
    Variable k = new Variable(m, "k", Integer.valueOf(0));
    Variable n = new Variable(m, "n", Integer.valueOf(0));
    Variable f = new Variable(m, "f", Integer.valueOf(1));
    Instruction inst = //
        new Pour( //
            new Affectation(k, n), //
            new Inf(k, x), //
            new Pour( //
                new Affectation(k, n), //
                new Inf(k, x), //
                new Pour( //
                    new Affectation(k, n), //
                    new Inf(k, x), //
                    new Affectation(k, new Addition(k, f)), //
                    new Affectation(k, new Addition(k, f))), //
                new Affectation(k, new Addition(k, f))), //
            new Affectation(k, new Addition(k, f)));
    ClasseJava cj = createClasse(inst, m, "PourClasseTest");
    testCompileAndExec(cj);
  }

  @Test
  public void test_CompilationDeBouclesTantQuePourImbriquees() throws Exception {
    Contexte m = new Memoire();
    Variable i = new Variable(m, "i");
    Variable j = new Variable(m, "j", 1);

    Instruction inst = //
        new TantQue( //
            new Inf(i, new Constante(10)), //
            new Sequence( //
                new TantQue( //
                    new Inf(j, new Constante(10)), //
                    new Pour( //
                        new Affectation(i, new Constante(0)), //
                        new Inf(i, new Constante(5)), //
                        new Sequence( //
                            new Affectation(
                                i, //
                                new Addition(i, new Constante(1))),
                            new Afficher(i)), //
                        new Affectation(j, new Addition(j, new Constante(1))))), //
                new Sequence( //
                    new Affectation(i, new Addition(i, new Constante(1))), //
                    new Affectation(j, new Constante(1)))));
    ClasseJava cj = createClasse(inst, m, "BoucleTantQuePour");
    testCompile(cj); // TRop long à exécuter.
  }

  @Test
  public void test_CompilationDeBouclesTantQuePourImbriquees_bis() throws Exception {
    Contexte m = new Memoire();
    Variable i = new Variable(m, "i");
    Variable j = new Variable(m, "j", 1);

    Instruction inst = //
        new TantQue( //
            new Inf(i, new Constante(10)), //
            new Sequence( //
                new TantQue( //
                    new Inf(j, new Constante(10)), //
                    new Pour( //
                        new Affectation(i, new Constante(0)), //
                        new Inf(i, new Constante(5)), //
                        new Sequence( //
                            new Affectation(i, new Addition(i, new Constante(1))), //
                            new Pour( //
                                new Affectation(i, new Constante(0)), //
                                new Inf(i, new Constante(5)), //
                                new Sequence( //
                                    new Affectation(i, new Addition(i, new Constante(1))), //
                                    new Afficher(i)), //
                                new Affectation(j, new Addition(j, new Constante(1))))), //
                        new Affectation(j, new Addition(j, new Constante(1))))), //
                new Sequence( //
                    new Affectation(i, new Addition(i, new Constante(1))), //
                    new Affectation(j, new Constante(1)))));
    ClasseJava cj = createClasse(inst, m, "BoucleTantQuePour2");
    testCompile(cj); // Trop long à exécuter
  }

  @Test
  public void test_AvecImbricationsMultiples() throws Exception {
    Contexte m = new Memoire();
    Variable i = new Variable(m, "i");
    Variable j = new Variable(m, "j", 1);

    Instruction inst = //
        new TantQue( //
            new Inf(i, new Constante(10)), //
            new Sequence( //
                new TantQue( //
                    new Inf(j, new Constante(10)), //
                    new Affectation(j, new Addition(j, new Constante(1)))), //
                new Sequence( //
                    new Affectation(i, new Addition(i, new Constante(1))), //
                    new Affectation(j, new Constante(1)))));

    Variable x = new Variable(m, "x", 5);
    Instruction inst2 = //
        new TantQue( //
            new Sup(x, new Constante(2)), //
            new Affectation(x, new Soustraction(x, new Constante(1))));

    Instruction inst3 = //
        new TantQue( //
            new Inf(i, new Constante(10)), //
            new Sequence( //
                new TantQue( //
                    new Inf(j, new Constante(10)), //
                    new Affectation(j, new Addition(j, new Constante(1)))), //
                new Sequence(new Affectation(i, new Addition(i, new Constante(1))), inst)));

    Instruction k = //
        new Pour( //
            new Affectation(x, new Constante(0)), //
            new Inf(x, new Constante(10)), //
            new Sequence(inst2, inst3), //
            new Affectation(x, new Addition(x, new Constante(1))));

    Instruction inst4 = new Selection(new Inf(x, new Constante(10)), k, inst3);

    Instruction inst5 = new Selection(new Inf(x, new Constante(10)), inst4, inst3);
    ClasseJava cj = createClasse(inst5, m, "TestsAvecImbricationsMultiples");
    testCompile(cj); // Trop long à exécuter.
  }
}
