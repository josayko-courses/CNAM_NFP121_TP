package question3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/* Cette vue observe le modèle, tout comme la VuePile. Il est
 * inutile que update mette à jour la VuePile, mais pour le
 * reste (les boutons à désactiver) oui. */
@SuppressWarnings("deprecation")
public class VueIHMCalculette extends JFrame implements Observer {
  private static final long serialVersionUID = 1697843137178332965L;
  PileModele<Integer> modele;
  Controleur contr;
  // Cette vue observe déjà le modèle
  VuePile vuePile;
  private JButton push, add, sub, mul, div, clear;
  private JTextField donnee;
  private JPanel boutons;

  public VueIHMCalculette(PileModele<Integer> m, Controleur c) throws HeadlessException {
    super("IHM Calculette question 3 (test d'évaluation)");
    this.contr = c;
    this.modele = m;
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    // À FAIRE: s'enregistrer comme observateur
    this.modele.addObserver(this);

    // Ceci est la partie affichage de la pile, elle se branche elle-même sur le modèle
    // passé en paramètre.
    this.vuePile = new VuePile(modele);
    setUpGraphics(); // mise en page
    // branchement des requêtes au contrôleur, appuie sur entrée dans la zone de texte = push
    donnee.addActionListener((ActionEvent ae) -> contr.reactToPush(donnee.getText()));
    push.addActionListener((ActionEvent ae) -> contr.reactToPush(donnee.getText()));
    // à compléter 
    add.addActionListener((ActionEvent ae) -> contr.reactToAdd());
    sub.addActionListener((ActionEvent ae) -> contr.reactToSub());
    mul.addActionListener((ActionEvent ae) -> contr.reactToMul());
    div.addActionListener((ActionEvent ae) -> contr.reactToDiv());
    clear.addActionListener((ActionEvent ae) -> contr.reactToClear());
  }

  private void setUpGraphics() {
    // 1 ligne pour afficher la pile, 1 pour taper, une pour les opérations
    setLayout(new GridLayout(3, 1));
    this.add(vuePile);
    this.donnee = new JTextField(8);
    this.add(donnee);
    this.push = new JButton("push");
    this.add = new JButton("+");
    this.sub = new JButton("-");
    this.mul = new JButton("*");
    this.div = new JButton("/");
    this.clear = new JButton("[]");
    boutons = new JPanel();
    boutons.setLayout(new FlowLayout());
    boutons.add(push);
    boutons.add(add);
    boutons.add(sub);
    boutons.add(mul);
    boutons.add(div);
    boutons.add(clear);
    boutons.setBackground(Color.red);
    this.add(boutons);
  }

  // quand le modèle notifie, la vue de la pile se met à jour
  // par elle-même, mais il faut activer/désactiver les boutons
  // (qui ne font partie de la VuePile en fonction de ce qu'il
  // y a dans la pile.
  private void update() {
    if (modele.estPleine()) push.setEnabled(false);
    else if (modele.taille() == 0) {
      push.setEnabled(true);
      clear.setEnabled(false);
      add.setEnabled(false);
      sub.setEnabled(false);
      mul.setEnabled(false);
      div.setEnabled(false);

    } else if (modele.taille() == 1) {
      clear.setEnabled(true);
      add.setEnabled(false);
      sub.setEnabled(false);
      mul.setEnabled(false);
      div.setEnabled(false);
    } else if (modele.taille() > 1) {
      push.setEnabled(true);
      add.setEnabled(true);
      sub.setEnabled(true);
      mul.setEnabled(true);
      div.setEnabled(true);
    }
  }

  // réaction à la notification, voir update()
  public void update(Observable o, Object arg) {
    update();
  }
}
