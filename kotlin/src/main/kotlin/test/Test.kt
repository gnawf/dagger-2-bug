package test

import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import javax.inject.Inject
import javax.inject.Scope

fun main(vararg args: String) {
    DaggerTestComponent.create().tests().forEach(Test::test)
    // DaggerTestComponent.create().testContainer()
}

@Scope
annotation class TestScoped

@TestScoped
@Component(modules = [TestModule::class])
interface TestComponent {
    fun tests(): Set<Test>

    // Uncomment this line and build fails
    // fun testContainer(): TestContainer
}

@TestScoped
class TestContainer @Inject constructor(tests: Set<Test>) {
    init {
        println(tests.size)
    }
}

abstract class Test {
    fun test() {
        println("Test ${javaClass.name}")
    }
}

@TestScoped
class Test1 @Inject constructor() : Test()

@TestScoped
class Test2 @Inject constructor() : Test()

// @Module
// interface TestModule {
// @Binds
// @ElementsIntoSet
// fun tests(test1: Test1, test2: Test2): Set<Test>
// }

@Module
object TestModule {
    @Provides
    @ElementsIntoSet
    fun tests(test1: Test1, test2: Test2): Set<Test> {
        return hashSetOf(test1, test2)
    }
}
