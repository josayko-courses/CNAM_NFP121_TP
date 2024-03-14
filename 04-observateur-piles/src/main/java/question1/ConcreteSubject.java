package question1;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Décrivez votre classe ConcreteSubject ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
@SuppressWarnings("deprecation")
public class ConcreteSubject extends Observable {

  /** ConcreteSubject est composé d'une liste list */
  private ArrayList<String> list;

  public ConcreteSubject() {
    list = new ArrayList<String>();
  }

  public void insert(String name) {
    list.add(name);
    setChanged();
    notifyObservers(name);
  }

  public String toString() {
    return list.toString();
  }
}
