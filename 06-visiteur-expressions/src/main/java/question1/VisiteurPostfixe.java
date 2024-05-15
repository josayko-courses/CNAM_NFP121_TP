package question1;

// En héritant de VisiteurParDefaut on s'assure que cette classe
// compile, mais on perd la vérification par le compilateur qu'on a
// redéfini toutes les méthodes. Si comme ici on veut redéfinir toutes
// les méthodes visite, il est donc préférable de remplacer ce extends
// par extends VisiteurExpression.
public class VisiteurPostfixe extends VisiteurExpression<String> {
  private Contexte c;

  public VisiteurPostfixe(Contexte c) {
    this.c = c;
  }

  public String visite(Constante c) {
    return Integer.toString(c.valeur());
  }

  public String visite(Variable v) {
    return v.nom();
  }

  public String visite(FonctionJava f) {
    return f.nom();
  }

  public String visite(Division d) {
    return "(" + d.op1().accepter(this) + "," + d.op2().accepter(this) + ")/";
  }

  public String visite(Addition a) {
    return "(" + a.op1().accepter(this) + "," + a.op2().accepter(this) + ")+";
  }

  public String visite(Multiplication m) {
    return "(" + m.op1().accepter(this) + "," + m.op2().accepter(this) + ")*";
  }

  public String visite(Soustraction s) {
    return "(" + s.op1().accepter(this) + "," + s.op2().accepter(this) + ")-";
  }

  public String visite(Moins s) {
    return "-(" + s.op().accepter(this) + ")";
  }

  public String visite(Factoriel s) {
    return "(" + s.op().accepter(this) + ")!";
  }

  public Contexte contexte() {
    return c;
  }
}
