package app

import scala.collection.immutable.ArraySeq.unsafeWrapArray

trait CountSeq {

  // count every unique seq of arbitrary length
  def countSequences(seq: String, minSeqLength: Int, maxSeqLength: Int): Iterable[(String, Int)] = {
    // count function separated to isolate the mutable map
    def count: Iterable[(String, CountRef)] = {
      val map = collection.mutable.Map.empty[String, CountRef]
      (minSeqLength to maxSeqLength).foreach { n =>
        seq
          .sliding(n)
          .foreach { str =>
            map
              .getOrElseUpdate(str, new CountRef)
              .inc()
          }
      }

      map
    }

    unsafeWrapArray(
      count
        // .collect will convert the map to an array buffer so the later conversion
        // to buffer should be nothing more than a type cast
        .collect { case (chSeq, ref) if ref.value > 1 =>
          (chSeq, ref.value)
        }
        .toBuffer
        .sorted(sortOrder)
        .reverse
        .toArray
    )
  }

  private val sortOrder: Ordering[(String, Int)] = {
    case ((_, count1), (_, count2)) =>
      Integer.compare(count1, count2)
  }

}
