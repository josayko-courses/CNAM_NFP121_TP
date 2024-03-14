package question1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class IHMPile extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;
  private JTextField donnee = new JTextField(6);
  private JTextField sommet = new JTextField(6);
  private JLabel contenu = new JLabel("[]");

  private Pile p;

  public IHMPile() {
    super("IHM Pile 1");
    JButton boutonEmpiler = new JButton("empiler");
    JButton boutonDepiler = new JButton("depiler");

    JPanel enHaut = new JPanel();
    enHaut.add(donnee);
    enHaut.add(boutonEmpiler);
    enHaut.add(boutonDepiler);
    enHaut.add(sommet);
    setLayout(new BorderLayout(5, 5));
    add("North", enHaut);
    add("Center", contenu);
    enHaut.setBackground(Color.red);
    setLocation(100, 100);
    pack();
    setVisible(true);
    boutonEmpiler.addActionListener(this);
    boutonDepiler.addActionListener(this);

    p = new Pile(5);

    // exit application when this window is closed
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  public void actionPerformed(ActionEvent ae) {
    if (ae.getActionCommand().equals("empiler")) {
      /* SOLUTION:
      // à compléter
      // pour le comportement attendu depuis votre répertoire
      // exécuter cette commande tp3>appletviewer tp3.html

      // en cas d'exception (compléter le message d'erreur)
      // contenu.setText("??" + " estPleine !"); */
      try {
        p.empiler(donnee.getText());
        contenu.setText(p.toString());
      } catch (PilePleineException e) {
        contenu.setText(p.toString() + " estPleine !");
      }
      /* FIN SOLUTION */
    } else {
      /* SOLUTION:// à compléter
      // en cas d'exception (compléter le message d'erreur)
      // contenu.setText("" + " estVide !"); */
      try {
        sommet.setText(p.depiler().toString());
        contenu.setText(p.toString());
      } catch (PileVideException e) {
        contenu.setText(p.toString() + " estVide !");
      }
      /* FIN SOLUTION */
    }
  }

  public static void main(String[] args) {
    new IHMPile();
  }
}
