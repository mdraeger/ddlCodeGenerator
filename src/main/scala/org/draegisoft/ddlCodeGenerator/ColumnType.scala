package org.draegisoft.ddlCodeGenerator

sealed trait ColumnType

case class SimpleColumn(name: String, dataType: DataType)
case class PkColumn(name: String, dataType: DataType)
case class ReferenceColumn(name: String, dataType: DataType, target: String) // use String instead of ColumnType to avoid an additional sweep

