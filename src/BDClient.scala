
class BDClient {
  object Perros extends Table(tableName = "Perros") {
    def nombre = Column[String]("nombre")
    def edad = Column[Int]("edad")
    def * = (nombre, edad)
  }
  
  val viejitos:Query = Query(Perros).filter(Perros.edad > 12)
  
}

