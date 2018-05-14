import Syntax.ComparableInt
import Syntax.ComparableString
object BDClient extends App{
  
  class Perro(val nombre: String, val edad: Int)
  class PerroTable extends Table[Perro, PerroTable]() {
    val tableName = "Perros"
    def nombre = Column[String]("nombre", tableName)
    def edad = Column[Int]("edad", tableName)
    def *():List[Column[_]] = List(nombre, edad)
  }
  object Perros extends PerroTable{}
  def query[ResultType, TableType <: Table[ResultType, TableType]](mapping: Table[ResultType, TableType]):
  Query[ResultType, TableType] = Query(mapping)
  
  
  val viejitos = query(Perros)                                  

  val asd = query(Perros).filter((x) => x.)
  
}


