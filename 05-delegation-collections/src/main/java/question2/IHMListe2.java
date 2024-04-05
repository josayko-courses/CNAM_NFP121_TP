package question2;

import java.awt.Dimension;
import java.util.*;
import javax.swing.*;

public class IHMListe2 extends JFrame {
  private static final long serialVersionUID = 1L;

  public IHMListe2() {
    super("IHM Liste 2");
    List<String> liste = Chapitre2CoreJava2.listeDesMots();
    Map<String, Integer> table = Chapitre2CoreJava2.occurrencesDesMots(liste);
    JPanelListe2 jPanelListe = new JPanelListe2(liste, table);
    add(jPanelListe);
    setLocation(120, 150);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    int wdth = this.getWidth();
    this.setMinimumSize(new Dimension(wdth, 300));
    pack();
    setVisible(true);
  }

  public static void main(String[] args) {
    new IHMListe2();
  }
}
