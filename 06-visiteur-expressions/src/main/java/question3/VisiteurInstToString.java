package question3;

import question1.*;
import question2.*;

/** Visisteur d'instruction, chaque classe concrète possède une implémentation de la visite */
public class VisiteurInstToString extends VisiteurInstruction<String> {

  private VisiteurExpression<String> vi;
  private VisiteurExpressionBooleenne<String> vb;

  /**
   * Création d'un visiteur d'instructions
   *
   * @param vi le visiteur d'expressions arithmétiques
   * @param vb le visiteur d'expression booléennes
   */
  public VisiteurInstToString(
      VisiteurExpression<String> vi, VisiteurExpressionBooleenne<String> vb) {
    this.vi = vi;
    this.vb = vb;
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

  public String visite(Vide v) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public String visite(AppelJava a) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }
}
