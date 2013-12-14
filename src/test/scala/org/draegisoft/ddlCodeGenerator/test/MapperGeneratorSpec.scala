package org.draegisoft.ddlCodeGenerator.test

import java.io._

import org.scalatest._

import org.draegisoft.ddlCodeGenerator._

class MapperGeneratorSpec extends MapperGenerator("com.example") with FlatSpecLike with ShouldMatchers{
  "The MapperGenerator" should "create simple table mappings" in {
     tableType2orm(SimpleTableType("my_simple_table", SimpleColumn("my_simple_int_column", IntegerType(10))::Nil)) should equal(
        """package com.example

import net.liftweb.mapper._

class My_simple_table extends Mapper[My_simple_table]{
  override def getSingleton = My_simple_table

  object my_simple_int_column extends MappedInteger(this)
}

object My_simple_table extends MetaMapper[My_simple_table]{
}
""")
  }
}
