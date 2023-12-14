package models


import java.util.UUID

case class Member(id: String, name:String, address:String)

object Member {

  // store the data coming from SELECT query
  def apply(id: String, name: String, address: String): Member = {
    new Member(id, name, address)
  }
  // getting the entered data and process it
  def apply(name:String, address:String): Member = {
    val id: String = UUID.randomUUID().toString

    new Member(id, name, address);
  }
  // to store the data retrieved from database
  def apply(memberData:  Map[String, String]): Member = {
    new Member(memberData("id"), memberData("name"), memberData("address"));
  }

  def toJson(member: Member): String = {
    s"""{"id": "${member.id}", "name": "${member.name}", "address": "${member.address}"}"""
  }

}




