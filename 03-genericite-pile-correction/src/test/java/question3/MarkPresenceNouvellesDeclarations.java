package question3;

import java.io.*;
import java.util.regex.Pattern;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import util.InfoClass;
import util.InfoMethod;

@TestInstance(Lifecycle.PER_CLASS) // Une seule instance pour tous les tests.
public class MarkPresenceNouvellesDeclarations {

  private static final String Q3TESTS = "/question3/UneUtilisation.class";

  private class U {
    public static final String MAIN =
        "public static void main(String[] args)\n\t\tthrows Exceptions: java.lang.Exception";
  }

  private static class P2 {
    private static final String INIT = "question3.Pile2.<init>()V";
    private static final String EMPILER = "question3.PileI.empiler(Ljava/lang/Object;)V";
    private static final String DEPILER = "question3.PileI.depiler()Ljava/lang/Object;";
  }

  private static final Pattern INIT_RE = pat(".*<init>.*");

  // contient une map stockant le nb d'appels à chaque méthode dans chaque méthode de test.
  InfoClass info = null;
  // Vue en un seul infomethod
  InfoMethod all; // pseudo -méthode avec tous les appels de la classe
  InfoMethod allTests; // avec tous les appels dans des méthodes de test
  InfoMethod umain;

  @BeforeAll
  public void setUp() throws IOException {
    // On lance la collecte d'infos (une seule fois avant les tests)
    info = new InfoClass(Q3TESTS);
    // union de tous les appels de méthodes dans la classe
    all = info.get("#all#");
    // pareil mais seulement les méthodes de test
    allTests = info.get("#alltests#");
    this.umain = info.get(U.MAIN);
  }

  private static Pattern pat(String regexp) {
    return Pattern.compile(regexp);
  }

  @Test
  public void test_nombre_de_new_Pile2() {
    umain.assertNbCallsGreater(
        P2.INIT, 4, "Trop peu de nouvelles créations de Pile2 ???(au moins 4)");
  }

  @Test
  public void test_nombre_de_new() {
    umain.assertNbCalls(
        INIT_RE,
        n -> n >= 4,
        "Avez-vous proposé vos déclarations,cf.énoncé ?(classe UneUtilisation)");
  }

  @Test
  public void test_nombre_de_empiler() {
    umain.assertNbCallsGreater(P2.EMPILER, 6);
  }

  @Test
  public void test_nombre_de_depiler() {
    umain.assertNbCallsGreater(P2.DEPILER, 6);
  }
}

// TODO: tester que chaque pile utilise la classe demandée.

// public void test_contains_dans_add(){
// try{
// List<String> resultat = byteCodeDeLaMethode(bytecode,"add(Ljava/lang/Object;)Z" );
// boolean contains=false; boolean table_contains=false;
// int compteur_new_ensemble=0, compteur_new_vector=0, compteur_new=0;
// for(String s : resultat){
// if(s.contains("question1.Ensemble.contains(Ljava/lang/Object;)Z"))
// contains = true;
// if(s.contains("java.util.Vector.contains(Ljava/lang/Object;)Z"))
// table_contains=true;
// else if(s.contains("#NEW=<question1.Ensemble>"))
// compteur_new_ensemble++;
// else if(s.contains("#NEW=<java.util.Vector>"))
// compteur_new_vector++;
// else if(s.contains("#NEW"))
// compteur_new++;
// }
// String r = table_contains?" à table.contains (pourquoi ?)":"";
// assertTrue(contains,"L'appel à this.contains est préféré"+r+"...");
// assertTrue(0==compteur_new_ensemble,"Aucune nouvelle allocation d'ensemble n'est
// requise ???");
// assertTrue(0==compteur_new_vector,"Aucune nouvelle allocation de vecteur n'est
// requise ???");
// assertTrue(0==compteur_new,"Au moins une allocation (appel de new) n'est pas
// nécessaire ???");

// }catch(Exception e){
// fail(" contains_dans_add " + e.getMessage());
// }
// }

// public void test_appel_add_dans_add(){
// try {
// List<String> resultat = byteCodeDeLaMethode(bytecode, "add(Ljava/lang/Object;)Z" );

// boolean add = false;
// boolean notUsed = false;
// for ( int i=0; i<resultat.size(); i++ ) {
// if ( resultat.get(i).contains( "java.util.Vector.add(Ljava/lang/Object;)Z" ) ) {
// add = true;
// notUsed = i+1<resultat.size() && resultat.get(i+1).equals( "#POP" );
// }
// }
// assertTrue(add , "pas d'ajout dans la table ??");
// assertFalse(notUsed , "le résultat de add est-il perdu ??");
// }
// catch ( final Exception e ) {
// fail( "add_dans_add: " + e.getMessage() );
// }
// }

