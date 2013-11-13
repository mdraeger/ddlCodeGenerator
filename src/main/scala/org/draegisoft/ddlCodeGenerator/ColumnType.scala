package org.draegisoft.ddlCodeGenerator

sealed trait ColumnType

case class SimpleColumn(name: String, dataType: DataType) extends ColumnType
case class PkColumn(name: String, dataType: DataType) extends ColumnType
case class ReferenceColumn(name: String, dataType: DataType, target: String) extends ColumnType // use String instead of ColumnType to avoid an additional sweep

