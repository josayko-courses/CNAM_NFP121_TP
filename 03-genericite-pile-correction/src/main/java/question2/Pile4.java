package question2;

import java.util.Iterator;
import question1.PilePleineException;
import question1.PileVideException;

public class Pile4 extends PileA implements PileI /* SOLUTION(rien) */, Cloneable /* FIN SOLUTION */ {

  /**
   * la liste des Maillons/Elements. Plus exactement son premier élément, qui contient le suivant
   * etc.
   */
  private Maillon stk;

  /** la capacité de la pile */
  private int capacite;

  /** le nombre */
  private int nombre;

  /**
   * Classe interne "statique" contenant chaque élément de la chaine c'est une proposition, vous
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

    /* SOLUTION (rien) */
    public Object clone() throws CloneNotSupportedException {
      System.out.println("clone");
      Maillon m = (Maillon) super.clone();
      m.element = this.element;
      m.suivant = this.suivant;
      return m;
    }
    /* FIN SOLUTION */
  }
  @SuppressWarnings("rawtypes")
  class IterMaillons implements Iterator {
    private Maillon cursor;
    
    public IterMaillons(Maillon m) {
      this.cursor = m;
    }
    @Override
    public boolean hasNext() {
      return cursor!=null;
    }

    @Override
    public Object next() {
      Object res = this.cursor.element();
      this.cursor = this.cursor.suivant();
      return res;
    }
  }
  /**
   * Création d'une pile.
   *
   * @param taille la taille de la pile, la taille doit être {@code > 0}
   */
  public Pile4(int taille) {
    if (taille <= 0) taille = CAPACITE_PAR_DEFAUT;
    this.stk = null;
    this.capacite = taille;
  }

  public Pile4() {
    this(PileI.CAPACITE_PAR_DEFAUT);
  }

  public void empiler(Object o) throws PilePleineException {
    if (estPleine()) throw new PilePleineException();
    /* SOLUTION:// à compléter */
    stk = new Maillon(o, stk);
    nombre++;
    /* FIN SOLUTION */
  }

  public Object depiler() throws PileVideException {
    if (estVide()) throw new PileVideException();
    /* SOLUTION:// à compléter
    return null;*/
    Object elt = stk.element();
    stk = stk.suivant();
    nombre--;
    return elt;
    /* FIN SOLUTION */
  }

  public Object sommet() throws PileVideException {
    if (estVide()) throw new PileVideException();
    /* SOLUTION:// à compléter
    return null;*/
    return stk.element();
    /* FIN SOLUTION */
  }

  /**
   * Effectue un test de l'état de la pile.
   *
   * @return vrai si la pile est vide, faux autrement
   */
  public boolean estVide() {
    /* SOLUTION:// à compléter
    return false; */
    return stk == null;
    /* FIN SOLUTION */
  }

  /**
   * Effectue un test de l'état de la pile.
   *
   * @return vrai si la pile est pleine, faux autrement
   */
  public boolean estPleine() {
    /* SOLUTION:// à compléter
    return false; */
    return capacite == nombre;
    /* FIN SOLUTION */
  }

  /**
   * Retourne une représentation en String d'une pile, contenant la représentation en String de
   * chaque élément.
   *
   * @return une représentation en String d'une pile
   */
  public String toString() {
    String s = "[";
    /* SOLUTION:// à compléter */
    Maillon m = this.stk;
    while (m != null) {
      s += m.element();
      m = m.suivant();
      if (m != null) s += ", ";
    }
    /* FIN SOLUTION */
    return s + "]";
  }

//  public boolean equals(Object o) {
//    if (o instanceof Pile4) {
//      /* SOLUTION:// à compléter */
//      Pile4 p = (Pile4) o;
//      if (p.taille() == this.taille() && p.capacite() == this.capacite()) {
//        Maillon mp = p.stk;
//        for (Maillon m = stk; m != null; m = m.suivant(), mp = mp.suivant())
//          if (!m.element().equals(mp.element())) return false;
//        return true;
//      }
//      /* FIN SOLUTION */
//      return false;
//    }
//    return false;
//  }

  /* SOLUTION (rien) */
  // Pour l'exemple on montre ce que doit faire clone dans une
  // situation comme celle-là: traverser toute la structure pour
  // recréer une liste identique, en recopiant les cases (mais pas les
  // objets qu'elles contiennent).
  public Object clone() throws CloneNotSupportedException {
    Pile4 p = new Pile4(this.capacite());
    traversee(p, stk);
    return p;
  }

  private void traversee(Pile4 p, Maillon m) {
    try {
      if (m != null) {
        traversee(p, m.suivant());
        p.empiler(m.element());
      }
    } catch (PilePleineException e) {
    }
  }

  /* FIN SOLUTION */
  public int capacite() {
    return this.capacite;
  }

  public int hashCode() {
    return toString().hashCode();
  }

  public int taille() {
    return nombre;
  }
  @SuppressWarnings("rawtypes")
  @Override
  public Iterator iterator() {
    return new IterMaillons(stk);
  }

}
