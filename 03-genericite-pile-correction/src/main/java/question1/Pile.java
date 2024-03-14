package question1;

/**
 * Remplacez int[] par Object[].
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Pile {
  public static final int TAILLE_PAR_DEFAUT = 5;

  private /* SOLUTION:int */ Object /* FIN SOLUTION */[] zone;
  private int ptr;

  public Pile(int taille) {
    if (taille < 0) taille = TAILLE_PAR_DEFAUT;
    this.zone = new /* SOLUTION:int */ Object /* FIN SOLUTION */[taille];
    this.ptr = 0;
  }

  public Pile() {
    this(TAILLE_PAR_DEFAUT);
  }

  public void empiler(
      /* SOLUTION:int i*/ Object o /* FIN SOLUTION */) throws PilePleineException { // à compléter
    if (estPleine()) throw new PilePleineException();
    this.zone[this.ptr] = /* SOLUTION:i*/ o /* FIN SOLUTION */;
    this.ptr++;
  }

  public /* SOLUTION:int */ Object /* FIN SOLUTION */ depiler()
      throws PileVideException { // à compléter
    if (estVide()) throw new PileVideException();
    this.ptr--;
    return zone[ptr];
  }

  public boolean estVide() {
    return ptr == 0;
  }

  public boolean estPleine() {
    return ptr == zone.length;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer("[");
    for (int i = ptr - 1; i >= 0; i--) {
      sb.append(/* SOLUTION:Integer.toString(zone[i])*/ zone[i].toString() /* FIN SOLUTION */);
      if (i > 0) sb.append(", ");
    }
    sb.append("]");
    return sb.toString();
  }
}
