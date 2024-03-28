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
  void reactToAdd() {
    try {
      modele.empiler(modele.depiler() + modele.depiler());
    } catch (PileVideException e) {
    } catch (PilePleineException e) {
    }
  }

  void reactToSub() {
    try {
      if (modele.taille() > 1) {
        Integer b = modele.depiler(); 
        Integer a = modele.depiler();
        modele.empiler(a - b);
      }
    } catch (PileVideException e) {
    } catch (PilePleineException e) {
    }
  }

  void reactToMul() {
    try {
      modele.empiler(modele.depiler() * modele.depiler());
    } catch (PileVideException e) {
    } catch (PilePleineException e) {
    }
  }

  void reactToDiv() {
    try {
      if (modele.sommet() != 0) {
        Integer b = modele.depiler(); 
        Integer a = modele.depiler();
        modele.empiler(a / b);
      }
    } catch (PileVideException e) {
    } catch (PilePleineException e) {
    }
  }

  void reactToClear() {
    try {
      while (!modele.estVide())
        modele.depiler();
    } catch (PileVideException e) {
    }
  }
}
