package app

import scala.annotation.{switch, tailrec}
import scala.io.StdIn

trait Menu {
  case class MenuResult(size: Int,
                        minSeqLength: Int,
                        maxSeqLength: Int)

  def menu(): MenuResult = {

    def readInput(): Int = {
      StdIn
        .readLine()
        .trim
        .toIntOption
        .getOrElse {
          println("value must be a valid positive integer! Please try again.")
          readInput()
        }
    }

    @tailrec
    def menu1(first: Boolean = true): Int = {
      if (first) print {
        s"""Hello, would you like to:
           |   1. read existing string from $testStringPath
           |   2. make a new test string at $testStringPath
           |""".stripMargin
      }

     (readInput(): @switch) match {
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
    def menu2(min: Int = 3,
              max: Int = 10,
              displayOptions: Boolean = true): (Int, Int) = {
      if (displayOptions) print {
        s"""Would you like to change the value of:
           |   1. the smallest seq to count (currently: $min)
           |   2. the largest seq to count (currently: $max)
           |   3. accept current values
           |""".stripMargin
      }

      (readInput(): @switch) match {
        case 1 =>
          println("Enter min seq length:")
          menu2(min = readInput(), max = max)
        case 2 =>
          println("Enter max seq length:")
          menu2(min = min, max = readInput())
        case 3 =>
          if (min > max) {
            println("min must be less than or equal the value set for max")
            menu2(min, max)
          } else {
            (min, max)
          }
        case _ =>
          println("please review the menu options and try again.")
          menu2(min, max, displayOptions = false)
      }
    }

    println("Run with defaults? (y/n)")
    if (StdIn.readChar() == 'y')
      MenuResult(size = 10000, minSeqLength = 3, maxSeqLength = 10)
    else {
      val size = menu1()
      val (min, max) = menu2()
      MenuResult(size, min, max)
    }
  }
}
