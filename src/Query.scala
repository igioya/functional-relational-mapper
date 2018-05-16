import sun.invoke.empty.Empty


class Query[TableType <: Table, ResultType](
    val table: TableType,
    val tempColumns: List[Column[_]] = List.empty,
    val conditions: List[Condition[_]] = List.empty,
    val limitData: List[LimitData] = List.empty,
    val orders: List[Order] = List.empty){
  
   
  
  val columns = tempColumns match {
    case Nil => table.*()
    case ls => ls
  }
//  def updateFunction(xxs: List[Column[_]]): _ => List[Any] = xxs match {
//    case Nil => () => List.empty
//    case x::xs => (y: x.type) => List(y).++(updateFunction(xs))
//  }
//  type UpdateFunctionType = updateFunction.type
//  
//  type columnTypes = columns.type
//  val subquery: Query
  def map[OtherResultType](f: TableType => Column[OtherResultType]): Query[TableType,OtherResultType]  = {
    new Query[TableType, OtherResultType](table,List(f.apply(table)), this.conditions)
  }
  
  def filter(f: TableType => Condition[_]): Query[TableType, ResultType] = {
    new Query[TableType, ResultType](table, this.columns, this.conditions.+:(f.apply(table)),this.limitData)
  }
  
  def take(limit:Integer):Query[TableType, ResultType] = {
    val limitData = Limit(limit.toString())
    val q = queryWithLimitData(limitData)
    return q
  }
  
  def drop(offset:Integer):Query[TableType, ResultType] = {
    val limitData = Offset(offset.toString())
    val q = queryWithLimitData(limitData)
    return q
  }
  
  def orderBy(f: TableType => Column[_], order:OrderByEnum.Value):Query[TableType, ResultType] = {
    val ordersTemp  = this.orders.+:(Order(f.apply(table), order))
    val q = new Query[TableType, ResultType](table, this.columns, this.conditions, this.limitData, ordersTemp)
    return q
  }
  
  def select(sqlEngine: RelationalEngine): ResultSet[ResultType] = {
    val sel = new Select(this, sqlEngine)
    return sel.resultSet();
  }
  
  def delete(sqlEngine: RelationalEngine): Unit = {
    val del = new Delete(this,sqlEngine)
    del.resultSet();
  }
  
  def update[T](f: TableType => Column[T], value: T): Unit = {
    val upd = new Update(this, f.apply(table),value)
    upd.resultSet;
  }
  
  def queryWithLimitData(limitData:LimitData):Query[TableType, ResultType] = {
    val limitDataTemp = List().++(this.limitData).++(List(limitData))
    new Query[TableType, ResultType](table, this.columns, this.conditions,limitDataTemp)
  }
}

trait LimitData {
  val value:String
  val name:String
}

case class Limit(val value:String) extends LimitData {val name:String = "LIMIT"}
case class Offset(val value:String) extends LimitData {val name:String = "OFFSET"}

case class Order(val column: Column[_], val orderEnum: OrderByEnum.Value)
object OrderByEnum extends Enumeration(){
  val ASC, DESC = Value
}

