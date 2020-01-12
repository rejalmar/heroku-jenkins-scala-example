import NativePackagerKeys._

packageArchetype.java_application

name := """scala-getting-started"""

version := "1.0"

//scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "com.twitter" % "finagle-http_2.10" % "6.18.0",
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "org.apache.commons" % "commons-dbcp2" % "2.0.1"
)

herokuJdkVersion in Compile := "1.7"

herokuAppName in Compile := "infinite-dawn-11170"

