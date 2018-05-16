trait RelationalCondition{
  def createWhere(query:Query[_,_], queryString:String):String = {
    query.conditions match{
      case Nil => queryString
      case s   => queryString + " WHERE " + s.map(cond => cond.column1.eval + " " + cond.operator + " " + cond.column2.eval) 
                                             .mkString(" AND ")                                         
    }
  } 
}


class Select[TableType <: Table, ResultType](query: Query[TableType,ResultType], relationalEngine: RelationalEngine) extends RelationalCondition{
  def resultSet(): ResultSet[ResultType] = {
    var queryString: String = "SELECT " + query.columns.map(c => c.columnName).mkString(", ") + "  FROM " + query.table.tableName
    queryString = createWhere(query,queryString)    
    queryString = addOrder(queryString)
    queryString = addLimitData(queryString) 
    print(queryString)
    
    return relationalEngine.run(queryString)
  }
  
  def addOrder(queryString:String): String = {
    query.orders match {
      case Nil => queryString
      case ls => queryString + " ORDER BY " + ls.map(order => order.column.columnName + " " + order.orderEnum).mkString(", ")
    }
  }
  
  def addLimitData(queryString:String):String = {
    queryString + " " + query.limitData.map(data => data.name + " " + data.value).mkString(" ")
  }
}

class Delete[TableType <: Table, ResultType](query: Query[TableType,ResultType], relationalEngine: RelationalEngine) extends RelationalCondition{
  def resultSet():Unit = {
    var queryString = "DELETE FROM " + query.table.tableName
    queryString = createWhere(query,queryString)    
    print(queryString)
  }
}

class Update[TableType <: Table, ResultType, ColumnType](query: Query[TableType,ResultType], column:Column[ColumnType], value:ColumnType ) extends RelationalCondition{
  def resultSet:Unit = {
    var queryString = "UPDATE " + query.table.tableName + "("+ column.columnName +") SET " + column.columnName + " = " + value
    queryString = createWhere(query,queryString)    
    print(queryString)
  }
}