package question2;

import java.util.Vector;
import question1.PilePleineException;
import question1.PileVideException;

public class Pile3 implements PileI {
  private Vector<Object> v;
  private int capacite;

  public Pile3() {
    this(CAPACITE_PAR_DEFAUT);
  }

  public Pile3(int taille) {
    if (taille < 0) {
      taille = CAPACITE_PAR_DEFAUT;
    }
    this.capacite = taille;
    this.v = new Vector<>();
  }

  public void empiler(Object o) throws PilePleineException {
    if (estPleine()) {
      throw new PilePleineException();
    }
    this.v.add(o);
  }

  public Object depiler() throws PileVideException {
    if (estVide()) {
      throw new PileVideException();
    }
    return this.v.remove(this.v.size() - 1);
  }

  public Object sommet() throws PileVideException {
    if (estVide()) {
      throw new PileVideException();
    }
    return this.v.lastElement();
  }

  public int taille() {
    return this.v.size();
  }

  public int capacite() {
    return this.capacite;
  }

  public boolean estVide() {
    return this.v.isEmpty();
  }

  public boolean estPleine() {
    return this.v.size() == this.capacite;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer("[");
    for (int i = this.v.size() - 1; i >= 0; i--) {
      sb.append(this.v.get(i));
      if (i > 0)
        sb.append(", ");
    }
    sb.append("]");
    return sb.toString();
  }

  public boolean equals(Object o) {
    if (o instanceof PileI) {
      PileI p = (PileI) o;
      return this.capacite() == p.capacite() && this.hashCode() == p.hashCode();
    } else
      return false;
  }

  // fonction fournie
  public int hashCode() {
    return toString().hashCode();
  }
}
