package app

import java.nio.file.Files

object App {

  def main(args: Array[String]): Unit = {
    val MenuResult(size, minSeqLength, maxSeqLength) = menu()
    val bw = Files.newBufferedWriter(outputPath)
    try
      countSequences(testString(size), minSeqLength, maxSeqLength)
        .foreach { case (chSeq, count) => bw.write(s"$chSeq,$count\n") }
    finally
      bw.close()
  }
}
