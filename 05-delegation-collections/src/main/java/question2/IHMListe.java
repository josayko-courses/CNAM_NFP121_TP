package question2;

import java.awt.Dimension;
import java.util.*;
import javax.swing.*;

public class IHMListe extends JFrame {
  private static final long serialVersionUID = 1L;

  public IHMListe() {
    super("IHM Liste");
    List<String> liste = Chapitre2CoreJava2.listeDesMots();
    Map<String, Integer> table = Chapitre2CoreJava2.occurrencesDesMots(liste);
    JPanelListe jPanelListe = new JPanelListe(liste, table);
    add(jPanelListe);
    // exit application when this window is closed
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
    int wdth = this.getWidth();
    this.setMinimumSize(new Dimension(wdth, 300));
    setVisible(true);
  }

  public static void main(String[] args) {
    new IHMListe();
  }
}
