package question1;

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
}
