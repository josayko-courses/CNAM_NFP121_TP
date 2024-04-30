package question1;

public class Moins extends Unaire {

  public Moins(Expression op) {
    super(op);
  }

  public <T> T accepter(VisiteurExpression<T> v) {
    return v.visite(this);
  }
}
