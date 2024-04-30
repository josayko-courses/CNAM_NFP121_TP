package preliminaire;

import java.util.Map;
import java.util.TreeMap;

public class Memoire implements Contexte {
  private Map<String, Integer> table;

  public Memoire() {
    table = new TreeMap<String, Integer>();
  }

  public Integer lire(String adresse) {
    return table.get(adresse);
  }

  public void ecrire(String adresse, int valeur) {
    table.put(adresse, valeur);
  }

  public String toString() {
    return table.toString();
  }
}
