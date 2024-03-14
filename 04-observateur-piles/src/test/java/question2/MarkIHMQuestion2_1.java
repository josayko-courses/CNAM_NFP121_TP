package question2;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.Robot;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MarkIHMQuestion2_1 {
  private JFrame f;
  private Robot robot;

  @Test
  public void test_presence_fichiers_question2() {
    try {
      Class.forName("question2.IHMQuestion2_1");
      Class.forName("question2.JButtonObserver");
      // Pourquoi est-ce commenté??
      // Class.forName("question2.JMouseObserver");
    } catch (ClassNotFoundException e) {
      fail("classe absente " + e.getMessage());
    }
  }

  /**
   * Met en place les engagements.
   *
   * <p>Méthode appelée avant chaque appel de méthode de test.
   */
  @BeforeEach
  public void setUp() throws java.lang.Exception {
    f = new IHMQuestion2_1();
    f.pack();
    f.setVisible(true);
    while (!(f.isShowing())) {}
    f.setAlwaysOnTop(true);
    // f.setLocation(random.nextInt(500), random.nextInt(500));
    f.setLocation(500, 500);
    robot = new Robot();
    robot.delay(100);
  }

  @AfterEach
  public void tearDown() { // throws java.lang.Exception
    f.dispose();
  }

  @Test
  public void test_clic_bouton_A() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 2, "ihm invalide ?, ne pas modifier l'interface");

    // la bonne IHM
    assertTrue(components[0] instanceof JPanel, "ihm invalide ?, ne pas modifier l'interface");
    assertTrue(components[1] instanceof TextArea, "ihm invalide ?, ne pas modifier l'interface");

    Component[] subComponents = ((JPanel) components[0]).getComponents();
    assertTrue(subComponents[0] instanceof JButton, "ihm invalide ?, ne pas modifier l'interface");
    assertTrue(subComponents[1] instanceof JButton, "ihm invalide ?, ne pas modifier l'interface");
    assertTrue(subComponents[2] instanceof JButton, "ihm invalide ?, ne pas modifier l'interface");

    Point location = subComponents[0].getLocationOnScreen();
    mouseMoveAndClick(
        location.x + (subComponents[0].getWidth() / 2),
        location.y + (subComponents[0].getHeight() / 2));
    TextArea sortie = (TextArea) components[1];
    StringTokenizer st = new StringTokenizer(sortie.getText(), "\n");
    assertTrue(st.countTokens() == 3, "  3 lignes sont attendues !!!");
    assertTrue(sortie.getText().contains("bouton A"));
    assertFalse(sortie.getText().contains("bouton B"));
    assertFalse(sortie.getText().contains("bouton C"));
  }

  @Test
  public void test_clic_bouton_B() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 2, "ihm invalide ?, ne pas modifier l'interface");

    // la bonne IHM
    assertTrue(components[0] instanceof JPanel, "ihm invalide ?, ne pas modifier l'interface");
    assertTrue(components[1] instanceof TextArea, "ihm invalide ?, ne pas modifier l'interface");

    Component[] subComponents = ((JPanel) components[0]).getComponents();
    assertTrue(subComponents[0] instanceof JButton, "ihm invalide ?, ne pas modifier l'interface");
    assertTrue(subComponents[1] instanceof JButton, "ihm invalide ?, ne pas modifier l'interface");
    assertTrue(subComponents[2] instanceof JButton, "ihm invalide ?, ne pas modifier l'interface");

    Point location = subComponents[1].getLocationOnScreen();
    mouseMoveAndClick(
        location.x + (subComponents[1].getWidth() / 2),
        location.y + (subComponents[1].getHeight() / 2));
    TextArea sortie = (TextArea) components[1];
    StringTokenizer st = new StringTokenizer(sortie.getText(), "\n");
    assertTrue(st.countTokens() == 2, "2 lignes sont attendues !!");
    // trop restrictif, seul le \n est imposé
    assertTrue(sortie.getText().endsWith("bouton B\n"));
    assertFalse(sortie.getText().contains("bouton A"));
    assertFalse(sortie.getText().contains("bouton C"));
  }

  @Test
  public void test_clic_bouton_C() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 2, "ihm invalide ?, ne pas modifier l'interface");

    // la bonne IHM
    assertTrue(components[0] instanceof JPanel, "ihm invalide ?, ne pas modifier l'interface");
    assertTrue(components[1] instanceof TextArea, "ihm invalide ?, ne pas modifier l'interface");

    Component[] subComponents = ((JPanel) components[0]).getComponents();
    assertTrue(subComponents[0] instanceof JButton, "ihm invalide ?, ne pas modifier l'interface");
    assertTrue(subComponents[1] instanceof JButton, "ihm invalide ?, ne pas modifier l'interface");
    assertTrue(subComponents[2] instanceof JButton, "ihm invalide ?, ne pas modifier l'interface");

    Point location = subComponents[2].getLocationOnScreen();
    mouseMoveAndClick(
        location.x + (subComponents[2].getWidth() / 2),
        location.y + (subComponents[2].getHeight() / 2));
    TextArea sortie = (TextArea) components[1];
    StringTokenizer st = new StringTokenizer(sortie.getText(), "\n");
    assertTrue(
        st.countTokens() == 1,
        " avec JButtonObserver (et testSouris==false) 1 seule ligne est attendue !");
    assertTrue(sortie.getText().contains("bouton C"));
    assertFalse(sortie.getText().contains("bouton A"));
    assertFalse(sortie.getText().contains("bouton B"));
  }

  @SuppressWarnings("deprecation")
  public void mouseMoveAndClick(int x, int y) {
    robot.mouseMove(x, y);
    robot.delay(60);
    robot.mousePress(InputEvent.BUTTON1_MASK);
    robot.delay(60);
    robot.mouseRelease(InputEvent.BUTTON1_MASK);
    robot.delay(60);
  } // end mouseMoveAndClick
}
