package question2;

import question1.VisiteurExpression;

public class VisiteurBoolToString extends VisiteurExpressionBooleenne<String> {
  private VisiteurExpression<String> ve;

  public VisiteurBoolToString(VisiteurExpression<String> ve) {
    this.ve = ve;
  }

  public String visite(Vrai v) {
    return "TODO"; 
  }

  public String visite(Faux f) {
    return "TODO"; 
  }

  public String visite(Non n) {
    return "TODO"; 
  }

  public String visite(Ou ou) {
    return "TODO"; 
  }

  public String visite(Et et) {
    return "TODO"; 
  }

  public String visite(Sup sup) {
    return "TODO"; 
  }

  public String visite(Egal eg) {
    return "TODO"; 
  }

  public String visite(Inf inf) {
    return "TODO"; 
  }
}
