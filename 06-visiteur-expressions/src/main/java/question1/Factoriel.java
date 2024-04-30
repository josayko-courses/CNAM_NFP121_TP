package question1;

public class Factoriel extends Unaire {

  public Factoriel(Expression op) {
    super(op);
  }

  public <T> T accepter(VisiteurExpression<T> v) {
    return v.visite(this);
  }
}
