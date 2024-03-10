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
    Maillon newNode = new Maillon(o, this.stk);
    this.stk = newNode;
    this.nombre++;
  }

  public Object depiler() throws PileVideException {
    if (estVide())
      throw new PileVideException();
    Maillon tmp = this.stk.suivant();
    Object el = this.stk.element();
    this.stk = tmp;
    this.nombre--;
    return el;
  }

  public Object sommet() throws PileVideException {
    if (estVide())
      throw new PileVideException();
    return this.stk.element;
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
    for (Maillon it = this.stk; it != null; it = it.suivant) {
      sb.append(it.element);
      if (it.suivant != null)
        sb.append(", ");
    }
    sb.append("]");
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof PileI) {
      PileI p = (PileI) o;
      if (this.taille() == p.taille()) {
        Boolean sameElements = true;
        try {
          Pile tmp = new Pile(p.capacite());
          for (Maillon it = this.stk; it != null; it = it.suivant) {
            Object el = p.depiler();
            tmp.empiler(el);
            if (it.element != el) {
              sameElements = false;
              break;
            }
          }
          int tailleTotale = tmp.taille();
          for (int i = 0; i < tailleTotale; i++) {
            Object t = tmp.depiler();
            p.empiler(t);
          }
        } catch (Exception e) {
        }
        System.out.println(p.toString());
        System.out.println(this.toString());
        if (sameElements)
          System.out.println("sameElements");
        if (this.capacite == p.capacite())
          System.out.println("meme capacite");
        if (this.hashCode() == p.hashCode())
          System.out.println("meme hashcode");
        return sameElements
            && this.capacite() == p.capacite()
            && this.hashCode() == p.hashCode();
      }
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
