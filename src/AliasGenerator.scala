object AliasGenerator{
  var lastAlias:String = "a"
  def aliasUsed:Map[Table,String] = Map()

  def anyAlias():String = {
    val alias = lastAlias
    lastAlias = (lastAlias.head + 1).toChar.toString
    alias
  }
}