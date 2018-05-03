
case class Column[T](nameColumn:String) extends Comparable[T]{
  def > (column: Comparable[T]):Condition[T] = {
    return Condition[T](">", this, column)
  }
  def eval = nameColumn
}

case class Condition[T](
  val operator:String
  ,val comparable1: Comparable[T]
  ,val comparable2: Comparable[T]){
  
  def toSql(){
    return comparable1.eval + " " + operator + " " + comparable2.eval
  }
}

trait Comparable[T]{
  def eval:String
}

  