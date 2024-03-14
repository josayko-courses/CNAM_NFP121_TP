package question1;

/**
 * Création d'un polygone régulier
 *
 * @param nombreDeCotes {@code (nombreDeCotes >=1 && nombreDeCotes <100)}
 * @param longueurDuCote {@code (longueurDuCote>=1 && longueurDuCote < 10000)}
 */
public class PolygoneRegulier {

  private int nombreDeCotes;
  private int longueurDuCote;

  public PolygoneRegulier(int nombreDeCotes, int longueurDuCote) {
    this.nombreDeCotes = nombreDeCotes;
    this.longueurDuCote = longueurDuCote;
  }

  public int perimetre() {
    return nombreDeCotes * longueurDuCote;
  }

  public int surface() {
    return (int)
        (1.0 / 4 * (nombreDeCotes * Math.pow(longueurDuCote, 2.0) * cotg(Math.PI / nombreDeCotes)));
  }

  private static double cotg(double x) {
    return Math.cos(x) / Math.sin(x);
  }

  public boolean equals(Object obj) {
    if (!(obj instanceof PolygoneRegulier)) {
      return false;
    } else {
      PolygoneRegulier poly = (PolygoneRegulier) obj;
      return poly.nombreDeCotes == nombreDeCotes && poly.longueurDuCote == longueurDuCote;
    }
  }

  public int hashCode() {
    return nombreDeCotes * 10000 + longueurDuCote;
  }

  public String toString() {
    return "<" + nombreDeCotes + "," + longueurDuCote + ">";
  }
}
