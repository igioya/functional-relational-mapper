
trait Table[ResultType, TableType <: Table[ResultType, TableType]{
  val tableName:String
  def *():List[Column[_]]
}
