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

public class MarkIHMListe {

  private JFrame f;
  private Robot robot;

  @BeforeEach
  public void setUp() throws java.lang.Exception {
    f = new IHMListe();
    f.pack();
    f.setVisible(true);
    while (!(f.isShowing())) {}
    f.setAlwaysOnTop(true);
    f.setLocation(300, 300);

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
    f.dispose();
    f = null;
    robot.delay(200);
    System.gc();
  }

  @Test
  public void test_IHMListe_rechercher() {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 1, "IHM modifiée !!!");

    // 		// la bonne IHM
    assertTrue(components[0] instanceof JPanel);
    Component[] subComponents = ((JPanel) components[0]).getComponents();

    assertTrue(subComponents[0] instanceof JPanel, "IHM modifiée, voir JPanel cmd !!!"); // cmd
    assertTrue(
        subComponents[1] instanceof JTextArea, "IHM modifiée, voir JTextArea cmd !!!"); // texte

    Component[] subSubComponents = ((JPanel) subComponents[0]).getComponents();
    assertTrue(
        subSubComponents[0] instanceof JLabel,
        "IHM modifiée, voir JLabel afficheur !!!"); // afficheur
    assertTrue(
        subSubComponents[1] instanceof JTextField,
        "IHM modifiée, voir JTextField saisie !!!"); // saisie
    assertTrue(
        subSubComponents[2] instanceof JPanel, "IHM modifiée, voir JPanel boutons !!!"); // boutons

    Component[] boutons = ((JPanel) subSubComponents[2]).getComponents();
    assertTrue(
        boutons[0] instanceof JButton,
        "IHM modifiée, voir JButton boutonRechercher !!!"); // boutonRechercher
    assertTrue(
        boutons[1] instanceof JButton,
        "IHM modifiée, voir JButton boutonRetirer !!!"); // boutonRetirer
    assertTrue(boutons[2] instanceof JLabel, "IHM modifiée, voir JLabel !!!"); //
    assertTrue(
        boutons[3] instanceof Checkbox, "IHM modifiée, voir Checkbox croissant !!!"); // croissant
    assertTrue(
        boutons[4] instanceof Checkbox,
        "IHM modifiée, voir Checkbox decroissant !!!"); // decroissant

    // Rechercher("Java");
    JTextField saisie = (JTextField) subSubComponents[1];
    saisie.setText("Java");

    // assertTrue(components[1] instanceof JButton);
    // 		JButton button = ((JButton)boutons[0]);
    // 		ActionListener[] listeners = button.getActionListeners();
    // 		assertTrue(listeners.length==1);
    //     listeners[0].actionPerformed(null);
    // Point location = boutons[0].getLocationOnScreen();
    // mouseMoveAndClick(location.x+(boutons[0].getWidth()/2),location.y+(boutons[0].getHeight()/2));
    JButton button = ((JButton) boutons[0]);
    button.doClick();

    JLabel res = (JLabel) subSubComponents[0];
    assertTrue(res.getText().endsWith("true"), "\"Java\" doit être présent !!!");

    // Rechercher("Java");
    saisie = (JTextField) subSubComponents[1];
    saisie.setText("Java2");

    //  	  location = boutons[0].getLocationOnScreen();
    //
    //
    //	mouseMoveAndClick(location.x+(boutons[0].getWidth()/2),location.y+(boutons[0].getHeight()/2));
    button.doClick();

