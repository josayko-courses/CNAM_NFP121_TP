package question2;

import java.util.Iterator;
import java.util.Vector;
import question1.PilePleineException;
import question1.PileVideException;

public class Pile3 extends PileA implements PileI {
  private Vector<Object> v;

  public Pile3() {
    /* SOLUTION:// à completer, on peut appeler le constructeur ci-dessous avec "this(...); */
    this(CAPACITE_PAR_DEFAUT);
    /* FIN SOLUTION */
  }

  public Pile3(int taille) {
    /* SOLUTION:// à compléter */
    if (taille <= 0) taille = CAPACITE_PAR_DEFAUT;
    v = new Vector<Object>(taille);
    /* FIN SOLUTION */
  }

  public void empiler(Object o) throws PilePleineException {
    /* SOLUTION */
    if (estPleine()) throw new PilePleineException();
    v.addElement(o);
    /* FIN SOLUTION */
  }

  public Object depiler() throws PileVideException {
    /* SOLUTION */
    if (estVide()) throw new PileVideException();
    return v.remove(v.size() - 1);
    /* FIN SOLUTION */
  }

  public Object sommet() throws PileVideException {
    /* SOLUTION */
    if (estVide()) throw new PileVideException();
    return v.lastElement();
    /* FIN SOLUTION */
  }

  public int taille() {
    /* SOLUTION */
    return v.size();
    /* FIN SOLUTION */
  }

  public int capacite() {
    /* SOLUTION */
    return v.capacity();
    /* FIN SOLUTION */
  }

  public boolean estVide() {
    /* SOLUTION */
    return v.size() == 0;
    /* FIN SOLUTION */
  }

  public boolean estPleine() {
    /* SOLUTION */
    return v.size() == v.capacity();
    /* FIN SOLUTION */
  }

  public String toString() {
    String s = "[";
    /* SOLUTION:// à compléter */
    for (int i = v.size() - 1; i >= 0; i--) {
      s = s + v.elementAt(i);
      if (i > 0) s = s + ", ";
    }
    /* FIN SOLUTION */
    return s + "]";
  }

//  public boolean equals(Object o) {
//    /* SOLUTION:// à compléter
//    return (false); */
//    if (o instanceof Pile3) {
//      Pile3 p = (Pile3) o;
//      if (p.taille() == this.taille() && p.capacite() == this.capacite()) {
//        for (int i = v.size() - 1; i >= 0; i--)
//          if (!v.elementAt(i).equals(p.v.elementAt(i))) return false;
//        return true;
//      }
//    }
//    return false;
//    /* FIN SOLUTION */
//  }

  // fonction fournie
  public int hashCode() {
    return toString().hashCode();
  }


  @SuppressWarnings("rawtypes")
  @Override
  public Iterator iterator() {
    return this.v.iterator();
  }
}
