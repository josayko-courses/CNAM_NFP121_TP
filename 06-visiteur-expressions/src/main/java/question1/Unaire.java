package question1;

public abstract class Unaire extends Expression {
  protected Expression op;

  public Unaire(Expression op) {
    this.op = op;
  }

  public Expression op() {
    return op;
  }
}
