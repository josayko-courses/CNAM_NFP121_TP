package question2;

import java.util.Arrays;
import java.util.Iterator;
import question1.PilePleineException;
import question1.PileVideException;

/**
 * A remplacer en partie par votre classe Pile de la question 1.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Pile extends PileA implements PileI {

  private Object[] zone;
  private int ptr;

  public Pile(int taille) {
    /* SOLUTION:// à compléter, prévoir le cas <= 0 */
    if (taille <= 0) taille = CAPACITE_PAR_DEFAUT;
    this.zone = new Object[taille];
    this.ptr = 0;
    /* FIN SOLUTION */
  }

  public Pile() {
    /* SOLUTION:// à completer, on peut appeler le constructeur ci-dessus avec "this(...); */
    this(CAPACITE_PAR_DEFAUT);
    /* FIN SOLUTION */
  }

  public void empiler(Object o) throws PilePleineException {
    /* SOLUTION */
    if (estPleine()) throw new PilePleineException();
    this.zone[this.ptr] = o;
    this.ptr++;
    /* FIN SOLUTION */
  }

  public Object depiler() throws PileVideException {
    /* SOLUTION */
    if (estVide()) throw new PileVideException();
    this.ptr--;
    return zone[ptr];
    /* FIN SOLUTION */
  }

  public Object sommet() throws PileVideException {
    /* SOLUTION */
    if (estVide()) throw new PileVideException();
    return zone[ptr - 1];
    /* FIN SOLUTION */
  }

  public int taille() {
    /* SOLUTION */
    return this.ptr;
    /* FIN SOLUTION */
  }

  public int capacite() {
    /* SOLUTION */
    return this.zone.length;
    /* FIN SOLUTION */
  }

  public boolean estVide() {
    /* SOLUTION */
    return ptr == 0;
    /* FIN SOLUTION */
  }

  public boolean estPleine() {
    /* SOLUTION */
    return ptr == zone.length;
    /* FIN SOLUTION */
  }

//  @Override
//  public boolean equals(Object o) {
//    /* SOLUTION:// à compléter*/
//    if ((o != null) && (o.getClass() == this.getClass())) {
//      Pile p = (Pile) o;
//      if (p.taille() == this.taille() && p.capacite() == this.capacite()) {
//        for (int i = ptr - 1; i >= 0; i--) if (!zone[i].equals(p.zone[i])) return false;
//        return true;
//      }
//    }
//    /* FIN SOLUTION */
//    return false;
//  }

  // Est-ce correct?
  //   public boolean equals(Object o){
  //     if(o instanceof PileI){
  //       PileI p = (PileI)o;
  //       return this.capacite()==p.capacite() && this.hashCode() == p.hashCode();
  //     }else
  //       return false;
  //   }

  // fonction fournie
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  @Override
  public String toString() {
    /* SOLUTION:// à compléter
    return null; */
    StringBuffer sb = new StringBuffer("[");
    for (int i = ptr - 1; i >= 0; i--) {
      sb.append(zone[i].toString());
      if (i > 0) sb.append(", ");
    }
    sb.append("]");
    return sb.toString();
    /* FIN SOLUTION */
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Iterator iterator() {
    return Arrays.stream(this.zone).iterator();
  }
}