    res = (JLabel) subSubComponents[0];
    assertTrue(res.getText().endsWith("false"), "\"Java2\" doit être absent !!!");
  }

  @Test
  public void test_IHM_occurrence() {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 1);

    // 		// la bonne IHM
    assertTrue(components[0] instanceof JPanel);
    Component[] subComponents = ((JPanel) components[0]).getComponents();

    assertTrue(subComponents[0] instanceof JPanel); // cmd
    assertTrue(subComponents[1] instanceof JTextArea); // texte

    Component[] subSubComponents = ((JPanel) subComponents[0]).getComponents();
    assertTrue(subSubComponents[0] instanceof JLabel); // afficheur
    assertTrue(subSubComponents[1] instanceof JTextField); // saisie
    assertTrue(subSubComponents[2] instanceof JPanel); // boutons

    Component[] boutons = ((JPanel) subSubComponents[2]).getComponents();
    assertTrue(boutons[0] instanceof JButton); // boutonRechercher
    assertTrue(boutons[1] instanceof JButton); // boutonRetirer
    assertTrue(boutons[2] instanceof JLabel); //
    assertTrue(boutons[3] instanceof Checkbox); // croissant
    assertTrue(boutons[4] instanceof Checkbox); // decroissant
    assertTrue(boutons[5] instanceof JButton); // occurrence

    // Rechercher("Java");
    JTextField saisie = (JTextField) subSubComponents[1];
    saisie.setText("you");

    // assertTrue(components[1] instanceof JButton);
    // 		JButton button = ((JButton)boutons[0]);
    // 		ActionListener[] listeners = button.getActionListeners();
    // 		assertTrue(listeners.length==1);
    //     listeners[0].actionPerformed(null);
    // Point location = boutons[5].getLocationOnScreen();
    // mouseMoveAndClick(location.x+(boutons[5].getWidth()/2),location.y+(boutons[5].getHeight()/2));
    JButton button = ((JButton) boutons[5]);
    button.doClick();

    JLabel res = (JLabel) subSubComponents[0];
    Scanner sc = new Scanner(res.getText());
    assertEquals("-->", sc.next(), "--> est attendu ???");

    try {
      int leNombre = sc.nextInt();
      assertEquals(36, leNombre, " Nombre d'occurrence de you est-il erroné ???");
    } catch (InputMismatchException ime) {
      fail("--> N, N : un entier est attendu ???");
    }

    //  	  assertTrue(res.getText().endsWith("true"),"\Java\" doit être présent !!!");
    //
    //  	  //Rechercher("Java");
    //  	  saisie = (JTextField)subSubComponents[1];
    //  	  saisie.setText("Java2");
    //
    //  	  location = boutons[0].getLocationOnScreen();
    //
    //	mouseMoveAndClick(location.x+(boutons[0].getWidth()/2),location.y+(boutons[0].getHeight()/2));
    //
    //
    //
    //     res = (JLabel)subSubComponents[0];
    //  	  assertTrue( res.getText().endsWith("false"),"\Java\" doit être absent !!!");
  }

  @Test
  public void test_IHMListe_tri_croissant() {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 1);

    // 		// la bonne IHM
    assertTrue(components[0] instanceof JPanel);
    Component[] subComponents = ((JPanel) components[0]).getComponents();

    assertTrue(subComponents[0] instanceof JPanel); // cmd
    assertTrue(subComponents[1] instanceof JTextArea); // texte

    Component[] subSubComponents = ((JPanel) subComponents[0]).getComponents();
    assertTrue(subSubComponents[0] instanceof JLabel); // afficheur
    assertTrue(subSubComponents[1] instanceof JTextField); // saisie
    assertTrue(subSubComponents[2] instanceof JPanel); // boutons

    Component[] boutons = ((JPanel) subSubComponents[2]).getComponents();
    assertTrue(boutons[0] instanceof JButton); // boutonRechercher
    assertTrue(boutons[1] instanceof JButton); // boutonRetirer
    assertTrue(boutons[2] instanceof JLabel); //
    assertTrue(boutons[3] instanceof Checkbox); // croissant
    assertTrue(boutons[4] instanceof Checkbox); // decroissant

    // tri en ordre croissant
    Point location = boutons[3].getLocationOnScreen();
    mouseMoveAndClick(
        location.x + (boutons[3].getWidth() / 2), location.y + (boutons[3].getHeight() / 2));
    robot.delay(200);

    JTextArea ta = (JTextArea) subComponents[1];
    StringTokenizer st = new StringTokenizer(ta.getText(), ", []");

    try {

      String s1 = st.nextToken();
      String s2 = st.nextToken();
      assertTrue(s1.compareTo(s2) <= 0);
      s1 = st.nextToken();
      s2 = st.nextToken();
      assertTrue(s1.compareTo(s2) <= 0, "tri en ordre croissant ??? ");
      s1 = st.nextToken();
      s2 = st.nextToken();
      assertTrue(s1.compareTo(s2) <= 0, "tri en ordre croissant ??? ");
      s1 = st.nextToken();
      s2 = st.nextToken();
      assertTrue(s1.compareTo(s2) <= 0, "tri en ordre croissant ??? ");
      s1 = st.nextToken();
      s2 = st.nextToken();
      assertTrue(s1.compareTo(s2) <= 0, "tri en ordre croissant ??? ");
      s1 = st.nextToken();
      s2 = st.nextToken();
      assertTrue(s1.compareTo(s2) <= 0, "tri en ordre croissant ??? ");
      s1 = st.nextToken();
      s2 = st.nextToken();
      assertTrue(s1.compareTo(s2) <= 0, "tri en ordre croissant ??? ");
      s1 = st.nextToken();
      s2 = st.nextToken();
      assertTrue(s1.compareTo(s2) <= 0, "tri en ordre croissant ??? ");
      s1 = st.nextToken();
      s2 = st.nextToken();
      assertTrue(s1.compareTo(s2) <= 0, "tri en ordre croissant ??? ");

    } catch (NoSuchElementException e) {
      fail("Exception pas de texte ??, IHMListe_tri ?? ");
    } catch (Exception e) {
      fail("exception inattendue ! " + e.getClass().getName());
    }
  }

  @Test
  public void test_IHMListe_tri_décroissant() {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 1);

    // 		// la bonne IHM
    assertTrue(components[0] instanceof JPanel);
    Component[] subComponents = ((JPanel) components[0]).getComponents();

    assertTrue(subComponents[0] instanceof JPanel); // cmd
    assertTrue(subComponents[1] instanceof JTextArea); // texte

    Component[] subSubComponents = ((JPanel) subComponents[0]).getComponents();
    assertTrue(subSubComponents[0] instanceof JLabel); // afficheur
    assertTrue(subSubComponents[1] instanceof JTextField); // saisie
    assertTrue(subSubComponents[2] instanceof JPanel); // boutons

    Component[] boutons = ((JPanel) subSubComponents[2]).getComponents();
    assertTrue(boutons[0] instanceof JButton); // boutonRechercher
    assertTrue(boutons[1] instanceof JButton); // boutonRetirer
    assertTrue(boutons[2] instanceof JLabel); //
    assertTrue(boutons[3] instanceof Checkbox); // croissant
    assertTrue(boutons[4] instanceof Checkbox); // decroissant

    // tri en ordre decroissant
    Point location = boutons[4].getLocationOnScreen();
    mouseMoveAndClick(
        location.x + (boutons[4].getWidth() / 2), location.y + (boutons[4].getHeight() / 2));

    robot.delay(200);
    JTextArea ta = (JTextArea) subComponents[1];
    StringTokenizer st = new StringTokenizer(ta.getText(), ", []");

    assertTrue(st.nextToken().compareTo(st.nextToken()) >= 0, "tri en ordre décroissant ?? ");
    assertTrue(st.nextToken().compareTo(st.nextToken()) >= 0, "tri en ordre décroissant ?? ");
    assertTrue(st.nextToken().compareTo(st.nextToken()) >= 0, "tri en ordre décroissant ?? ");
    assertTrue(st.nextToken().compareTo(st.nextToken()) >= 0, "tri en ordre décroissant ?? ");
    assertTrue(st.nextToken().compareTo(st.nextToken()) >= 0, "tri en ordre décroissant ?? ");
    assertTrue(st.nextToken().compareTo(st.nextToken()) >= 0, "tri en ordre décroissant ?? ");
    assertTrue(st.nextToken().compareTo(st.nextToken()) >= 0, "tri en ordre décroissant ?? ");
  }

  @Test
  public void test_IHMListe_retirer() {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 1);

    // 		// la bonne IHM
    assertTrue(components[0] instanceof JPanel);
    Component[] subComponents = ((JPanel) components[0]).getComponents();

    assertTrue(subComponents[0] instanceof JPanel); // cmd
    assertTrue(subComponents[1] instanceof JTextArea); // texte

    Component[] subSubComponents = ((JPanel) subComponents[0]).getComponents();
    assertTrue(subSubComponents[0] instanceof JLabel); // afficheur
    assertTrue(subSubComponents[1] instanceof JTextField); // saisie
    assertTrue(subSubComponents[2] instanceof JPanel); // boutons

    Component[] boutons = ((JPanel) subSubComponents[2]).getComponents();
    assertTrue(boutons[0] instanceof JButton); // boutonRechercher
    assertTrue(boutons[1] instanceof JButton); // boutonRetirer
    assertTrue(boutons[2] instanceof JLabel); //
    assertTrue(boutons[3] instanceof Checkbox); // croissant
    assertTrue(boutons[4] instanceof Checkbox); // decroissant

    JTextField saisie = (JTextField) subSubComponents[1];
    saisie.setText("you");

    Point location = boutons[1].getLocationOnScreen();
    mouseMoveAndClick(
        location.x + (boutons[1].getWidth() / 2), location.y + (boutons[1].getHeight() / 2));

    JLabel res = (JLabel) subSubComponents[0];
    assertTrue(res.getText().endsWith("true"), "\"you\" doit être présent, pour ce test");

    saisie = (JTextField) subSubComponents[1];
    saisie.setText("you");

    location = boutons[0].getLocationOnScreen();
    mouseMoveAndClick(
        location.x + (boutons[0].getWidth() / 2), location.y + (boutons[0].getHeight() / 2));

    res = (JLabel) subSubComponents[0];
    assertTrue(res.getText().endsWith("false"), "retrait est-il inopérant ??? ");

    location = boutons[1].getLocationOnScreen();
    mouseMoveAndClick(
        location.x + (boutons[1].getWidth() / 2), location.y + (boutons[1].getHeight() / 2));

    res = (JLabel) subSubComponents[0];
    assertTrue(res.getText().endsWith("false"));
  }

  @Test
  public void test_IHMListe_retirer_et_occurrence() {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 1);

    // 		// la bonne IHM
    assertTrue(components[0] instanceof JPanel);
    Component[] subComponents = ((JPanel) components[0]).getComponents();

    assertTrue(subComponents[0] instanceof JPanel); // cmd
    assertTrue(subComponents[1] instanceof JTextArea); // texte

    Component[] subSubComponents = ((JPanel) subComponents[0]).getComponents();
    assertTrue(subSubComponents[0] instanceof JLabel); // afficheur
    assertTrue(subSubComponents[1] instanceof JTextField); // saisie
    assertTrue(subSubComponents[2] instanceof JPanel); // boutons

    Component[] boutons = ((JPanel) subSubComponents[2]).getComponents();
    assertTrue(boutons[0] instanceof JButton); // boutonRechercher
    JButton rechercher = ((JButton) boutons[0]);
    assertTrue(boutons[1] instanceof JButton); // boutonRetirer
    JButton retirer = ((JButton) boutons[1]);
    assertTrue(boutons[2] instanceof JLabel); //
    assertTrue(boutons[3] instanceof Checkbox); // croissant
    assertTrue(boutons[4] instanceof Checkbox); // decroissant
    assertTrue(boutons[5] instanceof JButton); // occurrence
    JButton occurrence = ((JButton) boutons[5]);

    JTextField saisie = (JTextField) subSubComponents[1];
    saisie.setText("lin");

    //  	  location = boutons[1].getLocationOnScreen();
    //
    //	mouseMoveAndClick(location.x+(boutons[1].getWidth()/2),location.y+(boutons[1].getHeight()/2));

    retirer.doClick();
    robot.delay(300);

    JLabel res = (JLabel) subSubComponents[0];
    assertTrue(
        res.getText().endsWith("true"),
        " ce qui commence par \"lin\" doit être présent, pour ce test");

    saisie = (JTextField) subSubComponents[1];
    saisie.setText("linked");

    //  	  location = boutons[0].getLocationOnScreen();
    //
    //	mouseMoveAndClick(location.x+(boutons[0].getWidth()/2),location.y+(boutons[0].getHeight()/2));
    rechercher.doClick();
    robot.delay(300);

    res = (JLabel) subSubComponents[0];
    assertTrue(res.getText().endsWith("false"), "retrait est-il inopérant ??? ");

    //  	  location = boutons[1].getLocationOnScreen();
    //
    //	mouseMoveAndClick(location.x+(boutons[1].getWidth()/2),location.y+(boutons[1].getHeight()/2));
    retirer.doClick();
    robot.delay(300);
    res = (JLabel) subSubComponents[0];
    assertTrue(res.getText().endsWith("false"));

    saisie.setText("linked");
    // location = boutons[5].getLocationOnScreen();
    // mouseMoveAndClick(location.x+(boutons[5].getWidth()/2),location.y+(boutons[5].getHeight()/2));
    occurrence.doClick();
    robot.delay(300);

    res = (JLabel) subSubComponents[0];
    Scanner sc = new Scanner(res.getText());
    assertEquals("-->", sc.next(), "--> est attendu ???");
    try {
      int result = sc.nextInt();
      assertTrue(
          result == 0, " Nombre d'occurrence de linked est-il erroné ??? (0 après un retrait)");
    } catch (java.util.InputMismatchException e) {
      fail("L'affichage du nombre d'occurrence doit être de 0 après un retrait effectif");
    }
  }

  @Test
  public void test_IHMListe_retirer_et_occurrence_bis() {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 1);

    // 		// la bonne IHM
    assertTrue(components[0] instanceof JPanel);
    Component[] subComponents = ((JPanel) components[0]).getComponents();

    assertTrue(subComponents[0] instanceof JPanel); // cmd
    assertTrue(subComponents[1] instanceof JTextArea); // texte

    Component[] subSubComponents = ((JPanel) subComponents[0]).getComponents();
    assertTrue(subSubComponents[0] instanceof JLabel); // afficheur
    assertTrue(subSubComponents[1] instanceof JTextField); // saisie
    assertTrue(subSubComponents[2] instanceof JPanel); // boutons

    Component[] boutons = ((JPanel) subSubComponents[2]).getComponents();
    assertTrue(boutons[0] instanceof JButton); // boutonRechercher
    JButton rechercher = ((JButton) boutons[0]);
    assertTrue(boutons[1] instanceof JButton); // boutonRetirer
    JButton retirer = ((JButton) boutons[1]);
    assertTrue(boutons[2] instanceof JLabel); //
    assertTrue(boutons[3] instanceof Checkbox); // croissant
    assertTrue(boutons[4] instanceof Checkbox); // decroissant
    assertTrue(boutons[5] instanceof JButton); // occurrence
    JButton occurrence = ((JButton) boutons[5]);

    JTextField saisie = (JTextField) subSubComponents[1];
    saisie.setText("java");

    //  	  location = boutons[1].getLocationOnScreen();
    //
    //	mouseMoveAndClick(location.x+(boutons[1].getWidth()/2),location.y+(boutons[1].getHeight()/2));

    retirer.doClick();
    robot.delay(300);

    JLabel res = (JLabel) subSubComponents[0];
    assertTrue(res.getText().endsWith("true"), " le retrait d'un mot présent a échoué ???");

    saisie = (JTextField) subSubComponents[1];
    saisie.setText("java");

    //  	  location = boutons[0].getLocationOnScreen();
    //
    //	mouseMoveAndClick(location.x+(boutons[0].getWidth()/2),location.y+(boutons[0].getHeight()/2));
    rechercher.doClick();
    robot.delay(300);

    res = (JLabel) subSubComponents[0];
    assertTrue(res.getText().endsWith("false"), "le retrait est-il inopérant ??? ");

    //  	  location = boutons[1].getLocationOnScreen();
    //
    //	mouseMoveAndClick(location.x+(boutons[1].getWidth()/2),location.y+(boutons[1].getHeight()/2));
    retirer.doClick();
    robot.delay(300);
    res = (JLabel) subSubComponents[0];
    assertTrue(res.getText().endsWith("false"));

    saisie.setText("java");
    // location = boutons[5].getLocationOnScreen();
    // mouseMoveAndClick(location.x+(boutons[5].getWidth()/2),location.y+(boutons[5].getHeight()/2));
    occurrence.doClick();
    robot.delay(300);

    res = (JLabel) subSubComponents[0];
    Scanner sc = new Scanner(res.getText());
    assertEquals("-->", sc.next(), "--> est attendu ???");
    try {
      int result = sc.nextInt();
      assertTrue(
          result == 0, " Nombre d'occurrence de linked est-il erroné ??? (0 après un retrait)");
    } catch (java.util.InputMismatchException e) {
      fail("L'affichage du nombre d'occurrence doit être de 0 après un retrait effectif");
    }
  }

  @Test
  public void test_IHMListe_retirer_et_occurrence_ter() {
    Container panel = f.getContentPane();
    Component[] components = panel.getComponents();
    assertEquals(components.length, 1);

    // 		// la bonne IHM
    assertTrue(components[0] instanceof JPanel);
    Component[] subComponents = ((JPanel) components[0]).getComponents();

    assertTrue(subComponents[0] instanceof JPanel); // cmd
    assertTrue(subComponents[1] instanceof JTextArea); // texte

    Component[] subSubComponents = ((JPanel) subComponents[0]).getComponents();
    assertTrue(subSubComponents[0] instanceof JLabel); // afficheur
    assertTrue(subSubComponents[1] instanceof JTextField); // saisie
    assertTrue(subSubComponents[2] instanceof JPanel); // boutons

    Component[] boutons = ((JPanel) subSubComponents[2]).getComponents();
    assertTrue(boutons[0] instanceof JButton); // boutonRechercher
    JButton rechercher = ((JButton) boutons[0]);
    assertTrue(boutons[1] instanceof JButton); // boutonRetirer
    JButton retirer = ((JButton) boutons[1]);
    assertTrue(boutons[2] instanceof JLabel); //
    assertTrue(boutons[3] instanceof Checkbox); // croissant
    assertTrue(boutons[4] instanceof Checkbox); // decroissant
    assertTrue(boutons[5] instanceof JButton); // occurrence
    JButton occurrence = ((JButton) boutons[5]);

    JTextField saisie = (JTextField) subSubComponents[1];
    saisie.setText("scala");

    //  	  location = boutons[1].getLocationOnScreen();
    //
    //	mouseMoveAndClick(location.x+(boutons[1].getWidth()/2),location.y+(boutons[1].getHeight()/2));

    retirer.doClick();
    robot.delay(300);

    JLabel res = (JLabel) subSubComponents[0];
    assertTrue(
        res.getText().endsWith("false"), " le retrait d'un mot absent de la liste initiale ???");

    saisie = (JTextField) subSubComponents[1];
    saisie.setText("scala");

    //  	  location = boutons[0].getLocationOnScreen();
    //
    //	mouseMoveAndClick(location.x+(boutons[0].getWidth()/2),location.y+(boutons[0].getHeight()/2));
    rechercher.doClick();
    robot.delay(300);

    res = (JLabel) subSubComponents[0];
    assertTrue(res.getText().endsWith("false"), "le retrait est-il inopérant ??? ");

    //  	  location = boutons[1].getLocationOnScreen();
    //
    //	mouseMoveAndClick(location.x+(boutons[1].getWidth()/2),location.y+(boutons[1].getHeight()/2));
    retirer.doClick();
    robot.delay(300);
    res = (JLabel) subSubComponents[0];
    assertTrue(res.getText().endsWith("false"));

    saisie.setText("scala");
    // location = boutons[5].getLocationOnScreen();
    // mouseMoveAndClick(location.x+(boutons[5].getWidth()/2),location.y+(boutons[5].getHeight()/2));
    occurrence.doClick();
    robot.delay(300);

    res = (JLabel) subSubComponents[0];
    Scanner sc = new Scanner(res.getText());
    assertEquals("-->", sc.next(), "--> est attendu ???");
    try {
      int result = sc.nextInt();
      assertTrue(
          result == 0,
          " Nombre d'occurrence de scala est-il erroné ??? (??? après un retrait inopérant)");
    } catch (java.util.InputMismatchException e) {
      assertEquals(
          "???",
          sc.next(),
          "L'affichage du nombre d'occurrence doit être ??? d'un mot absent de la liste");
    }
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
        robot.delay(10);
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
    robot.delay(160);
  } // end mouseMoveAndClickClick
}
