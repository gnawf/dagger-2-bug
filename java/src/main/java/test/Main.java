package test;

import dagger.Binds;
import dagger.Component;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Scope;

public class Main {

  public static void main(String[] args) {
    DaggerTestComponent.create().testContainer();
  }
}

@Scope
@interface TestScoped {

}

@TestScoped
@Component(modules = {TestModule.class})
interface TestComponent {

  Set<Test> tests();

  TestContainer testContainer();
}

@TestScoped
class TestContainer {

  @Inject
  TestContainer(Set<Test> tests) {
    System.out.println(tests.size());
  }
}

abstract class Test {

  void test() {
    System.out.println("Test " + getClass().getName());
  }
}

@TestScoped
class Test1 extends Test {

  @Inject
  Test1() {
  }
}

@TestScoped
class Test2 extends Test {

  @Inject
  Test2() {
  }
}

// @Module
// interface TestModule {
//
// @Binds
// @ElementsIntoSet
// Set<Test> tests(Test1 test1, Test2 test2);
// }

@Module
class TestModule {

  @Provides
  @ElementsIntoSet
  static Set<Test> tests(Test1 test1, Test2 test2) {
    return new LinkedHashSet<>(List.of(test1, test2));
  }
}
