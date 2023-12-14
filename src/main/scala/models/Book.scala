package models

import java.util.UUID

case class Book(id:String,title:String, quantity:Int);

object Book {
  // when creating new book
  def apply(title: String, quantity: Int): Book = {
    val id = UUID.randomUUID().toString;
    new Book(id, title, quantity);
  }
  // when retrieve books from database
  def apply(bookData:  Map[String, String]): Book = {
    new Book(bookData("id"), bookData("title"), bookData("quantity").toInt);
  }
  def toJson(book: Book): String={
    s"""{"id": "${book.id}", "title": "${book.title}", "quantity": "${book.quantity}"}"""
  }
}
