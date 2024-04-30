package compilation;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

public class CompilationFailed extends Exception {
  private static final long serialVersionUID = 1L;
  public String code;
  public DiagnosticCollector<JavaFileObject> diagnostic;

  CompilationFailed(DiagnosticCollector<JavaFileObject> d, String code) {
    this.diagnostic = d;
    this.code = code;
  }

  @Override
  public String getMessage() {
    return "\nCompilation failed.\n*** Source Code ***\n"
        + this.code
        + "]\n*** Diagnostic ***\n"
        + this.diagnostic.getDiagnostics().toString();
  }
}
