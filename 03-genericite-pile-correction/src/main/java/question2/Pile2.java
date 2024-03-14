package question2;

import java.util.Iterator;
import java.util.Stack;
import question1.PilePleineException;
import question1.PileVideException;

public class Pile2 extends PileA implements PileI {
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
    /* SOLUTION:// à compléter, prévoir le cas <= 0 */
    if (taille <= 0) taille = CAPACITE_PAR_DEFAUT;
    this.stk = new Stack<Object>();
    this.capacite = taille;
    /* FIN SOLUTION */
  }

  public Pile2() {
    /* SOLUTION:// à completer, on peut appeler le constructeur ci-dessus avec "this(...); */
    this(PileI.CAPACITE_PAR_DEFAUT);
    /* FIN SOLUTION */
  }

  public void empiler(Object o) throws PilePleineException {
    /* SOLUTION */
    if (estPleine()) throw new PilePleineException();
    stk.push(o);
    /* FIN SOLUTION */
  }

  public Object depiler() throws PileVideException {
    /* SOLUTION */
    if (estVide()) throw new PileVideException();
    return stk.pop();
    /* FIN SOLUTION */
  }

  public Object sommet() throws PileVideException {
    /* SOLUTION */
    if (estVide()) throw new PileVideException();
    return stk.peek();
    /* FIN SOLUTION */
  }

  /**
   * Effectue un test de l'état de la pile.
   *
   * @return vrai si la pile est vide, faux autrement
   */
  public boolean estVide() {
    /* SOLUTION */
    return stk.empty();
    /* FIN SOLUTION */
  }

  /**
   * Effectue un test de l'état de la pile.
   *
   * @return vrai si la pile est pleine, faux autrement
   */
  public boolean estPleine() {
    /* SOLUTION */
    return capacite == stk.size();
    /* FIN SOLUTION */
  }

  /**
   * Retourne une représentation en String d'une pile, contenant la représentation en String de
   * chaque élément.
   *
   * @return une représentation en String d'une pile
   */
  public String toString() {
    String s = "[";
    /* SOLUTION:// à compléter*/
    for (int i = stk.size() - 1; i >= 0; i--) {
      s = s + stk.elementAt(i);
      if (i > 0) s = s + ", ";
    }
    /* FIN SOLUTION */
    return s + "]";
  }

//  public boolean equals(Object o) {
//    /* SOLUTION:// à compléter */
//    if (o instanceof Pile2) {
//      Pile2 p = (Pile2) o;
//      if (p.taille() == this.taille() && p.capacite() == this.capacite()) {
//        for (int i = stk.size() - 1; i >= 0; i--)
//          if (!stk.elementAt(i).equals(p.stk.elementAt(i))) return false;
//        return true;
//      }
//    }
//    /* FIN SOLUTION */
//    return false;
//  }

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
    /* SOLUTION:// à compléter
    return 0;*/
    return this.stk.size();
    /* FIN SOLUTION */
  }

  /**
   * Retourne la capacité de cette pile.
   *
   * @return le nombre d'élément
   */
  public int capacite() {
    /* SOLUTION:// à compléter
    return 0;*/
    return this.capacite;
    /* FIN SOLUTION */
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Iterator iterator() {
    return this.stk.iterator();
  }
}
