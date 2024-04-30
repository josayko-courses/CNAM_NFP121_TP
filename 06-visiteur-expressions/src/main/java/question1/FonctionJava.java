package question1;

public class FonctionJava extends Expression {
  private String nom;
  private Expression[] exp;

  public FonctionJava(String nom, Expression... exp) {
    this.nom = nom;
    this.exp = exp;
  }

  public String nom() {
    return nom;
  }

  public Expression[] exp() {
    return exp;
  }

  public <T> T accepter(VisiteurExpression<T> v) {
    return v.visite(this);
  }
}
