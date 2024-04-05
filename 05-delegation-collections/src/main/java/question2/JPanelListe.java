package question2;

import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.*;

/**
 * ne pas modifier les éléments de l'IHM, les tests les utilisent ... Il faut uniquement implémenter
 * les bons comportements.
 */
public class JPanelListe extends JPanel implements ActionListener, ItemListener {

  private static final long serialVersionUID = 1L;
  private JPanel cmd = new JPanel();
  private JLabel afficheur = new JLabel();
  private JTextField saisie = new JTextField();

  private JPanel panelBoutons = new JPanel();
  private JButton boutonRechercher = new JButton("rechercher");
  private JButton boutonRetirer = new JButton("retirer");

  private CheckboxGroup mode = new CheckboxGroup();
  private Checkbox ordreCroissant = new Checkbox("croissant", mode, false);
  private Checkbox ordreDecroissant = new Checkbox("décroissant", mode, false);

  private JButton boutonOccurrences = new JButton("occurrence");

  private JTextArea texte = new JTextArea();

  private List<String> liste;
  private Map<String, Integer> occurrences;

  /** ne pas modifier les éléments de l'IHM, les tests les utilisent .... */
  public JPanelListe(List<String> liste, Map<String, Integer> occurrences) {
    this.liste = liste;
    this.occurrences = occurrences;

    cmd.setLayout(new GridLayout(3, 1));

    cmd.add(afficheur);
    cmd.add(saisie);

    panelBoutons.setLayout(new FlowLayout(FlowLayout.LEFT));
    panelBoutons.add(boutonRechercher);
    panelBoutons.add(boutonRetirer);
    panelBoutons.add(new JLabel("tri du texte :"));
    panelBoutons.add(ordreCroissant);
    panelBoutons.add(ordreDecroissant);
    panelBoutons.add(boutonOccurrences);
    cmd.add(panelBoutons);

    if (liste != null && occurrences != null) {
      afficheur.setText(liste.getClass().getName() + " et " + occurrences.getClass().getName());
      texte.setText(liste.toString());
      texte.setLineWrap(true);
    } else {
      texte.setText("la classe Chapitre2CoreJava semble incomplète");
    }

    setLayout(new BorderLayout());

    add(cmd, "North");
    add(texte, "Center");

    boutonRechercher.addActionListener(this);
    // À compléter.
  }

  /** ne pas modifier les affichages, les classes de tests en ont besoin ... */
  public void actionPerformed(ActionEvent ae) {
    try {
      boolean res = false;
      if (ae.getSource() == boutonRechercher || ae.getSource() == saisie) {
        res = liste.contains(saisie.getText());
        // ??Integer occur = occurrences.get(saisie.getText());
        afficheur.setText("résultat de la recherche de : " + saisie.getText() + " -->  " + res);
      } else if (ae.getSource() == boutonRetirer) {
        res = retirerDeLaListeTousLesElementsCommencantPar(saisie.getText());
        afficheur.setText(
            "résultat du retrait de tous les éléments commençant par -->  "
                + saisie.getText()
                + " : "
                + res);
      } else if (ae.getSource() == boutonOccurrences) {
        Integer occur = occurrences.get(saisie.getText());
        if (occur != null) afficheur.setText(" -->  " + occur + " occurrence(s)");
        else afficheur.setText(" -->  ??? ");
      }
      texte.setText(liste.toString());

    } catch (Exception e) {
      afficheur.setText(e.toString());
    }
  }

  public void itemStateChanged(ItemEvent ie) {
    if (ie.getSource() == ordreCroissant) {
      // À compléter.
    } else if (ie.getSource() == ordreDecroissant) {
      // À compléter. 
    }

    texte.setText(liste.toString());
  }


  private boolean retirerDeLaListeTousLesElementsCommencantPar(String prefixe) {
    boolean resultat = false;
    // À compléter. 
    return resultat;
  }
}
