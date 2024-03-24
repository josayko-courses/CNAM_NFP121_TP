package question2;

import java.awt.TextArea;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Décrivez votre classe JButtonObserver ici.
 *
 * @author Jonny SAYKOSY
 * @version 2024.03.24
 */
public class JMouseObserver implements MouseListener {
  private String nom;
  private TextArea contenu;

  /** Constructeur d'objets de classe JButtonObserver */
  public JMouseObserver(String nom, TextArea contenu) {
    this.nom = nom;
    this.contenu = contenu;
  }

  public void mouseClicked(MouseEvent e) {}

  /**
   * affichage d'un message dans la zone de texte ce message est de la forme observateur this.nom :
   * souris entrée en (X,Y) exemple : observateur jmo1 : souris entrée en (15,20)
   */
  public void mouseEntered(MouseEvent e) {
    StringBuilder message = new StringBuilder(); 
    message
      .append(this.nom)
      .append(" : souris entrée en (")
      .append(e.getX())
      .append(",")
      .append(e.getY())
      .append(")");
    contenu.append(message.toString() + "\n"); 
  }

  public void mouseExited(MouseEvent e) {}

  public void mousePressed(MouseEvent e) {}

  public void mouseReleased(MouseEvent e) {}
}
