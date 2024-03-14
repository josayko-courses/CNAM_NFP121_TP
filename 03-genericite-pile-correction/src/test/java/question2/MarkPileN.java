package question2;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import question1.PilePleineException;
import question1.PileVideException;

// Cette classe de test contient des tests *paramétrés*. Cela permet d'écrire un seul test (avec des
// paramètres, alors que les test ne pernnent habituellement pas de paramètre) qui sera exécuté sur
// plusieurs valeurs de ce paramètre. Ici nous voulons lancer le même test sur plusieurs classes
// différentes. Les tests prennent donc une classe en argument. Cf lesClassesAtester plus bas.

public class MarkPileN {
  private Z_ReferencePileTestGen test;

  // On ne peut par paramétrer le BeforeEach par les même paramètres que les tests. Donc cette
  // méthode testUp n'appelle pas le setUp de Z_ReferencePileTestGen. On pourra améliorer ça quand
  // on pourra paramétrer une classe de test. Ce qui semble prévu dans junit
  // (https://github.com/junit-team/junit5/issues/878)

  @BeforeEach
  public void setUp() {
    test = new Z_ReferencePileTestGen();
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
    test.test_Pile_capacite(c);
  }

  @ParameterizedTest
  @MethodSource("lesClassesAtester")
  public void test_Pile_estPleine(Class<PileI> c) throws PilePleineException {
    test.test_Pile_estPleine(c);
  }

  @ParameterizedTest
  @MethodSource("lesClassesAtester")
  public void test_Pile_sommet(Class<PileI> c) throws PilePleineException, PileVideException {
    test.test_Pile_sommet(c);
  }

  @ParameterizedTest
  @MethodSource("lesClassesAtester")
  public void test_Pile_sommet2(Class<PileI> c) throws PilePleineException, PileVideException {
    test.test_Pile_sommet2(c);
  }

  @ParameterizedTest
  @MethodSource("lesClassesAtester")
  public void test_Pile_estVide(Class<PileI> c) {
    test.test_Pile_estVide(c);
  }

  @ParameterizedTest
  @MethodSource("lesClassesAtester")
  public void test_Pile_toString(Class<PileI> c) throws PilePleineException {
    test.test_Pile_toString(c);
  }

  @ParameterizedTest
  @MethodSource("lesClassesAtester")
  public void test_Pile_TailleNegative(Class<PileI> c) throws ReflectiveOperationException {
    test.test_Pile_TailleNegative(c);
  }

  @ParameterizedTest
  @MethodSource("lesClassesAtester")
  public void test_Pile_equals(Class<PileI> c) throws PilePleineException {
    test.test_Pile_equals(c);
  }

  @ParameterizedTest
  @MethodSource("lesClassesAtester")
  public void test_Pile_equals_hashCode(Class<PileI> c) throws PilePleineException {
    test.test_Pile_equals_hashCode(c);
  }
}
