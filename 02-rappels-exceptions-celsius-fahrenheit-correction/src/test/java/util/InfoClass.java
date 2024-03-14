package util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.bcel.classfile.*;

// Une class d'analyse du bytecode d'une classe. À la création, on parcourt le .class, on mémorise
// le nombre d'appels aux méthodes rencontrés partout et on fournit une classe InfoMethod par
// méthode, ainsi que deux pseudo méthodes "#all#" et "#test#" qui peuvent être interrogées à la
// junit.
public class InfoClass {
  JavaClass theClass;
  Method[] methodes;
  // nom de méthode m -> (nom de méthode m' -> nombre d'appels à m' dans m)
  // Attention les clés de 1er niveau ne sont pas les mêmes qu'au
  // 2e niveau même si les deux sont des noms de méthodes
  // Les 1er vienent du toString de Method alors que les
  // autres du toString de Code (quand on rencontre un appel).
  // Exemple: clé=méthode scannée: "public boolean add(Object t) [Signature: (TT;)Z]"
  // Exemple: sous-clé = méth. appelée: "question1.Ensemble.add(Ljava/lang/Object;)Z"
  public Map<String, InfoMethod> callMap;
  private Set<String> testMethods;
  // Les lambda créent des méthode "marquée synthetic". On va ajouter les appels des lambda aux
  // méthodes qui les contiennent. Pour cela le visiteur se contente de stocker quel synthetic est
  // défini par quelle méthode.
  public HashMap<String, Method> lambdaMap;

  public InfoClass(String classPath) throws IOException {
    super();
    analyseClass(classPath);
    // AJout des pseudo-méthodes accumulant les stats sur toutes les méthodes de la classe
    callMap.put("#all#", this.callMapSumAll());
    callMap.put("#alltests#", this.callMapSumOnlyTests()); // seulement les @Test
    // debug: pour voir les vrais noms des méthodes (les 2 niveaux)
    // System.out.println("MAP: "+this.callMap);
  }

  public InfoMethod get(String methName) {
    return this.callMap.get(methName);
  }

  public void assertNbTestGreater(int min) {
    assertTrue(
        getNbTestsMethods() >= min,
        "\n"
            + theClass.getClassName()
            + ": Trop peu ("
            + getNbTestsMethods()
            + ") de méthodes de tests. Il en faut au moins "
            + min
            + ".");
  }

  public int getNbTestsMethods() {
    return this.testMethods.size();
  }

  public Integer addCall(Method callerMeth, String mthName) {
    InfoMethod infoMeth = this.callMap.get(callerMeth.toString());
    if (infoMeth == null) { // Create the map if necessary
      infoMeth = new InfoMethod(callerMeth);
      // Also declare the caller as a test if it is
      // On compte les méthodes synthetic (lambdas) car elles sont en général dans des assertThrows
      if (infoMeth.isTest() || callerMeth.isSynthetic()) {
        testMethods.add(callerMeth.toString());
      }
      this.callMap.put(callerMeth.toString(), infoMeth);
    }
    return infoMeth.inc(mthName);
  }

  // Below this point all methods are private

  private void analyseClass(String classPath, org.apache.bcel.classfile.Visitor v)
      throws IOException {
    this.theClass = parseClasse(classPath);
    this.methodes = theClass.getMethods();
    DescendingVisitor dv = new org.apache.bcel.classfile.DescendingVisitor(theClass, v);
    this.callMap = new HashMap<String, InfoMethod>();
    this.testMethods = new HashSet<String>();
    this.lambdaMap = new HashMap<String, Method>();
    // On parcours les méthode de la classe et on stocke dans callMap les appels de méthodes
    // apparaissant dans les méthodes de test.
    dv.visit();
    // System.out.println("callMap =" + callMap);
    // On regroupe les données de chaque lambda dans la méthode qui le contient
    // NOTE: ça ne marche pas avec tous les compilateurs... Donc les méthode synthetic qui
    // restent sont comptées comme des tests.
    Iterator<Map.Entry<String, InfoMethod>> it = callMap.entrySet().iterator();
    while (it.hasNext()) { // On a besoin de l'itérateur pour fale le remove à la fin du corps
      Map.Entry<String, InfoMethod> mE = it.next();
      String motif = "private synthetic void lambda$";
      if (mE.getKey().startsWith(motif)) { // ceci est un lambda
        String lambdaNum = mE.getKey().substring(motif.length());
        lambdaNum = lambdaNum.substring(0, lambdaNum.indexOf('('));
        // System.out.println("\nlambda(1): " + mE.getKey() + "(" + lambdaNum + ")");
        Method caller = this.lambdaMap.get(lambdaNum);
        if (caller != null) { // sinon c'est un lambda introduit par le compilo
          // System.out.println("CALLEr = " + caller);
          InfoMethod infocaller = get(caller.toString()); // getByMethodName(caller.getName());
          // System.out.println("\nlambda(2): " + mE.getKey() + "\n called by " + caller.getName());
          for (Map.Entry<String, Integer> e : mE.getValue().entrySet()) {
            infocaller.inc(e.getKey(), e.getValue()); // merge calls to e.getKey() to its caller
          }
          it.remove();
        } else {
          System.out.println("la lambda: " + lambdaNum + "n'existe pas??");
        }
      }
    }
  }

  private void analyseClass(String classPath) throws IOException {
    analyseClass(classPath, new VisitorCode(this));
  }

  private static JavaClass parseClasse(final InputStream inputStream, final String className)
      throws IOException {
    ClassParser parser = new ClassParser(inputStream, className);
    JavaClass javaClass = parser.parse();
    return javaClass;
  }

  private static JavaClass parseClasse(String classPath) throws IOException {
    InputStream inputStream = InfoClass.class.getResourceAsStream(classPath);
    String[] l = classPath.split("/");
    String className = l[l.length - 1];
    return parseClasse(inputStream, className);
  }

  // Return the Infoclass corresponding to the union of all ctest methods
  private InfoMethod callMapSumOnlyTests() {
    return callMapSumFilter(
        s -> testMethods.contains(s), "Les méthode de @Test de " + theClass.getClassName());
  }

  // Return the InfoMethod corresponding to the union of all methods
  private InfoMethod callMapSumAll() {
    return callMapSumFilter(s -> true, "Les méthodes de la classe " + theClass.getClassName());
  }

  private InfoMethod callMapSumFilter(Predicate<String> test, String msg) {
    Map<String, Integer> mp = new HashMap<String, Integer>();
    // On globalise les nombres d'appels à chaque méthode depuis des méthodes de test.
    for (Map.Entry<String, InfoMethod> mE : callMap.entrySet()) {
      if (test.test(mE.getKey())) { // On ne traite que les méthodes vérifiant test
        for (Map.Entry<String, Integer> e : mE.getValue().entrySet()) {
          mp.put(e.getKey(), mp.getOrDefault(e.getKey(), 0) + e.getValue());
        }
      }
    }
    return new InfoMethod(msg, mp); // dummy method
  }
}
