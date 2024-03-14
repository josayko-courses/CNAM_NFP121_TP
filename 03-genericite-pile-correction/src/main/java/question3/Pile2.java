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
    /* SOLUTION:// à compléter*/
    if (taille <= 0) taille = CAPACITE_PAR_DEFAUT;
    this.stk = new Stack<T>();
    this.capacite = taille;
    /* FIN SOLUTION */
  }

  public Pile2() {
    /* SOLUTION:// à compléter*/
    this(PileI.CAPACITE_PAR_DEFAUT);
    /* FIN SOLUTION */
  }

  public void empiler(T o) throws PilePleineException {
    /* SOLUTION */
    if (estPleine()) throw new PilePleineException();
    stk.push(o);
    /* FIN SOLUTION */
  }

  public T depiler() throws PileVideException {
    /* SOLUTION */
    if (estVide()) throw new PileVideException();
    return stk.pop();
    /* FIN SOLUTION */
  }

  public T sommet() throws PileVideException {
    /* SOLUTION */
    if (estVide()) throw new PileVideException();
    return stk.peek();
    /* FIN SOLUTION */
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
    /* SOLUTION:return false; */
    return stk.empty();
    /* FIN SOLUTION */
  }

  /**
   * Effectue un test de l'etat de la pile.
   *
   * @return vrai si la pile est pleine, faux autrement
   */
  public boolean estPleine() {
    /* SOLUTION:return false; */
    return capacite == stk.size();
    /* FIN SOLUTION */
  }

  /**
   * Retourne une representation en String d'une pile, contenant la representation en String de
   * chaque element.
   *
   * @return une representation en String d'une pile
   */
  public String toString() {
    /* SOLUTION:return "xxx"; */
    String s = "[";
    for (int i = stk.size() - 1; i >= 0; i--) {
      s = s + stk.elementAt(i);
      if (i > 0) s = s + ", ";
    }
    return s + "]";
    /* FIN SOLUTION */
  }

  public int capacite() {
    return this.capacite;
  }

  public int taille() {
    return this.stk.size();
  }

  public boolean equals(Object o) {
    /* SOLUTION:return false; */
    if (o instanceof Pile2) {
      @SuppressWarnings("rawtypes")
      Pile2 p = (Pile2) o;
      if (p.taille() == this.taille()) {
        for (int i = stk.size() - 1; i >= 0; i--)
          if (!stk.elementAt(i).equals(p.stk.elementAt(i))) return false;
        return true;
      }
    }
    return false;
    /* FIN SOLUTION */
  }

  public int hashCode() {
    return toString().hashCode();
  }
}
