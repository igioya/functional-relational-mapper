

trait Table{
  type ResultType
  type TableType <: Table
  
  val tableName: String
  def *(): List[Column[_]]
}