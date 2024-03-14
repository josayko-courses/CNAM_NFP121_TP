package question2;

/**
 * Décrivez votre classe FahrenheitCelsius ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class FahrenheitCelsius {

  /**
   * le point d'entrée de cette application, dont le commentaire est à compléter
   *
   * @param args ...
   */
  public static void main(String[] args) {
    /* SOLUTION (rien) */
    for (String elt : args) {
      /* FIN SOLUTION */
      try {
        /* SOLUTION (rien) */
        float celsius = fahrenheitEnCelsius(Integer.parseInt(elt));
        System.out.println(elt + "\u00B0F -> " + celsius + "\u00B0C");
        /* FIN SOLUTION */
      } catch (NumberFormatException nfe) {
        System.out.println("error : " + nfe.getMessage()); // en cas d'erreur
        /* SOLUTION (rien) */
      }
      /* FIN SOLUTION */
    }
  }

  /**
   * la méthode à compléter.
   *
   * @param f la valeur en degré Fahrenheit
   * @return la conversion en degré Celsius
   */
  public static float fahrenheitEnCelsius(int f) {
    /* SOLUTION:return 0.F; // à compléter en remplaçant la valeur retournée par la fonction de conversion */
    float resultat = (5.0F / 9.0F * (f - 32));
    float resultatAttendu = ((int) (resultat * 10)) / 10.0F;
    return resultatAttendu;
    /* FIN SOLUTION */
  }
}
