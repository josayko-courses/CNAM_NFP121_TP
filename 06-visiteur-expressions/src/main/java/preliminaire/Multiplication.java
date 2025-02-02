package preliminaire;

public class Multiplication extends Binaire {

  public Multiplication(Expression op1, Expression op2) {
    super(op1, op2);
  }

  public String toString() {
    return "(" + op1.toString() + " * " + op2.toString() + ")";
  }

  public int interprete(Contexte c) {
    return op1.interprete(c) * op2.interprete(c);
  }
}
