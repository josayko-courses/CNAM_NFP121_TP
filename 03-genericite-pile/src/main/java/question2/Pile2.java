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
    this.stk = new Stack<Object>();
    this.capacite = taille > 0 ? taille : CAPACITE_PAR_DEFAUT;
  }

  public Pile2() {
    this(CAPACITE_PAR_DEFAUT);
  }

  public void empiler(Object o) throws PilePleineException {
    if (estPleine())
      throw new PilePleineException();
    this.stk.push(o);
  }

  public Object depiler() throws PileVideException {
    if (estVide())
      throw new PileVideException();
    return this.stk.pop();
  }

  public Object sommet() throws PileVideException {
    if (this.estVide()) {
      throw new PileVideException();
    }
    return this.stk.firstElement();
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
    for (int i = this.taille() - 1; i >= 0; i--) {
      sb.append(this.stk.get(i));
      if (i > 0)
        sb.append(", ");
    }
    sb.append("]");
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof PileI) {
      Boolean hasSameElements = true;
      PileI p = (PileI) o;
      if (!this.estVide()) {
        Pile2 tmp = new Pile2(p.taille());
        for (int i = this.taille() - 1; i >= 0; i--) {
          try {
            Object el = p.depiler();
            tmp.empiler(el);
            if (!el.equals(this.stk.get(i))) {
              hasSameElements = false;
              break;
            }
          } catch (Exception e) {
            hasSameElements = false;
          }
        }
        for (int i = this.taille() - 1; i >= 0; i--) {
          try {
            Object el = tmp.depiler();
            p.empiler(el);
          } catch (Exception e) {
          }
        }
      }
      return hasSameElements && this.taille() == p.taille()
          && this.capacite() == p.capacite()
          && this.hashCode() == p.hashCode();
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
