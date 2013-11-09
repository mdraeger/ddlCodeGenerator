package org.draegisoft.ddlCodeGenerator.mysql

import org.draegisoft.ddlCodeGenerator._

trait MySQLTableDdlParser extends TableDdlParser {

  override def tableName: Parser[String] = "`" ~> ident <~ "`" ^^ {case ident => ident}

  override def numericType = booleanType | super.numericType
  override def charType = super.charType | setType

  def booleanType: Parser[DataType] = ("TINYINT" | "tinyint") ~ "(" ~> wholeNumber <~ ")" ^^ 
                                      { case wholeNumber if (wholeNumber.toInt == 1) =>  BooleanType() 
                                        case wholeNumber => IntegerType(wholeNumber.toInt) 
                                      }
  override def intType: Parser[DataType] = ("MEDIUMINT" | "mediumint") ~ "(" ~> wholeNumber <~ ")" ^^ { case i => IntegerType(i.toInt) } | super.intType

  def setType: Parser[DataType] = ("SET" | "set") ~ "(" ~> repsep("'"~> """[\w\d\s]*""".r  <~"'", ",") <~ ")" ^^ {case l => SetType(l.toSet) }

}


