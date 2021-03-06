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
  
  def query[T <: Table](t: T): Query[T, t.ResultType] = {
    return new Query[T, t.ResultType](t)
  }
  
//  val q = query(Perros)
//  val a = q.map(_.nombre)
//  val b = a.filter(_.edad > 3)
//  val c = b.select(new SqlEngine)
  
//  val d = query(Perros).take(4).drop(7).orderBy(_.edad,OrderByEnum.ASC).select(new SqlEngine)
  
  val x = query(Perros).filter(_.edad > 3).delete(new SqlEngine)
  val y = query(Perros).filter(_.edad > 2).update(_.edad, 3)
  
//  val d = q.filter(_.nombre > 3)
//  val e = q.filter(_.asd > 3)
  
  
}

class SqlEngine extends RelationalEngine{
  def run[ResultType](q: String): ResultSet[ResultType] = {
    return new ResultSet
  }
}