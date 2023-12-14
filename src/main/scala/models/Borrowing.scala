package models

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID

case class Borrowing(id:String, memberId:String, bookId:String, location:String, returnDate:LocalDate, fine:Boolean=false);

object Borrowing {
  // insert data into database
  def apply(memberId:String, bookId:String, location:String, returnDate:LocalDate): Borrowing = {
    val id = UUID.randomUUID().toString
    new Borrowing(id, memberId, bookId,location, returnDate)
  }

  // retrieve data
  def apply(borrowingData: Map[String, String]): Borrowing = {
    val dateString: String = borrowingData("due_date")
    // Split the date and time using whitespace as the separator
    val dateParts: Array[String] = dateString.split("T")
    // Take the first part which is the date
    val datePortion: String = dateParts.head

    // Define the formatter for the date format
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    // Parse the string into a LocalDate
    val localDate: LocalDate = LocalDate.parse(datePortion, formatter)
    val fine: Boolean = localDate.isBefore(LocalDate.now())

    new Borrowing(borrowingData("id"), borrowingData("user_id"), borrowingData("book_id"), borrowingData("location"), localDate, fine)
  }

  def toJson(borrowing: Borrowing): String = {
    s"""{"id": "${borrowing.id}", "memberId": "${borrowing.memberId}", "bookId": "${borrowing.bookId}", "location": "${borrowing.location}", "returnDate": "${borrowing.returnDate}"}"""
  }


}
