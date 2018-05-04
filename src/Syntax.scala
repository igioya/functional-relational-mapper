

object Syntax {
  implicit class ComparableInt(val value: Int) extends Operable[Int]{
    def eval = value.toString()  
    def toComparable = value.asInstanceOf[Operable[Int]]
  }
}