package question2;

import java.util.Iterator;
import java.util.Stack;
import question1.PilePleineException;
import question1.PileVideException;

public class Pile2 implements PileI {
  public static final int TAILLE_PAR_DEFAUT = 5;
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
    this.stk = new Stack<Object>();
    this.capacite = taille > 0 ? taille : TAILLE_PAR_DEFAUT;
  }

  public Pile2() {
    this(TAILLE_PAR_DEFAUT);
  }

  public void empiler(Object o) throws PilePleineException {
    if (estPleine())
      throw new PilePleineException();
    this.stk.push(o);
    this.capacite++;
  }

  public Object depiler() throws PileVideException {
    if (estVide())
      throw new PileVideException();
    this.capacite--;
    return this.stk.lastElement();
  }

  public Object sommet() throws PileVideException {
    if (this.estVide()) {
      throw new PileVideException();
    }
    return this.stk.lastElement();
  }

  /**
   * Effectue un test de l'état de la pile.
   *
   * @return vrai si la pile est vide, faux autrement
   */
  public boolean estVide() {
    return this.stk.empty();
  }

  /**
   * Effectue un test de l'état de la pile.
   *
   * @return vrai si la pile est pleine, faux autrement
   */
  public boolean estPleine() {
    return this.stk.size() == this.capacite;
  }

  /**
   * Retourne une représentation en String d'une pile, contenant la représentation
   * en String de
   * chaque élément.
   *
   * @return une représentation en String d'une pile
   */
  public String toString() {
    StringBuffer sb = new StringBuffer("[");
    Iterator<Object> it = this.stk.iterator();
    while (it.hasNext()) {
      sb.append(it.next());
      if (it.hasNext())
        sb.append(", ");
    }
    sb.append("]");
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof PileI) {
      PileI p = (PileI) o;
      Boolean hasSameElements = true;
      if (this.taille() != 0) {
        for (int i = this.taille(); i >= 0; i--) {
          try {
            if (p.depiler() != this.stk.elementAt(i)) {
              hasSameElements = false;
              break;
            }
          } catch (Exception e) {
            hasSameElements = false;
          }
        }
      } else if (this.taille() != p.taille()) {
        hasSameElements = false;
      }
      return hasSameElements && this.hashCode() == p.hashCode();
    }
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
    return this.stk.size();
  }

  /**
   * Retourne la capacité de cette pile.
   *
   * @return le nombre d'élément
   */
  public int capacite() {
    return this.capacite;
  }
}
