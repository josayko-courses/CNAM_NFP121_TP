package question3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/* Cette classe contient les tests d'évaluation de votre TP, NE LA MODIFIEZ PAS */
public class MarkAuditeurCNAM {

  @BeforeEach
  public void setUp() { // throws java.lang.Exception
  }

  @AfterEach
  public void tearDown() {}

  @Test
  public void test_présence_fichiers() {
    try {
      Class.forName("question3.AuditeurCNAM");
    } catch (ClassNotFoundException e) {
      fail("classe absente " + e.getMessage());
    }
  }

  @Test
  public void test_du_nom() {
    question3.AuditeurCNAM auditeur1 = new question3.AuditeurCNAM("Dupont", "michel", "12345");
    assertEquals("Dupont", auditeur1.nom(), " nom ?? ");
  }

  @Test
  public void test_du_prénom() {
    question3.AuditeurCNAM auditeur1 = new question3.AuditeurCNAM("Dupont", "michel", "12345");
    assertEquals("michel", auditeur1.prenom(), " prénom ?? ");
  }

  @Test
  public void test_matricule() {
    question3.AuditeurCNAM auditeur1 = new question3.AuditeurCNAM("Dupont", "michel", "12345");
    assertEquals("12345", auditeur1.matricule(), " matricule ?? ");
  }

  @Test
  public void test_login() {
    question3.AuditeurCNAM auditeur1 = new question3.AuditeurCNAM("Dupont", "michel", "12345");
    assertEquals("dupont_m", auditeur1.login(), " login ?? ");
  }

  @Test
  public void test_toString() {
    question3.AuditeurCNAM auditeur1 = new question3.AuditeurCNAM("Dupont", "michel", "12345");
    assertEquals(
        "Dupont michel login : " + auditeur1.login(),
        auditeur1.toString(),
        " format :\"nom() prénom() login : login()");
  }

  @Test
  public void test_toString2() {
    question3.AuditeurCNAM auditeur2 = new question3.AuditeurCNAM("Dupont", "paul", "03-1234");
    assertEquals(
        "Dupont paul login : dupont_p", auditeur2.toString(), " nom() prénom() login : login()");
  }

  // 	public void test_nom_ou_prénom_ou_matricule_vide() {
  // 		question3.AuditeurCNAM auditeur1 = new question3.AuditeurCNAM("", "", "");
  // 		assertEquals("Mr Dupontelle michel ", "Dupontelle", auditeur1.nom());
  // 		assertEquals("Mr Dupontelle michel ", "michel", auditeur1.prénom());
  // 		assertEquals("Mr Dupontelle michel ", "dupont_m", auditeur1.login());
  // 	}
  //
  @Test
  public void test_nom_court() {
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
  public void test_nom_composé() {
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
    assertEquals(auditeur1.nom(), "Chloé");
    assertEquals("chloé", auditeur1.prenom(), "Mme Chloé chloé ");
    assertEquals("chloe_c", auditeur1.login(), " nom avec accent (é devient e) ? ");
  }

  @Test
  public void test_nom_avec_quote_et_accent() {
    question3.AuditeurCNAM auditeur1 = new question3.AuditeurCNAM("L'Ombre", "éric", "12345");
    assertEquals(auditeur1.nom(), "L'Ombre");
    assertEquals(auditeur1.prenom(), "éric");
    assertEquals("l_ombr_e", auditeur1.login(), "nom avec quote et accent ? (' devient _)");
  }

  @Test
  public void test_prénom_avec_accent() {
    question3.AuditeurCNAM auditeur1 = new question3.AuditeurCNAM("Pomme", "ève", "12345");
    assertEquals("Pomme", auditeur1.nom(), "Mme Pomme ève ");
    assertEquals("ève", auditeur1.prenom(), "Mme Pomme ève ");
    assertEquals("pomme_e", auditeur1.login(), " prénom avec accent (è devient e) ? ");
  }

  @Test
  public void test_nom_trés_court() {
    question3.AuditeurCNAM auditeur1 = new question3.AuditeurCNAM("PRO", "mylène", "12345");
    assertEquals("PRO", auditeur1.nom());
    assertEquals("mylène", auditeur1.prenom());
    assertEquals("pro_m", auditeur1.login(), "nom_court");
  }
}
