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
  }

  public Object depiler() throws PileVideException {
    if (estVide())
      throw new PileVideException();
    Object el = this.v.lastElement();
    this.v.removeElementAt(this.v.size() - 1);
    ;
    return el;
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

  @Override
  public boolean equals(Object o) {
    if (o instanceof PileI) {
      Boolean hasSameElements = true;
      PileI p = (PileI) o;
      if (!this.estVide()) {
        Pile tmp = new Pile(p.taille());
        for (int i = this.taille() - 1; i >= 0; i--) {
          try {
            Object el = p.depiler();
            tmp.empiler(el);
            if (!el.equals(this.v.get(i))) {
              hasSameElements = false;
              break;
            }
          } catch (Exception e) {
            hasSameElements = false;
          }
        }
        for (int i = this.taille(); i > 0; i--) {
          try {
            Object el = tmp.depiler();
            p.empiler(el);
          } catch (Exception e) {
            return false;
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
}
