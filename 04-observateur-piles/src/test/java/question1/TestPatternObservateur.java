package question1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SuppressWarnings("deprecation")
public class TestPatternObservateur {
  @Test
  public void testNotify() {
    ConcreteSubject list;
    ConcreteObserver observer;

    list = new ConcreteSubject(); // création d'un "observé" constitué d'une liste
    observer = new ConcreteObserver(); // création d'un observateur
    list.addObserver(observer); // ajouter cet observateur à la liste
    list.insert("il fait beau, ce matin"); // modification de cette liste, l'observateur doit
    // (devrait) être notifié
    // "vérification" :
    assertFalse(observer.senders().empty()); // elle ne doit pas être vide,
    assertEquals(list, observer.senders().pop()); // est-ce le bon émetteur ?
    assertEquals(
        "il fait beau, ce matin", observer.arguments().pop()); // le paramètre reçu est-il correct ?
  }

  // une liste, 2 observateurs
  @Test
  public void test1() {
    question1.ConcreteSubject l1 = new question1.ConcreteSubject();
    question1.ConcreteObserver o1 = new question1.ConcreteObserver();
    question1.ConcreteObserver o2 = new question1.ConcreteObserver();
    l1.addObserver(o1);
    l1.addObserver(o2);
    l1.insert("test");
    l1.insert(" 1 ");

    // vérifier que les deux observateurs ont bien été notifiés avec les bons paramètres
    // à compléter.
    assertFalse(o1.senders().empty());
    assertFalse(o2.senders().empty());

    assertEquals(l1, o1.senders().pop());
    assertEquals(l1, o1.senders().pop());
    assertEquals(l1, o2.senders().pop());
    assertEquals(l1, o2.senders().pop());

    assertEquals(" 1 ", o1.arguments().pop());
    assertEquals("test", o1.arguments().pop());
    assertEquals(" 1 ", o2.arguments().pop());
    assertEquals("test", o2.arguments().pop());

    // ne pas modifier ces lignes, dernières assertions de cette méthode
    assertTrue(o1.senders().empty() && o1.arguments().empty());
    assertTrue(o2.senders().empty() && o2.arguments().empty());
  }

  // deux listes, 1 observateur
  @Test
  public void test2() {
    question1.ConcreteSubject l1 = new question1.ConcreteSubject();
    question1.ConcreteSubject l2 = new question1.ConcreteSubject();

    question1.ConcreteObserver o = new question1.ConcreteObserver();
    l1.addObserver(o);
    l2.addObserver(o);
    l1.insert("testA");
    l1.insert(" A ");
    l2.insert("testB");
    l2.insert(" B ");

    // vérifier que l'observateur a bien été notifié par les deux listes
    // à compléter.
    assertFalse(o.senders().empty());

    assertEquals(l2, o.senders().pop());
    assertEquals(l2, o.senders().pop());
    assertEquals(l1, o.senders().pop());
    assertEquals(l1, o.senders().pop());

    assertEquals(" B ", o.arguments().pop());
    assertEquals("testB", o.arguments().pop());
    assertEquals(" A ", o.arguments().pop());
    assertEquals("testA", o.arguments().pop());

    // ne pas modifier cette ligne, dernière assertion de cette méthode
    assertTrue(o.senders().empty() && o.arguments().empty());
  }

  // deux listes, 2 observateurs
  @Test
  public void test3() {
    question1.ConcreteSubject l1 = new question1.ConcreteSubject();
    question1.ConcreteSubject l2 = new question1.ConcreteSubject();
    question1.ConcreteObserver o1 = new question1.ConcreteObserver();
    question1.ConcreteObserver o2 = new question1.ConcreteObserver();
    l1.addObserver(o1);
    l1.addObserver(o2);
    l2.addObserver(o1);
    l2.addObserver(o2);

    // vérifier le bon fonctionnement de countObservers(), de deleteObserver et deleteObservers()
    // à compléter.
    assertEquals(2, l1.countObservers());
    assertEquals(2, l2.countObservers());

    l1.deleteObserver(o1);
    assertEquals(1, l1.countObservers());
    l1.deleteObservers();
    assertEquals(0, l1.countObservers());

    l2.deleteObserver(o2);
    assertEquals(1, l2.countObservers());
    l2.deleteObservers();
    assertEquals(0, l2.countObservers());

    // ne pas modifier ces lignes, dernières assertions de cette méthode
    assertTrue(o1.senders().empty());
    assertTrue(o2.senders().empty());
    assertTrue(l1.countObservers() == 0);
    assertTrue(l2.countObservers() == 0);
  }
}
