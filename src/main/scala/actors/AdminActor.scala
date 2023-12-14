package actors

import actors.AdminActor._
import akka.actor.Actor
import akka.persistence.typed.scaladsl.{Effect, EventSourcedBehavior}
import models._
import org.mindrot.jbcrypt.BCrypt
import services.actors.AdminService
import utils.{DatabaseUtils, EventUtils}

import java.sql.{ResultSet, Timestamp}
import java.util.UUID
class  AdminActor  extends  Actor{
  override
  def receive:Receive = {
    case AddAdmin(name, password) =>AdminService.addAdmin(name, password)
    case Login(name, password) => AdminService.login(name, password)
    case AddMember(name, location) => AdminService.addMember(name, location)
    case ModifyMember(modifiedMember:Member)=> AdminService.modifyMember(modifiedMember:Member)
    case RemoveMember(id) => AdminService.removeMember(id)
    case ListMembers() => AdminService.listMembers()
    case AddBook(title, quantity) => AdminService.addBook(title, quantity)
    case ModifyBook(book:Book) => AdminService.modifyBook(book:Book)
    case RemoveBook(title) => AdminService.removeBook(title)
    case ListBooks() => AdminService.listBooks()
  }
}



object AdminActor {
  case class AddAdmin(name:String, password:String);
  case class Login(name:String, password:String);
  case class AddMember(name:String, address: String);
  case class ModifyMember(member:Member);
  case class RemoveMember(id:String);
  case class ListMembers();
  case class AddBook(title:String, quantity:Int= 1);
  case class ModifyBook(book:Book);
  case class RemoveBook(id:String);
  case class ListBooks();

}
