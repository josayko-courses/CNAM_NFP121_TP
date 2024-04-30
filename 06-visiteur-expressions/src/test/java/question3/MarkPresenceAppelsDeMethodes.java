package question3;

import java.io.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import util.InfoClass;
import util.InfoMethod;

@TestInstance(Lifecycle.PER_CLASS) // Une seule instance pour tous les tests.
public class MarkPresenceAppelsDeMethodes {

  private static final String Q3TESTS = "/question3/VisiteurInstEvaluation.class";

  private class V {
    private static final String VISITEWHILE =
        "public question1.Contexte visite(question3.TantQue tq)";
    private static final String SEQUENCE =
        "question3.Sequence.<init>(Lquestion3/Instruction;Lquestion3/Instruction;)V";
    private static final String TANTQUE =
        "question3.TantQue.<init>(Lquestion2/ExpressionBooleenne;Lquestion3/Instruction;)V";
    private static final String VISITEFOR = "public question1.Contexte visite(question3.Pour pour)";
  }

  // contient une map stockant le nb d'appels à chaque méthode dans chaque méthode de test.
  InfoClass info = null;
  // Vue en un seul infomethod
  InfoMethod all; // pseudo -méthode avec tous les appels de la classe
  InfoMethod allTests; // avec tous les appels dans des méthodes de test
  InfoMethod visiteTantque, visitePour, jumpBack;

  @BeforeAll
  public void setUp() throws IOException {
    // On lance la collecte d'infos (une seule fois avant les tests)
    info = new InfoClass(Q3TESTS);
    System.out.println("call map = " + info.callMap);
    // union de tous les appels de méthodes dans la classe
    all = info.get("#all#");
    // pareil mais seulement les méthodes de test
    // allTests = info.get("#alltests#");
    visiteTantque = info.get(V.VISITEWHILE);
    visitePour = info.get(V.VISITEFOR);
    // jumpBack = info.get("#gotoBackward#");
  }

  @Test
  public void test_VisiteurInstTantQue_SansWhileNiFor() {
    // le goto indique EN GÉNÉRAL une boucle... Mais pas à coup sûr. Cela dit il ne devrait rien y
    // avoir de compliqué ici donc le goto indique très probablement une boucle car c'est l'erreur
    // standard: faire la boucle en une fois au lieu d'utiliser la règle d'inférence qui fabrique
    // une seule étape (sequence) à la fois.
    visiteTantque.assertNbCallsLesser(
        "#goto#",
        0,
        "Ni while ni for de Java ni d'autres structures compliquées ne doivent être utilisées...");
  }

  @Test
  public void test_VisiteurInstTantQue_AvecLaRègleDInférence() {
    visiteTantque.assertNbCallsGreater(
        V.SEQUENCE,
        1,
        "Une nouvelle séquence est à créer pour la boucle TantQue, cf la règle d'inférence...");
    visiteTantque.assertNbCallsLesser(
        V.SEQUENCE,
        1,
        "Une SEUL nouvelle séquence est à créer pour la boucle TantQue, cf la règle d'inférence...");
  }

  @Test
  public void test_VisiteurInstPour_SansWhileNiFor() {
    // Même remarque que pour le tantque:
    // le goto indique EN GÉNÉRAL une boucle... Mais pas à coup sûr. Cela dit il ne devrait rien y
    // avoir de compliqué ici donc le goto indique très probablement une boucle car c'est l'erreur
    // standard: faire la boucle en une fois au lieu d'utiliser la règle d'inférence qui fabrique
    // une seule étape (sequence) à la fois.
    visitePour.assertNbCallsLesser(
        "#goto#",
        0,
        "Ni while ni for de Java ni d'autres structures compliquées ne doivent être utilisées...");
  }

  @Test
  public void test_VisiteurInstPour_AvecLaRègleDInférence() {
    visitePour.assertNbCallsGreater(
        V.SEQUENCE, 2, "Deux nouvelles séquences sont à créer, cf la règle d'inférence...");
    visitePour.assertNbCallsGreater(
        V.TANTQUE, 1, "Vous devez créer un TantQue, cf la règle d'inférence du Pour...");
  }
}
