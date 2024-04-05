package question1;

import java.util.regex.Pattern;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import util.InfoClass;
import util.InfoMethod;

@TestInstance(Lifecycle.PER_CLASS) // Une seule instance pour tous les tests.
public class MarkPresenceAppelsDeMethodes {
  private static final String Q1ENSEMBLE = "/question1/Ensemble.class";

  @SuppressWarnings("unused")
  private static class E {
    public static final String UNION =
        "question1.Ensemble.union(Lquestion1/Ensemble;)Lquestion1/Ensemble;";
    public static final String INTER =
        "question1.Ensemble.inter(Lquestion1/Ensemble;)Lquestion1/Ensemble;";
    public static final String DIFF =
        "question1.Ensemble.diff(Lquestion1/Ensemble;)Lquestion1/Ensemble;";
    public static final String RETAINALL = "question1.Ensemble.retainAll(Ljava/util/Collection;)Z";
    public static final String REMOVEALL = "question1.Ensemble.removeAll(Ljava/util/Collection;)Z";
    public static final String ADDALL = "question1.Ensemble.addAll(Ljava/util/Collection;)Z";
    public static final String INIT = "question1.Ensemble.<init>()V";
    public static final String CONTAINS = "question1.Ensemble.contains(Ljava/lang/Object;)Z";
    public static final String ITERATOR = "question1.Ensemble.iterator()Ljava/util/Iterator;";
  }

  private class V {
    public static final String INIT = "java.util.Vector.<init>()V";
    public static final String CONTAINS = "java.util.Vector.contains(Ljava/lang/Object;)Z";
    public static final String ADD = "java.util.Vector.add(Ljava/lang/Object;)Z";
  }

  private static final Pattern INIT_RE = pat(".*<init>.*");
  private static final Pattern VECTOR_RE = pat(".*java\\.util\\.Vector\\..*");
  private static final Pattern ITERATOR_RE = pat(".*iterator\\(\\)Ljava/util/Iterator;");

  // Il suffit de faire toString sur this.info pour voir ces noms de méthode
  @SuppressWarnings("unused")
  private class K { // Clé du callMap: signature des méthode de Ensemble
    public static String ADD = "public boolean add(Object t) [Signature: (TT;)Z]";
    public static String DIFFSYM =
        "public question1.Ensemble diffSym(question1.Ensemble e) [Signature: (Lquestion1/Ensemble<+TT;>;)Lquestion1/Ensemble<TT;>;]";
    public static String INTER =
        "public question1.Ensemble inter(question1.Ensemble e) [Signature: (Lquestion1/Ensemble<+TT;>;)Lquestion1/Ensemble<TT;>;]";
    public static String DIFF =
        "public question1.Ensemble diff(question1.Ensemble e) [Signature: (Lquestion1/Ensemble<+TT;>;)Lquestion1/Ensemble<TT;>;]";
    public static String ITERATOR =
        "public java.util.Iterator iterator() [Signature: ()Ljava/util/Iterator<TT;>;]";
    public static String SIZE = "public int size()";
    public static String INIT_COL =
        "public void <init>(java.util.Collection c) [Signature: (Ljava/util/Collection<+TT;>;)V]";
    public static String INIT = "public void <init>()";
    public static String UNION =
        "public question1.Ensemble union(question1.Ensemble e) [Signature: (Lquestion1/Ensemble<+TT;>;)Lquestion1/Ensemble<TT;>;]";
  }

  // contient une map stockant le nb d'appels à chaque méthode dans chaque méthode de test.
  InfoClass info;
  InfoMethod add, union, inter, diff, dfsym;

  @BeforeAll
  public void setUp2() throws Exception {
    // On lance la collecte d'infos (une seule fois avant les tests)
    // On regarde toutes les méthodes et pas seulement celles de test.
    info = new InfoClass(Q1ENSEMBLE);
    add = info.get(K.ADD);
    union = info.get(K.UNION);
    inter = info.get(K.INTER);
    diff = info.get(K.DIFF);
    dfsym = info.get(K.DIFFSYM);
  }

  private static Pattern pat(String regexp) {
    return Pattern.compile(regexp);
  }

  @Test
  public void test_addCallsThisContains() {
    add.assertNbCallsGreater(E.CONTAINS, 1);
  }

  @Test
  public void test_addNoCallsVectorContains() {
    // Faut-il tester ceci seulement si il n'y a pas d'appel à this.contains? Bof.
    add.assertNbCallsLesser(
        V.CONTAINS, 0, "L'appel à this.contains est préféré à table.contains (pourquoi ?)");
  }

