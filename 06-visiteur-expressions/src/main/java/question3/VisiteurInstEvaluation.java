package question3;

import question1.*;
import question2.*;

/** Visisteur d'instruction, chaque classe concrète possède une implémentation de la visite */
public class VisiteurInstEvaluation extends VisiteurInstruction<Contexte> {

  private VisiteurExpression<Integer> vi;
  private VisiteurExpressionBooleenne<Boolean> vb;

  /**
   * Création d'un visiteur d'instructions
   *
   * @param vi le visiteur d'expressions arithmétiques
   * @param vb le visiteur d'expression booléennes
   */
  public VisiteurInstEvaluation(
      VisiteurExpression<Integer> vi, VisiteurExpressionBooleenne<Boolean> vb) {
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
   * <p>l'affectation, X = Exp, Exp est une expression arithmétique <br>
   * <code>
   *
   *     M,Exp -interprete-> N       <br>
   * ________________________________<br>
   * M, X = Exp -interprete-> M[X]=N <br>
   * </code>
   *
   * @param a une affectation
   * @return la mémoire résultante
   */
  public Contexte visite(Affectation a) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  /**
   * Visite d'une séquence seq(I1,I2) <br>
   * <code>
   *
   *    M,I1 -interprete-> M1        <br>
   *    M1,I2 -interprete-> M2       <br>
   * ________________________________<br>
   *    M,seq(I,I2)-interprete-> M2  <br>
   * </code>
   *
   * @param seq une séquence
   * @return la mémoire résultante
   */
  public Contexte visite(Sequence seq) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  /**
   * Visiste d'une sélection si (Bexp) alors I1 sinon I2 fsi <br>
   * ou si (Bexp) alors I1 fsi <br>
   * <code>
   *
   *      M,Bexp-interprete-> vrai             <br>
   *      M,I1 -interprete-> M1                <br>
   * _________________________________________ <br>
   * M,si(Bexp)alorsI1 sinon I2-interprete-> M1<br>
   *
   *      M,Bexp -interprete-> faux            <br>
   *      M,I2 -interprete-> M2                <br>
   * __________________________________________<br>
   * M,si(Bexp)alorsI1 sinon I2-interprete->M2 <br>
   * </code>
   *
   * @param sel une sélection
   * @return la mémoire résultante
   */
  public Contexte visite(Selection sel) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  /**
   * La boucle tantque(Bexp,I1)<br>
   * <code>
   *
   *     M,Bexp -interprete->faux              <br>
   * _________________________________________ <br>
   * M,tantque(Bexp) faire I1-interprete-> M   <br>
   * <br>
   * M,Bexp -interprete-> vrai                          <br>
   * M,seq(I1,tantque(Bexp)faire I1)-interprete-> M1    <br>
   * ___________________________________________________<br>
   * M,tantque(Bexp)faire I1-interprete-> M1
   * </code>
   *
   * @param tq une itération de la classe tantque
   * @return la mémoire résultante
   */
  public Contexte visite(TantQue tq) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  /**
   * La boucle pour(init,Bexp,inc)I1<br>
   * <code>
   * M,seq(init,tantque(Bexp)seq(I1,inc))-visite->faux <br>
   * _____________________________________________________ <br>
   * M,pour(init,Bexp,inc) faire I1-visite-> M         <br>
   * </code>
   *
   * @param pour une itération de la classe Pour
   * @return la mémoire résultante
   */
  public Contexte visite(Pour pour) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  /**
   * L'affichage<br>
   * <code>
   *
   *    M,Exp-visite-> n                              <br>
   * ________________________________System.out.println(n)<br>
   * M,afficher(Exp)-visite-> M
   * </code>
   *
   * @param a une instruction d'affichage d'une expression arithmétique
   * @return la mémoire intacte
   */
  public Contexte visite(Afficher a) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  /**
   * Une assertion, si celle-ci échoue une exception est levée, emploi de la clause <a
   * href=http://java.sun.com/j2se/1.5.0/docs/guide/language/assert.html>assert</a> Expr ;
   *
   * @param a une assertion
   * @return la mémoire intacte
   * @throws une exception AssertionError
   */
  public Contexte visite(Assertion a) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  /**
   * Un appel d'une méthode statiques de Java,
   *
   * @param a undescription d'un appel de java, nom, et paramètres (: des entiers)
   * @return la mémoire éventuellement modifiée ?
   */
  public Contexte visite(AppelJava a) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public Contexte visite(Vide v) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }
}
