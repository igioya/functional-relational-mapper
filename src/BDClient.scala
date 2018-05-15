import Syntax.ComparableInt
import Syntax.ComparableString
object BDClient extends App{
  
  class Perro(val nombre: String, val edad: Int)
  class PerroTable extends Table() {
    type ResultType = Perro
    type TableType = PerroTable
    
    val tableName: String = "Perros"
    def nombre: Column[String] = Column("nombre")
    def edad: Column[Int] = Column("edad")
    def *():List[Column[_]] = List(nombre, edad)
  }
  object Perros extends PerroTable
  
  def query[T <: Table, R](t: T): Query[T, R] = {
    return new Query(t)
  }
  val q = query(Perros)
  val a = q.map(_.nombre)
  val b = a.filter(_.edad > 3)
  val c = b.select()
  
//  val d = q.filter(_.nombre > 3)
//  val e = q.filter(_.asd > 3)
  
  
}










class ResultSet[ResultType]()