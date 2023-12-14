package models

import java.util.UUID;
import org.mindrot.jbcrypt.BCrypt;

case class Admin(id: String, name:String, password:String=null);

object Admin {
//  // to store the data retrieved from database
//  def apply(id:String, name: String): Admin = {
//    new Admin(id, name);
//  }
  // object for logging in data when returned
  def apply(id: String, name: String, password: String): Admin = {
    new Admin(id, name, password)
  }

  // store the data coming from SELECT query
  def apply(adminData:  Map[String, String]): Admin = {
    new Admin(adminData("id"), adminData("name"));
  }
  // getting the entered data and process it
  def apply(name:String, password:String): Admin = {
    val id: String = UUID.randomUUID().toString
    val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())

    new Admin(id, name, hashedPassword);
  }

  def toJson(admin: Admin): String = {
    s"""{"id": "${admin.id}", "name": "${admin.name}", "password": "${admin.password}"}"""
  }

}



