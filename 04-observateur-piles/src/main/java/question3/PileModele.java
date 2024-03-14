package question3;

import java.util.Observable;
import question3.tp3.PileI;
import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

@SuppressWarnings("deprecation")
public class PileModele<T> extends Observable implements PileI<T> {
  private PileI<T> pile; // interface segregation: ici on met une interface

  public PileModele(PileI<T> pile) {
    this.pile = pile;
  }

  public void empiler(T o) throws PilePleineException {
    // à compléter, cf question 3 
  }

  public T depiler() throws PileVideException {
    // à compléter. CF question3
    throw new PileVideException();
  }

  public T sommet() throws PileVideException {
    return pile.sommet();
  }

  public int capacite() {
    return pile.capacite();
  }

  public int taille() {
    return pile.taille();
  }

  public boolean estVide() {
    return pile.estVide();
  }

  public boolean estPleine() {
    return pile.estPleine();
  }

  public String toString() {
    return pile.toString();
  }
}
