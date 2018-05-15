class Select[TableType <: Table, ResultType](query: Query[TableType,ResultType], relationalEngine: RelationalEngine){
  def resultSet(): ResultSet[ResultType] = {
    var queryString: String = "SELECT " + query.columns.map(c => c.columnName).mkString(", ") + "  FROM " + query.table.tableName
    queryString = query.conditions match{
      case Nil => queryString
      case s   => queryString + " WHERE " + s.map(cond => cond.column1.eval + " " + cond.operator + " " + cond.column2.eval)
                                             .mkString(" AND ")
    }
    return relationalEngine.run(queryString)
  }
}