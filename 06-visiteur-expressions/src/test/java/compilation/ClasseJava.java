package compilation;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import question1.Contexte;
import question3.Instruction;
import question3.VisiteurInstruction;

/** Utilitaire de génération de classe Java à parir de votre Visiteur VisiteurInstToJava */
public class ClasseJava {

  private static final String lineSeparator = System.getProperties().getProperty("line.separator");

  private String nomDeLaClasse;
  private String nomDuPaquetage;
  private VisiteurInstruction<String> vi;
  private Instruction inst;

  public ClasseJava(String nomDeLaClasse, Instruction inst, VisiteurInstruction<String> vi) {
    // On force le package, afin d'éviter les risques d'effacement de fichiers involontaires
    this(nomDeLaClasse, "fichiersGeneres", inst, vi);
  }

  private ClasseJava(
      String nomDeLaClasse,
      String nomDuPaquetage,
      Instruction inst,
      VisiteurInstruction<String> vi) {
    this.nomDeLaClasse = nomDeLaClasse;
    this.nomDuPaquetage = nomDuPaquetage;
    this.vi = vi;
    this.inst = inst;
  }

  public String getNomDeLaClasse() {
    return nomDeLaClasse;
  }

  public String getNomDuPaquetage() {
    return nomDuPaquetage;
  }

  private class ClassStringBuilder {

    private StringBuilder sb;

    public ClassStringBuilder() {
      super();
      this.sb = new StringBuilder();
    }

    public String toString() {
      return this.sb.toString();
    }

    public ClassStringBuilder append(String s) {
      sb.append(s);
      return this;
    }

    public ClassStringBuilder eol() {
      return this.append(lineSeparator);
    }

    public ClassStringBuilder appendnl(String s) {
      this.append(s).eol();
      return this;
    }

    public ClassStringBuilder addEnTete() {
      if (nomDuPaquetage != null) this.append("package ").append(nomDuPaquetage).appendnl(";");
      this.appendnl("/** NFP121 tp6, question3") //
          .appendnl(
              "   source Java généré par l'intermédiaire du visiteur \"VisiteurInstToJava\"") //
          .appendnl("*/"); //
      return this;
    }

    public ClassStringBuilder addDeclarations() {
      this.eol();
      Contexte ctxt = vi.contexte();
      Iterator<String> it = ctxt.iterator();
      while (it.hasNext()) {
        String identifiant = it.next();
        String valeur = Integer.toString(ctxt.lire(identifiant));
        this.append("    int ").append(identifiant).append("=").append(valeur).appendnl(";");
        // str = str + " " + "int " + identifiant + "=" + valeur + ";" + lineSeparator;
      }
      return this;
    }

    public ClassStringBuilder addInstructions() {
      return this.append(inst.accepter(vi));
    }

    public ClassStringBuilder addEnPied() {
      return this.eol().appendnl("}");
    }

    private ClassStringBuilder sourceComplet() {
      return this.addEnTete() //
          .append("public class " + nomDeLaClasse + "{" + lineSeparator + lineSeparator) //
          .append("  public static void main(String[] args)throws Exception {") //
          .addDeclarations() //
          .addInstructions()
          .eol() //
          .append("  }") //
          .addEnPied();
    }
  }

  public String sourceComplet() {
    ClassStringBuilder sb = new ClassStringBuilder();
    return sb.sourceComplet().toString();
  }

  public void enFichier() throws IOException {
    Path javaPath = Path.of("build", "generated");
    Path classPath = Path.of("build", "generated");
    if (nomDuPaquetage != null) {
      javaPath = javaPath.resolve(nomDuPaquetage);
      classPath = classPath.resolve(nomDuPaquetage);
    }
    javaPath = javaPath.resolve(nomDeLaClasse + ".java");
    classPath = classPath.resolve(nomDeLaClasse + ".class");
    System.out.println("détection du dossier " + javaPath.getParent());
    if (!Files.exists(classPath.getParent())) {
      System.out.println("Suppression du dossier " + javaPath.getParent());
      Files.createDirectories(classPath.getParent());
    }
    if (Files.exists(javaPath)) {
      System.out.println("Suppression du fichier " + javaPath);
      Files.delete(javaPath);
    }
    if (Files.exists(classPath)) {
      System.out.println("Suppression du fichier " + classPath);
      Files.delete(classPath);
    }
    BufferedWriter out = new BufferedWriter(new FileWriter(javaPath.toString()));
    System.out.println("Écriture du fichier " + javaPath);
    out.write(this.sourceComplet());
    out.close();
  }
}
