package question2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MarkPresenceFichiers {
  @Test
  public void test_présence_fichiers_question2() {
    try {
      Class.forName("question2.IHMListe");
      Class.forName("question2.IHMListe2");
      Class.forName("question2.JPanelListe");
      Class.forName("question2.JPanelListe2");
      Class.forName("question2.Chapitre2CoreJava2");
    } catch (ClassNotFoundException e) {
      fail("classe absente " + e.getMessage());
    }
  }
}
