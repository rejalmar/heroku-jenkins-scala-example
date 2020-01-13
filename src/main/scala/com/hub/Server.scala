//package com.hub
//
//import com.twitter.finagle.{Http, Service}
//import com.twitter.util.{Await, Future}
//import com.twitter.finagle.http
//import org.slf4j.Logger
//import org.slf4j.LoggerFactory
//
//import util.Properties
//
//object Server {
//  def main(args: Array[String]) {
//    val port = Properties.envOrElse("PORT", "8085").toInt
//    println("Starting on port: " + port)
//
//// below if not using a pool
////    val driver = new org.postgresql.Driver()
//
//    val server = Http.serve(":" + port, new Hello)
//    Await.ready(server)
//  }
//}
//
//class Hello extends Service[http.Request, http.Response] {
//  val log = LoggerFactory.getLogger(classOf[Hello])
//  log.info("Hello from Hello service class")
//  def apply(request: http.Request): Future[http.Response] = {
//    if (request.uri.endsWith("/db")) {
//      log.info("showDatabase")
//      showDatabase(request)
//    } else {
//      log.info("showHome")
//      showHome(request)
//    }
//  }
//
//  def showHome(request: http.Request): Future[http.Response] = {
//    val response = http.Response()
//
////    RelativisticModel.select()
////    val m = Amount.valueOf("12 GeV").to(KILOGRAM)
//    val m = "random text"
//    response.setContentString("Hi there: E=mc^2: 12 GeV = " + m)
//
//    Future(response)
//  }
//
//  def showDatabase(request: http.Request): Future[http.Response] = {
////    val connection = DBConnHolder.getConnection
//    val connection = Datasource.connectionPool.getConnection
//    val stmt = connection.createStatement
//    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)")
//    stmt.executeUpdate("INSERT INTO ticks VALUES (now())")
//
//    val rs = stmt.executeQuery("SELECT tick FROM ticks")
//
//    var out = ""
//    while (rs.next) {
//      out += "Read from DB: " + rs.getTimestamp("tick") + "\n"
//    }
//    connection.close()
//
//    val response = http.Response()
//    response.setContentString(out)
//    Future(response)
//  }
//
//}
