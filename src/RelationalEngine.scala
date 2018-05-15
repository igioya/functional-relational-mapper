trait RelationalEngine{
  def run[ResultType](query: String): ResultSet[ResultType]
}