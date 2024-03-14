package util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import org.apache.bcel.classfile.AnnotationEntry;
import org.apache.bcel.classfile.Method;

// Informations sur une méthode (ou pseudo-méthode): méthodes appelées et nb d'appels, annotations.
public class InfoMethod {
  // On ne stocke pas une vraie méthode ici car on a aussi des pseudo-méthodes (#all#, #alltests#)
  private String meth_name;
  private AnnotationEntry[] annots;
  public Map<String, Integer> calls = null;

  // Pour une méthode qui existe vraiment
  public InfoMethod(Method meth) {
    super();
    annots = meth.getAnnotationEntries();
    meth_name = meth.toString();
    this.calls = new HashMap<String, Integer>();
  }

  // Pour fabrique des infomethod sur une méthode qui n'existe pas forcément
  public InfoMethod(String meth_name, Map<String, Integer> calls) {
    super();
    this.meth_name = meth_name;
    this.calls = calls;
  }

  public boolean isTest() {
    for (AnnotationEntry a : annots) {
      System.out.println("annot: " + a);
      if (a.getAnnotationType().endsWith("jupiter/api/Test;")
          || a.getAnnotationType().endsWith("jupiter/params/ParameterizedTest;")) {
        return true;
      }
    }
    return false;
  }

  public String toString() {
    return calls.toString();
  }

  public int inc(String s) {
    return inc(s, 1);
  }

  public int inc(String s, int n) {
    Integer res = calls.put(s, this.getNbOcc(s) + n);
    return res == null ? 0 : res;
  }

  public int getNbOcc(String s) {
    return calls.getOrDefault(s, 0);
  }

  public int getNbOcc(Pattern regex) {
    Integer res = 0;

    for (Map.Entry<String, Integer> e : calls.entrySet()) {
      if (regex.matcher(e.getKey()).matches()) {
        res = res + e.getValue();
      }
    }
    return res;
  }

  public Set<Map.Entry<String, Integer>> entrySet() {
    return calls.entrySet();
  }

  // Doit marcher même si callee est une regexp
  public Function<Integer, String> nbCallsbuildMsg(String callee, String explic) {
    String calleeName = "d'appels à " + callee;
    if (callee.contains("<init>")) {
      calleeName = "d'appels à new " + callee.substring(0, callee.indexOf("<init>"));
    }
    if (callee.equals("#goto#")) {
      calleeName = "de boucles (for, while, forEach)";
    }
    if (callee.equals("#pop#")) {
      calleeName = "de valeurs d'expressions non utilisées";
    }
    final String calleeName2 = calleeName;
    final String caller = meth_name;
    Function<Integer, String> fmsg =
        n -> "\nDans " + caller + "\nle nb " + calleeName2 + " = " + n + ".\nIncorrect: " + explic;
    return fmsg;
  }

  // callee est un pattern (résultat de Pattern.compile("regexp")).
  public void assertNbCalls(Pattern callee, Predicate<Integer> p, Function<Integer, String> fmsg) {
    Integer nbCalls = this.getNbOcc(callee);
    assertTrue(p.test(nbCalls), fmsg.apply(nbCalls) + "\n");
  }

  public void assertNbCalls(String callee, Predicate<Integer> p, Function<Integer, String> fmsg) {
    Integer nbCalls = this.getNbOcc(callee);
    assertTrue(p.test(nbCalls), fmsg.apply(nbCalls) + "\n");
  }

  public void assertNbCalls(String callee, Predicate<Integer> p, String explic) {
    Function<Integer, String> fmsg = nbCallsbuildMsg(callee.toString(), explic);
    assertNbCalls(callee, p, fmsg);
  }

  public void assertNbCalls(Pattern callee, Predicate<Integer> p, String explic) {
    Function<Integer, String> fmsg = nbCallsbuildMsg(callee.toString(), explic);
    assertNbCalls(callee, p, fmsg);
  }

  public void assertNbCalls(String callee, Predicate<Integer> p) {
    assertNbCalls(callee, p, "");
  }

  public void assertNbCalls(Pattern callee, Predicate<Integer> p) {
    assertNbCalls(callee, p, "");
  }

  private static String cat(String[] t) {
    StringBuffer sb = new StringBuffer();
    boolean beg = true;
    for (String s : t) {
      sb.append((beg ? "\n" : "") + s);
      beg = false;
    }
    return sb.toString();
  }

  public void assertNbCallsLesser(String callee, int max, String... explic) {
    assertNbCalls(callee, n -> n <= max, "max = " + max + ". " + cat(explic));
  }

  public void assertNbCallsLesser(Pattern callee, int max, String... explic) {
    assertNbCalls(callee, n -> n <= max, "max = " + max + ". " + cat(explic));
  }

  public void assertNbCallsGreater(String callee, int min, String... explic) {
    assertNbCalls(callee, n -> n >= min, "min = " + min + ". " + cat(explic));
  }

  public void assertNbCallsGreater(Pattern callee, int min, String... explic) {
    assertNbCalls(callee, n -> n >= min, "min = " + min + ". " + cat(explic));
  }
}
