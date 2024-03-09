package question2;

import java.util.Vector;
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

  public Object depiler() throws PileVideException {
    if (estVide())
      throw new PileVideException();
    if (this.stk == null || this.stk.suivant() == null) {
      Object el = this.stk.element();
      this.stk = null; // If the list is empty or has only one node, set head to null
      this.nombre--;
      return el;
    }

    Maillon prev = null;
    Maillon curr = this.stk;

    // Traverse the list until the second last node
    while (curr.suivant() != null) {
      prev = curr;
      curr = curr.suivant();
    }
    Object el = curr.element();
    prev.suivant = null; // Set the next of second last node to null to remove the last node
    this.nombre--;
    return el;
  }

  public Object sommet() throws PileVideException {
    if (estVide())
      throw new PileVideException();
    return this.stk.element();
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
    StringBuffer sb = new StringBuffer("]");
    if (!this.estVide()) {
      for (Maillon it = this.stk; it != null; it = it.suivant()) {
        sb.append(it.element());
        if (it.suivant() == null)
          break;
        sb.append(" ,");
      }
    }
    sb.append("[");
    return sb.reverse().toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof PileI) {
      Boolean hasSameElements = true;
      PileI p = (PileI) o;
      Vector<Object> tmp1 = new Vector<>();
      Vector<Object> tmp2 = new Vector<>();
      if (!this.estVide()) {
        try {
          Maillon it = this.stk;
          int size = p.taille();
          for (int i = size - 1; i >= 0; i--) {
            Object el = p.depiler();
            tmp1.add(0, el);
            tmp2.add(it.element());
            it = it.suivant();
          }
          for (int i = 0; i < size; i++) {
            p.empiler(tmp1.get(i));
          }
        } catch (Exception e) {
          System.out.println("error");
          hasSameElements = false;
        }
      }
      if (!tmp1.equals(tmp2)) {
        hasSameElements = false;
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
