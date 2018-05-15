

trait Table{
  type ResultType
  type TableType
  
  val tableName: String
  def *(): List[Column[_]]
}