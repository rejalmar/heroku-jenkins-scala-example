package com.example

import com.twitter.finagle.{Http, Service}
import com.twitter.util.{Await, Future}
import com.twitter.finagle.http.Response
import java.net.InetSocketAddress

import org.jboss.netty.handler.codec.http._

import util.Properties
import java.net.URI
import java.sql.Connection
import java.sql.DriverManager

object Server {
  def main(args: Array[String]) {
    val port = Properties.envOrElse("PORT", "8085").toInt
    println("Starting on port: " + port)

//    val driver = new org.postgresql.Driver()
    //    java.sql.DriverManager.registerDriver(driver)

    val server = Http.serve(":" + port, new Hello)
    Await.ready(server)
  }
}

//object DBConnHolder {
//
//  def getConnection(): Connection = {
//    connection
//  }
//
//  private lazy val connection = createConnection()
//
//  // see https://jdbc.postgresql.org/documentation/head/connect.html
//
//  private def createConnection(): Connection = {
//    //    val dbUri = new URI(System.getenv("DATABASE_URL"))
//    //    val dbUriStr = "postgres://rvjgzaxmfirtxf:e4fe0997c8dbb7b5c76b7c645b2ccb548f72df2a17eaeed487a58bb12f1490e7@ec2-184-73-192-172.compute-1.amazonaws.com:5432/d8u8pgptt8dli6?sslmode=require"
//
//    val url = "jdbc:postgresql://ec2-184-73-192-172.compute-1.amazonaws.com:5432/d8u8pgptt8dli6?sslmode=require&user=rvjgzaxmfirtxf&password=e4fe0997c8dbb7b5c76b7c645b2ccb548f72df2a17eaeed487a58bb12f1490e7"
//    val conn = java.sql.DriverManager.getConnection(url)
//
//    // Below also works
//    //    val url = "jdbc:postgresql://ec2-184-73-192-172.compute-1.amazonaws.com:5432/d8u8pgptt8dli6?sslmode=require"
//    //    val username = "rvjgzaxmfirtxf"
//    //    val password = "e4fe0997c8dbb7b5c76b7c645b2ccb548f72df2a17eaeed487a58bb12f1490e7"
//    //    val conn = DriverManager.getConnection(url, username, password)
//
//    //    val url = "jdbc:postgresql://ec2-184-73-192-172.compute-1.amazonaws.com/d8u8pgptt8dli6"
//    //    val props = new java.util.Properties();
//    //    props.setProperty("user", "rvjgzaxmfirtxf")
//    //    props.setProperty("password", "e4fe0997c8dbb7b5c76b7c645b2ccb548f72df2a17eaeed487a58bb12f1490e7")
//    //    props.setProperty("sslmode", "require")
//    //    val conn = java.sql.DriverManager.getConnection(url, props)
//
//    //    props.setProperty("ssl", "true")
//
//    conn
//  }
//
//
//}

class Hello extends Service[HttpRequest, HttpResponse] {
  def apply(request: HttpRequest): Future[HttpResponse] = {
    if (request.getUri.endsWith("/db")) {
      showDatabase(request)
    } else {
      showHome(request)
    }
  }

  def showHome(request: HttpRequest): Future[HttpResponse] = {
    val response = Response()

//    RelativisticModel.select()
//    val m = Amount.valueOf("12 GeV").to(KILOGRAM)
    val m = "random text"
    response.setContentString("Hi there: E=mc^2: 12 GeV = " + m)

    Future(response)
  }

  def showDatabase(request: HttpRequest): Future[HttpResponse] = {
//    val connection = DBConnHolder.getConnection
    val connection = Datasource.connectionPool.getConnection
    val stmt = connection.createStatement
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)")
    stmt.executeUpdate("INSERT INTO ticks VALUES (now())")

    val rs = stmt.executeQuery("SELECT tick FROM ticks")

    var out = ""
    while (rs.next) {
      out += "Read from DB: " + rs.getTimestamp("tick") + "\n"
    }

    val response = Response()
    response.setContentString(out)
    Future(response)
  }

}
