import collection.mutable.{ListBuffer, Stack}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by qiucongxian on 12/31/16.
  */
class ExampleSpec extends FlatSpec with Matchers {
    "A Stack" should  "pop values in last-in-first-out order" in {
        val stack = new Stack[Int]
        stack.push(1)
        stack.push(2)
        stack.pop() should be (2)
        //assert(stack.pop() == 2)
    }

    ignore should "throw NoSuchElementException if an empty stack is popped" in {
        val emptyStack = new Stack[String]
        intercept[NoSuchElementException] {
            emptyStack.pop()
        }
    }

    def myfixture = new {
        val builder = new StringBuilder("ScalaTest is ")
        val buffer = new ListBuffer[String]
    }

    "Testing" should "be easy" in {
        val f = myfixture
        f.builder.append("fun!")
        assert(f.builder.toString === "ScalaTest is fun!")
        assert(f.buffer.isEmpty)
    }

    it should "be fun" in {
        val f = myfixture
        f.builder.append("fun!")
        assert(f.builder.toString === "ScalaTest is fun!")
        assert(f.buffer.isEmpty)
    }
}
