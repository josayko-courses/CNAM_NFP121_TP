package compilation;

import static java.util.Objects.requireNonNull;

import compilation.util.com.baeldung.inmemorycompilation.InMemoryFileManager;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

public class CompileString {

  // Compile une classe et lance sa méthode main (si exec est vrai.
  // Ne produit aucun fichier intermédiaire.
  private static DiagnosticCollector<JavaFileObject> compileEtExecuteGen(
      String classpath, String packageName, String code, boolean exec)
      throws ReflectiveOperationException, MalformedURLException, CompilationFailed {
    boolean success = true;
    DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    InMemoryFileManager manager =
        new InMemoryFileManager(compiler.getStandardFileManager(null, null, null));
    String classFullName = packageName != null ? packageName + "." + classpath : classpath;
    List<JavaFileObject> sourceFiles =
        Collections.singletonList(new JavaSourceFromString(classFullName, code));
    JavaCompiler.CompilationTask task =
        compiler.getTask(null, manager, diagnostics, null, null, sourceFiles);
    success = task.call();
    // On lève une exception si la compilation échoue ou si un elle génère un warning
    if (!success || diagnostics.getDiagnostics().size() != 0) {
      throw new CompilationFailed(diagnostics, "class " + classpath + " " + code);
    } else { // Sinon on exécute le code compilé sauf si !exec
      if (exec) {
        ClassLoader classLoader = manager.getClassLoader(null);
        Class<?> clazz = classLoader.loadClass(classFullName);
        clazz
            .getDeclaredMethod("main", new Class[] {String[].class})
            .invoke(null, new Object[] {null});
      }
      return diagnostics;
    }
  }

  /**
   * Compile une classe sans lancer sa méthode main ensuite. Ne produit aucun fichier intermédiaire.
   *
   * @throws CompilationFailed si la compilation échoue. Les diagnostique sont dans l'exception
   * @throws n'importe quelle exception levée par la méthode main lancée après la compilation
   * @return les diagnostiques (message non fatals) si la compilation réussie.
   */
  public static DiagnosticCollector<JavaFileObject> compileEtExecute(ClasseJava cj)
      throws ReflectiveOperationException, MalformedURLException, CompilationFailed {
    return compileEtExecuteGen(
        cj.getNomDeLaClasse(), cj.getNomDuPaquetage(), cj.sourceComplet(), true);
  }

  /**
   * Compile une classe et lance sa méthode main. Ne produit aucun fichier intermédiaire.
   *
   * @throws CompilationFailed si la compilation échoue. Les diagnostique sont dans l'exception
   * @return les diagnostiques (message non fatals) si la compilation réussie.
   */
  public static DiagnosticCollector<JavaFileObject> compileSeulement(ClasseJava cj)
      throws ReflectiveOperationException, MalformedURLException, CompilationFailed {
    return compileEtExecuteGen(
        cj.getNomDeLaClasse(), cj.getNomDuPaquetage(), cj.sourceComplet(), false);
  }
}

// Classe utilitaire pour la compilation
class JavaSourceFromString extends SimpleJavaFileObject {
  final String code;

  public JavaSourceFromString(String name, String sourceCode) {
    super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
    this.code = requireNonNull(sourceCode, "sourceCode must not be null");
  }

  @Override
  public CharSequence getCharContent(boolean ignoreEncodingErrors) {
    return code;
  }
}
