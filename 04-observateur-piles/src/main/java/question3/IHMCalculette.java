package question3;

import javax.swing.*;

public class IHMCalculette {
  public JFrame frame;

  public IHMCalculette() {
    PileModele<Integer> modele = new PileModele<Integer>(new question3.tp3.Pile2<Integer>(5));
    Controleur control = new Controleur(modele);
    VueIHMCalculette win = new VueIHMCalculette(modele, control);
    win.setLocation(200, 200);
    this.frame = win;
    win.pack();
    win.setVisible(true);
  }

  public static void main(String[] args) {
    new IHMCalculette();
  }
}
