package question4;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import javax.swing.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MarkIHMCalculette {

  private JFrame f;
  private Robot robot;

  // private static Random random= new Random();

  /**
   * Met en place les engagements.
   *
   * <p>Méthode appelée avant chaque appel de méthode de test.
   */
  @BeforeEach
  public void setUp() throws java.lang.Exception {
    f = new IHMCalculette().frame;
    f.pack();
    f.setVisible(true);
    while (!(f.isShowing())) {}
    f.setAlwaysOnTop(true);
    f.setLocation(500, 500);
    robot = new Robot();
    robot.delay(60); // avec 10ms parfois le click arrivait avant la création dfe la fenêtre...
  }

  /**
   * Supprime les engagements
   *
   * <p>Méthode appelée après chaque appel de méthode de test.
   */
  @AfterEach
  public void tearDown() { // throws java.lang.Exception
    f.dispose();
  }

  // Ces fonctions d'accès ne marche que si la structure
  // graphique de l'application est inchangée. Ceci devrait
  // être systématiquement vérifié (CF checkStructure())
  // avant d'appeler ces méthodes.

  private Container getMainPanel() {
    return f.getContentPane();
  }

  private Component[] getMainComponents() {
    return getMainPanel().getComponents();
  }

  private Component[] getPileComponents() {
    return ((JPanel) (getMainComponents()[0])).getComponents();
  }

  private JLabel getPileLabel() {
    return (JLabel) (getPileComponents()[0]);
  }

  private String getPileTexte() {
    return (String) (getPileLabel().getText());
  }

  private Component[] getBoutons() {
    return ((JPanel) (getMainComponents()[2])).getComponents();
  }

  private JTextField getTextEntry() {
    return (JTextField) (getMainComponents()[1]);
  }

  private JButton getPushButton() {
    return (JButton) (getBoutons()[0]);
  }

  private JButton getAddButton() {
    return (JButton) (getBoutons()[1]);
  }

  private JButton getSubButton() {
    return (JButton) (getBoutons()[2]);
  }

  private JButton getMulButton() {
    return (JButton) (getBoutons()[3]);
  }

  private JButton getDivButton() {
    return (JButton) (getBoutons()[4]);
  }

  private JButton getClearButton() {
    return (JButton) (getBoutons()[5]);
  }

  // Ceci devrait être vérifié avant d'utiliser les méthodes d'accès getXXX ci-dessus.
  private void checkStructure(JFrame j) throws Exception {
    // Container panel = getMainPanel();
    Component[] components = getMainComponents();
    assertEquals(3, components.length, " ce n'est pas l'IHM attendue ?");
    assertTrue(components[0] instanceof JPanel, " ce n'est pas l'IHM attendue ?");
    assertTrue(components[1] instanceof JTextField, " ce n'est pas l'IHM attendue ?");
    assertTrue(components[2] instanceof JPanel, " ce n'est pas l'IHM attendue ?");
    Component[] compVuePile = getPileComponents();
    assertEquals(1, compVuePile.length, " ce n'est pas l'IHM attendue ?");
    Component[] subComponents = getBoutons();
    assertEquals(6, subComponents.length, " ce n'est pas l'IHM attendue ?");
    assertTrue(subComponents[0] instanceof JButton, " ce n'est pas l'IHM attendue ?"); // push
    assertTrue(subComponents[1] instanceof JButton, " ce n'est pas l'IHM attendue ?"); // +
    assertTrue(subComponents[2] instanceof JButton, " ce n'est pas l'IHM attendue ?"); // -
    assertTrue(subComponents[3] instanceof JButton, " ce n'est pas l'IHM attendue ?"); // *
    assertTrue(subComponents[4] instanceof JButton, " ce n'est pas l'IHM attendue ?"); // /
  }

  @Test
  public void test_IHMCalculette_addition() throws Exception {
    checkStructure(f);
    empiler("15");
    assertTrue("[15]".equals(getPileLabel().getText()), "empiler(15), en sortie != [15]");
    empiler("12");
    assertTrue(
        "[12, 15]".equals(getPileLabel().getText()),
        "empiler(15),empiler(12) en sortie != [12, 15]");
    assertTrue("[27]".equals(add()), "15+12 != 27 ???");
    empiler("12");
    assertTrue("[12, 27]".equals(getPileLabel().getText()));
    assertTrue("[39]".equals(add()), "27 + 12 != 39 ??? ");
  }

  @Test
  public void test_IHMCalculette_addition2() throws Exception {
    checkStructure(f);
    empiler("15");
    assertTrue("[15]".equals(getPileTexte()), "empiler(15), en sortie != [15]");
    empiler("12");
    assertTrue("[12, 15]".equals(getPileTexte()), "empiler(15),empiler(12) en sortie != [12, 15]");
    assertTrue("[27]".equals(add()));
    empiler("-12");
    // assertEquals("[ 27,-12]",((JLabel)components[0]).getText());
    assertTrue("[15]".equals(add()), "27 + (-12) != 15 ??? ");
  }

  @Test
  public void test_IHMCalculette_addition3() throws Exception {
    checkStructure(f);
    empiler("15");
    assertTrue("[15]".equals(getPileTexte()), "empiler(15), en sortie != [15]");
    empiler("-12");
    assertTrue(
        "[-12, 15]".equals(getPileTexte()), "empiler(15),empiler(-12) en sortie != [-12, 15]");
    // assertEquals(((JLabel)components[0]).getText(),"[-12, 27]");
    assertTrue("[3]".equals(add()), "-12 + 15 != 3 ??? ");
  }

  @Test
  public void test_IHMmultiplication() throws Exception {
    checkStructure(f);
    empiler("15");
    assertTrue("[15]".equals(getPileLabel().getText()), "empiler(15), en sortie != [15]");
    empiler("12");
    assertTrue(
        "[12, 15]".equals(getPileLabel().getText()),
        "empiler(15),empiler(12) en sortie != [12, 15]");
    assertTrue("[180]".equals(mul()), "15+12 != 180 ???");
    empiler("2");
    assertTrue("[2, 180]".equals(getPileLabel().getText()));
    assertTrue("[360]".equals(mul()), "180 * 2 != 360 ??? ");
  }

  @Test
  public void test_IHMCalculette_soustraction() throws Exception {
    checkStructure(f);
    empiler("32");
    assertTrue("[32]".equals(getPileLabel().getText()), "empiler(32), en sortie != [32]");
    empiler("10");
    assertTrue(
        "[10, 32]".equals(getPileLabel().getText()),
        "empiler(32),empiler(10) en sortie != [10, 32]");
    assertTrue("[22]".equals(sub()), " 32-10 != 22 ???");
  }

  @Test
  public void test_IHMCalculette_soustraction2() throws Exception {
    checkStructure(f);
    empiler("32");
    assertTrue("[32]".equals(getPileTexte()), "empiler(32), en sortie != [32]");
    empiler("-10");
    assertTrue(
        "[-10, 32]".equals(getPileTexte()), "empiler(32),empiler(-10) en sortie != [-10, 32]");
    assertTrue("[42]".equals(sub()), " 32-(-10) != 42 ???");
  }

  @Test
  public void test_IHMCalculette_MauvaisFormatDuNombre() throws Exception {
    checkStructure(f);
    empiler("32");
    assertTrue("[32]".equals(getPileTexte()));
    empiler("AA");
    assertTrue("[32]".equals(getPileTexte()), "incidence sur la pile d'évaluation ???");
    empiler("32");
    assertTrue("[32, 32]".equals(getPileTexte()), "incidence sur la pile d'évaluation ???");
  }

  @Test
  public void test_IHMCalculette_vider() throws Exception {
    checkStructure(f);
    empiler("32");
    empiler("22");
    clickCenterOf(getClearButton());
    assertTrue("[]".equals(getPileTexte()), "[] est attendu ...");
    empiler("32");
    assertTrue("[32]".equals(getPileTexte()));
  }

  @Test
  public void test_IHMCalculette_division() throws Exception {
    checkStructure(f);
    empiler("32");
    empiler("2");
    clickCenterOf(getDivButton());
    assertTrue("[16]".equals(getPileTexte()), " 32/2 != 16 ???");
  }

  @Test
  public void test_IHMCalculette_divisionParZero() throws Exception {
    checkStructure(f);
    empiler("32");
    empiler("0");
    assertTrue("[0, 32]".equals(getPileTexte())); // 32 / 0
    clickCenterOf(getDivButton());
    assertTrue("[0, 32]".equals(getPileTexte()), "incidence sur la pile d'évaluation ???"); // 32
    // / 0
    add();
    empiler("23");
    empiler("1");
    assertTrue(add().startsWith("[24"), "incidence sur le fonctionnement ???");
  }

  @Test
  public void test_IHMCalculette_addition_un_seul_operande() throws Exception {
    checkStructure(f);
    empiler("32");
    add();
    empiler("23");
    empiler("1");
    assertTrue(add().startsWith("[24"), "incidence sur le fonctionnement ???");
  }

  @Test
  public void test_IHMCalculette_soustraction_un_seul_opérande() throws Exception {
    checkStructure(f);
    empiler("32");
    sub();
    empiler("23");
    empiler("1");
    assertTrue(sub().startsWith("[22"), "incidence sur le fonctionnement ???");
  }

  @Test
  public void test_ControleurHasOnlyReact() {
    Method[] allMethods = Controleur.class.getDeclaredMethods();
    int nPublicMethods = 0;
    for (Method method : allMethods) {
      int mods = method.getModifiers();
      if (Modifier.isPublic(mods) || Modifier.isProtected(mods)) {
        nPublicMethods = nPublicMethods + 1;
        String methodeName = method.getName();
        assertEquals(
            "reactTo",
            methodeName,
            "La seule méthode non privée du contrôleur doit "
                + "s'appeler \"reactTo\" or celle-ci s'appelle: ["
                + methodeName
                + "].");
        assertEquals(
            1, method.getParameterTypes().length, "reactTo doit avoir un (unique) paramètre.");
        assertEquals(
            PileEvent.class,
            method.getParameterTypes()[0],
            "Le paramètre de reactTo doit être de type PileEvent");
      }
    }
    assertEquals(
        1,
        nPublicMethods,
        "Le contrôleur ne doit avoir qu'une seule méthode publique (reactTo(PileEvent)).");
  }

  private void clickCenterOf(Component c) {
    Point loc = c.getLocationOnScreen();
    mouseMoveAndClick(loc.x + (c.getWidth() / 2), loc.y + (c.getHeight() / 2));
  }

  private void clickClickCenterOf(Component c) {
    Point loc = c.getLocationOnScreen();
    mouseMoveAndClickClick(loc.x + (c.getWidth() / 2), loc.y + (c.getHeight() / 2));
  }

  private void empiler(String str) throws Exception {
    clickClickCenterOf(getTextEntry());
    typeLine(str, robot, false);
    clickCenterOf(getPushButton());
  }

  private String add() {
    clickCenterOf(getAddButton());
    return getPileTexte();
  }

  private String mul() {
    clickCenterOf(getMulButton());
    return getPileTexte();
  }

  private String sub() {
    clickCenterOf(getSubButton());
    return getPileTexte();
  }

  // Méthodes permettant de simuler les entrées utilisateurs
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
      } else if (array[i] == '-') {
        robot.keyPress(KeyEvent.VK_SUBTRACT);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_SUBTRACT);
      } else if (array[i] == '+') {
        robot.keyPress(KeyEvent.VK_ADD);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_ADD);
      } else if (array[i] >= 'A' && array[i] <= 'Z') {
        // robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress((int) array[i] - (int) 'A' + 65);
        robot.delay(10);
        robot.keyRelease((int) array[i] - (int) 'A' + 65);
        robot.delay(10);
        // robot.keyRelease(KeyEvent.VK_SHIFT);
      } else if (array[i] >= '0' && array[i] <= '9') {
        // robot.keyPress(KeyEvent.VK_SHIFT);
        robot.delay(10);
        robot.keyPress(KeyEvent.VK_0 + (int) (array[i] - '0'));
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_0 + (int) (array[i] - '0'));
        robot.delay(10);
        // robot.keyRelease(KeyEvent.VK_SHIFT);
      }
      robot.delay(10);
    }
  }

  @SuppressWarnings({"deprecation"})
  public void mouseMoveAndClick(int x, int y) {
    robot.mouseMove(x, y);
    robot.delay(20);
    robot.mousePress(InputEvent.BUTTON1_MASK);
    robot.delay(20);
    robot.mouseRelease(InputEvent.BUTTON1_MASK);
    robot.delay(20);
  } // end mouseMoveAndClick

  @SuppressWarnings("deprecation")
  public void mouseMoveAndClickClick(int x, int y) {
    robot.mouseMove(x, y);
    robot.delay(10);
    robot.mousePress(InputEvent.BUTTON1_MASK);
    robot.delay(10);
    robot.mouseRelease(InputEvent.BUTTON1_MASK);
    robot.delay(100);
    robot.mousePress(InputEvent.BUTTON1_MASK);
    robot.delay(10);
    robot.mouseRelease(InputEvent.BUTTON1_MASK);
    robot.delay(10);
  } // end mouseMoveAndClickClick
}
