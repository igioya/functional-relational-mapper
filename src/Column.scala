
case class Column[T](nameColumn:String, nameTable:String) extends Operable[T]{
  def > (column: Operable[T]):Condition[T] = {
    return Condition[T](">", this, column)
  }
  
  def == (column: Operable[T]):Condition[T] = {
    return Condition[T]("=", this, column)
  }
  def eval = nameColumn
}

  