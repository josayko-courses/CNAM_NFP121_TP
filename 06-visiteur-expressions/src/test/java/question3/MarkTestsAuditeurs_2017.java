package question3;

import static org.junit.jupiter.api.Assertions.*;

import compilation.ClasseJava;
import compilation.CompilationFailed;
import compilation.CompileString;
import java.net.MalformedURLException;
import java.util.List;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import org.junit.jupiter.api.Test;
import question1.*;
import question2.*;

public class MarkTestsAuditeurs_2017 {

  private ClasseJava createClasse(Instruction inst, Contexte m, String className) {
    VisiteurExpression<String> ves = new question1.VisiteurInfixe(m);
    VisiteurExpressionBooleenne<String> vbs = new question2.VisiteurBoolToJava(ves);
    VisiteurInstruction<String> vs = new question3.VisiteurInstToJava(ves, vbs, 4);
    ClasseJava cj = new ClasseJava(className, inst, vs);
    System.out.println("code compilé:\n" + cj.sourceComplet());
    return cj;
  }

  private void testCompileAndExec(ClasseJava cj)
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    List<Diagnostic<? extends JavaFileObject>> diags =
        CompileString.compileSeulement(cj).getDiagnostics();
    assertEquals(0, diags.size(), "There are some error/warning messages:\n" + diags);
    CompileString.compileEtExecute(cj);
  }

  public void ignore_testVariableInutile() {
    Contexte m = new Memoire();
    VisiteurExpression<Integer> ve = new VisiteurEvaluation(m);
    VisiteurExpressionBooleenne<Boolean> vb = new VisiteurBoolEvaluation(ve);
    VisiteurInstruction<Contexte> vi = new VisiteurInstEvaluation(ve, vb);
    new Selection(
            new Faux(), //
            new Affectation(new Variable(m, "variableInutile"), new Constante(2)))
        .accepter(vi);
    assertThrows(
        RuntimeException.class,
        () -> m.lire("variableInutile"),
        "le contexte ne devrait pas contenir variableInutile puisque sa création est soumise à condition = false");
  }

  @Test
  public void testFactorielPour() {
    Contexte m = new Memoire();
    Variable x = new Variable(m, "x");
    Variable fact = new Variable(m, "fact", 1);
    VisiteurExpression<Integer> ve = new VisiteurEvaluation(m);
    VisiteurExpressionBooleenne<Boolean> vb = new VisiteurBoolEvaluation(ve);
    VisiteurInstruction<Contexte> vi = new VisiteurInstEvaluation(ve, vb);

    VisiteurExpression<String> ves = new VisiteurInfixe(m);
    VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToString(ves);
    VisiteurInstruction<String> vs = new VisiteurInstToString(ves, vbs);

    Instruction i =
        new Pour( //
            new Affectation(x, new Constante(5)), //
            new Sup(x, new Constante(1)), //
            new Affectation(fact, new Multiplication(fact, x)), //
            new Affectation(x, new Soustraction(x, new Constante(1)))); //

    i.accepter(vi);
    assertTrue(
        m.lire("fact") == fact(5),
        (String) i.accepter(vs) + " ne donne pas le résultat attendu ...");
  }

  private static int fact(int x) {
    int fact = 1;
    while (x > 1) {
      fact = fact * x;
      x = x - 1;
    }
    return fact;
  }

  /**
   * Teste le visiteur InstToJava pour le programme Somme. Test si la compilation réussie sur une
   * classe contenant ce code dans la méthode main.
   *
   * @throws CompilationFailed
   * @throws ReflectiveOperationException
   * @throws MalformedURLException
   */
  @Test
  public void testSommeToJava()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    Contexte m = new Memoire();
    Variable n = new Variable(m, "n", 100);
    Variable s = new Variable(m, "s", 0);
    Variable i = new Variable(m, "i", 0);

    Instruction inst =
        new Sequence( //
            new TantQue( //
                new Inf(i, n), //
                new Sequence( //
                    new Affectation(i, new Addition(i, new Constante(1))), //
                    new Affectation(s, new Addition(s, i)))), //
            new Afficher(s)); //
    ClasseJava cj = createClasse(inst, m, "Somme100");
    testCompileAndExec(cj);
  }

  /**
   * Teste le visiteur InstToJava pour le programme Mult.
   *
   * @throws CompilationFailed
   * @throws ReflectiveOperationException
   * @throws MalformedURLException
   */
  @Test
  public void testMultToJava()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {

    Contexte m = new Memoire();
    Variable a = new Variable(m, "a", 7);
    Variable b = new Variable(m, "b", 85);
    Variable z = new Variable(m, "z", 0);
    Instruction inst =
        new Sequence( //
            new TantQue( //
                new Sup(b, new Constante(0)), //
                new Selection( //
                    new Egal( //
                        new Soustraction( //
                            b, //
                            new Multiplication( //
                                new Division(b, new Constante(2)), new Constante(2))), //
                        new Constante(1)), //
                    new Sequence( //
                        new Affectation(z, new Addition(z, a)), //
                        new Affectation(b, new Soustraction(b, new Constante(1)))), //
                    new Sequence( //
                        new Affectation(a, new Multiplication(a, new Constante(2))), //
                        new Affectation(b, new Division(b, new Constante(2)))))), //
            new Afficher(z));

    ClasseJava cj = createClasse(inst, m, "Mult");
    testCompileAndExec(cj);
  }

  /**
   * Test simple pour compilation de boucle Pour.
   *
   * @throws CompilationFailed
   * @throws ReflectiveOperationException
   * @throws MalformedURLException
   */
  @Test
  public void testBouclePour()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    Contexte m = new Memoire();
    Variable x = new Variable(m, "x");
    Variable fact = new Variable(m, "fact", 1);

    Instruction inst =
        new Sequence( //
            new Pour( //
                new Affectation(x, new Constante(5)), //
                new Sup(x, new Constante(1)), //
                new Affectation(fact, new Multiplication(fact, x)), //
                new Affectation(x, new Soustraction(x, new Constante(1)))), //
            new Afficher(fact));
    ClasseJava cj = createClasse(inst, m, "BouclePourFact");
    testCompileAndExec(cj);
  }

  /**
   * Test pour compilation de boucles imbriquées TantQue / Pour.
   *
   * @throws CompilationFailed
   * @throws ReflectiveOperationException
   * @throws MalformedURLException
   */
  @Test
  public void testBouclesImbriquees()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    Contexte m = new Memoire();
    Variable x = new Variable(m, "x");
    Variable y = new Variable(m, "y", 3);
    Variable fact = new Variable(m, "fact", 1);

    VisiteurExpression<Integer> vi = new VisiteurEvaluation(m);
    VisiteurExpressionBooleenne<Boolean> vb = new VisiteurBoolEvaluation(vi);
    VisiteurInstruction<Contexte> vc = new VisiteurInstEvaluation(vi, vb);

    Instruction inst =
        new Sequence( //
            new TantQue( //
                new Sup(y, new Constante(0)), //
                new Sequence( //
                    new Pour( //
                        new Affectation(x, new Constante(5)), //
                        new Sup(x, new Constante(1)), //
                        new Affectation(fact, new Multiplication(fact, x)), //
                        new Affectation(x, new Soustraction(x, new Constante(1)))), //
                    new Affectation(y, new Soustraction(y, new Constante(1))))), //
            new Afficher(fact));
    ClasseJava cj = createClasse(inst, m, "BouclesImbriquees");
    testCompileAndExec(cj);
    assertTrue(1728000 == vc.visite((Sequence) inst).lire("fact"));
  }

  /**
   * Test pour compilation de boucles imbriquées Pour / TantQue.
   *
   * @throws CompilationFailed
   * @throws ReflectiveOperationException
   * @throws MalformedURLException
   */
  @Test
  public void testBouclesImbriquees2()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    Contexte m = new Memoire();
    Variable x = new Variable(m, "x");
    Variable y = new Variable(m, "y", 3);
    Variable fact = new Variable(m, "fact", 1);

    VisiteurExpression<Integer> vi = new VisiteurEvaluation(m);
    VisiteurExpressionBooleenne<Boolean> vb = new VisiteurBoolEvaluation(vi);
    VisiteurInstruction<Contexte> vc = new VisiteurInstEvaluation(vi, vb);

    Instruction inst =
        new Sequence( //
            new Pour( //
                new Affectation(x, new Constante(5)), //
                new Sup(x, new Constante(1)), //
                new Sequence( //
                    new TantQue( //
                        new Sup(y, new Constante(1)), //
                        new Sequence( //
                            new Affectation(fact, new Multiplication(fact, y)), //
                            new Affectation(y, new Soustraction(y, new Constante(1))))), //
                    new Affectation(y, new Constante(3))), //
                new Affectation(x, new Soustraction(x, new Constante(1)))), //
            new Afficher(fact));
    ClasseJava cj = createClasse(inst, m, "BouclesImbriquees2");
    testCompileAndExec(cj);
    assertTrue(1296 == vc.visite((Sequence) inst).lire("fact"));
  }

  /**
   * Test simple pour compilation de Selection dans une boucle Pour.
   *
   * @throws CompilationFailed
   * @throws ReflectiveOperationException
   * @throws MalformedURLException
   */
  @Test
  public void testSelection()
      throws MalformedURLException, ReflectiveOperationException, CompilationFailed {
    Contexte m = new Memoire();
    Variable x = new Variable(m, "x");
    Variable fact = new Variable(m, "fact", 1);
    Instruction inst =
        new Sequence( //
            new Pour( //
                new Affectation(x, new Constante(5)), //
                new Sup(x, new Constante(1)), //
                new Selection( //
                    new Sup(x, new Constante(3)), //
                    new Affectation(fact, new Multiplication(fact, x)), //
                    new Affectation(fact, new Division(fact, x))), //
                new Affectation(x, new Soustraction(x, new Constante(1)))), //
            new Afficher(fact));
    ClasseJava cj = createClasse(inst, m, "Selec");
    testCompileAndExec(cj);
  }
}
