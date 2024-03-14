package question3;

/** usage de java.awt.robot : http://www.developer.com/java/other/article.php/2241561 */
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.Robot;
import java.awt.event.*;
import javax.swing.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MarkIHMFahrenheit {

  private JFrame f;
  private Robot robot;

  @BeforeEach
  public void setUp() throws Exception {
    f = new question3.IHMFahrenheit();
    f.pack();
    f.setVisible(true);
    while (!(f.isShowing())) {}
    f.setAlwaysOnTop(true);
    // f.setLocation(random.nextInt(900), random.nextInt(600));
    f.setLocation(900, 600);
    robot = new Robot();
    robot.delay(100);
  }

  public void tearDown() {
    f.dispose();
    robot.delay(100);
  }

  @Test
  public void test_IHMFahrenheit_convertir() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertTrue(3 == components.length, " f invalide ? ");

    // la bonne IHM
    assertTrue(components[0] instanceof JTextField, "f invalide ?  ");
    assertTrue(components[1] instanceof JButton, "f invalide  ?  ");
    assertTrue(components[2] instanceof JTextField, "f invalide  ? ");

    // entrée d'un texte : 100
    // entrée d'un texte : -100
    ((JTextField) components[0]).setText("100");

    JButton button = ((JButton) components[1]);
    ActionListener[] listeners = button.getActionListeners();
    assertTrue(listeners.length == 1);
    listeners[0].actionPerformed(new ActionEvent(button, 1, button.getActionCommand()));
    // convertir, action sur le JButton
    // Point location = components[1].getLocationOnScreen();
    //
    // mouseMoveAndClick(location.x+(components[1].getWidth()/2),location.y+(components[1].getHeight()/2));
    robot.delay(100);
    // à la sortie 37.7
    JTextField sortie = (JTextField) components[2];
    assertEquals(
        37.7, Float.parseFloat(sortie.getText()), 0.1, "sortie invalide ?  37.7 est attendu ");
  }

  @Test
  public void test_IHMFahrenheit_convertir_avec_erreur() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertTrue(3 == components.length, " f invalide ? ");

    // la bonne IHM
    assertTrue(components[0] instanceof JTextField, "f invalide ?  ");
    assertTrue(components[1] instanceof JButton, "f invalide  ?  ");
    assertTrue(components[2] instanceof JTextField, "f invalide  ? ");
    // entrée d'un texte : ZZZ
    ((JTextField) components[0]).setText("ZZZ");

    // convertir, action sur le JButton
    assertTrue(components[1] instanceof JButton, "f invalide ?  ");
    JButton button = ((JButton) components[1]);
    ActionListener[] listeners = button.getActionListeners();
    assertTrue(listeners.length == 1);
    listeners[0].actionPerformed(new ActionEvent(button, 1, button.getActionCommand()));
    // Point location = components[1].getLocationOnScreen();
    //
    // mouseMoveAndClick(location.x+(components[1].getWidth()/2),location.y+(components[1].getHeight()/2));
    robot.delay(100);
    JTextField sortie = (JTextField) components[2];
    assertTrue(
        sortie.getText().startsWith("error") || sortie.getText().startsWith("erreur"),
        "\"error\" est attendu ? ");
    // modif le 21 Oct 2008
  }

  @Test
  public void test_IHMFahrenheit_convertir_bis() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertTrue(3 == components.length, " f invalide ? ");

    // la bonne IHM
    assertTrue(components[0] instanceof JTextField, "f invalide ?  ");
    assertTrue(components[1] instanceof JButton, "f invalide  ?  ");
    assertTrue(components[2] instanceof JTextField, "f invalide  ? ");
    // entrée d'un texte : -100
    ((JTextField) components[0]).setText("-100");
    // convertir, action sur le JButton
    assertTrue(components[1] instanceof JButton, "f invalide ?  ");
    JButton button = ((JButton) components[1]);
    ActionListener[] listeners = button.getActionListeners();
    assertTrue(listeners.length == 1);
    listeners[0].actionPerformed(new ActionEvent(button, 1, button.getActionCommand()));

    // Point location = components[1].getLocationOnScreen();
    //
    // mouseMoveAndClick(location.x+(components[1].getWidth()/2),location.y+(components[1].getHeight()/2));
    robot.delay(100);
    // à la sortie -73.3
    JTextField sortie = (JTextField) components[2];
    assertEquals(-73.3, Float.parseFloat(sortie.getText()), 0.1, "invalide ? -73.3 est attendu ");
    assertTrue(sortie.getText().equals("-73.3"), "invalide ? -73.3 est attendu ");
  }

  @Test
  public void test_IHMFahrenheit_convertir_120() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertTrue(3 == components.length, " f invalide ? ");
    // la bonne IHM
    assertTrue(components[0] instanceof JTextField, "f invalide ?  ");
    assertTrue(components[1] instanceof JButton, "f invalide  ?  ");
    assertTrue(components[2] instanceof JTextField, "f invalide  ? ");
    // entrée d'un texte : 120
    ((JTextField) components[0]).setText("120");
    // convertir, action sur le JButton
    assertTrue(components[1] instanceof JButton, "f invalide ?  ");
    JButton button = ((JButton) components[1]);
    ActionListener[] listeners = button.getActionListeners();
    assertTrue(listeners.length == 1);
    listeners[0].actionPerformed(new ActionEvent(button, 1, button.getActionCommand()));

    // Point location = components[1].getLocationOnScreen();
    //
    // mouseMoveAndClick(location.x+(components[1].getWidth()/2),location.y+(components[1].getHeight()/2));
    robot.delay(100);
    // à la sortie 48.8
    JTextField sortie = (JTextField) components[2];
    assertEquals(48.8F, Float.parseFloat(sortie.getText()), 0.1, "invalide ? 48.8 est attendu ");
    assertTrue(sortie.getText().equals("48.8"), "invalide ? 48.8 est attendu ");
  }

  @Test
  public void test_IHMFahrenheit_convertir_112() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertTrue(3 == components.length, " f invalide ? ");
    // la bonne IHM
    assertTrue(components[0] instanceof JTextField, "f invalide ?  ");
    assertTrue(components[1] instanceof JButton, "f invalide  ?  ");
    assertTrue(components[2] instanceof JTextField, "f invalide  ? ");
    // entrée d'un texte : 112
    ((JTextField) components[0]).setText("112");
    // convertir, action sur le JButton
    assertTrue(components[1] instanceof JButton, "f invalide ?  ");
    JButton button = ((JButton) components[1]);
    ActionListener[] listeners = button.getActionListeners();
    assertTrue(listeners.length == 1);
    listeners[0].actionPerformed(new ActionEvent(button, 1, button.getActionCommand()));
    robot.delay(100);
    // Point location = components[1].getLocationOnScreen();
    //
    // mouseMoveAndClick(location.x+(components[1].getWidth()/2),location.y+(components[1].getHeight()/2));

    // à la sortie 44.4
    JTextField sortie = (JTextField) components[2];
    assertEquals(44.4F, Float.parseFloat(sortie.getText()), 0.1, "invalide ? 44.4 est attendu ");
    assertTrue(sortie.getText().equals("44.4"), "invalide ? 44.4 est attendu ");
  }

  @Test
  public void test_IHMFahrenheit_ZéroAbsolu() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertTrue(3 == components.length, " f invalide ? ");

    ((JTextField) components[0]).setText("-500");
    try {
      // convertir, action sur le JButton
      assertTrue(components[1] instanceof JButton, "f invalide ?  ");
      JButton button = ((JButton) components[1]);
      ActionListener[] listeners = button.getActionListeners();
      assertTrue(listeners.length == 1);
      listeners[0].actionPerformed(new ActionEvent(button, 1, button.getActionCommand()));
      robot.delay(100);
      JTextField sortie = (JTextField) components[2];
      assertEquals(
          -273.1,
          Float.parseFloat(sortie.getText()),
          0.1,
          " est-ce le zéro absolu : -273.1 °Celsius !!!  ");
    } catch (Exception e) {
      fail(" est-ce le zéro absolu en sortie ?");
    }
    // robot.delay(2000);
  }

  // public void mouseMoveAndClick(int x, int y){
  // robot.mouseMove( x,y);
  // robot.delay(30);
  // robot.mousePress(InputEvent.BUTTON1_MASK);
  // robot.delay(30);
  // robot.mouseRelease(InputEvent.BUTTON1_MASK);
  // robot.delay(30);
  // }//end mouseMoveAndClick

}
