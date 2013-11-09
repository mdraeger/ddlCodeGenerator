package org.draegisoft.ddlCodeGenerator

import scala.util.parsing.combinator._

/** 
 * This parser covers the part of ANSI SQL that I'm interested in. 
 */
trait TableDdlParser extends JavaTokenParsers {
  
  def tables: Parser[Map[String, Any]] = rep(table) ^^ { Map() ++ _ }
  
  def table: Parser[(String,Any)] = 
    (("CREATE TABLE IF NOT EXISTS" | "CREATE TABLE" | "TABLE") ~ tableName ~ columns 
       ^^ { case "TABLE" ~ tableName ~ tableContents => (tableName,tableContents) })
  
  def tableName: Parser[String] = ident ^^ { case ident => ident }
  
  def columns: Parser[Map[String, Any]] = "("~> repsep(column, ",") <~")" ^^ { Map() ++ _ }
  
  def column: Parser[(String,Any)] = 
    columnName ~ dataType ^^ { case columnName ~ dataType => (columnName,dataType) }
    
  def columnName: Parser[String] = ident ^^ { case ident => ident }
  
  def dataType: Parser[DataType] = numericType | charType | dateTimeType


  def numericType = intType | floatType | doubleType | decimalType

  def charType = stringType | textType

  def dateTimeType = timestampType | dateType | timeType 
  
  def intType: Parser[DataType] =  ("INTEGER" | "integer" | "INT" | "int" | "SMALLINT" | "smallint" ) ~ "(" ~> wholeNumber <~ ")" ^^ {case s => IntegerType(s.toInt)}
  def floatType: Parser[DataType] = ("FLOAT" | "float" | "REAL" | "real") ^^ {case s => FloatType()}
  def doubleType: Parser[DataType] = ("DOUBLE" | "double") ^^ {case s => DoubleType()}
  def decimalType: Parser[DataType] = ("DECIMAL" | "decimal") ~ "(" ~> repsep(wholeNumber, ",") <~ ")" ^^ {case  precision:: scale:: Nil => DecimalType(precision.toInt, scale.toInt)
 case _ => throw new IllegalArgumentException("Decimal without scale or too many arguments")
}

  def stringType: Parser[DataType] =  ("varchar" | "VARCHAR" | "char" | "CHAR") ~ "(" ~> wholeNumber <~ ")" ^^ {case s => StringType(s.toInt)}
  def textType: Parser[DataType] = ("TEXT" | "text") ^^ {case s => TextType()}

  def timestampType: Parser[DataType] = ("TIMESTAMP" | "timestamp") ^^ {case s => TimestampType()}
  def dateType: Parser[DataType] = ("DATE" | "date") ^^ {case s => DateType()}
  def timeType: Parser[DataType] = ("TIME" | "time") ^^ {case s => TimeType()}
}

