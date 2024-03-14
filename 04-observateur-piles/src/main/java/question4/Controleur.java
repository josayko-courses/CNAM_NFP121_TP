package question4;

import question3.PileModele;
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

  // Ceci est la seule méthode *publique* autorisée pour la question 4
  // Vous ne devez changer ni son nom ni sa signature (sous peine de
  // faire échouer les tests).
  public void reactTo(PileEvent ev) {
    // à compléter
  }

}
