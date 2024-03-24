package question2;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Décrivez votre classe JButtonObserver ici.
 *
 * @author Jonny SAYKOSY
 * @version 2024.03.24
 */
public class JButtonObserver implements ActionListener  {
  private String nom;
  private TextArea contenu;

  /**
   * Constructeur d'objets de classe JButtonObserver
   *
   * @param nom le nom du bouton, jbo1, jbo2, jbo3, jmo1, jmo2, jmo3
   * @param contenu la zone de texte de l'applette
   */
  public JButtonObserver(String nom, TextArea contenu) {
    this.nom = nom;
    this.contenu = contenu;
  }

  /**
   * affichage d'un message dans la zone de texte ce message est de la forme observateur this.nom :
   * clic du bouton nom_du_bouton exemple : observateur jbo1 : clic du bouton A, voir la méthode
   * getActionCommand()
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    StringBuilder message = new StringBuilder();
    message
      .append("observateur ")
      .append(this.nom)
      .append(" : clic du bouton ")
      .append(e.getActionCommand());
    this.contenu.append(message.toString() + "\n");
  }
}
