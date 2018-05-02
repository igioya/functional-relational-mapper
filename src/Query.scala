

case class Query(table:Table) {
  var query:String = "select * from "+ table.tableName
  
  def map:Query = this    
  def filter(f: Table => Condition[_]):Query = {
    val condition = f.apply(table)
    val alias = aliasGenerator.newAlias
    val newQuery = "select * from (" + query + ") as " + alias + 
                   "where " + alias + "." + condition.column1.nameColumn + condition
    this.copy(query = newQuery)
  }
  def run:ResultSet = null
  
}

object aliasGenerator{
  def newAlias:String = "a"
}