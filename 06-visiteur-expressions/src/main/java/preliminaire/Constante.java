package preliminaire;

import java.util.Iterator;

public class Constante extends Expression {
  private int i;

  public Constante(int i) {
    this.i = i;
  }

  public int valeur() {
    return i;
  }

  public String toString() {
    return Integer.toString(i);
  }

  public int interprete(Contexte c) {
    return valeur();
  }

  public Iterator<Expression> iterator() {
    return new Iterator<Expression>() {
      private boolean next = true;

      public boolean hasNext() {
        return next;
      }

      public Expression next() {
        next = false;
        return Constante.this;
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }
}
