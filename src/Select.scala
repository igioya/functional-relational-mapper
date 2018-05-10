

case class Select(query:Query, columns: List[Column[_]], where:Option[Condition[_]]){
  def run = ResultSet()
  def mapToSql = {
    var sql:String = query.subquery match {
      case Some(subquery) => "SELECT " + evalColumnsWithAlias(query.table.tableName) + "FROM (" + subquery.showSql + ") AS " + query.table.tableName
      case None        => "SELECT " + evalColumns() + "FROM" + query.table.tableName
    }
    
    where match {
      case Some(condition) => sql + " WHERE " + condition.eval
      case None => sql
    }    
  }
  
  def evalColumnsWithAlias(alias:String)
  def evalColumns():String = {
    var acum:String = ""
    val sqlColumns:String = columns.foldLeft(acum){
      (str,col) => str + col.nameColumn + ","
    }
    sqlColumns.dropRight(1)
  }
  
  def subselect(condition:Option[Condition[_]]):Select = {
    Select(mapToSql,columns,condition)
  }

}