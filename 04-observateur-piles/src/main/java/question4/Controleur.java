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
    if (ev instanceof PushEvent) {
      this.reactToPush(((PushEvent)ev).getData());
    } else if (ev instanceof AddEvent) {
      this.reactToAdd();
    } else if (ev instanceof SubEvent) {
      this.reactToSub();
    } else if (ev instanceof MulEvent) {
      this.reactToMul();
    } else if (ev instanceof DivEvent) {
      this.reactToDiv();
    } else if (ev instanceof ClearEvent) {
      this.reactToClear();
    }
  }

  private void reactToPush(String s) {
    try {
      modele.empiler(Integer.parseInt(s));
    } catch (NumberFormatException nfe) {
    } catch (PilePleineException e) {
    }
  }

  private void reactToAdd() {
    try {
      modele.empiler(modele.depiler() + modele.depiler());
    } catch (PileVideException e) {
    } catch (PilePleineException e) {
    }
  }

  private void reactToSub() {
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

  private void reactToMul() {
    try {
      modele.empiler(modele.depiler() * modele.depiler());
    } catch (PileVideException e) {
    } catch (PilePleineException e) {
    }
  }

  private void reactToDiv() {
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

  private void reactToClear() {
    try {
      while (!modele.estVide())
        modele.depiler();
    } catch (PileVideException e) {
    }
  }
}
