package org.draegisoft.ddlCodeGenerator

sealed abstract class ColumnType(val name: String, val dataType: DataType)

case class SimpleColumn(override val name: String, override val dataType: DataType) extends ColumnType(name, dataType)
case class PkColumn(override val name: String, override val dataType: DataType) extends ColumnType(name, dataType)
case class ReferenceColumn(override val name: String, override val dataType: DataType, target: String) extends ColumnType(name, dataType) // use String instead of ColumnType to avoid an additional sweep

