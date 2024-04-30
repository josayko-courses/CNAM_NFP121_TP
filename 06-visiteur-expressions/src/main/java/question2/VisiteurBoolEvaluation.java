package question2;

import question1.VisiteurExpression;

public class VisiteurBoolEvaluation extends VisiteurExpressionBooleenne<Boolean> {
  private VisiteurExpression<Integer> ve;

  public VisiteurBoolEvaluation(VisiteurExpression<Integer> ve) {
    this.ve = ve;
  }

  public Boolean visite(Vrai v) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public Boolean visite(Faux f) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public Boolean visite(Non n) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public Boolean visite(Ou ou) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public Boolean visite(Et et) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public Boolean visite(Sup sup) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public Boolean visite(Egal eg) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public Boolean visite(Inf inf) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }
}
