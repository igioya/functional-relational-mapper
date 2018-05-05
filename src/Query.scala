
case class Query(table:Table) {
  var query:Select = Select(table.tableName, table*, None)
  
  def run:ResultSet = null
  def showSql:String = query.mapToSql
  def map:Query = this    
  def filter(columns: List[Column[_]],condition:Condition[_]):Query = {
    val newQuery = query.subselect(columns, Some(condition))
    var copy = Query(table)
    copy.query = newQuery
    copy
  }
}







