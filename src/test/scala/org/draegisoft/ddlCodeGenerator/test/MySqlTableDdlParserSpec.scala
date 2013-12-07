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

   "The MySqlTableDdlParser" should "parse column names in back ticks" in {
      implicit val parserToTest = columnName
      parsing("`column_name`") should equal("column_name")
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
      parsing("set('foo', 'bar', 'baz')") should equal (SetType(Set("foo", "bar", "baz")))
      parsing("smallint(2)") should equal (IntegerType(2))
      parsing("mediumint(10)") should equal (IntegerType(10))
      parsing("double") should equal (DoubleType())
      parsing("timestamp") should equal (TimestampType())
     
      assertFail("decimal(1,2,3)") // too many arguments
      assertFail("decimal(3)") // too few arguments
   }

   "The MySqlTableDdlParser" should "parse a simple column description correctly" in {
      implicit val parserToTest = column
      parsing("`column_name` timestamp") should equal(SimpleColumn("column_name", TimestampType()))
   }

   "The MySqlTableDdlParser" should "parse a simple table description correctly" in {
      implicit val parserToTest = table
      parsing("CREATE TABLE IF NOT EXISTS `table_name` (`column_name` int(10))") should equal (SimpleTableType("table_name", List(SimpleColumn("column_name", IntegerType(10)))))
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