// public void test_appels_dans_union(){
// try{
// List<String> resultat =
// byteCodeDeLaMethode(bytecode,"union(Lquestion1/Ensemble;)Lquestion1/Ensemble;" );
// int compteur_addAll=0, compteur_new_ensemble=0, compteur_goto=0;
// int compteur_new = 0, compteur_new_vector = 0, compteur_iterator=0,
// compteur_usage_vector=0;
// //System.out.println("resultat: " + resultat);
// for(String s : resultat){
// //System.out.println("s: " + s);
// if(s.contains("question1.Ensemble.addAll(Ljava/util/Collection;)Z"))
// compteur_addAll++;
// else if(s.contains("#NEW=<question1.Ensemble>"))
// compteur_new_ensemble++;
// else if(s.contains("#NEW=<java.util.Vector>"))
// compteur_new_vector++;
// else if(s.contains("java.util.Vector."))
// compteur_usage_vector++;
// else if(s.contains("#NEW"))
// compteur_new++;
// else if(s.contains("#GOTO"))
// compteur_goto++;
// else if(s.contains("question1.Ensemble.iterator()Ljava/util/Iterator;"))
// compteur_iterator++;
// }

// assertTrue(0==compteur_new_vector,"Aucune nouvelle allocation de vecteur n'est
// requise ???");
// assertTrue(0==compteur_usage_vector,"Aucun appel de méthodes de la classe Vector
// n'est nécessaire ???");
// assertTrue(0==compteur_iterator,"Aucune boucle (foreach, iterator) n'est requise
// ???");
// assertTrue(0==compteur_goto,"Aucune boucle (for, while) n'est requise ???");
// assertTrue(0==compteur_new,"Au moins une allocation (appel de new) n'est pas
// nécessaire ???");
// assertTrue(1==compteur_new_ensemble,"Un seul Ensemble est à créer ???");
// assertTrue(2==compteur_addAll,"Deux appels à la méthode addAll sont requis ???");
// }catch(Exception e){
// fail(" dans_union " + e.getMessage());
// }
// }

// public void test_appels_dans_inter(){
// try{
// List<String> resultat = byteCodeDeLaMethode(bytecode,
// "inter(Lquestion1/Ensemble;)Lquestion1/Ensemble;" );
// int compteur_addAll=0, compteur_retainAll=0, compteur_goto=0, compteur_iterator=0;
// int compteur_new_ensemble=0, compteur_new_vector=0, compteur_new=0,
// compteur_usage_vector=0;
// for(String s : resultat){
// if(s.contains("question1.Ensemble.addAll(Ljava/util/Collection;)Z"))
// compteur_addAll++;
// else if(s.contains("question1.Ensemble.retainAll(Ljava/util/Collection;)Z"))
// compteur_retainAll++;
// else if(s.contains("#NEW=<question1.Ensemble>"))
// compteur_new_ensemble++;
// else if(s.contains("#NEW=<java.util.Vector>"))
// compteur_new_vector++;
// else if(s.contains("java.util.Vector."))
// compteur_usage_vector++;
// else if(s.contains("#NEW"))
// compteur_new++;
// else if(s.contains("#GOTO"))
// compteur_goto++;
// else if(s.contains("question1.Ensemble.iterator()Ljava/util/Iterator;"))
// compteur_iterator++;
// }

// assertTrue(0==compteur_new_vector,"Aucune nouvelle allocation de vecteur n'est
// requise???");
// assertTrue(0==compteur_new,"Au moins une allocation (appel de new) n'est pas
// nécessaire ???");
// assertTrue(0==compteur_iterator,"Aucune boucle (foreach, iterator) n'est
// requise???");
// assertTrue(0==compteur_goto,"Aucune boucle (for, while) n'est requise???");
// assertTrue(0==compteur_usage_vector,"Aucun appel de méthodes de la classe Vector
// n'est nécessaire ???");
// assertTrue(1==compteur_new_ensemble,"Un seul Ensemble est à créer ???");
// assertTrue(1==compteur_addAll,"Un seul appel à la méthode addAll est requis ???");
// assertTrue(1==compteur_retainAll,"Un seul appel à la méthode retainAll est requis
// ???");
// }catch(Exception e){
// fail(" dans_inter " + e.getMessage());
// }

// }

// public void test_appels_dans_diff(){
// try{
// List<String> resultat = byteCodeDeLaMethode(bytecode,
// "diff(Lquestion1/Ensemble;)Lquestion1/Ensemble;" );
// int compteur_addAll=0, compteur_removeAll=0, compteur_goto=0, compteur_iterator=0,
// compteur_usage_vector=0;
// int compteur_new_ensemble=0, compteur_new=0, compteur_new_vector=0,
// compteur_retainAll=0;
// for(String s : resultat){
// if(s.contains("question1.Ensemble.addAll(Ljava/util/Collection;)Z"))
// compteur_addAll++;
// else if(s.contains("question1.Ensemble.removeAll(Ljava/util/Collection;)Z"))
// compteur_removeAll++;
// else if(s.contains("question1.Ensemble.retainAll(Ljava/util/Collection;)Z"))
// compteur_retainAll++;
// else if(s.contains("#NEW=<question1.Ensemble>"))
// compteur_new_ensemble++;
// else if(s.contains("#NEW=<java.util.Vector>"))
// compteur_new_vector++;
// else if(s.contains("java.util.Vector."))
// compteur_usage_vector++;
// else if(s.contains("#NEW"))
// compteur_new++;
// else if(s.contains("#GOTO"))
// compteur_goto++;
// else if(s.contains("question1.Ensemble.iterator()Ljava/util/Iterator;"))
// compteur_iterator++;

