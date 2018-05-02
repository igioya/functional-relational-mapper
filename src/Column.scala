

case class Column[T](nameColumn:String) {
  val value:T
  def > (column: Column[T]):Condition[T] = {
    return Condition[T](">", this, column)
  }
}

case class Condition[T](
  val operator:String
  ,val column1: Column[T]
  ,val column2: Column[T]){
  
  def toSql(){
    return column1.nameColumn + " " + operator + " " +column2.nameColumn
  }
}