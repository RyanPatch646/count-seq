import java.nio.file.Path

package object app extends CountSeq with Menu with TestStringGenerator {

  val testStringPath: Path = Path.of("target/testString.txt")
  val outputPath: Path = Path.of("target/out.csv")
}
