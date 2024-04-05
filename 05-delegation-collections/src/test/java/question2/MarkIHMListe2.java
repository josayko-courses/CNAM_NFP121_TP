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

public class MarkIHMListe2 {

  private JFrame f;
  private Robot robot;

  /**
   * Met en place les engagements.
   *
   * <p>Méthode appelée avant chaque appel de méthode de test.
   */
  @BeforeEach
  public void setUp() throws java.lang.Exception {

    f = new IHMListe2();

    f.pack();
    f.setVisible(true);
    while (!(f.isShowing())) {}
    f.setAlwaysOnTop(true);
    f.setLocation(500, 500);

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
    System.gc();
    robot.delay(200);
  }

  @Test
  public void test_IHMListe2_tri_annuler() {
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
    assertTrue(boutons[6] instanceof JButton); // annuler

    JTextArea tavant = (JTextArea) subComponents[1];
    String avant = tavant.getText();
    // System.out.println(avant);

    // tri en ordre croissant
    Point location = boutons[3].getLocationOnScreen();
    mouseMoveAndClick(
        location.x + (boutons[3].getWidth() / 2), location.y + (boutons[3].getHeight() / 2));
    robot.delay(300);

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

      //     location = boutons[6].getLocationOnScreen();
      //
      // mouseMoveAndClick(location.x+(boutons[6].getWidth()/2),location.y+(boutons[6].getHeight()/2));
      JButton annuler = (JButton) boutons[6];
      annuler.doClick();
      robot.delay(300);

      JTextArea tapres = (JTextArea) subComponents[1];
      if (!(avant.equals(tapres.getText()))) {
        fail("le texte initial n'est pas restitué ???");
      }

    } catch (NoSuchElementException e) {
      fail("Exception pas de texte ??, IHMListe2_tri ?? ");
    }

    // tri en ordre decroissant
    //  	  location = boutons[4].getLocationOnScreen();
    //
    //	mouseMoveAndClick(location.x+(boutons[4].getWidth()/2),location.y+(boutons[4].getHeight()/2));
    //

