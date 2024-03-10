package question2;

import question1.PilePleineException;
import question1.PileVideException;

/**
 * A remplacer en partie par votre classe Pile de la question 1.
 *
 * @author Jonny SAYKOSY
 * @version 2023.03.10
 */
public class Pile implements PileI {

  private Object[] zone;
  private int ptr;
  private int capacite;

  public Pile(int taille) {
    if (taille < 0) {
      taille = CAPACITE_PAR_DEFAUT;
    }
    this.capacite = taille;
    this.zone = new Object[this.capacite];
    this.ptr = 0;
  }

  public Pile() {
    this(CAPACITE_PAR_DEFAUT);
  }

  public void empiler(Object o) throws PilePleineException {
    if (estPleine()) {
      throw new PilePleineException();
    }
    this.zone[this.ptr] = o;
    this.ptr++;
  }

  public Object depiler() throws PileVideException {
    if (estVide()) {
      throw new PileVideException();
    }
    this.ptr--;
    return this.zone[this.ptr];
  }

  public Object sommet() throws PileVideException {
    if (estVide()) {
      throw new PileVideException();
    }
    return this.zone[this.ptr];
  }

  public int taille() {
    return this.zone.length;
  }

  public int capacite() {
    return this.capacite;
  }

  public boolean estVide() {
    return this.zone.length == 0;
  }

  public boolean estPleine() {
    return this.zone.length == this.capacite;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof PileI) {
      PileI p = (PileI) o;
      return this.capacite() == p.capacite() && this.hashCode() == p.hashCode();
    } else
      return false;
  }

  // Est-ce correct?
  // public boolean equals(Object o){
  // if(o instanceof PileI){
  // PileI p = (PileI)o;
  // return this.capacite()==p.capacite() && this.hashCode() == p.hashCode();
  // }else
  // return false;
  // }

  // fonction fournie
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer("[");
    for (int i = ptr - 1; i >= 0; i--) {
      sb.append(zone[i]);
      if (i > 0)
        sb.append(", ");
    }
    sb.append("]");
    return sb.toString();
  }
}
