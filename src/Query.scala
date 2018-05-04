
case class Query(table:Table) {
  var query:String = "select * from "+ table.tableName
  
  def run:ResultSet = null
  def showSql:String = query
  def map:Query = this    
  def filter(condition:Condition[_]):Query = {
    val alias = AliasGenerator.anyAlias
    val newQuery = "select * from (" + query + ") as " + alias + 
                   " where " + alias + "." + condition.eval
    var copy = Query(table)
    copy.query = newQuery
    copy
  }
}