
trait Table[ResultType, TableType <: Table[ResultType, TableType]]{
  val tableName:String
  def *():List[Column[_]]
}

case class TableMapping[ResultType, TableType](val tableName: String, val columns: List[Column[_]]) extends Table[ResultType, TableType]{
  def *():List[Column[_]] = columns
}