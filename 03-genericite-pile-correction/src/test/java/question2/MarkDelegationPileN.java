package question2;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.InfoClass;
import util.InfoMethod;

@TestInstance(Lifecycle.PER_CLASS) // Une seule instance pour tous les tests.
class MarkDelegationPileN {

  private static final String PILE = "/question2/Pile.class";
  private static final String PILE2 = "/question2/Pile2.class";
  private static final String PILE3 = "/question2/Pile3.class";
  private static final String PILE4 = "/question2/Pile4.class";

  // qq nom de méthodes du délégué
  private class D {
    private static final Pattern VECTOR = pat("java.util.Vector.*");
    private static final Pattern STACK = pat("java.util.Stack.*");
    private static final Pattern ARRAY = pat("#anewarray#");
    private static final Pattern ARRAYLIST = pat("java.util.ArrayList.*");
  }

  static HashSet<Pattern> allDelegues =
      new HashSet<Pattern>(List.of(D.ARRAY, D.STACK, D.VECTOR, D.ARRAYLIST));
  static HashSet<String> allImpl = new HashSet<String>(List.of(PILE, PILE2, PILE3, PILE4));
  static HashMap<String, HashSet<Pattern>> interdits = new HashMap<String, HashSet<Pattern>>();
  static HashMap<String, HashSet<Pattern>> obligatoires = new HashMap<String, HashSet<Pattern>>();

  @BeforeAll
  public void setUp() throws IOException {
    for (String impl : MarkDelegationPileN.allImpl) {
      obligatoires.put(impl, new HashSet<Pattern>());
    }
    // Les contrainte d'implémentation du TP:
    obligatoires.get(PILE).add(D.ARRAY); // Pile doit utiliser un tableau
    obligatoires.get(PILE2).add(D.STACK); // Pile2 un Stack
    obligatoires.get(PILE3).add(D.VECTOR); // Pile3 un Vector
    // et Pile4 ne doit utiliser rien du tout
    // on remplit interdits avec tous les non obligatoires.
    for (String impl : allImpl) {
      HashSet<Pattern> all = new HashSet<Pattern>();
      all.addAll(allDelegues); // Ajoute tous les délégués
      all.removeAll(obligatoires.get(impl)); // retire les obligatoires
      interdits.put(impl, all);
    }
  }

  // Vous pouvez temporairement enlever certaines classes de ce stream afin de ne tester qu'un
  // sous-ensemble des classes. Mais n'oubliez pas de remettre tout le moment venu.
  private static Stream<Arguments> lesClassesAtester() {
    return allImpl.stream().map(Arguments::of);
  }

  private static Pattern pat(String regexp) {
    return Pattern.compile(regexp);
  }

  @ParameterizedTest
  @MethodSource("lesClassesAtester")
  public void test_Utilise_Gen(String impl) throws IOException {
    HashSet<Pattern> h = obligatoires.get(impl);
    InfoMethod info = (new InfoClass(impl)).get("#all#");
    // System.out.println("callMap = " + info);
    h.forEach((d) -> info.assertNbCallsGreater(d, 1, "Pas de trace de " + d.toString() + "."));
  }

  @ParameterizedTest
  @MethodSource("lesClassesAtester")
  public void test_NUtilisePas_Gen(String impl) throws IOException {
    HashSet<Pattern> h = interdits.get(impl);
    InfoMethod info = (new InfoClass(impl)).get("#all#");
    // System.out.println("callMap = " + info);
    h.forEach((d) -> info.assertNbCallsLesser(d, 0, d.toString() + " interdit dans cette classe."));
  }
}
