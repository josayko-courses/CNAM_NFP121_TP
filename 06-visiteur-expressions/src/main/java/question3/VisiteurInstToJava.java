package question3;

import question1.*;
import question2.*;

/** */
public class VisiteurInstToJava extends VisiteurInstruction<String> {
  private static final int TAB = 2;
  private static final String lineSeparator = System.getProperties().getProperty("line.separator");

  private VisiteurExpression<String> vi;
  private VisiteurExpressionBooleenne<String> vb;

  // Pour gérer l'indentation dans le code généré. Vous pouvez l'ignorer.
  private int tabulations;

  /**
   * Création d'un visiteur d'instructions
   *
   * @param vi le visiteur d'expressions arithmétiques
   * @param vb le visiteur d'expression booléennes
   * @param tabulations tabulations initiales
   */
  public VisiteurInstToJava(
      VisiteurExpression<String> vi, VisiteurExpressionBooleenne<String> vb, int tabulations) {
    this.vi = vi;
    this.vb = vb;
    this.tabulations = tabulations;
  }

  /**
   * Création d'un visiteur d'instructions
   *
   * @param vi le visiteur d'expressions arithmétiques
   * @param vb le visiteur d'expression booléennes
   */
  public VisiteurInstToJava(VisiteurExpression<String> vi, VisiteurExpressionBooleenne<String> vb) {
    this.vi = vi;
    this.vb = vb;
    this.tabulations = 0;
  }

  /**
   * obtention du contexte, ici celui du visiteur d'expression arithmétiques
   *
   * @return le contexte ici de vi(le visiteur d'expression)
   */
  public Contexte contexte() {
    return this.vi.contexte();
  }


  /**
   * Visite d'une instance de la classe Affectation.
   *
   * @param a une affectation
   * @return a := exp
   */
  public String visite(Affectation a) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  /**
   * Visiste d'une séquence seq(I1,I2) <br>
   *
   * @param seq une séquence
   * @return i1;i2
   */
  public String visite(Sequence seq) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public String visite(Selection sel) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public String visite(TantQue tq) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public String visite(Pour pour) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public String visite(Afficher a) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public String visite(Assertion a) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public String visite(AppelJava a) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public String visite(Vide v) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }
}