  @Test
  public void test_NoNewEnsemble_dans_add() {
    add.assertNbCallsLesser(E.INIT, 0, "Appel à new Ensemble manquant?");
  }

  @Test
  public void test_addNoNewVector() {
    String msg =
        "Il ne faut pas de new Vector mais new Ensemble, "
            + "cela diminue le couplage entre Ensemble et Vector.";
    add.assertNbCallsLesser(V.INIT, 0, msg);
  }

  @Test
  public void test_NoNew_dans_add() {
    String msg = "Au moins une allocation (appel de new) n'est pas nécessaire ???";
    add.assertNbCalls(INIT_RE, n -> n <= 0, msg);
  }

  @Test
  public void test_appel_vadd_dans_add() {
    add.assertNbCalls(V.ADD, n -> n >= 1, "Pas d'ajout dans la table ??");
  }

  @Test
  public void test_pop_dans_add() {
    String msg = "le résultat de add est-il perdu ??";
    if (add.getNbOcc(V.ADD) >= 1) add.assertNbCallsLesser("#pop#", 0, msg);
  }

  @Test
  public void test_appels_newVect_dans_union() {
    union.assertNbCallsLesser(V.INIT, 0);
  }

  @Test
  public void test_appels_othernew_dans_union() {
    String msg =
        "Au moins une allocation (appel de new) n'est pas nécessaire ???.\n"
            + "Le seul new autorisé doit être sur Ensemble (ni Vector, ni Iterator, etc).\n"
            + "Cela diminue le couplage entre Ensemble et les autres classes.";
    int nb_new_ens = union.getNbOcc(E.INIT); // les new Ensemble
    // pas d'autre new que celui d'Ensemble.
    union.assertNbCallsLesser(INIT_RE, nb_new_ens, msg);
  }

  @Test
  public void test_appels_newEns_dans_union() {
    union.assertNbCallsLesser(E.INIT, 1, "Un seul Ensemble est à créer ???");
  }

  @Test
  public void test_appels_EnsaddAll_dans_union() {
    // System.out.println("union =" + union.getNbOcc(E.ADDALL));
    union.assertNbCallsGreater(E.ADDALL, 2, "Deux appels à la méthode addAll sont requis???");
  }

  @Test
  public void test_boucle_dans_union() { // les boucle sont en général compilées vers un goto
    union.assertNbCallsLesser("#goto#", 0, "Aucune boucle (for, while, foreach) n'est requise ???");
  }

  @Test
  public void test_iterator_dans_union() {
    String msg = "Aucune boucle (foreach, iterator) n'est requise ???";
    union.assertNbCallsLesser(ITERATOR_RE, 0, msg);
  }

  @Test
  public void test_vector_dans_union() {
    String msg = "Aucun appel de méthodes de la classe Vector n'est nécessaire ???";
    union.assertNbCallsLesser(VECTOR_RE, 0, msg);
  }

  @Test
  public void test_appels_EnsaddAll_dans_inter() {
    inter.assertNbCallsLesser(E.ADDALL, 1, "Un seul appel à la méthode addAll est requis ???");
  }

  @Test
  public void test_appels_EnsretainAll_dans_inter() {
    inter.assertNbCallsLesser(
        E.RETAINALL, 1, "Un seul appel à la méthode retainAll est requis ???");
  }

  @Test
  public void test_appels_newVect_dans_inter() {
    inter.assertNbCallsLesser(V.INIT, 0, "Aucune nouvelle allocation de vecteur n'est requise ???");
  }

  @Test
  public void test_appels_othernew_dans_inter() {
    String msg =
        "Au moins une allocation (appel de new) n'est pas nécessaire ???.\n"
            + "Le seul new autorisé doit être sur Ensemble (ni Vector, ni iterator, etc).";
    inter.assertNbCallsLesser(INIT_RE, inter.getNbOcc(E.INIT), msg);
  }

  @Test
  public void test_appels_newEns_dans_inter() {
    inter.assertNbCallsLesser(E.INIT, 1, "Un seul Ensemble est à créer ???");
  }

  @Test
  public void test_boucle_dans_inter() { // les boucle sont en général compilées vers un goto
    inter.assertNbCallsLesser("#goto#", 0, "Aucune boucle (for, while, foreach) n'est requise ???");
  }

  @Test
  public void test_iterator_dans_inter() {
    inter.assertNbCallsLesser(
        ITERATOR_RE, 0, "Aucune boucle (foreach, iterator) n'est requise ???");
  }

