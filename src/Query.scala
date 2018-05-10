
case class Query[ResultType, TableType](table:Table[ResultType, TableType]) {
  var conditions: List[Condition[_]] = List.empty
  def run:ResultSet = {
    val select = Select(this, columns, conditions)
    select.result()
  }
  def showSql:String = query.mapToSql
  def map:Query = this    
  def filter(f: Table[ResultType, TableType] => Condition[_]):Query[ResultType, TableType] = {
    val newQuery = this.copy(table)
    newQuery.conditions = newQuery.conditions.+:(f.apply(table))
  }
}






