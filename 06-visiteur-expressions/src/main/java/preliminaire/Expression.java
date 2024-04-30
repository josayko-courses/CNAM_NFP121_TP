package preliminaire;

import java.util.Iterator;

public abstract class Expression implements Iterable<Expression> {

  public abstract int interprete(Contexte c);

  public abstract Iterator<Expression> iterator();
}
