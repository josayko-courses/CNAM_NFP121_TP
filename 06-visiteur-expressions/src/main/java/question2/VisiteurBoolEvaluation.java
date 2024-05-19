package question2;

import question1.VisiteurExpression;

public class VisiteurBoolEvaluation extends VisiteurExpressionBooleenne<Boolean> {
  private VisiteurExpression<Integer> ve;

  public VisiteurBoolEvaluation(VisiteurExpression<Integer> ve) {
    this.ve = ve;
  }

  public Boolean visite(Vrai v) {
    return true;
  }

  public Boolean visite(Faux f) {
    return false;
  }

  public Boolean visite(Non n) {
    return !n.bop().accepter(this);
  }

  public Boolean visite(Ou ou) {
    return ou.bop1().accepter(this) || ou.bop2().accepter(this);
  }

  public Boolean visite(Et et) {
    return et.bop1().accepter(this) && et.bop2().accepter(this);
  }

  public Boolean visite(Sup sup) {
    Integer exp1 = sup.op1().accepter(ve);
    Integer exp2 = sup.op2().accepter(ve);
    return exp1 > exp2;
  }

  public Boolean visite(Egal eg) {
    Integer exp1 = eg.op1().accepter(ve);
    Integer exp2 = eg.op2().accepter(ve);
    return exp1 == exp2;
  }

  public Boolean visite(Inf inf) {
    Integer exp1 = inf.op1().accepter(ve);
    Integer exp2 = inf.op2().accepter(ve);
    return exp1 < exp2;
  }
}
