package preliminaire;

import java.util.Iterator;

public abstract class Unaire extends Expression {

  protected Expression op;

  public Unaire(Expression op) {
    this.op = op;
  }

  public Iterator<Expression> iterator() {
    return null;
  }
}
