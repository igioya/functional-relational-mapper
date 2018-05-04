import Syntax.ComparableInt
object BDClient extends App{
  
  object Perros extends Table(tableName = "Perros") {
    def nombre = Column[String]("nombre")
    def edad = Column[Int]("edad")
    def * = (nombre, edad)
  }
  
  val viejitos:Query = Query(Perros).filter(Perros.edad > 2 AND Perros.edad > 2)
  print(viejitos.showSql)  
  
}



