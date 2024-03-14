package question3;

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

  // à compléter

  /**
   * Création d'un auditeur avec son nom et son prénom
   *
   * @param nom le nom de l'auditeur
   * @param prenom son prénom
   * @param matricule sur la carte d'inscription, près de la photo
   */
  public AuditeurCNAM(String nom, String prenom, String matricule) {
    this.nom = nom;
    this.prenom = prenom;
    this.matricule = matricule;
    // à compléter
  }

  /**
   * le login au Cnam : 6 premières lettres du nom suivi de la première lettre du prénom séparées de
   * '_' voir les classes prédéfines, java.lang.String : les méthodes replaceAll, toLowerCase et
   * substring java.lang.Math : la méthode min. Vous devez inférer le comportement attendu grâce aux
   * tests.
   */

  /* SOLUTION (rien) */
  /* Le tests impliquent que le login retourné est en minuscules, le trait d'union, ou spéciaux
  <i>(pour unix)</i> sont remplacés par des '_' et les caractères accentués sont désaccentués */
  /* FIN SOLUTION */
  public String login() {
    /* SOLUTION */
    String strNom =
        this.nom.replaceAll(" ", "_").replaceAll("-", "_").substring(0, Math.min(nom.length(), 6));
    strNom =
        strNom
            .replaceAll("é", "e")
            .replaceAll("è", "e")
            .replaceAll("î", "i")
            .replaceAll("ç", "c")
            .replaceAll("'", "_");

    String strPrénom =
        new String(this.prenom.charAt(0) + "")
            .replaceAll("é", "e")
            .replaceAll("è", "e")
            .replaceAll("ï", "i")
            .replaceAll("î", "i")
            .replaceAll("ç", "c");
    String res = strNom + "_" + strPrénom;
    return res.toLowerCase();
    /* FIN SOLUTION */
  }

  /**
   * lecture du nom de l'auditeur
   *
   * @return son nom
   */
  public String nom() {
    /* SOLUTION */
    return nom;
    /* FIN SOLUTION */
  }

  /**
   * lecture du prénom de l'auditeur
   *
   * @return son nom
   */
  public String prenom() {
    /* SOLUTION */
    return prenom;
    /* FIN SOLUTION */
  }

  /**
   * lecture du matricule de l'auditeur
   *
   * @return son nom
   */
  public String matricule() {
    /* SOLUTION */
    return matricule;
    /* FIN SOLUTION */
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
