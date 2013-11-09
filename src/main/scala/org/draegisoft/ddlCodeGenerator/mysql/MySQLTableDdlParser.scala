package org.draegisoft.ddlCodeGenerator.mysql

import org.draegisoft.ddlCodeGenerator._

trait MySQLTableDdlParser extends TableDdlParser {

  override def tableName: Parser[String] = "`" ~> ident <~ "`" ^^ {case ident => ident}

  def booleanType: Parser[DataType] = ("TINYINT" | "tinyint") ~ "(" ~> wholeNumber <~ ")" ^^ 
                                      { case wholeNumber if (wholeNumber.toInt == 1) =>  BooleanType() 
                                        case wholeNumber => IntegerType(wholeNumber.toInt) 
                                      }

  override def intType: Parser[DataType] = ("MEDIUMINT" | "mediumint") ~ "(" ~> wholeNumber <~ ")" ^^ { case wholeNumber => IntegerType(wholeNumber.toInt) } | super.intType

  override def numericType = booleanType | super.numericType
}


