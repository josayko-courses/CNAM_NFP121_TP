package question3;

import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

/**
 * Décrivez votre classe Controleur ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Controleur {
  private PileModele<Integer> modele;

  public Controleur(PileModele<Integer> pile) {
    super();
    this.modele = pile;
  }

  void reactToPush(String s) {
    try {
      modele.empiler(Integer.parseInt(s));
    } catch (NumberFormatException nfe) {
    } catch (PilePleineException e) {
    }
  }

  // à compléter: reactToAdd, etc 
}
