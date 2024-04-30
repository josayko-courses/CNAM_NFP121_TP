package preliminaire;

public interface Contexte {

  public Integer lire(String adresse);

  public void ecrire(String adresse, int valeur);
}
