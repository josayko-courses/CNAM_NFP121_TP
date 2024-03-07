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
  }

  public Object depiler() throws PileVideException {
    if (estVide())
      throw new PileVideException();
    this.nombre--;
    Maillon it = this.stk;
    while (it.suivant != null) {
      it = this.stk.suivant();
    }
    Object el = it.element();
    it = null;
    return el;
  }

  public Object sommet() throws PileVideException {
    if (estVide())
      throw new PileVideException();
    // à compléter
    return null;
  }

  /**
   * Effectue un test de l'état de la pile.
   *
   * @return vrai si la pile est vide, faux autrement
   */
  public boolean estVide() {
    // à compléter
    return false;
  }

  /**
   * Effectue un test de l'état de la pile.
   *
   * @return vrai si la pile est pleine, faux autrement
   */
  public boolean estPleine() {
    // à compléter
    return false;
  }

  /**
   * Retourne une représentation en String d'une pile, contenant la représentation
   * en String de
   * chaque élément.
   *
   * @return une représentation en String d'une pile
   */
  public String toString() {
    String s = "[";
    // à compléter
    return s + "]";
  }

  public boolean equals(Object o) {
    if (o instanceof Pile4) {
      // à compléter
      return false;
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
