
class Query[TableType <: Table, ResultType](val table: TableType){
  
  var columns: List[Column[_]] = table.*
  var conditions: List[Condition[_]] = List.empty
  
  def map[OtherResultType](f: TableType => Column[OtherResultType]): Query[TableType,OtherResultType]  = {
    val q = new Query[TableType, OtherResultType](table)
    q.columns = List(f.apply(table))
    q.conditions = this.conditions
    
    return q
  }
  def filter(f: TableType => Condition[_]): Query[TableType, ResultType] = {
    val q = new Query[TableType, ResultType](table)
    q.columns = this.columns
    q.conditions = this.conditions.+:(f.apply(table))
    
    return q
  }
  
  def select(sqlEngine: RelationalEngine): ResultSet[ResultType] = {
    val sel = new Select(this, sqlEngine)
    return sel.resultSet();
    
  }
}



