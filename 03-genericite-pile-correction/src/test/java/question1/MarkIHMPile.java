package question1;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.Robot;
import java.awt.event.*;
import javax.swing.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MarkIHMPile {
  private question1.IHMPile ihm;
  // private Frame f;
  // private question1.IHMPile f;
  private Robot robot;

  /**
   * Met en place les engagements.
   *
   * <p>Méthode appelée avant chaque appel de méthode de test.
   */
  @BeforeEach
  public void setUp() throws java.lang.Exception {
    // ihm = new question1.IHMPile();
    // Setup a default context and stub.

    ihm = new question1.IHMPile(); // new JFrame("test");
    //    f.add(ihm.getContentPane());
    //    f.pack();
    //    f.setVisible(true);
    while (!(ihm.isShowing())) {}
    //    f.setAlwaysOnTop(true);
    // ihm.setLocation(random.nextInt(800), random.nextInt(500));
    ihm.setAlwaysOnTop(true);
    ihm.setLocation(800, 500);
    robot = new Robot();
    robot.delay(200);
  }

  /**
   * Supprime les engagements
   *
   * <p>Méthode appelée après chaque appel de méthode de test.
   */
  @AfterEach
  public void tearDown() { // throws java.lang.Exception
    // f.dispose();
    ihm.dispose();
    robot.delay(200);
  }

  @Test
  public void test_IHMPile() throws Exception { // juste le bon IHM
    Container panel = ihm.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 2);

    // la bonne IHM
    assertTrue(components[0] instanceof JPanel);
    assertTrue(components[1] instanceof JLabel);

    Component[] subComponents = ((JPanel) components[0]).getComponents();
    assertTrue(subComponents[0] instanceof JTextField);
    assertTrue(subComponents[1] instanceof JButton);
    assertTrue(subComponents[2] instanceof JButton);
    assertTrue(subComponents[3] instanceof JTextField);
  }

  public void ignore_test1_IHMPile() throws Exception {
    Container panel = ihm.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 2);

    // la bonne IHM
    assertTrue(components[0] instanceof JPanel);
    assertTrue(components[1] instanceof JLabel);

    Component[] subComponents = ((JPanel) components[0]).getComponents();
    assertTrue(subComponents[0] instanceof JTextField);
    assertTrue(subComponents[1] instanceof JButton);
    assertTrue(subComponents[2] instanceof JButton);
    assertTrue(subComponents[3] instanceof JTextField);

    empiler("100");

    empiler("101");

    JLabel sortie = (JLabel) components[1];
    assertEquals("[101, 100]", sortie.getText(), " sortie ???");

    // depiler
    // Point location = subComponents[2].getLocationOnScreen();
    // mouseMoveAndClick(location.x+(subComponents[2].getWidth()/2),location.y+(subComponents[2].getHeight()/2));
    assertTrue(subComponents[2] instanceof JButton);
    JButton button = ((JButton) subComponents[2]);
    ActionListener[] listeners = button.getActionListeners();
    assertTrue(listeners.length == 1);
    listeners[0].actionPerformed(null);

    JTextField sommet = (JTextField) subComponents[3];
    assertEquals(101, Integer.parseInt(sommet.getText()));
    sortie = (JLabel) components[1];
    assertEquals("[100]", sortie.getText(), " sortie ???");

    // depiler
    // location = subComponents[2].getLocationOnScreen();
    // mouseMoveAndClick(location.x+(subComponents[1].getWidth()/2),location.y+(subComponents[1].getHeight()/2));
    assertTrue(subComponents[2] instanceof JButton);
    button = ((JButton) subComponents[2]);
    listeners = button.getActionListeners();
    assertTrue(listeners.length == 1);
    listeners[0].actionPerformed(null);

    sommet = (JTextField) subComponents[3];
    assertEquals("100", sommet.getText(), " sommet ??? ");
    sortie = (JLabel) components[1];
    assertEquals("[]", sortie.getText(), " sortie ??? ");
  }

  @Test
  public void test2_IHMPile() throws Exception {
    Container panel = ihm.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 2);

    // la bonne IHM
    // assertTrue(components[0] instanceof JPanel);
    // assertTrue(components[1] instanceof JLabel);

    Component[] subComponents = ((JPanel) components[0]).getComponents();
    // assertTrue(subComponents[0] instanceof JTextField);
    // assertTrue(subComponents[1] instanceof JButton);
    assertTrue(subComponents[2] instanceof JButton);
    // assertTrue(subComponents[3] instanceof JTextField);

    JLabel sortie = (JLabel) components[1];
    assertEquals("[]", sortie.getText(), " sortie ??? ");

    // depiler
    Point location = subComponents[2].getLocationOnScreen();
    mouseMoveAndClick(
        location.x + (subComponents[1].getWidth() / 2),
        location.y + (subComponents[1].getHeight() / 2));

    // JButton button = ((JButton)subComponents[2]);
    // ActionListener[] listeners = button.getActionListeners();
    // assertTrue(listeners.length>=1);
    // listeners[0].actionPerformed(null);

    sortie = (JLabel) components[1];
    assertTrue(sortie.getText().endsWith("estVide !"), " en sortie, \"estVide !\" introuvable ???");
  }

  @Test
  public void test3_IHMPile_estPleine() throws Exception {
    Container panel = ihm.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 2);

    // la bonne IHM
    // assertTrue(components[0] instanceof JPanel);
    // assertTrue(components[1] instanceof JLabel);

    ((JPanel) components[0]).getComponents();
    // assertTrue(subComponents[0] instanceof JTextField);
    // assertTrue(subComponents[1] instanceof JButton);
    // assertTrue(subComponents[2] instanceof JButton);
    // assertTrue(subComponents[3] instanceof JTextField);
    //
    JLabel sortie = (JLabel) components[1];
    int i = 1;
    empiler(Integer.toString(i++));
    while (!sortie.getText().endsWith("estPleine !") && (i < 10)) {
      empiler(Integer.toString(i++));
    }
    assertTrue(i < 10, " en sortie, \"estPleine !\" introuvable ?");
  }

  @Test
  public void test4_IHMPile() throws Exception {
    Container panel = ihm.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 2);

    // la bonne IHM
    // assertTrue(components[0] instanceof JPanel);
    // assertTrue(components[1] instanceof JLabel);

    ((JPanel) components[0]).getComponents();
    // assertTrue(subComponents[0] instanceof JTextField);
    // assertTrue(subComponents[1] instanceof JButton);
    // assertTrue(subComponents[2] instanceof JButton);
    // assertTrue(subComponents[3] instanceof JTextField);
    //
    JLabel sortie = (JLabel) components[1];
    empiler("ZZ");
    assertEquals("[ZZ]", sortie.getText(), " sortie ??? ");
    empiler("AA");
    assertEquals("[AA, ZZ]", sortie.getText(), " sortie ??? ");
    empiler("10");
    assertEquals("[10, AA, ZZ]", sortie.getText(), " sortie ??? ");
  }

  @SuppressWarnings("unused") // Non utilisé pour l'instant
  private void depiler() {
    Container panel = ihm.getContentPane();
    Component[] components = panel.getComponents();
    Component[] subComponents = ((JPanel) components[0]).getComponents();

    // Point location = subComponents[2].getLocationOnScreen();
    // mouseMoveAndClick(location.x+(subComponents[1].getWidth()/2),location.y+(subComponents[1].getHeight()/2));
    assertTrue(subComponents[2] instanceof JButton);
    JButton button = ((JButton) subComponents[2]);
    ActionListener[] listeners = button.getActionListeners();
    assertTrue(listeners.length == 1);
    listeners[0].actionPerformed(null);
  }

  private void empiler(String str) throws Exception {
    Container panel = ihm.getContentPane();
    Component[] components = panel.getComponents();
    Component[] subComponents = ((JPanel) components[0]).getComponents();

    // 		Point location = subComponents[0].getLocationOnScreen();
    // 		mouseMoveAndClickClick(location.x,location.y);
    // 		typeLine(str, robot, false);

    ((JTextField) subComponents[0]).setText(str);
    Point location = subComponents[1].getLocationOnScreen();
    mouseMoveAndClick(
        location.x + (subComponents[1].getWidth() / 2),
        location.y + (subComponents[1].getHeight() / 2));

    // 		assertTrue(subComponents[1] instanceof JButton);
    // 		JButton button = ((JButton)subComponents[1]);
    // 		ActionListener[] listeners = button.getActionListeners();
    // 		assertTrue(listeners.length==1);
    //     listeners[0].actionPerformed(null);
  }

  // extrait de http://www.hazirkod.com/hazirkodv.asp?KID=1425
  public static void typeLine(String s, Robot robot, boolean enter) throws Exception {
    char[] array = s.toCharArray();
    for (int i = 0; i < array.length; i++) {

      if (array[i] >= 'a' && array[i] <= 'z') {
        robot.keyPress((int) array[i] - (int) 'a' + 65);
        robot.delay(10);
        robot.keyRelease((int) array[i] - (int) 'a' + 65);
      } else if (array[i] == ' ') {
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_SPACE);
      } else if (array[i] >= 'A' && array[i] <= 'Z') {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.delay(10);
        robot.keyPress((int) array[i] - (int) 'A' + 65);
        robot.delay(10);
        robot.keyRelease((int) array[i] - (int) 'A' + 65);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_SHIFT);
      } else if (array[i] >= '0' && array[i] <= '9') {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.delay(10);
        robot.keyPress(KeyEvent.VK_0 + (int) (array[i] - '0'));
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_0 + (int) (array[i] - '0'));
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_SHIFT);
      }
      robot.delay(10);
    }
  }

  @SuppressWarnings("deprecation")
  public void mouseMoveAndClick(int x, int y) {
    robot.mouseMove(x, y);
    robot.delay(100);
    robot.mousePress(InputEvent.BUTTON1_MASK);
    robot.delay(200);
    robot.mouseRelease(InputEvent.BUTTON1_MASK);
    robot.delay(200);
    // robot.delay(2000);
  } // end mouseMoveAndClick

  @SuppressWarnings("deprecation")
  public void mouseMoveAndClickClick(int x, int y) {
    robot.mouseMove(x, y);
    robot.delay(100);
    robot.mousePress(InputEvent.BUTTON1_MASK);
    robot.delay(200);

    robot.mouseRelease(InputEvent.BUTTON1_MASK);
    robot.delay(100);
    // robot.delay(10);
    robot.mousePress(InputEvent.BUTTON1_MASK);
    robot.delay(200);

    robot.mouseRelease(InputEvent.BUTTON1_MASK);
    robot.delay(200);
  } // end mouseMoveAndClickClick
}
