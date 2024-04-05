package question3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MarkSetFactory {

  private Z_ReferenceSetFactoryTests test;

  @BeforeEach
  public void setUp() {
    test = new Z_ReferenceSetFactoryTests();
    test.setUp();
  }

  @Test
  public void test_HashSetFactory() {
    test.test_HashSetFactory();
  }

  @Test
  public void ignore_test_TreeSetFactory() {
    test.test_TreeSetFactory();
  }

  @Test
  public void test_TreeSetFactoryComparable1() {
    test.test_TreeSetFactoryComparable1();
  }

  @Test
  public void test_HashSetFactoryNonComparable() {
    test.test_HashSetFactoryNonComparable();
  }

  @Test
  public void ignore_test_TreeSetFactoryComparable2() {
    test.ignore_test_TreeSetFactoryComparable2();
  }

  @Test
  public void test_Factory_create() throws Exception {
    test.test_Factory_create();
  }
}
