package data_access

import models.Borrowing
import utils.DatabaseUtils

object BorrowingDao {
  def insertBorrowing(borrowing: Borrowing): Unit = {
    val insertSql:String = s"INSERT INTO borrowings (id, user_id, book_id, location, due_date) VALUES ('${borrowing.id}', '${borrowing.memberId}', '${borrowing.bookId}', '${borrowing.location}', '${borrowing.returnDate}')"
    DatabaseUtils.executeCreateUpdateDeleteQuery(insertSql)
  }
  def deleteBorrowing(id:String): Unit = {
    val sql = s"DELETE FROM borrowings WHERE id = '$id'"
    DatabaseUtils.executeCreateUpdateDeleteQuery(sql)
  }
  def getById(id:String): Option[Borrowing] = {
    val sql = s"SELECT * FROM borrowings WHERE id = '$id'"
    val resultData: List[Map[String, String]] = DatabaseUtils.executeReadQuery(sql);
    resultData.headOption.map {
      resultSet =>
      val borrowing: Borrowing = Borrowing(resultSet)
      borrowing
    }
  }

}
