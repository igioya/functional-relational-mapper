

case class Select(from:String, columns: List[Column[_]], where:Option[Condition[_]]){
  def run = ResultSet()
  def mapToSql = {
    var sql:String = "SELECT " + evalColumns(columns) + " FROM (" + from + ")" 
    where match {
      case Some(condition) => sql + " WHERE " + condition.eval
      case None => sql
    }    
  }
  
  def evalColumns(columns: List[Column[_]]):String = {
    var acum:String = ""
    val sqlColumns:String = columns.foldLeft(acum){
      (str,col) => str + col.nameColumn + ","
    }
    sqlColumns.dropRight(1)
  }
  
  def subselect(columns: List[Column[_]], condition:Option[Condition[_]]):Select = {
    Select(mapToSql,columns,condition)
  }

}