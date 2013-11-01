package org.draegisoft.ddlCodeGenerator.test

import org.specs2.mutable._

import org.draegisoft.ddlCodeGenerator.TableDdlParser

class TableDdlParserSpec extends Specification {
  
  val input = """TABLE person (first_name VARCHAR, last_name VARCHAR, age INTEGER)
         TABLE place (city VARCHAR, state VARCHAR)"""

  val output = Map("person" -> Map("first_name" -> "VARCHAR", 
                                   "last_name" -> "VARCHAR", 
                                   "age" -> "INTEGER"), 
                   "place" -> Map("city" -> "VARCHAR", 
                                  "state" -> "VARCHAR"))

  val tableDdlParser = new TableDdlParser()
  val tables = tableDdlParser.tables

  "The parser " should {
    "parse the input %s".format(input) in {
      tableDdlParser.parseAll(tables, input).get === output 
    }
  }
}
