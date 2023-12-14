package models

// commands
sealed class Command
//case class AddAdmin(name: String, password: String)

sealed trait Events
case class AdminAddedSuccessfully(admin: Admin)
