package actors
import actors.MemberActor.{BorrowBook, ReturnBook}
import akka.actor.Actor
import models.Borrowing
import services.MemberService
class MemberActor extends Actor{
  def receive: Receive = { // Receiving message
    case BorrowBook(borrowing: Borrowing)=>MemberService.borrowBook(borrowing)
    case ReturnBook(borrowingId:String)=>
      MemberService.returnBook(borrowingId)
  }
}

object MemberActor {
  case class BorrowBook(borrowing: Borrowing)
  case class ReturnBook(borrowingId: String)
}


