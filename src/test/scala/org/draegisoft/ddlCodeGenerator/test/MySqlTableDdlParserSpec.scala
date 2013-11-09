package org.draegisoft.ddlCodeGenerator.test

import java.io._

import scala.util.parsing.input.CharSequenceReader

import org.scalatest._

import org.draegisoft.ddlCodeGenerator._
import org.draegisoft.ddlCodeGenerator.mysql._

class MySqlTableDdlParserSpec extends TableDdlParser with MySQLTableDdlParser with FlatSpecLike with ShouldMatchers {
   "The MySqlTableDdlParser" should "parse table names in back ticks" in {
      implicit val parserToTest = tableName
      parsing("`rollen`") should equal("rollen")
   }

   
   "The MySqlTableDdlParser" should "parse data types correctly" in {
      implicit val parserToTest = dataType
      parsing("int(10)") should equal (IntegerType(10))
      parsing("integer(10)") should equal (IntegerType(10))
      parsing("tinyint(2)") should equal (IntegerType(2))
      parsing("tinyint(1)") should equal (BooleanType())
      parsing("decimal(10,2)") should equal (DecimalType(10, 2))
      parsing("text") should equal (TextType())
      parsing("varchar(255)") should equal (StringType(255))
      parsing("smallint(2)") should equal (IntegerType(2))
      parsing("mediumint(10)") should equal (IntegerType(10))
      parsing("double") should equal (DoubleType())
      parsing("timestamp") should equal (TimestampType())
     
      assertFail("decimal(1,2,3)") // too many arguments
      assertFail("decimal(3)") // too few arguments
   }




   private def assertFail[T](input: String)(implicit p:Parser[T]) = {
      evaluating(parsing(input)(p)) should produce[IllegalArgumentException]
   }

   private def parsing[T](s:String)(implicit p:Parser[T]):T = {
      val phraseParser = phrase(p)
      val input = new CharSequenceReader(s)
      phraseParser(input) match {
         case Success(t,_) => t
         case NoSuccess(msg,_) => throw new IllegalArgumentException("Could not parse '%s': %s".format(s,msg))
      }
   }
}