    //     ta = (JTextArea)subComponents[1];
    //     st = new StringTokenizer( ta.getText(),", []");
    //
    //     assertTrue(st.nextToken().compareTo(st.nextToken())>=0,"tri en ordre decroissant ?? ");
    //     assertTrue(st.nextToken().compareTo(st.nextToken())>=0,"tri en ordre decroissant ?? ");
    //     assertTrue(st.nextToken().compareTo(st.nextToken())>=0,"tri en ordre decroissant ?? ");
    //     assertTrue(st.nextToken().compareTo(st.nextToken())>=0,"tri en ordre decroissant ?? ");
    //     assertTrue(st.nextToken().compareTo(st.nextToken())>=0,"tri en ordre decroissant ?? ");
    //     assertTrue(st.nextToken().compareTo(st.nextToken())>=0,"tri en ordre decroissant ?? ");
  }

  @Test
  public void test_IHMListe2_tri_annulerBis() {
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
    assertTrue(boutons[6] instanceof JButton); // annuler

    JTextArea tavant = (JTextArea) subComponents[1];
    String avant = tavant.getText();
    // System.out.println(avant);
    robot.delay(300);
    // tri en ordre decroissant
    Point location = boutons[4].getLocationOnScreen();
    mouseMoveAndClick(
        location.x + (boutons[4].getWidth() / 2), location.y + (boutons[4].getHeight() / 2));

    JTextArea ta = (JTextArea) subComponents[1];
    StringTokenizer st = new StringTokenizer(ta.getText(), ", []");

    assertTrue(st.nextToken().compareTo(st.nextToken()) >= 0, "tri en ordre decroissant ?? ");
    assertTrue(st.nextToken().compareTo(st.nextToken()) >= 0, "tri en ordre decroissant ?? ");
    assertTrue(st.nextToken().compareTo(st.nextToken()) >= 0, "tri en ordre decroissant ?? ");
    assertTrue(st.nextToken().compareTo(st.nextToken()) >= 0, "tri en ordre decroissant ?? ");
    assertTrue(st.nextToken().compareTo(st.nextToken()) >= 0, "tri en ordre decroissant ?? ");
    assertTrue(st.nextToken().compareTo(st.nextToken()) >= 0, "tri en ordre decroissant ?? ");

    //     location = boutons[6].getLocationOnScreen();
    //
    // mouseMoveAndClick(location.x+(boutons[6].getWidth()/2),location.y+(boutons[6].getHeight()/2));
    JButton annuler = (JButton) boutons[6];
    annuler.doClick();
    robot.delay(300);
    JTextArea tapres = (JTextArea) subComponents[1];
    if (!(avant.equals(tapres.getText()))) {
      fail(" annuler ne restitue pas le texte initial ???");
    }
  }

  @Test
  public void test_IHMListe2_tri_annuler_2_fois() {
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
    assertTrue(boutons[6] instanceof JButton); // annuler

    JTextArea tavant = (JTextArea) subComponents[1];
    String avant = tavant.getText();
    // System.out.println(avant);

    // tri en ordre croissant
    Point location = boutons[3].getLocationOnScreen();
    mouseMoveAndClick(
        location.x + (boutons[3].getWidth() / 2), location.y + (boutons[3].getHeight() / 2));
    robot.delay(300);
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

    } catch (NoSuchElementException e) {
      fail("Exception pas de texte ??, IHMListe2_tri ?? ");
    }

    // tri en ordre decroissant
    location = boutons[4].getLocationOnScreen();
    mouseMoveAndClick(
        location.x + (boutons[4].getWidth() / 2), location.y + (boutons[4].getHeight() / 2));

    ta = (JTextArea) subComponents[1];
    st = new StringTokenizer(ta.getText(), ", []");

    assertTrue(st.nextToken().compareTo(st.nextToken()) >= 0, "tri en ordre decroissant ?? ");
    assertTrue(st.nextToken().compareTo(st.nextToken()) >= 0, "tri en ordre decroissant ?? ");
    assertTrue(st.nextToken().compareTo(st.nextToken()) >= 0, "tri en ordre decroissant ?? ");
    assertTrue(st.nextToken().compareTo(st.nextToken()) >= 0, "tri en ordre decroissant ?? ");
    assertTrue(st.nextToken().compareTo(st.nextToken()) >= 0, "tri en ordre decroissant ?? ");
    assertTrue(st.nextToken().compareTo(st.nextToken()) >= 0, "tri en ordre decroissant ?? ");

    //     location = boutons[6].getLocationOnScreen();
    //
    // mouseMoveAndClick(location.x+(boutons[6].getWidth()/2),location.y+(boutons[6].getHeight()/2));
    //     location = boutons[6].getLocationOnScreen();
    //
    // mouseMoveAndClick(location.x+(boutons[6].getWidth()/2),location.y+(boutons[6].getHeight()/2));
    JButton annuler = (JButton) boutons[6];
    annuler.doClick();
    robot.delay(300);
    annuler.doClick();

    robot.delay(300);
    JTextArea tapres = (JTextArea) subComponents[1];
    if (!(avant.equals(tapres.getText()))) {
      fail(" annuler 2 fois, ne semble pas fonctionner ???");
    }
  }

  //
  @Test
  public void test_IHMListe2_retirer_annuler() {
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
    assertTrue(boutons[6] instanceof JButton); // annuler

    JTextArea tavant = (JTextArea) subComponents[1];
    String avant = tavant.getText();

    JTextField saisie = (JTextField) subSubComponents[1];
    saisie.setText("you");

    //  	  Point location = boutons[1].getLocationOnScreen();
    //
    //	mouseMoveAndClick(location.x+(boutons[1].getWidth()/2),location.y+(boutons[1].getHeight()/2));
    JButton rechercher = (JButton) boutons[1];
    rechercher.doClick();
    robot.delay(300);

    JLabel res = (JLabel) subSubComponents[0];
    assertTrue(res.getText().endsWith("true"), "\"you\" doit être présent");

    saisie = (JTextField) subSubComponents[1];
    saisie.setText("you");

    //  	  location = boutons[1].getLocationOnScreen();
    //
    //	mouseMoveAndClick(location.x+(boutons[0].getWidth()/2),location.y+(boutons[0].getHeight()/2));
    rechercher.doClick();
    robot.delay(300);
    res = (JLabel) subSubComponents[0];
    assertTrue(res.getText().endsWith("false"), "retrait est-il inopérant ??? ");

    //  	  location = boutons[1].getLocationOnScreen();
    //
    //	mouseMoveAndClick(location.x+(boutons[1].getWidth()/2),location.y+(boutons[1].getHeight()/2));
    rechercher.doClick();
    robot.delay(300);
    res = (JLabel) subSubComponents[0];
    assertTrue(res.getText().endsWith("false"));

    //  	  location = boutons[6].getLocationOnScreen();
    //
    // mouseMoveAndClick(location.x+(boutons[6].getWidth()/2),location.y+(boutons[6].getHeight()/2));
    JButton annuler = (JButton) boutons[6];
    annuler.doClick();
    robot.delay(300);
    JTextArea tapres = (JTextArea) subComponents[1];
    if (!(avant.equals(tapres.getText()))) {
      fail(" après 2 retraits (un succès et un échec), annuler ne fonctionne pas ???");
    }
  }

  @Test
  public void test_IHMListe2_retirer_annuler_simple() {
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
    assertTrue(boutons[6] instanceof JButton); // annuler

    JTextArea tavant = (JTextArea) subComponents[1];
    String avant = tavant.getText();

    JTextField saisie = (JTextField) subSubComponents[1];
    saisie.setText("you");

    //  	  Point location = boutons[1].getLocationOnScreen();
    //
    //	mouseMoveAndClick(location.x+(boutons[1].getWidth()/2),location.y+(boutons[1].getHeight()/2));
    JButton b = (JButton) boutons[1];
    b.doClick();

    JLabel res = (JLabel) subSubComponents[0];
    assertTrue(res.getText().endsWith("true"), "\"you\" doit être présent");

    //  	  location = boutons[6].getLocationOnScreen();
    //
    // mouseMoveAndClick(location.x+(boutons[6].getWidth()/2),location.y+(boutons[6].getHeight()/2));
    b = (JButton) boutons[6];
    b.doClick();

    JTextArea tapres = (JTextArea) subComponents[1];
    if (!(avant.equals(tapres.getText()))) {
      fail(" après un retrait en succès, annuler ne fonctionne pas ???");
    }
  }

  //
  @Test
  public void test_IHMListe2_retirer_annulerBis() {
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
    assertTrue(boutons[6] instanceof JButton); // annuler

    // JTextArea tavant = (JTextArea) subComponents[1];
    // String avant = tavant.getText();
    // System.out.println(avant);

    JTextField saisie = (JTextField) subSubComponents[1];
    saisie.setText("Java");
    //  	  Point location = boutons[1].getLocationOnScreen();
    //
    //	mouseMoveAndClick(location.x+(boutons[1].getWidth()/2),location.y+(boutons[1].getHeight()/2));
    JButton b = (JButton) boutons[1];
    b.doClick();
    robot.delay(300);

    JLabel res = (JLabel) subSubComponents[0];
    assertTrue(res.getText().endsWith("true"), "\"Java\" doit être présent");
    saisie = (JTextField) subSubComponents[1];

    saisie.setText("Java");
    //  	  location = boutons[1].getLocationOnScreen();
    //
    //	mouseMoveAndClick(location.x+(boutons[0].getWidth()/2),location.y+(boutons[0].getHeight()/2));
    b.doClick();
    robot.delay(300);

    res = (JLabel) subSubComponents[0];
    assertTrue(res.getText().endsWith("false"), "retrait est-il inopérant ??? ");

    JTextArea ta = (JTextArea) subComponents[1];
    String av = ta.getText();

    // annuler
    //  	  location = boutons[6].getLocationOnScreen();
    //
    // mouseMoveAndClick(location.x+(boutons[6].getWidth()/2),location.y+(boutons[6].getHeight()/2));
    JButton annuler = (JButton) boutons[6];
    annuler.doClick();
    robot.delay(300);

    JTextArea tapres = (JTextArea) subComponents[1];
    //  	  System.out.println(av);
    //   	System.out.println(tapres.getText());

    if ((av.equals(tapres.getText()))) {
      fail(" retirer un élément absent engendre-t-il une sauvegarde, curieux ???");
    }
  }

  @Test
  public void test_IHMListe2_occurrence_annuler() {
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
    assertTrue(boutons[6] instanceof JButton); // annuler

    // Rechercher("you");
    JTextField saisie = (JTextField) subSubComponents[1];
    saisie.setText("you");

    // occurrence
    // Point location = boutons[5].getLocationOnScreen();
    // mouseMoveAndClick(location.x+(boutons[5].getWidth()/2),location.y+(boutons[5].getHeight()/2));
    JButton occurrence = (JButton) boutons[5];
    occurrence.doClick();
    robot.delay(300);
    JLabel res = (JLabel) subSubComponents[0];
    Scanner sc = new Scanner(res.getText());
    assertEquals("-->", sc.next(), "--> est attendu ???");
    try {
      int leNombre = sc.nextInt();
      assertEquals(36, leNombre, " Nombre d'occurrence est-il erroné ???");
    } catch (InputMismatchException ime) {
      fail("--> N, N : un entier est attendu ???");
    }

    // boutonRetirer
    //  	  location = boutons[1].getLocationOnScreen();
    //
    //	mouseMoveAndClick(location.x+(boutons[1].getWidth()/2),location.y+(boutons[1].getHeight()/2));
    JButton retirer = (JButton) boutons[1];
    retirer.doClick();
    robot.delay(300);
    res = (JLabel) subSubComponents[0];
    assertTrue(res.getText().endsWith("true"), "\"you\" doit être présent");

    // occurrence
    //  	  location = boutons[5].getLocationOnScreen();
    //
    // mouseMoveAndClick(location.x+(boutons[5].getWidth()/2),location.y+(boutons[5].getHeight()/2));
    occurrence.doClick();
    robot.delay(300);
    res = (JLabel) subSubComponents[0];
    sc.close();
    sc = new Scanner(res.getText());
    assertEquals("-->", sc.next(), "--> est attendu ???");
    assertEquals(0, sc.nextInt(), " Nombre d'occurrence doit être nul après un retrait ???");

    // annuler
    //    	location = boutons[6].getLocationOnScreen();
    //
    // mouseMoveAndClick(location.x+(boutons[6].getWidth()/2),location.y+(boutons[6].getHeight()/2));
    JButton annuler = (JButton) boutons[6];
    annuler.doClick();
    robot.delay(300);
    // occurrence
    // location = boutons[5].getLocationOnScreen();
    // mouseMoveAndClick(location.x+(boutons[5].getWidth()/2),location.y+(boutons[5].getHeight()/2));
    occurrence.doClick();
    robot.delay(300);
    res = (JLabel) subSubComponents[0];
    sc.close();
    sc = new Scanner(res.getText());
    assertEquals("-->", sc.next(), "--> est attendu ???");

    try {
      int result = sc.nextInt();
      assertTrue(
          result == 36,
          " le nombre d'occurrence est-il erroné ?, après l'annulation d'un retrait ???");
    } catch (java.util.InputMismatchException e) {
      fail("L'affichage du nombre d'occurrence doit être un nombre");
    }
  }

  @Test
  public void test_IHMListe2_occurrence_annuler_bis() {
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
    assertTrue(boutons[6] instanceof JButton); // annuler

    // Rechercher("there");
    JTextField saisie = (JTextField) subSubComponents[1];
    saisie.setText("there");

    // occurrence
    // Point location = boutons[5].getLocationOnScreen();
    // mouseMoveAndClick(location.x+(boutons[5].getWidth()/2),location.y+(boutons[5].getHeight()/2));
    JButton occurrence = (JButton) boutons[5];
    occurrence.doClick();
    robot.delay(300);
    JLabel res = (JLabel) subSubComponents[0];
    Scanner sc = new Scanner(res.getText());
    assertEquals("-->", sc.next(), "--> est attendu ???");
    try {
      int leNombre = sc.nextInt();
      assertEquals(7, leNombre, " Nombre d'occurrence est-il erroné ???");
    } catch (InputMismatchException ime) {
      fail("--> N, N : un entier est attendu ???");
    }

    // boutonRetirer
    //  	  location = boutons[1].getLocationOnScreen();
    //
    //	mouseMoveAndClick(location.x+(boutons[1].getWidth()/2),location.y+(boutons[1].getHeight()/2));
    JButton retirer = (JButton) boutons[1];
    retirer.doClick();
    robot.delay(300);
    res = (JLabel) subSubComponents[0];
    assertTrue(res.getText().endsWith("true"), "\"there\" doit être présent");

    // occurrence
    //  	  location = boutons[5].getLocationOnScreen();
    //
    // mouseMoveAndClick(location.x+(boutons[5].getWidth()/2),location.y+(boutons[5].getHeight()/2));
    occurrence.doClick();
    robot.delay(300);
    res = (JLabel) subSubComponents[0];
    sc.close();
    sc = new Scanner(res.getText());
    assertEquals("-->", sc.next(), "--> est attendu ???");
    assertEquals(0, sc.nextInt(), " Nombre d'occurrence doit être = 0 après un retrait ???");

    saisie = (JTextField) subSubComponents[1];
    saisie.setText("the");

    // occurrence
    // Point location = boutons[5].getLocationOnScreen();
    // mouseMoveAndClick(location.x+(boutons[5].getWidth()/2),location.y+(boutons[5].getHeight()/2));
    occurrence = (JButton) boutons[5];
    occurrence.doClick();
    robot.delay(300);

    res = (JLabel) subSubComponents[0];
    sc.close();
    sc = new Scanner(res.getText());
    assertEquals("-->", sc.next(), "--> est attendu ???");
    try {
      int leNombre = sc.nextInt();
      assertEquals(214, leNombre, " Nombre d'occurrence est-il erroné ???");
    } catch (InputMismatchException ime) {
      fail("--> N, N : un entier est attendu ???");
    }

    // annuler
    //    	location = boutons[6].getLocationOnScreen();
    //
    // mouseMoveAndClick(location.x+(boutons[6].getWidth()/2),location.y+(boutons[6].getHeight()/2));
    JButton annuler = (JButton) boutons[6];
    annuler.doClick();
    robot.delay(300);
    // occurrence
    // location = boutons[5].getLocationOnScreen();
    // mouseMoveAndClick(location.x+(boutons[5].getWidth()/2),location.y+(boutons[5].getHeight()/2));
    res = (JLabel) subSubComponents[0];
    sc.close();
    sc = new Scanner(res.getText());
    assertEquals("-->", sc.next(), "--> est attendu ???");
    try {
      int leNombre = sc.nextInt();
      assertEquals(214, leNombre, " Nombre d'occurrence est-il erroné ???");
    } catch (InputMismatchException ime) {
      fail("--> N, N : un entier est attendu ???");
    }

    saisie = (JTextField) subSubComponents[1];
    saisie.setText("there");

    // occurrence
    // Point location = boutons[5].getLocationOnScreen();
    // mouseMoveAndClick(location.x+(boutons[5].getWidth()/2),location.y+(boutons[5].getHeight()/2));
    occurrence = (JButton) boutons[5];
    occurrence.doClick();
    robot.delay(300);
    res = (JLabel) subSubComponents[0];
    sc.close();
    sc = new Scanner(res.getText());
    assertEquals("-->", sc.next(), "--> est attendu ???");
    try {
      int leNombre = sc.nextInt();
      assertEquals(7, leNombre, " Nombre d'occurrence est-il erroné ???");
    } catch (InputMismatchException ime) {
      fail("--> N, N : un entier est attendu ???");
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
    robot.delay(300);
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
