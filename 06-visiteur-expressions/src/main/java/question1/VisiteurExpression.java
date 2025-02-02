package question1;

public abstract class VisiteurExpression<T> {

  public abstract T visite(Constante c);

  public abstract T visite(Variable v);

  public abstract T visite(FonctionJava f);

  public abstract T visite(Division d);

  public abstract T visite(Addition a);

  public abstract T visite(Multiplication m);

  public abstract T visite(Soustraction s);

  public abstract T visite(Moins s);

  public abstract T visite(Factoriel s);

  public abstract Contexte contexte();
}
