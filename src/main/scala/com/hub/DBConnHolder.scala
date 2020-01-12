package com.hub

import java.net.URI
import java.sql.Connection
import org.apache.commons.dbcp2.BasicDataSource

object Datasource {
  val defaultDBUrl="postgres://rvjgzaxmfirtxf:e4fe0997c8dbb7b5c76b7c645b2ccb548f72df2a17eaeed487a58bb12f1490e7@ec2-184-73-192-172.compute-1.amazonaws.com:5432/d8u8pgptt8dli6?sslmode=require"
  val envDBUrl = System.getenv("DATABASE_URL")
  val urlStr = if(envDBUrl!=null){
    System.out.println("Using Env string")
    envDBUrl
  } else {
    System.out.println("Using default DBUrl")
    defaultDBUrl
  }
  val dbUri = new URI(urlStr)
  val dbUrl = s"jdbc:postgresql://${dbUri.getHost}:${dbUri.getPort}${dbUri.getPath}"
  val connectionPool = new BasicDataSource()

  if (dbUri.getUserInfo != null) {
    connectionPool.setUsername(dbUri.getUserInfo.split(":")(0))
    connectionPool.setPassword(dbUri.getUserInfo.split(":")(1))
  }
  connectionPool.addConnectionProperty("sslmode","require")
  connectionPool.setDriverClassName("org.postgresql.Driver")
  connectionPool.setUrl(dbUrl)
  connectionPool.setInitialSize(1)
}

object DBConnHolder {

  def getConnection(): Connection = {
    connection
  }

  private lazy val connection = createConnection()

  // see https://jdbc.postgresql.org/documentation/head/connect.html

  private def createConnection(): Connection = {
    val url = "jdbc:postgresql://ec2-184-73-192-172.compute-1.amazonaws.com:5432/d8u8pgptt8dli6?sslmode=require&user=rvjgzaxmfirtxf&password=e4fe0997c8dbb7b5c76b7c645b2ccb548f72df2a17eaeed487a58bb12f1490e7"
    val conn = java.sql.DriverManager.getConnection(url)
    conn
  }
}


//// see https://jdbc.postgresql.org/documentation/head/connect.html
//
//private def createConnection(): Connection = {
//  //    val dbUri = new URI(System.getenv("DATABASE_URL"))
//  //    val dbUriStr = "postgres://rvjgzaxmfirtxf:e4fe0997c8dbb7b5c76b7c645b2ccb548f72df2a17eaeed487a58bb12f1490e7@ec2-184-73-192-172.compute-1.amazonaws.com:5432/d8u8pgptt8dli6?sslmode=require"
//
//  val url = "jdbc:postgresql://ec2-184-73-192-172.compute-1.amazonaws.com:5432/d8u8pgptt8dli6?sslmode=require&user=rvjgzaxmfirtxf&password=e4fe0997c8dbb7b5c76b7c645b2ccb548f72df2a17eaeed487a58bb12f1490e7"
//  val conn = java.sql.DriverManager.getConnection(url)
//
//  // Below also works
//  //    val url = "jdbc:postgresql://ec2-184-73-192-172.compute-1.amazonaws.com:5432/d8u8pgptt8dli6?sslmode=require"
//  //    val username = "rvjgzaxmfirtxf"
//  //    val password = "e4fe0997c8dbb7b5c76b7c645b2ccb548f72df2a17eaeed487a58bb12f1490e7"
//  //    val conn = DriverManager.getConnection(url, username, password)
//
//  //    val url = "jdbc:postgresql://ec2-184-73-192-172.compute-1.amazonaws.com/d8u8pgptt8dli6"
//  //    val props = new java.util.Properties();
//  //    props.setProperty("user", "rvjgzaxmfirtxf")
//  //    props.setProperty("password", "e4fe0997c8dbb7b5c76b7c645b2ccb548f72df2a17eaeed487a58bb12f1490e7")
//  //    props.setProperty("sslmode", "require")
//  //    val conn = java.sql.DriverManager.getConnection(url, props)
//
//  //    props.setProperty("ssl", "true")
//
//  conn
//}
