

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
    var acc:String = ""
    val ret:String = columns.foldLeft(acc){
      (str,col) => str + col.nameColumn + ","
    }
    ret.dropRight(1)
  }
  
  def subselect(columns: List[Column[_]], condition:Option[Condition[_]]):Select = {
    Select(mapToSql,columns,condition)
  }

}