// }

// assertTrue(0==compteur_retainAll,"Aucun appel à la méthode retainAll n'est
// requis???");
// assertTrue(0==compteur_iterator,"Aucune boucle (foreach, iterator) n'est
// requise???");
// assertTrue(0==compteur_goto,"Aucune boucle (for, while) n'est requise???");
// assertTrue(0==compteur_usage_vector,"Aucun appel de méthodes de la classe Vector
// n'est nécessaire ???");

// assertTrue(1==compteur_new_ensemble,"Un seul Ensemble est à créer ???");
// assertTrue(0==compteur_new_vector,"Aucune nouvelle allocation de vecteur n'est
// requise???");
// assertTrue(0==compteur_new,"Au moins une allocation (appel de new) n'est pas
// nécessaire ???");
// assertTrue(1==compteur_addAll,"Un seul appel à la méthode addAll est requis ???");
// assertTrue(1==compteur_removeAll,"Un seul appel à la méthode removeAll est requis
// ???");
// }catch(Exception e){
// fail(" dans_diff " + e.getMessage());
// }
// }

// public void test_appel_union_diff_inter_dans_diffSym(){

// try{
// List<String> resultat = byteCodeDeLaMethode(bytecode,
// "diffSym(Lquestion1/Ensemble;)Lquestion1/Ensemble;" );

// int
// compteur_union=0,compteur_inter=0,compteur_diff=0,compteur_addAll=0,compteur_goto=0,
// compteur_iterator=0;
// int compteur_new_ensemble=0, compteur_new=0, compteur_new_vector=0,
// compteur_removeAll=0,compteur_retainAll=0;
// int compteur_usage_vector=0;
// for(String s : resultat){
// if(s.contains("question1.Ensemble.union(Lquestion1/Ensemble;)Lquestion1/Ensemble;"))
// compteur_union++;
// else
// if(s.contains("question1.Ensemble.inter(Lquestion1/Ensemble;)Lquestion1/Ensemble;"))
// compteur_inter++;
// else
// if(s.contains("question1.Ensemble.diff(Lquestion1/Ensemble;)Lquestion1/Ensemble;"))
// compteur_diff++;
// else if(s.contains("question1.Ensemble.addAll(Ljava/util/Collection;)Z"))
// compteur_addAll++;
// else if(s.contains("question1.Ensemble.removeAll(Ljava/util/Collection;)Z"))
// compteur_removeAll++;
// else if(s.contains("question1.Ensemble.retainAll(Ljava/util/Collection;)Z"))
// compteur_retainAll++;
// else if(s.contains("#NEW=<question1.Ensemble>"))
// compteur_new_ensemble++;
// else if(s.contains("#NEW=<java.util.Vector>"))
// compteur_new_vector++;
// else if(s.contains("java.util.Vector."))
// compteur_usage_vector++;
// else if(s.contains("#NEW"))
// compteur_new++;
// else if(s.contains("#GOTO"))
// compteur_goto++;
// else if(s.contains("question1.Ensemble.iterator()Ljava/util/Iterator;"))
// compteur_iterator++;
// }

// assertTrue(0==compteur_addAll,"Aucun appel à la méthode addAll n'est requis???");
// assertTrue(0==compteur_removeAll,"Aucun appel à la méthode removeAll n'est
// requis???");
// assertTrue(0==compteur_retainAll,"Aucun appel à la méthode retainAll n'est
// requis???");
// assertTrue(0==compteur_iterator,"Aucune boucle (foreach, iterator) n'est
// requise???");
// assertTrue(0==compteur_goto,"Aucune boucle (for, while) n'est requise???");
// assertTrue(0==compteur_new_ensemble,"Aucune nouvelle allocation d'ensemble n'est
// requise???");
// assertTrue(0==compteur_new_vector,"Aucune nouvelle allocation de vecteur n'est
// requise???");
// assertTrue(0==compteur_usage_vector,"Aucun appel de méthodes de la classe Vector
// n'est nécessaire ???");

// assertTrue(0==compteur_new,"Au moins une allocation (appel de new) n'est pas
// nécessaire ???");
// assertTrue(1==compteur_union,"Un seul appel à la méthode union est requis ???");
// assertTrue(1==compteur_inter,"Un seul appel à la méthode inter est requis???");
// assertTrue(1==compteur_diff,"Un seul appel à la méthode diff est requis ???");
// }catch(Exception e){
// fail(" dans_diff " + e.getMessage());
// }
// }
