package question1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MarkPresenceFichiers {
  @Test
  public void test_présence_fichiers_question1() {
    try {
      Class.forName("question1.Ensemble");
      Class.forName("question1.EnsembleTest");
      // Class.forName("question1.AppletteTestEnsemble");
      Class.forName("question1.IHMTestEnsemble");
    } catch (ClassNotFoundException e) {
      fail("classe absente " + e.getMessage());
    }
  }
}
