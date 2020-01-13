import NativePackagerKeys._

scalaVersion := "2.12.10"

packageArchetype.java_application

name := """scala-getting-started"""

version := "1.0"

val Http4sVersion = "0.20.15"
val CirceVersion = "0.11.1"
val Specs2Version = "4.1.0"
val LogbackVersion = "1.2.3"

//scalaVersion := "2.10.4"

// https://mvnrepository.com/artifact/com.twitter/finagle-http
libraryDependencies += "com.twitter" %% "finagle-http" % "19.12.0"

libraryDependencies ++= Seq(
//  "com.twitter" % "finagle-http_2.10" % "6.18.0",
//  "com.twitter" %% "finagle-http" % "6.18.0",
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "org.apache.commons" % "commons-dbcp2" % "2.0.1",
  "org.http4s"      %% "http4s-blaze-server" % Http4sVersion,
  "org.http4s"      %% "http4s-blaze-client" % Http4sVersion,
  "org.http4s"      %% "http4s-circe"        % Http4sVersion,
  "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
  "io.circe"        %% "circe-generic"       % CirceVersion,
  "org.specs2"      %% "specs2-core"         % Specs2Version % "test",
  "ch.qos.logback"  %  "logback-classic"     % LogbackVersion
)
//

addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.10.3")

//
addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.0")
//

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Ypartial-unification",
  "-Xfatal-warnings"
)

herokuJdkVersion in Compile := "1.8"

herokuAppName in Compile := "infinite-dawn-11170"

//val Http4sVersion = "0.20.15"
//val CirceVersion = "0.11.1"
//val Specs2Version = "4.1.0"
//val LogbackVersion = "1.2.3"
//
//lazy val root = (project in file("."))
//  .settings(
//    organization := "com.example",
//    name := "htt4ssamp",
//    version := "0.0.1-SNAPSHOT",
//    scalaVersion := "2.12.10",
//    libraryDependencies ++= Seq(
//      "org.http4s"      %% "http4s-blaze-server" % Http4sVersion,
//      "org.http4s"      %% "http4s-blaze-client" % Http4sVersion,
//      "org.http4s"      %% "http4s-circe"        % Http4sVersion,
//      "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
//      "io.circe"        %% "circe-generic"       % CirceVersion,
//      "org.specs2"      %% "specs2-core"         % Specs2Version % "test",
//      "ch.qos.logback"  %  "logback-classic"     % LogbackVersion
//    ),
//    addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.10.3"),
//    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.0")
//  )
//
//scalacOptions ++= Seq(
//  "-deprecation",
//  "-encoding", "UTF-8",
//  "-language:higherKinds",
//  "-language:postfixOps",
//  "-feature",
//  "-Ypartial-unification",
//  "-Xfatal-warnings",
//)