  @Test
  public void test_vector_dans_inter() {
    inter.assertNbCallsLesser(
        VECTOR_RE, 0, "Aucun appel de méthodes de la classe Vector n'est nécessaire ???");
  }

  @Test
  public void test_VRetainAll_dans_diff() {
    diff.assertNbCallsLesser(E.RETAINALL, 0, "Aucun appel à la méthode retainAll n'est requis???");
  }

  @Test
  public void test_EIterator_dans_diff() {
    diff.assertNbCallsLesser(ITERATOR_RE, 0, "Aucun appel un itérateur n'est requis???");
  }

  @Test
  public void test_boucle_dans_diff() {
    diff.assertNbCallsLesser("#goto#", 0, "Aucune boucle (for, while) n'est requise???");
  }

  @Test
  public void test_vector_dans_diff() {
    String msg = "Aucun appel de méthodes de la classe Vector n'est nécessaire ???";
    diff.assertNbCallsLesser(VECTOR_RE, 0, msg);
  }

  @Test
  public void test_appels_newEns_dans_diff() {
    diff.assertNbCallsLesser(E.INIT, 1, "Un seul Ensemble est à créer ???");
  }

  @Test
  public void test_appels_newVect_dans_diff() {
    diff.assertNbCallsLesser(V.INIT, 0, "Aucune nouvelle allocation de vecteur n'est requise ???");
  }

  @Test
  public void test_appels_othernew_dans_diff() {
    String msg =
        "Au moins une allocation (appel de new) n'est pas nécessaire ???.\n"
            + "Le seul new autorisé doit être sur Ensemble (ni Vector, ni Iterator, etc).\n"
            + "Cela diminue le couplage entre Ensemble et les autres classes.";
    int nb_new_ens = union.getNbOcc(E.INIT); // les new Ensemble
    // pas d'autre new que celui d'Ensemble.
    diff.assertNbCallsLesser(INIT_RE, nb_new_ens, msg);
  }

  @Test
  public void test_appels_EnsaddAll_dans_diff() {
    // System.out.println("union =" + union.getNbOcc(E.ADDALL));
    diff.assertNbCallsLesser(E.ADDALL, 1, "Un seul appel à la méthode addAll est requis ???");
  }

  @Test
  public void test_appels_ERemAll_dans_diff() {
    // System.out.println("union =" + union.getNbOcc(E.ADDALL));
    diff.assertNbCallsLesser(E.REMOVEALL, 1, "Un seul appel à la méthode addAll est requis ???");
  }

  @Test
  public void test_appels_EnsaddAll_dans_diffsym() {
    dfsym.assertNbCallsLesser(E.ADDALL, 1, "Un seul appel à la méthode addAll est requis ???");
  }

  @Test
  public void test_appels_EnsRemAll_dans_diffsym() {
    dfsym.assertNbCallsLesser(E.REMOVEALL, 0, "Aucun appel à la méthode removeAll n'est requis???");
  }

  @Test
  public void test_appels_EnsRetAll_dans_diffsym() {
    dfsym.assertNbCallsLesser(E.RETAINALL, 0, "Aucun appel à la méthode retainAll n'est requis???");
  }

  @Test
  public void test_EIterator_dans_diffsym() {
    dfsym.assertNbCallsLesser(ITERATOR_RE, 0, "Aucun appel un itérateur n'est requis???");
  }

  @Test
  public void test_boucle_dans_diffsym() {
    dfsym.assertNbCallsLesser("#goto#", 0, "Aucune boucle (for, while) n'est requise???");
  }

  @Test
  public void test_newVec_dans_diffsym() {
    dfsym.assertNbCallsLesser(V.INIT, 0, "Aucune nouvelle allocation de vecteur n'est requise???");
  }

  @Test
  public void test_vec_dans_diffsym() {
    String msg = "Aucun appel de méthodes de la classe Vector n'est nécessaire ???";
    dfsym.assertNbCallsLesser(VECTOR_RE, 0, msg);
  }

  @Test
  public void test_EUnion_dans_diffsym() {
    String msg = "Un seul appel à la méthode union est requis ???";
    dfsym.assertNbCallsLesser(E.UNION, 1, msg);
  }

  @Test
  public void test_EInter_dans_diffsym() {
    String msg = "Un seul appel à la méthode inter est requis ???";
    dfsym.assertNbCallsLesser(E.INTER, 1, msg);
  }

  @Test
  public void test_EDiff_dans_diffsym() {
    String msg = "Un seul appel à la méthode diff est requis ???";
    dfsym.assertNbCallsLesser(E.DIFF, 1, msg);
  }
}
