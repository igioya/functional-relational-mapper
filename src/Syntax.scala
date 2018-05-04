

object Syntax {
  implicit class ComparableInt(val value: Int) extends Operable[Int]{
    def eval = value.toString()  
    def toComparable = value.asInstanceOf[Operable[Int]]
  }
  implicit class ComparableString(val value: String) extends Operable[String]{
    def eval = value  
    def toComparable = value.asInstanceOf[Operable[String]]
  }
}