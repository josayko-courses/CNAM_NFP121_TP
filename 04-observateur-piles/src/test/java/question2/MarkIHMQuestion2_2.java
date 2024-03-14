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

public class MarkIHMQuestion2_2 {
  // private question2.ihmQuestion2 ihm;
  private JFrame f;
  private Robot robot;

  @Test
  public void test_presence_fichiers_question2() {
    try {
      Class.forName("question2.IHMQuestion2_2");
      Class.forName("question2.JButtonObserver");
      Class.forName("question2.JMouseObserver");
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
    f = new IHMQuestion2_2();
    f.pack();
    f.setVisible(true);
    while (!(f.isShowing())) {}
    f.setAlwaysOnTop(true);
    // f.setLocation(random.nextInt(500), random.nextInt(500));
    f.setLocation(400, 400);
    robot = new Robot();
    robot.delay(200);
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
    assertTrue(st.countTokens() > 3, "  4 lignes sont attendues !!!( le déplacement+3 clicks)");
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
    assertTrue(st.countTokens() > 2, "3 lignes sont attendues !!( le déplacement+2 clicks)");
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
    assertTrue(st.countTokens() > 1, "  2 lignes sont attendues !( le déplacement+1 clicks)");
    assertTrue(sortie.getText().contains("bouton C"));
    assertFalse(sortie.getText().contains("bouton A"));
    assertFalse(sortie.getText().contains("bouton B"));
  }

  public void ignore_test_sortie_ihm_bouton_C() throws Exception {
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

    // try{
    // Field testSourisField = ihm.getClass().getDeclaredField("testSouris");
    // testSourisField.setAccessible(true);
    // testSourisField.set(ihm,false); // ihm.testsouris == false
    // }catch(Exception e){
    // fail(" la variable d'instance privée de l'ihm testSouris a-t-elle été modifiée ???");
    // }

    Point location = subComponents[2].getLocationOnScreen();
    mouseMoveAndClick(
        location.x + (subComponents[2].getWidth() / 2),
        location.y + (subComponents[2].getHeight() / 2));
    TextArea sortie = (TextArea) components[1];
    Scanner s = new Scanner(sortie.getText());
    assertEquals(s.findInLine("jbo1"), " \"jbo1\" est absent ??? ", "jbo1");
  }

  public void ignore_test_sortie_ihm_bouton_B() throws Exception {
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

    Scanner s = new Scanner(sortie.getText());
    assertEquals("jbo2", s.findInLine("jbo2"), " \"jbo2\" est absent ??? ");
    assertTrue(s.hasNextLine());
    s.nextLine();
    assertEquals("jbo1", s.findInLine("jbo1"), "\"jbo1\" est absent ??? ");
  }

  @Test
  public void test_deplacement_souris_vers_A() throws Exception {
    // tearDown(); // beurk

    // f = new IHMQuestion2_2();
    // f.pack();
    // f.setVisible(true);
    // while(!(f.isShowing())){}
    // f.setAlwaysOnTop(true);
    // f.setLocation(400, 400);
    // robot = new Robot();
    // robot.delay(300);
    // re-beurk

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
    TextArea sortieInit = (TextArea) components[1];
    sortieInit.setText("");
    Point location = subComponents[0].getLocationOnScreen();
    robot.mouseMove(
        location.x + (subComponents[0].getWidth() / 2),
        location.y + (subComponents[0].getHeight() / 2));
    robot.delay(300);
    TextArea sortie = (TextArea) components[1];
    StringTokenizer st = new StringTokenizer(sortie.getText(), "\n");
    assertTrue(st.countTokens() == 1, " avec JMouseObserver pour ce test 1 ligne est attendue !");
  }

  // Ce test était était désactivé via un try catch vide. Pourquoi?
  @Test
  public void test_deplacement_souris_vers_B() throws Exception {
    // tearDown(); // beurk

    // f = new IHMQuestion2_2();
    // f.pack();
    // f.setVisible(true);
    // while(!(f.isShowing())){}
    // f.setAlwaysOnTop(true);
    // f.setLocation(400, 400);
    // robot = new Robot();
    // robot.delay(300);
    // // re-beurk

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
    TextArea sortieInit = (TextArea) components[1];
    sortieInit.setText("");

    Point location = subComponents[2].getLocationOnScreen();
    // //
    // robot.mouseMove(location.x+(subComponents[1].getWidth()/2),location.y+(subComponents[1].getHeight()/2));
    // // du bouton 1 au 2
    // // robot.delay(1000);

    robot.mouseMove(
        location.x + (subComponents[2].getWidth() / 2),
        location.y + (subComponents[2].getHeight() / 2));
    robot.delay(300);
    TextArea sortie = (TextArea) components[1];
    StringTokenizer st = new StringTokenizer(sortie.getText(), "\n");
    assertTrue(st.countTokens() == 1, " avec JMouseObserver pour ce test 1 ligne est attendue !");
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
