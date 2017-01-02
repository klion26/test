import scala.collection.mutable

/**
  * Created by qiucongxian on 12/31/16.
  */
class MySpec extends UnitSpec {
    "MySpec" should "Test fdfdd" in {
        val a = new mutable.Stack[Int]
        a.push(1)
        a.push(2)
        assert(a.pop() == 2)

        val b = 5
        val c = 3
        assertResult(2) {
            b - c
        }

        assertThrows[IndexOutOfBoundsException] {
            "fdjkfd".charAt(-1)
        }

        //assert(3 === 4, "this is a clue")

        val s = "hi"
        val caught = intercept[IndexOutOfBoundsException] {
            s.charAt(-1)
        }
        assert(caught.getMessage.indexOf("-1") != -1)

        assertDoesNotCompile("val a: String = 1")
        assertCompiles("val a : Int = 3")
    }
}
