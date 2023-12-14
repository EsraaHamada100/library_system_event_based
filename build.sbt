ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "library_system"
  )
libraryDependencies += "com.typesafe.akka" %% "akka-persistence-typed" % "2.8.0"
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.33"

libraryDependencies += "org.mindrot" % "jbcrypt" % "0.4"
