package preliminaire;

import java.util.Iterator;
import java.util.Stack;

public abstract class Binaire extends Expression {
  protected Expression op1;
  protected Expression op2;

  public Binaire(Expression op1, Expression op2) {
    this.op1 = op1;
    this.op2 = op2;
  }

  public Iterator<Expression> iterator() {
    return new BinaireIterator(op1.iterator(), op2.iterator());
  }

  private static class BinaireIterator implements Iterator<Expression> {
    private Stack<Iterator<Expression>> stk = new Stack<Iterator<Expression>>();

    public BinaireIterator(Iterator<Expression> it1, Iterator<Expression> it2) {
      stk.push(it2);
      stk.push(it1);
    }

    public Expression next() {
      if (hasNext()) {
        Iterator<Expression> it = stk.peek();
        Expression e = it.next();
        if (e instanceof Binaire || e instanceof Unaire) {
          stk.push(e.iterator());
        }
        return e;
      } else {
        return null;
      }
    }

    public boolean hasNext() {
      if (stk.isEmpty()) {
        return false;
      } else {
        Iterator<Expression> it = stk.peek();
        if (!it.hasNext()) {
          stk.pop();
          return hasNext();
        } else {
          return true;
        }
      }
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
