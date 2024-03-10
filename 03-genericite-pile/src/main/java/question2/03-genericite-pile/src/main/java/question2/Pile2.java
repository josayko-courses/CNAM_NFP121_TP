package question2;

import java.util.Stack;
import question1.PilePleineException;
import question1.PileVideException;

public class Pile2 implements PileI {
  /** par délégation : utilisation de la class Stack */
  private Stack<Object> stk;

  /** la capacité de la pile */
  private int capacite;

  /**
   * Création d'une pile.
   *
   * @param taille la taille de la pile, la taille doit être {@code > 0}
   */
  public Pile2(int taille) {
    // à compléter, prévoir le cas <= 0 
  }

  public Pile2() {
    // à completer, on peut appeler le constructeur ci-dessus avec "this(...); 
  }

  public void empiler(Object o) throws PilePleineException {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public Object depiler() throws PileVideException {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public Object sommet() throws PileVideException {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  /**
   * Effectue un test de l'état de la pile.
   *
   * @return vrai si la pile est vide, faux autrement
   */
  public boolean estVide() {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  /**
   * Effectue un test de l'état de la pile.
   *
   * @return vrai si la pile est pleine, faux autrement
   */
  public boolean estPleine() {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  /**
   * Retourne une représentation en String d'une pile, contenant la représentation en String de
   * chaque élément.
   *
   * @return une représentation en String d'une pile
   */
  public String toString() {
    String s = "[";
    // à compléter
    return s + "]";
  }

  public boolean equals(Object o) {
    // à compléter 
    return false;
  }

  // fonction fournie
  public int hashCode() {
    return toString().hashCode();
  }

  /**
   * Retourne le nombre d'élément d'une pile.
   *
   * @return le nombre d'élément
   */
  public int taille() {
    // à compléter
    return 0;
  }

  /**
   * Retourne la capacité de cette pile.
   *
   * @return le nombre d'élément
   */
  public int capacite() {
    // à compléter
    return 0;
  }
}
