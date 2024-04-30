package question3;

public class Vide extends Instruction {

  public <T> T accepter(VisiteurInstruction<T> vi) {
    return vi.visite(this);
  }
}
