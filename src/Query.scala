
case class Query[ResultType, TableType](table: Table[ResultType, TableType]) {
  var conditions: List[Condition[_]] = List.empty
  var columns: List[Column[_]] = table.*
  def run:ResultSet[ResultType] = {
    val select = Select(this, columns, conditions)
    select.run
  }
  
  def map[OtherResultType](f: table.type => Column[OtherResultType]): Query[OtherResultType, TableType] = {
    val column = f.apply(table)
    val newTable = TableMapping[OtherResultType, TableType](table.tableName, table.*)
    val newQuery = Query[OtherResultType, TableType](newTable)
    newQuery.columns = List(column)
    return newQuery
  }
  def filter(f: Table[ResultType, TableType] => TableType => Condition[_]):Query[ResultType, TableType] = {
    val newQuery = this.copy(table)
    newQuery.conditions = newQuery.conditions.+:(f.apply(table).apply(table))
    return newQuery
  }
}






