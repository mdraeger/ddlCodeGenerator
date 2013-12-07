package org.draegisoft.ddlCodeGenerator

sealed trait TableType

case class SimpleTableType(name: String, columns: Seq[ColumnType]) extends TableType
