import Syntax.ComparableInt
import Syntax.ComparableString
object BDClient extends App{
  
  object Perros extends Table(tableName = "Perros") {
    def nombre = Column[String]("nombre")
    def edad = Column[Int]("edad")
    def *():List[Column[_]] = List(nombre, edad)
  }
  
  val viejitos:Query = Query(Perros).filter(Perros*,Perros.edad > 2)
                                    .filter(Perros*,Perros.nombre == "firulay")
  print(viejitos.showSql)  
  
}



