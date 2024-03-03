package question1;

/**
 * Remplacez int[] par Object[].
 *
 * @author Jonny SAYKOSY
 * @version 2024.03.03
 */
public class Pile {
  public static final int TAILLE_PAR_DEFAUT = 5;

  private Object[] zone;
  private int ptr;

  public Pile(int taille) {
    if (taille < 0)
      taille = TAILLE_PAR_DEFAUT;
    this.zone = new Object[taille];
    this.ptr = 0;
  }

  public Pile() {
    this(TAILLE_PAR_DEFAUT);
  }

  public void empiler(
      Object i) throws PilePleineException { // à compléter
    if (estPleine())
      throw new PilePleineException();
    this.zone[this.ptr] = i;
    this.ptr++;
  }

  public Object depiler()
      throws PileVideException { // à compléter
    if (estVide())
      throw new PileVideException();
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
      sb.append(zone[i]);
      if (i > 0)
        sb.append(", ");
    }
    sb.append("]");
    return sb.toString();
  }
}
