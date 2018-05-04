case class Condition[T] (
  val operator:String
  ,val comparable1: Operable[T]
  ,val comparable2: Operable[T]) extends Operable[T]{
  
  def eval:String = comparable1.eval + " " + operator + " " + comparable2.eval
  
}