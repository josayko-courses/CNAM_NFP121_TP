package question1;

// Réponse à la question 1:
// - On ne peut pas factoriser la méthode "accepter" dans une classe abstraite
// car  nous ne pouvons pas résoudre statiquement les appels de méthodes surchargés 
// par leur type déclaré. Le type constaté pourrait être plus restrictif et donc 
// nous appelerions la mauvaise méthode surchargé.

// En héritant de VisiteurParDefaut on s'assure que cette classe

// En héritant de VisiteurParDefaut on s'assure que cette classe
// compile, mais on perd la vérification par le compilateur qu'on a
// redéfini toutes les méthodes. Si comme ici on veut redéfinir toutes
// les méthodes visite, il est donc préférable de remplacer ce extends
// par extends VisiteurExpression.
public class VisiteurEvaluation extends VisiteurParDefaut<Integer> {
  private Contexte c;

  public VisiteurEvaluation(Contexte c) {
    this.c = c;
  }

  public Contexte contexte() {
    return c;
  }

  public Integer visite(Constante c) {
    return c.valeur();
  }

  // Compléter 
  public Integer visite(Variable v) {
    return v.accepter(this);
  }

  public Integer visite(FonctionJava f) {
    return f.accepter(this);
  }

  public Integer visite(Division d) {
    return  d.op1().accepter(this) / d.op2().accepter(this);
  }

  public Integer visite(Addition a) {
    return a.op1().accepter(this) + a.op2().accepter(this);
  }

  public Integer visite(Multiplication m) {
    return m.op1().accepter(this) * m.op2().accepter(this);
  }

  public Integer visite(Soustraction s) {
    return s.op1().accepter(this) - s.op2().accepter(this);
  }

  public Integer visite(Moins s) {
    return s.op().accepter(this);
  }

  public Integer visite(Factoriel s) {
    return s.op().accepter(this);
  }
}
