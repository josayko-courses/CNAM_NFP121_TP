package question3;

import java.text.Normalizer;

/**
 * NFP121 TpIntroduction, Classes, Junit, soumission git.
 *
 * @version février 2006
 * @author nfp121
 * @see "java.lang.String, java.lang.Math"
 */
public class AuditeurCNAM {
  private String nom;
  private String prenom;
  private String matricule;

  /**
   * Création d'un auditeur avec son nom et son prénom
   *
   * @param nom       le nom de l'auditeur
   * @param prénom    son prénom
   * @param matricule sur la carte d'inscription, près de la photo
   */
  public AuditeurCNAM(String nom, String prenom, String matricule) {
    this.nom = nom;
    this.prenom = prenom;
    this.matricule = matricule;
    // à compléter
  }

  /**
   * le login au Cnam : 6 premières lettres du nom suivi de la première lettre du
   * prénom séparées de
   * '_' voir les classes prédéfines, java.lang.String : les méthodes replaceAll,
   * toLowerCase et
   * substring java.lang.Math : la méthode min. Vous devez inférer le comportement
   * attendu grâce aux
   * tests.
   */

  public String login() {
    String shortenedName = this.nom.length() > 6
        ? this.nom.substring(0, 6)
        : this.nom;

    // Concatenate "_" and the first letter of prenom
    // Removes accents and diacritics with .normalize()
    // Replace unwanted code points from normalized result
    // Replace specials characters with "_" .replaceAll()
    String rawLogin = Normalizer.normalize(shortenedName + "_" + this.prenom.charAt(0), Normalizer.Form.NFKD)
        .replaceAll("\\p{M}", "")
        .replaceAll("[^a-zA-Z]", "_");
    return rawLogin.toLowerCase();
  }

  /**
   * lecture du nom de l'auditeur
   *
   * @return son nom
   */
  public String nom() {
    return this.nom;
  }

  /**
   * lecture du prénom de l'auditeur
   *
   * @return son prénom
   */
  public String prenom() {
    return this.prenom;
  }

  /**
   * lecture du matricule de l'auditeur
   *
   * @return son nom
   */
  public String matricule() {
    return this.matricule;
  }

  /**
   * méthode toString de la classe Object redéfinie
   *
   * @return la concaténation du nom et du prénom, séparés d'un 'blanc'
   */
  public String toString() {
    return nom() + " " + prenom() + " login : " + login();
  }
}
