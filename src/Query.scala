import sun.invoke.empty.Empty


class Query[TableType <: Table, ResultType](val table: TableType){
  
  var columns: List[Column[_]] = table.*
  var conditions: List[Condition[_]] = List.empty
  var limitData: List[LimitData] = List.empty
  
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
  
  def take(limit:Integer):Query[TableType, ResultType] = {
    val limitData = Limit(limit)
    val q = queryWithLimitData(limitData)
    return q
  }
  
  def drop(offset:Integer):Query[TableType, ResultType] = {
    val limitData = Offset(offset)
    val q = queryWithLimitData(limitData)
    return q
  }
  
  def select(sqlEngine: RelationalEngine): ResultSet[ResultType] = {
    val sel = new Select(this, sqlEngine)
    return sel.resultSet();
    
  }
  
  def queryWithLimitData(limitData:LimitData):Query[TableType, ResultType] = {
    val q = new Query[TableType, ResultType](table)
    q.columns = this.columns
    q.conditions = this.conditions
    q.limitData = List().++(this.limitData).++(List(limitData))
    q
  }
}

trait LimitData {
  val value:Integer
}

case class Limit(val value:Integer) extends LimitData {}
case class Offset(val value:Integer) extends LimitData {}


