package question3;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

public class TestVisiteurToJava {

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

  @Test
  public void testFactoriel() throws Exception {
    Contexte m = new Memoire();
    Variable x = new Variable(m, "x", 5);
    Variable fact = new Variable(m, "fact", 1);

    Instruction inst =
        new TantQue(
            new Sup(x, new Constante(1)),
            new Sequence(
                new Affectation(fact, new Multiplication(fact, x)),
                new Affectation(x, new Soustraction(x, new Constante(1)))));

    VisiteurExpression<String> ves = new VisiteurInfixe(m);
    VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToJava(ves);
    VisiteurInstruction<String> vs = new VisiteurInstToJava(ves, vbs, 4);
    // Ceci crée une classe Fact, avec une méthode main contenant les déclarations de variables de m
    // + inst.
    ClasseJava cj = new ClasseJava("Fact", inst, vs);
    // ClasseJava cj = new ClasseJava("Fact", "question3", inst, vs); // Le répertoire du package
    // doit exister
    // Option 1: afficher la classe  vérifier manuellement son contenu
    System.out.println(cj.sourceComplet());
    // Option 2: écrire dans un fichier et vérifier manuellement son contenu (ou bien
    // le compiler avec javac)
    cj.enFichier(); // Le fichier généré devrait être dans build/generated/fichierGeneres/
    // Option 3: compiler le code dans une classe (sans créer de fichier) et tester qu'aucune erreur
    // ou warning de compilation n'est levé:
    testCompile(cj);
    // Option 4: option 3 + lancer la méthode main de la classe ainsi compilée.
    testCompileAndExec(cj);

    // ci dessous un exemple de ce qui pourrait être généré voir le fichier
    // Fact.java

    //
    // /** NFP121 question3, tp6
    // source Java genere par l'intermediaire de votre visiteur
    // "VisiteurInstToJava"
    // */
    // public class Fact{
    //
    // public static void main(String[] args)throws Exception{
    // int fact=1;
    // int x=5;
    //
    // while(x > 1){
    // fact=(fact * x);
    // x=(x - 1);
    // }
    // }
    //
    // }
    //
  }
}
