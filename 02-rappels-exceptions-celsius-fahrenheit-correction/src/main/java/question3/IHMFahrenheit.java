package question3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import question1.*;

public class IHMFahrenheit extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;

  private JTextField entree = new JTextField(6);

  /** Le bouton de conversion. */
  private JButton boutonDeConversion = new JButton("convertir");

  /** La sortie en degré Celsius. */
  private JTextField sortie = new JTextField(6);

  public IHMFahrenheit() {
    super("IHM Fahrenheit");

    JRootPane rootPane = this.getRootPane();
    rootPane.putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);

    setLayout(new FlowLayout());
    add(entree);
    add(boutonDeConversion);
    add(sortie);
    sortie.setEditable(false);
    getContentPane().setBackground(Color.pink);
    setLocation(100, 100);
    pack();
    setVisible(true);

    boutonDeConversion.addActionListener(this);

    // exit application when this window is closed
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  /**
   * méthode déclenchée lorsque le bouton de conversion est appuyé. remarquer que le champs de
   * droite (les degrés Celsius) n'est pas éditable.
   *
   * @param ae l'événement transmis
   */
  public void actionPerformed(ActionEvent ae) {
    try {
      /* SOLUTION:int fahrenheit = 0; // valeur est une String et doit être convertie en entier,
             // voir java.lang.Integer méthode parseInt (--> try/catch)
      float celsius = 0F; // à compléter, en appelant la méthode ad'hoc de la question2
      // un test ici pour le zéro absolu (-273.1) */
      int fahrenheit = Integer.parseInt(entree.getText());
      float celsius = FahrenheitCelsius.fahrenheitEnCelsius(fahrenheit);
      if (celsius < -273.1F) celsius = -273.1F;
      /* FIN SOLUTION */
      sortie.setText(Float.toString(celsius));
    } catch (NumberFormatException nfe) {
      sortie.setText("error ! ");
    }
  }

  public static void main(String[] args) {
    new IHMFahrenheit();
  }
}
