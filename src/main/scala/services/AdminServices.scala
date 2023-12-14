package services

package actors

import data_access.{BookDao, MemberDao}
import data_access.actors.AdminDao
import models.{Book, Member}
import org.mindrot.jbcrypt.BCrypt
import utils.{DatabaseUtils, EventUtils}

object AdminService {
  import models.Admin

  def addAdmin(name: String, password: String): Unit = {
    val admin = Admin(name, password)
    val isSuccess: Boolean = EventUtils.insertEvent("AddAdmin", admin.id, "Admin", Admin.toJson(admin))
    if (isSuccess) {
      AdminDao.insertAdmin(admin)
      println("Admin added successfully")
    } else {
      println("There is an error while trying to add an admin")
    }
  }

  def login(name: String, password: String): Unit = {
    val admin: Option[Admin] = AdminDao.getByUsername(name)

    if (admin.exists(admin => BCrypt.checkpw(password, admin.password))) {
      val isSuccess = EventUtils.insertEvent("Login", admin.get.id, "Admin")
      if (isSuccess) {
        println("Login successfully")
      } else {
        println("There is an error while trying to login")
      }
    } else {
      println("Wrong name or password")
    }
  }

  def addMember(name: String, address: String): Unit = {
    val member = Member(name, address);
    val isSuccess = EventUtils.insertEvent("AddMember", member.id, "Member", Member.toJson(member))
    if (isSuccess) {
      MemberDao.insertMember(member)
      println("Member added successfully")
    } else {
      println("There is an error while trying to add a member")
    }
  }
  def modifyMember(modifiedMember:Member): Unit = {
    val isSuccess = EventUtils.insertEvent("ModifyMember", modifiedMember.id, "Member", Member.toJson(modifiedMember))
    if (isSuccess) {
      MemberDao.updateMember(modifiedMember);
      println("Member modified successfully")
    }else {
      println("There is an error while trying to modify a member")
    }
  }
  def removeMember(id: String): Unit = {
    val isSuccess = EventUtils.insertEvent("RemoveMember", id, "Member")
    if (isSuccess) {
      MemberDao.deleteMember(id)
      println("Member removed successfully")
    } else {
      println("There is an error while trying to remove a member")
    }
  }

  def listMembers(): Unit = {
    val members = MemberDao.getAll
    members.foreach(println)
  }

  def addBook(title: String, quantity: Int): Unit = {
    val book = Book(title, quantity);

    val isSuccess = EventUtils.insertEvent("AddBook", book.id, "Book", Book.toJson(book) )
    if (isSuccess) {
      BookDao.insertBook(book)
      println("Book added successfully")
    } else {
      println("There is an error while trying to add a book")
    }
  }
  def modifyBook(book:Book): Unit = {
    val isSuccess = EventUtils.insertEvent("ModifyBook", book.id, "Book", Book.toJson(book))
    if (isSuccess) {
      BookDao.updateBook(book)
      println("Book modified successfully")
    }else {
      println("There is an error while trying to modify a book")
    }
  }
  def removeBook(id: String): Unit = {
    val isSuccess = EventUtils.insertEvent("RemoveBook", id, "Book")
    if (isSuccess) {
      BookDao.deleteBook(id)
      println("Book removed successfully")
    } else {
      println("There is an error while trying to remove a book")
    }
  }

  def listBooks(): Unit = {
    val books = BookDao.getAll;
    books.foreach(println);
  }

}

