package question2;

import question1.PilePleineException;
import question1.PileVideException;

/**
 * A remplacer en partie par votre classe Pile de la question 1.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Pile implements PileI {

  private Object[] zone;
  private int ptr;

  public Pile(int taille) {
    // à compléter, prévoir le cas <= 0 
  }

  public Pile() {
    // à completer, on peut appeler le constructeur ci-dessus avec "this(...); 
  }

  public void empiler(Object o) throws PilePleineException {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public Object depiler() throws PileVideException {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public Object sommet() throws PileVideException {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public int taille() {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public int capacite() {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public boolean estVide() {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public boolean estPleine() {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  @Override
  public boolean equals(Object o) {
    // à compléter
    return false;
  }

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
    // à compléter
    return null; 
  }
}
