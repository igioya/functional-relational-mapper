
abstract class BDClient {
  import Utils.ComparableInt
  object Perros extends Table(tableName = "Perros") {
    def nombre = Column[String]("nombre")
    def edad = Column[Int]("edad")
    def * = (nombre, edad)
  }
  
  val viejitos:Query = Query(Perros).filter((table) => null)
  Perros.edad > 2
}


object Utils{
  
  implicit class ComparableInt(val value: Int) extends Comparable[Int]{
    def eval = value.toString()  
    def toComparable = value.asInstanceOf[Comparable[Int]]
  }
}



