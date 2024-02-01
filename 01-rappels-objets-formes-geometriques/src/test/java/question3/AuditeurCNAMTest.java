package question3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe-test AuditeurCNAMTest.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class AuditeurCNAMTest {
  // Définissez ici les variables d'instance nécessaires à vos engagements (fixtures);

  /** Constructeur de la classe-test AuditeurCNAMTest. */
  public AuditeurCNAMTest() {}

  /**
   * Met en place les engagements.
   *
   * <p>Méthode appelée avant chaque appel de méthode de test.
   */
  @BeforeEach
  protected void setUp() { // throws java.lang.Exception
    // Initialisez ici vos engagements
  }

  /**
   * Supprime les engagements
   *
   * <p>Méthode appelée après chaque appel de méthode de test.
   */
  @AfterEach
  protected void tearDown() { // throws java.lang.Exception
    // Libérez ici les ressources engagées par setUp()
  }

  /*
   * Il ne vous reste plus qu'à définir une ou plusieurs méthodes de test. Ces
   * méthodes doivent vérifier les résultats attendus à l'aide d'assertions
   * assertXXX. Par convention, leurs noms devraient débuter par
   * "test". Vous pouvez ébaucher le corps grace au menu contextuel
   * "Enregistrer une méthode de test".
   */

  /** Un test de la méthode toString(). */
  @Test
  public void test_toString() {
    question3.AuditeurCNAM auditeur1 = new question3.AuditeurCNAM("Dupont", "paul", "03-1234");
    assertEquals(auditeur1.toString(), "Dupont paul login : dupont_p");
  }

  @Test
  public void test_nom_court() {
    question3.AuditeurCNAM auditeur1 = new question3.AuditeurCNAM("paul", "pierre", "12345");
    assertEquals("paul", auditeur1.nom());
    assertEquals("pierre", auditeur1.prenom());
    assertEquals("paul_p", auditeur1.login());
  }

  @Test
  public void test_nom_court_bis() {
    question3.AuditeurCNAM auditeur1 = new question3.AuditeurCNAM("thon", "germon", "12345");
    assertEquals("thon", auditeur1.nom(), "Mr thon germon");
    assertEquals("germon", auditeur1.prenom(), "Mr thon germon");
    assertEquals("thon_g", auditeur1.login(), "Mr thon germon");
  }

  @Test
  public void test_nom_avec_particule() {
    question3.AuditeurCNAM auditeur1 = new question3.AuditeurCNAM("le Thon", "alban", "12345");
    assertEquals("le Thon", auditeur1.nom(), "Mr le Thon albacore ");
    assertEquals("alban", auditeur1.prenom(), "Mr le Thon albacore ");
    assertEquals("12345", auditeur1.matricule(), " matricule ?");
    assertEquals("le_tho_a", auditeur1.login(), " login ? ");
  }

  @Test
  public void test_nom_compose() {
    question3.AuditeurCNAM auditeur1 = new question3.AuditeurCNAM("Ton-Ton", "max", "12345");
    assertEquals("Ton-Ton", auditeur1.nom(), "Mr Ton-Ton max ");
    assertEquals("max", auditeur1.prenom(), "Mr Ton-Ton max ");
    assertEquals("ton_to_m", auditeur1.login(), "Mr Ton-Ton max ");
  }

  @Test
  public void test_nom_court_avec_particule() {
    question3.AuditeurCNAM auditeur1 = new question3.AuditeurCNAM("Te-Te", "max", "12345");
    assertEquals("Te-Te", auditeur1.nom(), "Mr Te-Te max ");
    assertEquals("max", auditeur1.prenom(), "Mr Te-Te max ");
    assertEquals("te_te_m", auditeur1.login(), "nom court avec particules ? ");
  }

  @Test
  public void test_nom_avec_accent() {
    question3.AuditeurCNAM auditeur1 = new question3.AuditeurCNAM("Chloé", "chloé", "12345");
    assertEquals("Chloé", auditeur1.nom(), "Mme Chloé chloé ");
    assertEquals("chloé", auditeur1.prenom(), "Mme Chloé chloé ");
    assertEquals("chloe_c", auditeur1.login(), " nom avec accent (é devient e) ? ");
  }

  // Complétez

}
