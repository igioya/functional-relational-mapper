class Condition[ColumnType](
  val operator: String,
  val column1: Operable[ColumnType],
  val column2: Operable[ColumnType])