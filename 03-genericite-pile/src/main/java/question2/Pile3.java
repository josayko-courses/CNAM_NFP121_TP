package question2;

import java.util.Vector;
import question1.PilePleineException;
import question1.PileVideException;

public class Pile3 implements PileI {
  private Vector<Object> v;

  public Pile3() {
    // à completer, on peut appeler le constructeur ci-dessous avec "this(...); 
  }

  public Pile3(int taille) {
    // à compléter 
  }

  public void empiler(Object o) throws PilePleineException {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public Object depiler() throws PileVideException {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public Object sommet() throws PileVideException {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public int taille() {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public int capacite() {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public boolean estVide() {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public boolean estPleine() {
    throw new RuntimeException("Pas_encore_implante"); // REMPLIR ICI
  }

  public String toString() {
    String s = "[";
    // à compléter 
    return s + "]";
  }

  public boolean equals(Object o) {
    // à compléter
    return (false); 
  }

  // fonction fournie
  public int hashCode() {
    return toString().hashCode();
  }
}
