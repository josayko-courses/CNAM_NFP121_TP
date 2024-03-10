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
    if (taille < 0) {
      taille = CAPACITE_PAR_DEFAUT;
    }
    this.capacite = taille;
    this.stk = new Stack<>();
  }

  public Pile2() {
    this(CAPACITE_PAR_DEFAUT);
  }

  public void empiler(Object o) throws PilePleineException {
    if (estPleine()) {
      throw new PilePleineException();
    }
    this.stk.push(o);
  }

  public Object depiler() throws PileVideException {
    if (estVide()) {
      throw new PileVideException();
    }
    return this.stk.pop();
  }

  public Object sommet() throws PileVideException {
    System.out.println(this.toString());
    if (estVide()) {
      throw new PileVideException();
    }
    return this.stk.peek();
  }

  /**
   * Effectue un test de l'état de la pile.
   *
   * @return vrai si la pile est vide, faux autrement
   */
  public boolean estVide() {
    return this.stk.isEmpty();
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
    for (int i = this.stk.size() - 1; i >= 0; i--) {
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
      PileI p = (PileI) o;
      Boolean sameElements = true;
      if (this.taille() == p.taille()) {
        try {
          Pile tmp = new Pile(p.capacite());
          int tailleTotale = this.taille();
          for (int i = tailleTotale - 1; i >= 0; i--) {
            Object el = p.depiler();
            tmp.empiler(el);
            if (tailleTotale == this.taille() && this.stk.get(i) != el) {
              sameElements = false;
              break;
            }
          }
          for (int i = 0; i < tailleTotale; i++) {
            Object t = tmp.depiler();
            p.empiler(t);
          }
        } catch (Exception e) {
          //
        }
        return sameElements
            && this.capacite() == p.capacite()
            && this.hashCode() == p.hashCode();
      }
      return false;
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
