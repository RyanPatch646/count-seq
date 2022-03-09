package app

import java.nio.charset.Charset
import java.nio.file.Files

trait TestStringGenerator {

  // non-positive values indicate
  def testString(size: Int): String =
    if (size > 0) makeTestString(size)
    else Files.readString(testStringPath)

  private def makeTestString(nCharacters: Int): String = {
    val str = new String(
      java.util.concurrent.ThreadLocalRandom
        .current()
        .ints(nCharacters, 97, 123)
        .toArray
        .map(_.toByte),
      Charset.forName("ASCII")
    )

    Files.writeString(testStringPath, str)
    str
  }
}


