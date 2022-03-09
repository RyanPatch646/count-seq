package app

import scala.annotation.tailrec
import scala.io.StdIn

trait Menu {
  case class MenuResult(size: Int,
                        minSeqLength: Int,
                        maxSeqLength: Int)

  def menu(): MenuResult = {

    @tailrec
    def menu1(first: Boolean = true): Int = {
      if (first) print {
        s"""Hello, would you like to:
           |   1. read existing string from $testStringPath
           |   2. make a new test string at $testStringPath
           |""".stripMargin
      }

      StdIn.readByte() match {
        case 1 => -1
        case 2 =>
          println("how many characters do you want in the random string?")
          StdIn.readInt()
        case _ =>
          println("please try again")
          menu1(first = false)
      }
    }

    @tailrec
    def menu2(first: Boolean = true,
              min: Int = 3,
              max: Int = 10): (Int, Int) = {
      if (first) print {
        s"""Would you like to change the value of:
           |   1. the smallest seq to count (currently: $min)
           |   2. the largest seq to count (currently: $max)
           |   3. accept current values
           |""".stripMargin
      }

      StdIn.readByte() match {
        case 1 =>
          println("Enter min seq length:")
          menu2(min = StdIn.readInt(), max = max)
        case 2 =>
          println("Enter max seq length:")
          menu2(min = min, max = StdIn.readInt())
        case 3 =>
          (min, max)
        case _ =>
          println("please try again")
          menu2(first = false, max, min)
      }
    }

    println("Run with defaults? (y/n)")
    if (StdIn.readChar() == 'y') MenuResult(
      10000,
      minSeqLength = 3,
      maxSeqLength = 10
    ) else {
      val size = menu1()
      val (min, max) = menu2()
      MenuResult(size, min, max)
    }
  }
}
