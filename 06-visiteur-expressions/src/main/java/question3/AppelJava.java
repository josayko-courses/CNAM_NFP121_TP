package question3;

import question1.Expression;

public class AppelJava extends Instruction {
  private String nom;
  private Expression[] exp;

  public AppelJava(String nom, Expression... exp) {
    this.nom = nom;
    this.exp = exp;
  }

  public <T> T accepter(VisiteurInstruction<T> vi) {
    return vi.visite(this);
  }

  public String nom() {
    return nom;
  }

  public Expression[] exp() {
    return exp;
  }
}
