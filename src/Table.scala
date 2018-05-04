
abstract class Table(val tableName:String) {
  def *():List[Column[_]]
}

