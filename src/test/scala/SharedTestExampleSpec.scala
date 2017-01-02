import org.scalatest.{FlatSpec, Matchers}
import org.scalactic.StringNormalizations.{lowerCased, trimmed}

/**
  * Created by qiucongxian on 1/1/17.
  * 点击右键运行测试的时候, 测试的范围与鼠标所在的位置有关,
  * 这一点对于 Behaviors 尤其重要, 只有鼠标在能表示整个类的位置才会运行 Behavior 的测试
  */
class SharedTestExampleSpec extends FlatSpec with StackBehaviors with Matchers {

    // Stack fixture creation methods
    def emptyStack = new Stack[Int]

    def fullStack = {
        val stack = new Stack[Int]
        for (i <- 0 until stack.MAX)
            stack.push(i)
        stack
    }

    def stackWithOneItem = {
        val stack = new Stack[Int]
        stack.push(9)
        stack
    }

    def stackWithOneItemLessThanCapacity = {
        val stack = new Stack[Int]
        for (i <- 1 to 9)
            stack.push(i)
        stack
    }

    val lastValuePushed = 9

    "A Stack (when empty)" should "be empty" in {
        assert(emptyStack.empty)
    }

    it should "complain on peek" in {
        intercept[IllegalStateException] {
            emptyStack.peek
        }
    }

    it should "complain on pop" in {
        intercept[IllegalStateException] {
            emptyStack.pop
        }
    }

    "A Stack (with one item)" should behave like nonEmptyStack(stackWithOneItem, lastValuePushed)

    it should behave like nonFullStack(stackWithOneItem)

    "A Stack (with one item less than capacity)" should
    behave like nonEmptyStack(stackWithOneItemLessThanCapacity, lastValuePushed)

    it should behave like nonFullStack(stackWithOneItemLessThanCapacity)

    "A Stack (full)" should "be full" in {
        fullStack.full should equal (true)
        assert(fullStack.full)
    }

    it should behave like nonEmptyStack(fullStack, lastValuePushed)

    it should "complain on a push" in {
        intercept[IllegalStateException] {
            fullStack.push(10)
        }
    }


    "A Matcher" should "go through all the tests" in {
        assert(Array(1, 2) === Array(1, 2))
        Array(1, 2) should equal (Array(1, 2))
        val str = "Hello seven world"
        str should startWith ("Hello")
        str should startWith regex "Hel*o"
        str should endWith ("world")
        str should endWith regex "wo.ld"
        str should include ("seven")
        str should include regex "wo.ld"
        "32.34234" should fullyMatch regex """(-)?(\d+)(\.\d*)?"""
        "abbccxxx" should startWith regex ("a(b*)(c*)" withGroups ("bb", "cc"))
        "xxxabbcc" should endWith regex ("a(b*)(c*)" withGroups ("bb", "cc"))
        "xxxabbccxxx" should include regex ("a(b*)(c*)" withGroups ("bb", "cc"))
        "abbcc" should fullyMatch regex ("a(b*)(c*)" withGroups ("bb", "cc"))
        "abcd" shouldBe a [String]
        "abcd" should not be a [Int]

        //checking numbers against a range
        val sevenDotOh = 7.0
        sevenDotOh should equal (6.9 +- 0.2)
        sevenDotOh shouldBe 6.9 +- 0.2

        val seven = 7
        seven should equal (6 +- 2)
        seven shouldBe 6 +- 2

        //checking contain
        (Array("Doe", "Ray", "Me") should contain oneOf ("X", "RAY", "BEAM")) (after being lowerCased)
        (Vector(" A", "B ") should contain atLeastOneOf ("a ", "b", "c")) (after being lowerCased and trimmed)
        List(1, 2, 2, 3, 3, 3) should contain inOrderOnly (1, 2, 3)
        List(1, 2, 2, 3, 3, 3) should contain theSameElementsAs Vector(3, 2, 3, 1, 2, 3)
        List(1, 2, 3, 2, 1) should contain only (1, 2, 3)
        List(1, 2, 3, 4, 5) should contain allOf (2, 3, 5)
    }
}

