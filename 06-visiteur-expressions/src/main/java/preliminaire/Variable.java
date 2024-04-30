package preliminaire;

import java.util.Iterator;

public class Variable extends Expression {
  private String nom;

  public Variable(Contexte c, String nom, Integer valeur) {
    this.nom = nom;
    c.ecrire(nom, valeur);
  }

  public Variable(Contexte c, String nom) {
    this(c, nom, 0);
  }

  public String toString() {
    return nom;
  }

  public int interprete(Contexte c) {
    return c.lire(this.nom);
  }

  public Iterator<Expression> iterator() {
    return new Iterator<Expression>() {
      private boolean next = true;

      public boolean hasNext() {
        return next;
      }

      public Expression next() {
        next = false;
        return Variable.this;
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }
}
