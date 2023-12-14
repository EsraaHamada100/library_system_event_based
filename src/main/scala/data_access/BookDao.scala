package data_access

import models.{Admin, Book}
import utils.DatabaseUtils

object BookDao {
  def insertBook(book: Book): Unit = {
    val insertSql: String = s"INSERT INTO books (id, title, quantity) VALUES ('${book.id}', '${book.title}', '${book.quantity}')"
    DatabaseUtils.executeCreateUpdateDeleteQuery(insertSql)
  }

  def updateBook(book: Book): Unit = {
    val sql = s"UPDATE books SET title = '${book.title}', quantity = '${book.quantity}' WHERE id = '${book.id}'"
    DatabaseUtils.executeCreateUpdateDeleteQuery(sql)
  }

  def updateQuantity(id:String, quantity:Int): Unit = {
    val sql = s"UPDATE books SET quantity = '${quantity}' WHERE id = '${id}'"
    DatabaseUtils.executeCreateUpdateDeleteQuery(sql)
  }

  def deleteBook(id:String): Unit = {
    val sql = s"DELETE FROM books WHERE id = '$id'"
    DatabaseUtils.executeCreateUpdateDeleteQuery(sql)
  }

  def getAll: List[Book] = {
    val sql = "SELECT * FROM books"
    val resultData: List[Map[String, String]] = DatabaseUtils.executeReadQuery(sql)
    val books = resultData.map { resultSet =>
      val book = Book(resultSet)
      book
    }
    books
  }

  def getById(id:String): Option[Book] = {
    val sql = s"SELECT * FROM books WHERE id = '$id'"
    val resultData: List[Map[String, String]] = DatabaseUtils.executeReadQuery(sql);
    resultData.headOption.map { resultSet =>
      val book = Book(resultSet)
      book
    }
  }
}
