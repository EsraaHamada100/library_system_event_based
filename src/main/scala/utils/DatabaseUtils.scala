// connection with database and querying functions

package utils

import java.sql.{Connection, DriverManager, ResultSet, Statement}

object DatabaseUtils {
  // Define your database connection parameters
  private val driver = "com.mysql.cj.jdbc.Driver"
  private val url = "jdbc:mysql://localhost:3306/library_system"
  private val username = "root"
  private val password = "root"

  // Establish a database connection
  def getConnection(): Connection = {
    try{
      Class.forName(driver)
      DriverManager.getConnection(url, username, password)
    }catch{
      case e: Exception => e.printStackTrace
        null
    }
  }

  // Execute a SELECT query and process the ResultSet
  def executeReadQuery(query: String): List[Map[String, String]] = {
    var connection: Connection = null
    var statement: Statement = null
    var resultSet: ResultSet = null
    var resultData: List[Map[String, String]] = List();
    try {
      connection = getConnection()
      statement = connection.createStatement()
      resultSet = statement.executeQuery(query)
      while (resultSet.next()) {
        // Extract data from the current row and store it as a map
        val metaData = resultSet.getMetaData
        val columnCount = metaData.getColumnCount
        val rowData = (1 to columnCount).map { i =>
          val columnName = metaData.getColumnName(i)
          // if he is a string column, get the value as a string
          // else convert the value to a string
          val columnType = metaData.getColumnType(i)
          var columnValue:String = "";
          if (columnType == java.sql.Types.VARCHAR) {
            columnValue = resultSet.getString(i)
          }else {
            columnValue = resultSet.getObject(i).toString
          }
//          val columnValue:String = resultSet.getObject(i).asInstanceOf[String]
          columnName -> columnValue
        }.toMap
        resultData = resultData :+ rowData
      }

    } catch {
      case e: Exception => e.printStackTrace()

    } finally {
      closeResources(connection, statement, resultSet)

    }
    resultData
  }


  def executeCreateUpdateDeleteQuery(query: String): Boolean = {
    var connection: Connection = null
    var statement: Statement = null
    var resultSet: ResultSet = null

    try {
      connection = getConnection()
      statement = connection.createStatement()
      val rowsAffected: Int = statement.executeUpdate(query)
      // Close the resources
      statement.close()
      connection.close()
      if (rowsAffected > 0) true
      else false


    } catch {
      case e: Exception =>
        e.printStackTrace()
        false
    } finally {
      closeResources(connection, statement, resultSet)
    }
  }
  // Close database resources
  private def closeResources(connection: Connection, statement: Statement, resultSet: ResultSet): Unit = {
    try {
      if (resultSet != null) resultSet.close()
      if (statement != null) statement.close()
      if (connection != null) connection.close()
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
}


