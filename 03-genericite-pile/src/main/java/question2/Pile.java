package question2;

import question1.PilePleineException;
import question1.PileVideException;

/**
 * A remplacer en partie par votre classe Pile de la question 1.
 *
 * @author Jonny SAYKOSY
 * @version 2023.03.10
 */
public class Pile implements PileI {

  private Object[] zone;
  private int ptr;
  private int capacite;

  public Pile(int taille) {
    if (taille < 0) {
      taille = CAPACITE_PAR_DEFAUT;
    }
    this.capacite = taille;
    this.zone = new Object[this.capacite];
    this.ptr = 0;
  }

  public Pile() {
    this(CAPACITE_PAR_DEFAUT);
  }

  public void empiler(Object o) throws PilePleineException {
    if (estPleine()) {
      throw new PilePleineException();
    }
    this.zone[this.ptr] = o;
    this.ptr++;
  }

  public Object depiler() throws PileVideException {
    if (estVide()) {
      throw new PileVideException();
    }
    this.ptr--;
    Object el = this.zone[this.ptr];
    this.zone[this.ptr] = null;
    return el;
  }

  public Object sommet() throws PileVideException {
    if (estVide()) {
      throw new PileVideException();
    }
    return this.zone[this.ptr];
  }

  public int taille() {
    return this.ptr;
  }

  public int capacite() {
    return this.capacite;
  }

  public boolean estVide() {
    return this.zone.length == 0;
  }

  public boolean estPleine() {
    return this.ptr == this.capacite;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof PileI) {
      PileI p = (PileI) o;
      if (this.hashCode() != p.hashCode()) {
        System.out.println("Différents hash codes");
        return false;
      }
      if (this.capacite() != p.capacite()) {
        System.out.println("Différentes capacités");
        return false;

      }
      if (this.taille() == p.taille()) {
        for (int i = this.taille() - 1; i >= 0; i--) {
          try {
            if (this.zone[i] != p.depiler()) {
              System.out.println("Différent éléments");
              return false;
            }
          } catch (PileVideException e) {
            System.out.println("Différent éléments (vide)");
            return false;
          }
        }
        // On reconstruit p après l'avoir dépilée
      } else {
        System.out.println("Différentes tailles");
        return false;
      }
      System.out.println("Égaux");
      return true;
    }
    System.out.println("pas une instance de PileI");
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
  // Réponse: ce n'est pas correcte car on ne vérifie pas que le contenu de la
  // pile doit être similaire (même éléments de même valeur dans le même ordre)

  // On ne peut pas vérifier l'égalité des piles en comparant uniquement leur
  // représentation en string car les types des éléments ne sont pas vérifier

  // fonction fournie
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer("[");
    System.out.println(this.zone[0]);
    System.out.println(this.zone[1]);
    System.out.println(this.zone[2]);
    for (int i = this.taille() - 1; i >= 0; i--) {
      sb.append(zone[i]);
      if (i > 0)
        sb.append(", ");
    }
    sb.append("]");
    return sb.toString();
  }
}
