/**
  * Created by qiucongxian on 1/1/17.
  */
import scala.collection.mutable.ListBuffer

class Stack[T] {
    val MAX = 10
    private val buf = new ListBuffer[T]

    def push(o: T): Unit = {
        if (!full)
            buf.prepend(o)
        else
            throw new IllegalStateException("can't push onto a full stack")
    }

    def pop() : T = {
        if(!empty)
            buf.remove(0)
        else
            throw new IllegalStateException("can't pop an empty stack")
    }

    def peek: T = {
        if(!empty)
            buf(0)
        else
            throw new IllegalStateException("can't peek an empty stack")
    }

    def full: Boolean = buf.size == MAX
    def empty: Boolean = buf.size == 0
    def size = buf.size

    override def toString : String = buf.mkString("Stack(", ", ", ")")
}
