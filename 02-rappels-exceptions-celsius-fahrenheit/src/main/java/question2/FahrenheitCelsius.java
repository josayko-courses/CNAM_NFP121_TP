package question2;

/**
 * Décrivez votre classe FahrenheitCelsius ici.
 *
 * @author Jonny SAYKOSY
 * @version 2024.03.03
 */
public class FahrenheitCelsius {

  /**
   * @param args liste des valeurs en degrés Fahrenheit
   */
  public static void main(String[] args) {
    try {
      for (String arg : args) {
        int fahrenheit = Integer.parseInt(arg);
        float celsius = fahrenheitEnCelsius(fahrenheit);
        System.out.println(fahrenheit + "\u00B0F -> " + celsius + "\u00B0C");
      }
    } catch (NumberFormatException nfe) {
      System.out.println("error : " + nfe.getMessage()); // en cas d'erreur
    }
  }

  /**
   * la méthode à compléter.
   *
   * @param f la valeur en degré Fahrenheit
   * @return la conversion en degré Celsius
   */
  public static float fahrenheitEnCelsius(int f) {
    float celsius = 5f / 9 * (f - 32);
    return celsius;
  }
}
