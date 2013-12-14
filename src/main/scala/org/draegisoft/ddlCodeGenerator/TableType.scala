package org.draegisoft.ddlCodeGenerator

sealed abstract class TableType(val name:String)

case class SimpleTableType(override val name: String, columns: Seq[ColumnType]) extends TableType(name)
