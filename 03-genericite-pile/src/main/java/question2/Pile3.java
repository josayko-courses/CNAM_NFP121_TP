package question2;

import java.util.Iterator;
import java.util.Vector;
import question1.PilePleineException;
import question1.PileVideException;

public class Pile3 implements PileI {
  public static final int TAILLE_PAR_DEFAUT = 5;
  private Vector<Object> v;
  private int size;

  public Pile3(int taille) {
    this.v = new Vector<Object>(taille);
    this.size = taille;
  }

  public Pile3() {
    this(TAILLE_PAR_DEFAUT);
  }

  public void empiler(Object o) throws PilePleineException {
    if (estPleine())
      throw new PilePleineException();
    this.v.add(o);
    this.size++;
  }

  public Object depiler() throws PileVideException {
    if (estVide())
      throw new PileVideException();
    this.size--;
    return this.v.lastElement();
  }

  public Object sommet() throws PileVideException {
    if (this.estVide()) {
      throw new PileVideException();
    }
    return this.v.lastElement();
  }

  public int taille() {
    return this.v.size();
  }

  public int capacite() {
    return this.size;
  }

  public boolean estVide() {
    return this.v.isEmpty();
  }

  public boolean estPleine() {
    return this.v.size() == this.size;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer("[");
    Iterator<Object> it = this.v.iterator();
    while (it.hasNext()) {
      sb.append(it.next());
      if (it.hasNext())
        sb.append(", ");
    }
    sb.append("]");
    return sb.toString();
  }

  public boolean equals(Object o) {
    if (o instanceof PileI) {
      PileI p = (PileI) o;
      Boolean hasSameElements = true;
      if (this.taille() != 0) {
        for (int i = this.taille(); i >= 0; i--) {
          try {
            if (p.depiler() != this.v.elementAt(i)) {
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
}
