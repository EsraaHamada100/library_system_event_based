
import actors.{AdminActor, MemberActor}
import actors.AdminActor.{AddAdmin, AddBook, AddMember, ListBooks, ListMembers, Login, ModifyBook, ModifyMember, RemoveBook, RemoveMember}
import actors.MemberActor.{BorrowBook, ReturnBook}
import akka.actor.{ActorSystem, Props}
import models.{Book, Borrowing, Member}

import java.time.LocalDate


object LibrarySystem extends App {
    private val librarySystem = ActorSystem("LibrarySystem")
    val admin = librarySystem.actorOf(Props[AdminActor])
    val member = librarySystem.actorOf(Props[MemberActor])
//    member ! BorrowBook(Borrowing("58c10f49-1188-42ea-ba7e-a75bc8d00bfe","298233de-29d5-46bb-beea-10e0aa74dd84","shelf_1", LocalDate.now().plusDays(7)))
//    admin ! Login("Shrouq", "123")
//    admin ! AddMember("Eman_Member", "123-street")
//    admin ! ModifyMember(Member("Eman_Member", "123-street"))
//    admin ! RemoveMember("9bd90d62-38c3-4f0e-91bf-eb44970337db")
//    admin ! ListMembers()

//    admin ! AddBook("Book1", 1)
//    admin ! AddBook("Book2", 2)
//    admin ! AddBook("Book3")
//    admin ! ListBooks()
//    admin ! RemoveBook("8cf3359c-a858-43b2-9403-fd729ebdee5c");

//    admin ! ModifyBook(Book("889902a0-a413-4c97-8e43-5617419ee3a8", "Book1", 0))
//    member ! BorrowBook(Borrowing("cc6bec37-7e35-4dfa-90ae-b8edeffe33bc","9155ab57-81be-44f9-b063-76f468e247a5","shelf_1", LocalDate.now().plusDays(7)))
    member ! ReturnBook("18fd44dc-624a-474e-bea7-e1d92579b575");
}



