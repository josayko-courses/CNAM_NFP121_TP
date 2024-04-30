package question1;

// En héritant de VisiteurParDefaut on s'assure que cette classe
// compile, mais on perd la vérification par le compilateur qu'on a
// redéfini toutes les méthodes. Si comme ici on veut redéfinir toutes
// les méthodes visite, il est donc préférable de remplacer ce extends
// par extends VisiteurExpression.
public class VisiteurPostfixe extends VisiteurParDefaut<String> {
  private Contexte c;

  public VisiteurPostfixe(Contexte c) {
    this.c = c;
  }

  public Contexte contexte() {
    return c;
  }

  public String visite(Constante c) {
    return "TODO"; 
  }

  // Compléter 
}
