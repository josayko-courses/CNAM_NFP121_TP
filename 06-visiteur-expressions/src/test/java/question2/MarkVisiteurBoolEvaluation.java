package question2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MarkVisiteurBoolEvaluation {

  Z_TestsVisiteurBoolEvaluation test;

  @BeforeEach
  public void setUp() {
    test = new Z_TestsVisiteurBoolEvaluation();
    test.setUp();
  }

  @Test
  public void ignore_test_BooleanEnRetourDe_accepter() {
    test.ignore_test_BooleanEnRetourDe_accepter();
  }

  @Test
  public void testVraiFaux() {
    test.testVraiFaux();
  }

  @Test
  public void testSup() {
    test.testSup();
  }

  @Test
  public void testNonSup() {
    test.testNonSup();
  }

  @Test
  public void testInf() {
    test.testInf();
  }

  @Test
  public void testNonInf() {
    test.testNonInf();
  }

  @Test
  public void testEgal() {
    test.testEgal();
  }

  @Test
  public void testNonEgal() {
    test.testNonEgal();
  }

  @Test
  public void test_TableVérité_Ou() {
    test.test_TableVérité_Ou();
  }

  @Test
  public void test_TableVérité_Non_Ou() {
    test.test_TableVérité_Non_Ou();
  }

  @Test
  public void test_TableVérité_Et() {
    test.test_TableVérité_Et();
  }

  @Test
  public void test_TableVérité_Non_Et() {
    test.test_TableVérité_Non_Et();
  }

  @Test
  public void test_TableVérité_Non() {
    test.test_TableVérité_Non();
  }
}
