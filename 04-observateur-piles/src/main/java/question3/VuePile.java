package question3;

import java.awt.*;
import javax.swing.*;

import java.util.Observable;
import java.util.Observer;

/**
 * La vue de la pile, sans bouton ni aucun contrôle. Ceci sera intégré
 * dans la fenêtre de l'IHM. On met en place l'observateur vers la
 * pile sur cette vue. Sachant qu'on en mettra un aussi sur le JFrame
 * englobant de l'application. Ici nous n'avons pas de contrôleur car
 * cette partie n'est pas interactive
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
@SuppressWarnings("deprecation")
public class VuePile extends JPanel implements Observer{
  private static final long serialVersionUID = -3654648432712136094L;
    private JLabel etatPile;
    private PileModele<Integer> pile;

    public VuePile(PileModele<Integer> pile){
        super();
        this.pile = pile;
        this.etatPile = new JLabel("entrez des nombres entiers");
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(etatPile);
        setBackground(Color.green);
        pile.addObserver(this);
    }

    public void update(Observable obs, Object arg){
        etatPile.setText(pile.toString()); // ou obs.toString()
    }

}
