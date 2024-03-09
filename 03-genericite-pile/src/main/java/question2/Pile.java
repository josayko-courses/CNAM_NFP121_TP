package question2;

import java.util.Vector;

import question1.PilePleineException;
import question1.PileVideException;

/**
 * A remplacer en partie par votre classe Pile de la question 1.
 *
 * @author Jonny SAYKOSY
 * @version 2023.03.03
 */
public class Pile implements PileI {
  public static final int TAILLE_PAR_DEFAUT = 5;

  private Object[] zone;
  private int ptr;

  public Pile(int taille) {
    if (taille <= 0)
      taille = TAILLE_PAR_DEFAUT;
    this.zone = new Object[taille];
    this.ptr = 0;
  }

  public Pile() {
    this(TAILLE_PAR_DEFAUT);
  }

  public void empiler(Object o) throws PilePleineException {
    if (estPleine())
      throw new PilePleineException();
    this.zone[this.ptr] = o;
    this.ptr = this.ptr++;
  }

  public Object depiler() throws PileVideException {
    if (estVide())
      throw new PileVideException();
    Object el = zone[ptr];
    zone[ptr] = null;
    this.ptr = this.ptr--;
    return el;
  }

  public Object sommet() throws PileVideException {
    if (estVide())
      throw new PileVideException();
    return zone[0];
  }

  public int taille() {
    return ptr;
  }

  public int capacite() {
    return zone.length;
  }

  public boolean estVide() {
    return ptr == 0;
  }

  public boolean estPleine() {
    return ptr == zone.length;
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
            if (!el.equals(this.zone[i])) {
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
          }
        }
      }
      return hasSameElements && this.taille() == p.taille()
          && this.capacite() == p.capacite()
          && this.hashCode() == p.hashCode();
    }
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
  // Réponse: c'est incorrect car deux piles égaux doivent également avoir les
  // éléments contenus identiques.
  // La comparaison de la représentation .toString() de deux piles est non égales
  // car leur hashCode sera toujours différent.

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
