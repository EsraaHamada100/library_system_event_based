package data_access

import models.Member
import utils.DatabaseUtils

object MemberDao {

  def insertMember(member: Member): Unit = {
    val insertSql: String = s"INSERT INTO members (id, name, address) VALUES ('${member.id}', '${member.name}', '${member.address}')"
    DatabaseUtils.executeCreateUpdateDeleteQuery(insertSql)
  }
  def updateMember(member: Member): Unit = {
    val sql = s"UPDATE members SET name = '${member.name}', address = '${member.address}' WHERE id = '${member.id}'"
    DatabaseUtils.executeCreateUpdateDeleteQuery(sql)
  }
  def deleteMember(id:String): Unit = {
    val sql = s"DELETE FROM members WHERE id = '$id'"
    DatabaseUtils.executeCreateUpdateDeleteQuery(sql)
  }
  def getAll: List[Member] = {
    val sql = "SELECT * FROM members"
    val resultData: List[Map[String, String]] = DatabaseUtils.executeReadQuery(sql)
    val members = resultData.map { resultSet =>
      val member = Member(resultSet("id"), resultSet("name"), resultSet("address"))
      member
    }
    members
  }
}
