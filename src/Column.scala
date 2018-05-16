case class Column[T](val columnName: String) extends Operable[T]{
  type Tipo = T
  var valor: Option[T] = None
  
  def >(column: Operable[T]):Condition[T] = {
    new Condition[T](">",this,column)    
  }
  
  def eval = columnName
}