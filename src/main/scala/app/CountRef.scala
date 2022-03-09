package app

final class CountRef(private var n: Int = 0) {
  @inline def inc(): Unit = n += 1
  @inline def value: Int = n
  @inline override def toString: String = n.toString
}
