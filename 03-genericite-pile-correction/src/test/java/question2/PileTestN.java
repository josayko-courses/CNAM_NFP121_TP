package question2;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

// Le but de cette classe est de tester les différentes implémentations des piles. On pourrait
// recopier les tests pour chaque implémentation (Pil,e Pile2, Pile3, Pile4) mais cela deviendrait
// difficile à maintenir: chaque modification des tests nécessiterait d'être propagées dans les 4
// fichiers.

// On décide donc d'utiliser une fonctionnalité de junit: les tests paramétrés. Le principe est
// simple: normalement les méthodes de tests ne prennent pas d'argument, mais une méthode de test
// paramétrée prend un argument (ou plusieurs) et est précédée par une annotation donnant une liste
// de valeurs à passer successivement à la méthode.

// Ici nous donnons un seul paramètre à chacun des tests (de type java.lang.Class): la classe qu'on
// veut tester. L'annotation @MethodSource("lesClassesAtester") spécifie la liste des classes à
// utiliser.

public class PileTestN {
  private PileI p1, p2, p;

  // Pour l'instant junit5 ne permet pas de paramétrer le BeforeEach par les même paramètres que les
  // tests. Donc on appelle la fixture à la main dans chaque test pour créer les objet de la bonne
  // classe.
  // NOTE: On pourra améliorer ça quand on pourra paramétrer une classe de test. Ce qui semble prévu
  // dans junit (https://github.com/junit-team/junit5/issues/878).

  // A appeler au début de chaque méthode de test
  public void setUpGen(Class<PileI> c) {
    try {
      Constructor<PileI> constructor = c.getDeclaredConstructor();
      p1 = constructor.newInstance();
      p2 = constructor.newInstance();
      p = c.getDeclaredConstructor(int.class).newInstance(3);
    } catch (ReflectiveOperationException e) {
      fail("setUpGen a échoué: problème de classe.");
      e.printStackTrace();
    }
  }

  // Vous pouvez temporairement enlever certaines classes de ce stream afin de ne tester qu'un
  // sous-ensemble des classes. Mais n'oubliez pas de remettre tout le moment venu.
  private static Stream<Arguments> lesClassesAtester() {
    return Stream.of( // évite que le formatteur chang les sauts de ligne.
        Arguments.of(question2.Pile.class), //
        Arguments.of(question2.Pile2.class), //
        Arguments.of(question2.Pile3.class), //
        Arguments.of(question2.Pile4.class));
  }

  @ParameterizedTest
  @MethodSource("lesClassesAtester")
  public void test_Pile_capacite(Class<PileI> c) {
    this.setUpGen(c); // Faire ceci dans chaque test pour déclencher la fixture
    assertEquals(PileI.CAPACITE_PAR_DEFAUT, p1.capacite());
  }

  /* SOLUTION://compléter */
  @ParameterizedTest
  @MethodSource("lesClassesAtester")
  public void test_Pile_estPleine(Class<PileI> c) throws Exception {
    this.setUpGen(c);
    p.empiler(3);
    assertEquals(1, p.taille());
    p.empiler(2);
    assertEquals(2, p.taille());
    p.empiler(1);
    assertEquals(3, p.taille());
    assertEquals(true, p.estPleine());
    assertEquals(p.taille(), p.capacite());
    assertThrows(question1.PilePleineException.class, () -> p.empiler(0));
  }

  @ParameterizedTest
  @MethodSource("lesClassesAtester")
  public void test_Pile_sommet(Class<PileI> c) throws Exception {
    this.setUpGen(c);
    assertEquals(true, p.estVide());
    p.empiler(Integer.valueOf(3));
    assertEquals(Integer.valueOf(3), p.sommet(), " sommet ?? ");
    assertEquals(1, p.taille());
    assertEquals(Integer.valueOf(3), p.depiler(), " depiler ?? ");
    assertEquals(0, p.taille());
  }

  @ParameterizedTest
  @MethodSource("lesClassesAtester")
  public void test_Pile_estVide(Class<PileI> c) throws Exception {
    this.setUpGen(c);
    assertEquals(true, p.estVide());
    assertThrows(question1.PileVideException.class, () -> p.depiler());
  }

  @ParameterizedTest
  @MethodSource("lesClassesAtester")
  public void test_Pile_toString(Class<PileI> c) throws Exception {
    this.setUpGen(c);
    assertEquals("[]", p.toString(), "toString incorrect ? ");
    p.empiler(4);
    assertEquals("[4]", p.toString(), "toString incorrect ? ");
    p.empiler(5);
    assertEquals("[5, 4]", p.toString(), "toString incorrect ? ");
    p.empiler(3);
    assertEquals("[3, 5, 4]", p.toString(), "toString incorrect ? ");
  }

  @ParameterizedTest
  @MethodSource("lesClassesAtester")
  public void test_Pile_TailleNegative(Class<PileI> c) throws ReflectiveOperationException {
    this.setUpGen(c);
    p = c.getDeclaredConstructor(int.class).newInstance(-3);
    PileI p = new question2.Pile(-3);
    assertEquals(PileI.CAPACITE_PAR_DEFAUT, p.capacite());
  }

  @ParameterizedTest
  @MethodSource("lesClassesAtester")
  public void test_Pile_equals(Class<PileI> c) throws Exception {
    this.setUpGen(c);
    p1.empiler(3);
    p1.empiler(2);
    p1.empiler(1);
    p2.empiler(3);
    p2.empiler(2);
    p2.empiler(1);
    assertTrue(p1.equals(p2), "égalité de deux piles ? ");
    assertTrue(p2.equals(p1), "égalité de deux piles ? ");
    assertTrue(p1.equals(p1), "égalité de deux piles ? ");
    p2.empiler(1);
    assertFalse(p1.equals(p2), "égalité de deux piles ? ");
  }
  /* FIN SOLUTION */
}
