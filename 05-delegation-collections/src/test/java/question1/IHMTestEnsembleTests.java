package question1;

// Classe de test de l'interface graphique fournie aux élèves pour les
// opérations ensemblistes. Ces tests n'ont pas à être donnés aux
// élèves, ils sont à usage interne. Contrairement à JPanelListe et
// JPaneListe2 qui sont à compléter.

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.Robot;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IHMTestEnsembleTests {
  // private static Random random = new Random();
  private static JFrame f = new IHMTestEnsemble();
  private Robot robot;

  @BeforeEach
  public void setUp() throws java.lang.Exception {

    // f = new IHMTestEnsemble();
    f.pack();
    // exit application when this window is closed
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
    while (!(f.isShowing())) {}
    f.setAlwaysOnTop(true);
    f.setLocation(400, 400);
    robot = new Robot();
    robot.delay(60);
  }

  @AfterEach
  public void tearDown() { // throws java.lang.Exception
    f.dispose();
  }

  @Test
  public void test_IHMTestEnsemble_union() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 4, " IHM a-t-elle été modifiée ?");

    // la bonne IHM
    assertTrue(components[0] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[1] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[2] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[3] instanceof JPanel, " IHM a-t-elle été modifiée ?");

    Component[] subComponents = ((JPanel) components[2]).getComponents();
    assertTrue(subComponents[1] instanceof JButton, " IHM a-t-elle été modifiée ?"); // union
    assertTrue(subComponents[2] instanceof JButton, " IHM a-t-elle été modifiée ?"); // inter
    assertTrue(subComponents[3] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diff
    assertTrue(subComponents[4] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diffSym

    final String e1 = "2 3 4 5";
    final String e2 = "4 5 6 7";
    placerPourE1(e1);
    placerPourE2(e2);
    unionE1_E2();
    verificationDuResultat(e1, "union", e2, "2 3 4 5 6 7");
  }

  @Test
  public void test_IHMTestEnsemble_union_bis() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 4, " IHM a-t-elle été modifiée ?");

    // la bonne IHM
    assertTrue(components[0] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[1] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[2] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[3] instanceof JPanel, " IHM a-t-elle été modifiée ?");

    Component[] subComponents = ((JPanel) components[2]).getComponents();
    assertTrue(subComponents[1] instanceof JButton, " IHM a-t-elle été modifiée ?"); // union
    assertTrue(subComponents[2] instanceof JButton, " IHM a-t-elle été modifiée ?"); // inter
    assertTrue(subComponents[3] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diff
    assertTrue(subComponents[4] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diffSym

    final String e1 = "2 3";
    placerPourE1(e1);
    final String e2 = "4 5 6 7";
    placerPourE2(e2);
    unionE1_E2();
    verificationDuResultat(e1, "union", e2, "2 3 4 5 6 7");
  }

  @Test
  public void test_IHMTestEnsemble_union_ter() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 4, " IHM a-t-elle été modifiée ?");

    // la bonne IHM
    assertTrue(components[0] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[1] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[2] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[3] instanceof JPanel, " IHM a-t-elle été modifiée ?");

    Component[] subComponents = ((JPanel) components[2]).getComponents();
    assertTrue(subComponents[1] instanceof JButton, " IHM a-t-elle été modifiée ?"); // union
    assertTrue(subComponents[2] instanceof JButton, " IHM a-t-elle été modifiée ?"); // inter
    assertTrue(subComponents[3] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diff
    assertTrue(subComponents[4] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diffSym

    final String e1 = "2";
    placerPourE1(e1);
    final String e2 = "2";
    placerPourE2(e2);
    unionE1_E2();
    verificationDuResultat(e1, "union", e2, "2");
  }

  @Test
  public void test_IHMTestEnsemble_inter() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 4, " IHM a-t-elle été modifiée ?");

    // la bonne IHM
    assertTrue(components[0] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[1] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[2] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[3] instanceof JPanel, " IHM a-t-elle été modifiée ?");

    Component[] subComponents = ((JPanel) components[2]).getComponents();
    assertTrue(subComponents[1] instanceof JButton, " IHM a-t-elle été modifiée ?"); // union
    assertTrue(subComponents[2] instanceof JButton, " IHM a-t-elle été modifiée ?"); // inter
    assertTrue(subComponents[3] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diff
    assertTrue(subComponents[4] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diffSym

    final String e1 = "2 3 4 5";
    placerPourE1(e1);
    final String e2 = "4 5 6 7";
    placerPourE2(e2);
    interE1_E2();
    verificationDuResultat(e1, "inter", e2, "4 5");
  }

  @Test
  public void test_IHMTestEnsemble_inter_bis() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 4, " IHM a-t-elle été modifiée ?");

    // la bonne IHM
    assertTrue(components[0] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[1] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[2] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[3] instanceof JPanel, " IHM a-t-elle été modifiée ?");

    Component[] subComponents = ((JPanel) components[2]).getComponents();
    assertTrue(subComponents[1] instanceof JButton, " IHM a-t-elle été modifiée ?"); // union
    assertTrue(subComponents[2] instanceof JButton, " IHM a-t-elle été modifiée ?"); // inter
    assertTrue(subComponents[3] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diff
    assertTrue(subComponents[4] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diffSym

    final String e1 = "2 3";
    placerPourE1(e1);
    final String e2 = "4 5 6 7";
    placerPourE2(e2);
    interE1_E2();
    verificationDuResultat(e1, "inter", e2, "");
  }

  @Test
  public void test_IHMTestEnsemble_inter_ter() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 4, " IHM a-t-elle été modifiée ?");

    // la bonne IHM
    assertTrue(components[0] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[1] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[2] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[3] instanceof JPanel, " IHM a-t-elle été modifiée ?");

    Component[] subComponents = ((JPanel) components[2]).getComponents();
    assertTrue(subComponents[1] instanceof JButton, " IHM a-t-elle été modifiée ?"); // union
    assertTrue(subComponents[2] instanceof JButton, " IHM a-t-elle été modifiée ?"); // inter
    assertTrue(subComponents[3] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diff
    assertTrue(subComponents[4] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diffSym

    final String e1 = "2 3 4 5";
    placerPourE1(e1);
    final String e2 = "2 3 4 5";
    placerPourE2(e2);
    interE1_E2();
    verificationDuResultat(e1, "inter", e2, "2 3 4 5");
  }

  @Test
  public void test_IHMTestEnsemble_diff() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 4, " IHM a-t-elle été modifiée ?");

    // la bonne IHM
    assertTrue(components[0] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[1] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[2] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[3] instanceof JPanel, " IHM a-t-elle été modifiée ?");

    Component[] subComponents = ((JPanel) components[2]).getComponents();
    assertTrue(subComponents[1] instanceof JButton, " IHM a-t-elle été modifiée ?"); // union
    assertTrue(subComponents[2] instanceof JButton, " IHM a-t-elle été modifiée ?"); // inter
    assertTrue(subComponents[3] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diff
    assertTrue(subComponents[4] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diffSym

    final String e1 = "2 3 4 5";
    placerPourE1(e1);
    final String e2 = "4 5 6 7";
    placerPourE2(e2);
    diffE1_E2();
    verificationDuResultat(e1, "diff", e2, "2 3");
  }

  @Test
  public void test_IHMTestEnsemble_diff_bis() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 4, " IHM a-t-elle été modifiée ?");

    // la bonne IHM
    assertTrue(components[0] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[1] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[2] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[3] instanceof JPanel, " IHM a-t-elle été modifiée ?");

    Component[] subComponents = ((JPanel) components[2]).getComponents();
    assertTrue(subComponents[1] instanceof JButton, " IHM a-t-elle été modifiée ?"); // union
    assertTrue(subComponents[2] instanceof JButton, " IHM a-t-elle été modifiée ?"); // inter
    assertTrue(subComponents[3] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diff
    assertTrue(subComponents[4] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diffSym

    final String e1 = "2 3";
    placerPourE1(e1);
    final String e2 = "4 5 6 7";
    placerPourE2(e2);
    diffE1_E2();
    verificationDuResultat(e1, "diff", e2, "2 3");
  }

  @Test
  public void test_IHMTestEnsemble_diff_ter() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 4, " IHM a-t-elle été modifiée ?");

    // la bonne IHM
    assertTrue(components[0] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[1] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[2] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[3] instanceof JPanel, " IHM a-t-elle été modifiée ?");

    Component[] subComponents = ((JPanel) components[2]).getComponents();
    assertTrue(subComponents[1] instanceof JButton, " IHM a-t-elle été modifiée ?"); // union
    assertTrue(subComponents[2] instanceof JButton, " IHM a-t-elle été modifiée ?"); // inter
    assertTrue(subComponents[3] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diff
    assertTrue(subComponents[4] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diffSym

    final String e1 = "2 3";
    placerPourE1(e1);
    final String e2 = "2 3 6 7";
    placerPourE2(e2);
    diffE1_E2();
    verificationDuResultat(e1, "diff", e2, "");
  }

  @Test
  public void test_IHMTestEnsemble_diffSym() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 4, " IHM a-t-elle été modifiée ?");

    // la bonne IHM
    assertTrue(components[0] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[1] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[2] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[3] instanceof JPanel, " IHM a-t-elle été modifiée ?");

    Component[] subComponents = ((JPanel) components[2]).getComponents();
    assertTrue(subComponents[1] instanceof JButton, " IHM a-t-elle été modifiée ?"); // union
    assertTrue(subComponents[2] instanceof JButton, " IHM a-t-elle été modifiée ?"); // inter
    assertTrue(subComponents[3] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diff
    assertTrue(subComponents[4] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diffSym

    final String e1 = "2 3 4 5";
    placerPourE1(e1);
    final String e2 = "4 5 6 7";
    placerPourE2(e2);
    diffSymE1_E2();
    verificationDuResultat(e1, "diffSym", e2, "2 3 6 7");
  }

  @Test
  public void test_IHMTestEnsemble_diffSym_bis() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 4, " IHM a-t-elle été modifiée ?");

    // la bonne IHM
    assertTrue(components[0] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[1] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[2] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[3] instanceof JPanel, " IHM a-t-elle été modifiée ?");

    Component[] subComponents = ((JPanel) components[2]).getComponents();
    assertTrue(subComponents[1] instanceof JButton, " IHM a-t-elle été modifiée ?"); // union
    assertTrue(subComponents[2] instanceof JButton, " IHM a-t-elle été modifiée ?"); // inter
    assertTrue(subComponents[3] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diff
    assertTrue(subComponents[4] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diffSym

    final String e1 = "2 3";
    placerPourE1(e1);
    final String e2 = "6 7";
    placerPourE2(e2);
    diffSymE1_E2();
    verificationDuResultat(e1, "diffSym", e2, "2 3 6 7");
  }

  @Test
  public void test_IHMTestEnsemble_diffSym_ter() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 4, " IHM a-t-elle été modifiée ?");

    // la bonne IHM
    assertTrue(components[0] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[1] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[2] instanceof JPanel, " IHM a-t-elle été modifiée ?");
    assertTrue(components[3] instanceof JPanel, " IHM a-t-elle été modifiée ?");

    Component[] subComponents = ((JPanel) components[2]).getComponents();
    assertTrue(subComponents[1] instanceof JButton, " IHM a-t-elle été modifiée ?"); // union
    assertTrue(subComponents[2] instanceof JButton, " IHM a-t-elle été modifiée ?"); // inter
    assertTrue(subComponents[3] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diff
    assertTrue(subComponents[4] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diffSym

    final String e1 = "";
    placerPourE2(e1);
    placerPourE1(e1);
    final String e2 = "";
    placerPourE2(e1);
    placerPourE2(e2);
    diffSymE1_E2();
    verificationDuResultat(e1, "diffSym", e2, "");
  }

  private void placerPourE1(String str) throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    Component[] subComponents = ((JPanel) components[0]).getComponents();
    assertTrue(subComponents[1] instanceof JTextField);
    JTextField jt = (JTextField) subComponents[1];
    jt.setText(str);
    robot.delay(100);
    //         Point location = subComponents[1].getLocationOnScreen();
    //         mouseMoveAndClickClick(location.x,location.y);
    //         typeLine(str, robot, false);
  }

  private void placerPourE2(String str) throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    Component[] subComponents = ((JPanel) components[1]).getComponents();

    assertTrue(subComponents[1] instanceof JTextField);
    JTextField jt = (JTextField) subComponents[1];
    jt.setText(str);
    robot.delay(100);
    //         Point location = subComponents[1].getLocationOnScreen();
    //         mouseMoveAndClickClick(location.x,location.y);
    //         typeLine(str, robot, false);
  }

  private void unionE1_E2() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    Component[] subComponents = ((JPanel) components[2]).getComponents();

    assertTrue(subComponents[1] instanceof JButton, " IHM a-t-elle été modifiée ?"); // union
    //         JButton union = ((JButton)subComponents[1]);
    //         ActionListener[] listeners = union.getActionListeners();
    //         assertTrue(listeners.length==1);
    //     listeners[0].actionPerformed(null);

    Point location = subComponents[1].getLocationOnScreen();
    mouseMoveAndClick(
        location.x + (subComponents[1].getWidth() / 2),
        location.y + (subComponents[1].getHeight() / 2));
  }

  private void interE1_E2() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    Component[] subComponents = ((JPanel) components[2]).getComponents();

    assertTrue(subComponents[2] instanceof JButton, " IHM a-t-elle été modifiée ?"); // inter
    // 		JButton inter = ((JButton)subComponents[2]);
    // 		ActionListener[] listeners = inter.getActionListeners();
    // 		assertTrue(listeners.length==1);
    //     listeners[0].actionPerformed(null);

    Point location = subComponents[2].getLocationOnScreen();
    mouseMoveAndClick(
        location.x + (subComponents[2].getWidth() / 2),
        location.y + (subComponents[2].getHeight() / 2));
  }

  private void diffE1_E2() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    Component[] subComponents = ((JPanel) components[2]).getComponents();

    assertTrue(subComponents[3] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diff
    // 		JButton diff = ((JButton)subComponents[3]);
    // 		ActionListener[] listeners = diff.getActionListeners();
    // 		assertTrue(listeners.length==1);
    //     listeners[0].actionPerformed(null);

    Point location = subComponents[3].getLocationOnScreen();
    mouseMoveAndClick(
        location.x + (subComponents[3].getWidth() / 2),
        location.y + (subComponents[3].getHeight() / 2));
  }

  private void diffSymE1_E2() throws Exception {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    Component[] subComponents = ((JPanel) components[2]).getComponents();

    assertTrue(subComponents[4] instanceof JButton, " IHM a-t-elle été modifiée ?"); // diffSym
    // 		JButton diffSym = ((JButton)subComponents[4]);
    // 		ActionListener[] listeners = diffSym.getActionListeners();
    // 		assertTrue(listeners.length==1);
    //     listeners[0].actionPerformed(null);
    Point location = subComponents[4].getLocationOnScreen();
    mouseMoveAndClick(
        location.x + (subComponents[4].getWidth() / 2),
        location.y + (subComponents[4].getHeight() / 2));
  }

  private void verificationDuResultat(String e1, String op, String e2, String resultatAttendu) {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    Component[] subComponents = ((JPanel) components[3]).getComponents();
    JTextField res = (JTextField) subComponents[1];
    TreeSet<String> resultat = getSet(res.getText());
    TreeSet<String> reference = getSet(resultatAttendu);
    assertTrue(
        reference.toString().equals(resultat.toString()),
        "["
            + e1
            + "] "
            + op
            + " ["
            + e2
            + "] # ["
            + resultatAttendu
            + "] ??, obtenu:("
            + resultat
            + ")");
  }

  private TreeSet<String> getSet(String str) {
    TreeSet<String> e = new TreeSet<String>();
    java.util.StringTokenizer st = new java.util.StringTokenizer(str, " ,][");
    while (st.hasMoreTokens()) {
      e.add(st.nextToken());
    }
    return e;
  }

  // extrait de http://www.hazirkod.com/hazirkodv.asp?KID=1425
  public static void typeLine(String s, Robot robot, boolean enter) throws Exception {
    char[] array = s.toCharArray();
    for (int i = 0; i < array.length; i++) {

      if (array[i] >= 'a' && array[i] <= 'z') {
        robot.keyPress((int) array[i] - (int) 'a' + 65);
        robot.delay(60);
        robot.keyRelease((int) array[i] - (int) 'a' + 65);
      } else if (array[i] == ' ') {
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.delay(60);
        robot.keyRelease(KeyEvent.VK_SPACE);
      } else if (array[i] >= 'A' && array[i] <= 'Z') {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.delay(60);
        robot.keyPress((int) array[i] - (int) 'A' + 65);
        robot.delay(60);
        robot.keyRelease((int) array[i] - (int) 'A' + 65);
        robot.delay(60);
        robot.keyRelease(KeyEvent.VK_SHIFT);
      } else if (array[i] >= '0' && array[i] <= '9') {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.delay(60);
        robot.keyPress(KeyEvent.VK_0 + (int) (array[i] - '0'));
        robot.delay(60);
        robot.keyRelease(KeyEvent.VK_0 + (int) (array[i] - '0'));
        robot.delay(60);
        robot.keyRelease(KeyEvent.VK_SHIFT);
      }
      robot.delay(60);
    }
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

  @SuppressWarnings("deprecation")
  public void mouseMoveAndClickClick(int x, int y) {
    robot.mouseMove(x, y);
    robot.delay(60);
    robot.mousePress(InputEvent.BUTTON1_MASK);
    robot.delay(60);
    robot.mouseRelease(InputEvent.BUTTON1_MASK);
    robot.delay(60);
    robot.mousePress(InputEvent.BUTTON1_MASK);
    robot.delay(60);
    robot.mouseRelease(InputEvent.BUTTON1_MASK);
    robot.delay(60);
  } // end mouseMoveAndClickClick
}
