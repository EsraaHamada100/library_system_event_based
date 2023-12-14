package services

import data_access.{BookDao, BorrowingDao}
import models.{Book, Borrowing}
import utils.EventUtils

object MemberService {
  def borrowBook(borrowing: Borrowing): Unit = {
    // check the availability of the book
    val book: Option[Book] = BookDao.getById(borrowing.bookId)
    if(book.isEmpty) {
      println("Book not found")
    }else if(book.get.quantity == 0){
        println("Book not available")
    }else {
      val isSuccess = EventUtils.insertEvent("BorrowBook", borrowing.id, "Borrowing", Borrowing.toJson(borrowing))
      if(isSuccess) {
        BorrowingDao.insertBorrowing(borrowing)
        BookDao.updateQuantity(borrowing.bookId, book.get.quantity-1 )
        println("Book borrowed successfully")
      }else {
        println("Failed to borrow book")
      }
    }



  }
  def returnBook(borrowingId:String): Unit = {
    val borrowing: Option[Borrowing] = BorrowingDao.getById(borrowingId)
    if(borrowing.isEmpty){
      println("Borrowing not found")
      return
    }
    val isSuccess = EventUtils.insertEvent("ReturnBook", borrowingId, "Borrowing")
    if(isSuccess) {
      BorrowingDao.deleteBorrowing(borrowingId)
      val book: Option[Book] = BookDao.getById(borrowing.get.bookId)
      BookDao.updateQuantity(borrowing.get.bookId, book.get.quantity+1)
      if(borrowing.get.fine){
        println("Book returned successfully and you should pay a fine because  you are a person who is not committed to deadlines.")
      }else {
        println("Book returned successfully")
      }
    }

  }
}
