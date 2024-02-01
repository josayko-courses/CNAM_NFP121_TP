package util;

import java.util.*;
import org.apache.bcel.classfile.*;

public class VisitorCode extends org.apache.bcel.classfile.EmptyVisitor {

  InfoClass infoClass = null;

  // Pour chaque méthode de test, la map du nombre d'appel à chaque méthode

  public VisitorCode(InfoClass info) {
    super();
    this.infoClass = info;
  }

  @Override
  public void visitMethod(Method m) {
    Code code = m.getCode();
    System.out.println("###code of " + m.toString() + "= [[[[" + code + "###]]]]]");
    Scanner vSc = new Scanner(code.toString());
    String vL = null;

    while (vSc.hasNextLine()) {
      vL = vSc.nextLine();
      if (vL.contains("invokespecial")
          || vL.contains("invokevirtual")
          || vL.contains("invokeinterface")
          || vL.contains("invokestatic")) {
        String[] line = vL.split("\\s+");
        infoClass.addCall(m, line[2] + line[3]);
      }
      // Cas particulier, si on voit un pop on enregistre un appel à la méthode imaginaire #pop#.
      // Dans certains cas on veut tester qu'il n'y a pas de pop, c'est-à-dire qu'on
      // ne jette pas un résultat de méthode.
      if (vL.contains("pop")) {
        String[] line = vL.split("\\s+");
        if (line[1].equals("pop")) { // pour être sûr que le contains ne vient pas d'autre chose
          infoClass.addCall(m, "#pop#");
        }
      }
      if (vL.contains("goto")) {
        String[] line = vL.split("\\s+");
        if (line[1].equals("goto")) { // pour être sûr que le contains ne vient pas d'autre chose
          infoClass.addCall(m, "#goto#");
        }
        // On veut parfois différencier les jump en arrière des jump en avant...
        // En fait ça servait à détecter les boucles mais elles ne sont plus compilées de la même
        // façon maintenant... Le goto est vers l'avant et le retour se fait par un if (ifne ou
        // autre). Donc pour les TPs en général on cherche un goto et ça indique une structure de
        // boucle ou qqchose de compliqué qui ne devrait pas être là...
        int currentLine = Integer.parseInt(line[0], 0, line[0].length() - 1, 10); // "34:" -> 34
        int jumpLine = Integer.parseInt(line[2].substring(1)); // "#42" -> 42
        if (jumpLine < currentLine) infoClass.addCall(m, "#gotoBackward#");
        else infoClass.addCall(m, "#gotoForward#");
      }
      // Les lambda on cette forme:
      // 32:  invokedynamic 0:execute
      // (Lquestion1/TestsACompleter;)Lorg/junit/jupiter/api/function/Executable;
      if (vL.contains(":execute")) {
        String[] line = vL.split("\\s+");
        if (line[1].equals("invokedynamic") //
            && line[2].contains(":execute")) { // pour être sûr que le contains ne vient pas d'autre
          // chose
          String lambdaName = line[2].split(":")[0]; // extract 0 from: 0:execute
          // System.out.println("lambda detected: " + lambdaName);
          // System.out.println("pool: " + infoClass.theClass.getConstantPool());
          infoClass.lambdaMap.put(lambdaName, m);
        }
      }
    }
  }
}
