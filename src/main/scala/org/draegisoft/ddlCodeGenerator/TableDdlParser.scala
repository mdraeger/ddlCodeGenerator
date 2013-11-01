package org.draegisoft.ddlCodeGenerator

import scala.util.parsing.combinator._

class TableDdlParser extends JavaTokenParsers {
  
  def tables: Parser[Map[String, Any]] = rep(table) ^^ { Map() ++ _ }
  
  def table: Parser[(String,Any)] = 
    ("TABLE" ~ tableName ~ columns 
       ^^ { case "TABLE" ~ tableName ~ tableContents => (tableName,tableContents) })
  
  def tableName: Parser[String] = ident ^^ { case ident => ident }
  
  def columns: Parser[Map[String, Any]] = "("~> repsep(column, ",") <~")" ^^ { Map() ++ _ }
  
  def column: Parser[(String,Any)] = 
    columnName ~ dataType ^^ { case columnName ~ dataType => (columnName,dataType) }
    
  def columnName: Parser[String] = ident ^^ { case ident => ident }
  
  def dataType: Parser[Any] = "VARCHAR" | "INTEGER"
  
}

