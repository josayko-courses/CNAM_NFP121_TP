package question2;

import question1.PilePleineException;
import question1.PileVideException;

public class Pile4 implements PileI {

  /**
   * la liste des Maillons/Elements. Plus exactement son premier élément, qui
   * contient le suivant
   * etc.
   */
  private Maillon stk;

  /** la capacité de la pile */
  private int capacite;

  /** le nombre */
  private int nombre;

  /**
   * Classe interne "statique" contenant chaque élément de la chaine c'est une
   * proposition, vous
   * pouvez l'ignorer !
   */
  private static class Maillon implements Cloneable {
    private Object element;
    private Maillon suivant;

    public Maillon(Object element, Maillon suivant) {
      this.element = element;
      this.suivant = suivant;
    }

    public Maillon suivant() {
      return this.suivant;
    }

    public Object element() {
      return this.element;
    }

  }

  /**
   * Création d'une pile.
   *
   * @param taille la taille de la pile, la taille doit être {@code > 0}
   */
  public Pile4(int taille) {
    if (taille <= 0)
      taille = CAPACITE_PAR_DEFAUT;
    this.stk = null;
    this.capacite = taille;
    this.nombre = 0;
  }

  public Pile4() {
    this(PileI.CAPACITE_PAR_DEFAUT);
  }

  public void empiler(Object o) throws PilePleineException {
    if (estPleine())
      throw new PilePleineException();
    if (this.stk != null) {
      Maillon it = this.stk;
      while (it.suivant != null) {
        it = this.stk.suivant();
      }
      it.suivant = new Maillon(o, null);
    } else {
      this.stk = new Maillon(o, null);
    }
    this.nombre++;
  }

  // TODO: pas bon
  public Object depiler() throws PileVideException {
    if (estVide())
      throw new PileVideException();
    Object el = null;
    Maillon previous = this.stk;
    for (Maillon it = this.stk; it != null; it = it.suivant()) {
      if (it.suivant() == null) {
        el = it.element();
        break;
      }
      previous = it;
    }
    previous.suivant = null;
    this.nombre--;
    System.out.println(previous.element());
    return el;
  }

  public Object sommet() throws PileVideException {
    if (estVide())
      throw new PileVideException();
    Maillon it = this.stk;
    while (it.suivant != null) {
      it = this.stk.suivant();
    }
    return it.element();
  }

  /**
   * Effectue un test de l'état de la pile.
   *
   * @return vrai si la pile est vide, faux autrement
   */
  public boolean estVide() {
    return this.stk == null;
  }

  /**
   * Effectue un test de l'état de la pile.
   *
   * @return vrai si la pile est pleine, faux autrement
   */
  public boolean estPleine() {
    return this.nombre == this.capacite;
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
    if (!this.estVide()) {
      for (Maillon it = this.stk; it != null; it = it.suivant()) {
        sb.append(it.element());
        if (it.suivant() == null)
          break;
        sb.append(", ");
      }
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
        Pile4 tmp = new Pile4(p.taille());
        for (Maillon it = this.stk; it != null; it = it.suivant()) {
          try {
            Object el = p.depiler();
            System.out.println(el);
            tmp.empiler(el);
            if (!el.equals(it.element())) {
              hasSameElements = false;
              break;
            }
          } catch (Exception e) {
            hasSameElements = false;
          }
        }
        // for (Maillon it = tmp.stk; it != null; it = it.suivant()) {
        // try {
        // Object el = tmp.depiler();
        // p.empiler(el);
        // } catch (Exception e) {
        // }
        // }
      }
      return hasSameElements && this.taille() == p.taille()
          && this.capacite() == p.capacite()
          && this.hashCode() == p.hashCode();
    }
    return false;

  }

  public int capacite() {
    return this.capacite;
  }

  public int hashCode() {
    return toString().hashCode();
  }

  public int taille() {
    return nombre;
  }
}
