package question1;

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
    /* SOLUTION:// pour tous les paramètres de la ligne de commande
    int fahrenheit = 0;
    float celsius = 0;
    // ligne, format imposé
    System.out.println(fahrenheit + "\u00B0F -> " + celsius + "\u00B0C");
    */
    for (String elt : args) {
      float celsius = fahrenheitEnCelsius(Integer.parseInt(elt));
      System.out.println(elt + "\u00B0F -> " + celsius + "\u00B0C");
    }
    /* FIN SOLUTION */
  }

  /**
   * la méthode à compléter.
   *
   * @param f la valeur en degré Fahrenheit
   * @return la conversion en degré Celsius
   */
  public static float fahrenheitEnCelsius(int f) {
    /* SOLUTION:return 0.F; // à compléter en remplaçant ce return 0.F par la fonction
    // de conversion*/
    float celsius;
    celsius = (f - 32) * 5 / 9.f;
    celsius = (int) (celsius * 10) / 10.f;
    return celsius;
    /* FIN SOLUTION */
  }
  /* SOLUTION (rien) */
  //      /**
  //       * la méthode à compléter.
  //       *   @param f la valeur en degré Fahrenheit
  //       *   @return  la conversion en degré Celsius
  //       */
  //      public static float fahrenheitEnCelsius( int f){
  //        float resultat = (5.0F/9.0F * (f-32));
  //        float resultatAttendu = ((int)(resultat*10)) / 10.0F;
  //        //java.util.Properties p = System.getProperties();
  //        return resultatAttendu;
  //      }

  /* FIN SOLUTION */
}
