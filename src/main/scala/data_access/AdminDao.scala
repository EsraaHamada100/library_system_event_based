package data_access

package actors

import models.{Admin, Member}
import utils.DatabaseUtils

object AdminDao {
  def getByUsername(name: String): Option[Admin] = {
    val sql = s"SELECT * FROM Admins WHERE name = '$name'"
    val resultData: List[Map[String, String]] = DatabaseUtils.executeReadQuery(sql)

    resultData.headOption.map { resultSet =>
      val admin = Admin(resultSet)
      admin
    }
  }
  def insertAdmin(admin: Admin): Unit = {
    val insertSql: String = s"INSERT INTO Admins (id, name, password) VALUES ('${admin.id}', '${admin.name}', '${admin.password}')"
    DatabaseUtils.executeCreateUpdateDeleteQuery(insertSql)
  }

}

