package question2;

import question1.VisiteurExpression;

public class VisiteurBoolToJava extends VisiteurExpressionBooleenne<String> {
  private VisiteurExpression<String> ve;

  public VisiteurBoolToJava(VisiteurExpression<String> ve) {
    this.ve = ve;
  }

  public String visite(Vrai v) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public String visite(Faux f) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public String visite(Non n) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public String visite(Ou ou) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public String visite(Et et) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public String visite(Sup sup) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public String visite(Egal eg) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public String visite(Inf inf) {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }
}
