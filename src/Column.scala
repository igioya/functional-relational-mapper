case class Column[T](val columnName: String) extends Operable[T]{
  
  var valor: Option[T] = None
  
  def >(column: Operable[T]):Condition[T] = {
    val cond = new Condition[T]() 
    cond.operator   = ">"
    cond.column1    = this
    cond.column2    = column
    return cond
  }
  
  def eval = columnName
}