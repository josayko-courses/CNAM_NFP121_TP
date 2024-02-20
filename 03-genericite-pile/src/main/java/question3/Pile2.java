package question3;

import java.util.Stack;
import question1.PilePleineException;
import question1.PileVideException;

public class Pile2<T> implements PileI<T> {
  /** par délégation : utilisation de la class Stack */
  private Stack<T> stk;

  /** la capacité de la pile */
  private int capacite;

  /**
   * Création d'une pile.
   *
   * @param taille la "taille maximale" de la pile, doit etre {@code > 0}
   */
  public Pile2(int taille) {
    // à compléter
  }

  public Pile2() {
    // à compléter
  }

  public void empiler(T o) throws PilePleineException {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public T depiler() throws PileVideException {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public T sommet() throws PileVideException {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  // recopier ici toutes les autres méthodes
  // qui ne sont pas modifiées en fonction
  // du type des éléments de la pile */

  /**
   * Effectue un test de l'etat de la pile.
   *
   * @return vrai si la pile est vide, faux autrement
   */
  public boolean estVide() {
    return false; 
  }

  /**
   * Effectue un test de l'etat de la pile.
   *
   * @return vrai si la pile est pleine, faux autrement
   */
  public boolean estPleine() {
    return false; 
  }

  /**
   * Retourne une representation en String d'une pile, contenant la representation en String de
   * chaque element.
   *
   * @return une representation en String d'une pile
   */
  public String toString() {
    return "xxx"; 
  }

  public int capacite() {
    return this.capacite;
  }

  public int taille() {
    return this.stk.size();
  }

  public boolean equals(Object o) {
    return false; 
  }

  public int hashCode() {
    return toString().hashCode();
  }
}
