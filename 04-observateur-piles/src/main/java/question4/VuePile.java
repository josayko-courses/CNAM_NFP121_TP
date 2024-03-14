package question4;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import question3.PileModele;

/**
 * La vue de la pile, sans bouton ni aucun contrôle. Ceci sera intégré dans la fenêtre de l'IHM. On
 * met en place l'observateur vers la pile uniquement sur cette vue.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
@SuppressWarnings("deprecation")
public class VuePile extends JPanel implements Observer {
  private static final long serialVersionUID = -3654648432712136094L;
  private JLabel etatPile;
  private PileModele<Integer> pile;

  public VuePile(PileModele<Integer> pile) {
    super();
    this.pile = pile;
    this.etatPile = new JLabel("entrez des nombres entiers");
    setLayout(new FlowLayout(FlowLayout.LEFT));
    add(etatPile);
    setBackground(Color.green);
    pile.addObserver(this);
  }

  public void update(Observable obs, Object arg) {
    etatPile.setText(pile.toString()); // ou obs.toString()
  }
}
