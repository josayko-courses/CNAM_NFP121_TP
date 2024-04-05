package question1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MarkEnsemble {
  public Z_ReferenceEnsembleTest test;

  @BeforeEach
  public void setUp() {
    test = new Z_ReferenceEnsembleTest();
    test.setUp();
  }

  @Test
  public void test_Ensemble_Unicite() {
    test.test_Ensemble_Unicite();
  }

  @Test
  public void test_Ensemble_Union() {
    test.test_Ensemble_Union();
  }

  @Test
  public void test_Ensemble_Union_1() { // {} union {3,4}
    test.test_Ensemble_Union_1();
  }

  @Test
  public void test_Ensemble_Union_2() { // {2,3} union {}
    test.test_Ensemble_Union_2();
  }

  @Test
  public void test_Ensemble_Union_3() { // {} union {}
    test.test_Ensemble_Union_3();
  }

  @Test
  public void test_Ensemble_D_Elements_Quelconques() {
    test.test_Ensemble_D_Elements_Quelconques();
  }

  @Test
  public void test_instance_non_modifiée_par_union() {
    test.test_instance_non_modifiée_par_union();
  }

  @Test
  public void test_instance_non_modifiée_par_inter() {
    test.test_instance_non_modifiée_par_inter();
  }

  @Test
  public void test_instance_non_modifiée_par_diff() {
    test.test_instance_non_modifiée_par_diff();
  }

  @Test
  public void test_instance_non_modifiée_par_diffSym() {
    test.test_instance_non_modifiée_par_diffSym();
  }

  @Test
  public void test_Ensemble_add_null() {
    test.test_Ensemble_add_null();
  }

  @Test
  public void test_Ensemble_Intersection() {
    test.test_Ensemble_Intersection();
  }

  @Test
  public void test_Ensemble_Intersection_1() {
    test.test_Ensemble_Intersection_1();
  }

  @Test
  public void test_Ensemble_Intersection_2() {
    test.test_Ensemble_Intersection_2();
  }

  @Test
  public void test_Ensemble_Intersection_3() {
    test.test_Ensemble_Intersection_3();
  }

  @Test
  public void test_Ensemble_Diff() {
    test.test_Ensemble_Diff();
  }

  @Test
  public void test_Ensemble_Diff_1() {
    test.test_Ensemble_Diff_1();
  }

  @Test
  public void test_Ensemble_Diff_2() {
    test.test_Ensemble_Diff_2();
  }

  @Test
  public void test_Ensemble_Diff_3() {
    test.test_Ensemble_Diff_3();
  }

  @Test
  public void test_Ensemble_DiffSym() {
    test.test_Ensemble_DiffSym();
  }

  @Test
  public void test_Ensemble_DiffSym_1() {
    test.test_Ensemble_DiffSym_1();
  }

  @Test
  public void test_Ensemble_DiffSym_2() {
    test.test_Ensemble_DiffSym_2();
  }

  @Test
  public void test_Ensemble_DiffSym_3() {
    test.test_Ensemble_DiffSym_3();
  }

  @Test
  public void test_Ensemble_non_modifié() {
    test.test_Ensemble_non_modifié();
  }

  @Test
  public void test_Ensemble_non_modifié_bis() {
    test.test_Ensemble_non_modifié_bis();
  }

  @Test
  public void test_Ensemble_de_String() {
    test.test_Ensemble_de_String();
  }
}
