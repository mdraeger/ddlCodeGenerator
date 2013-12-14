package org.draegisoft.ddlCodeGenerator

class MapperGenerator(override val packageName: String) extends Generator(packageName) {

  override def tableType2orm(tt: TableType): String = {
    val (superClass, superObject, objectList) = tt match {
      case t: SimpleTableType => ("Mapper", "MetaMapper", t.columns.map(columnType2obj _))
    }
    super.tableType2orm(tt).replaceAll("@parentClass@", superClass)
                           .replaceAll("@parentObject@", superObject)
                           .replaceAll("@OBJECT_LIST@", objectList.mkString("\n"))
  }

  override def columnType2obj(ct: ColumnType): String = {
    val template = "object " + ct.name + " extends "
    ct match {
      case s: SimpleColumn => template + dataType2orm(ct.dataType)
      case _ => ""
    }
  }

  override def dataType2orm(dt: DataType): String = dt match {
      case i:IntegerType => "MappedInteger(this)"
      case _ => ""
    }

  override def ormTemplate = 
    """package @package@

import net.liftweb.mapper._

class @className@ extends @parentClass@[@className@]{
  override def getSingleton = @className@

  @OBJECT_LIST@
}

object @className@ extends @parentObject@[@className@]{
}
"""
}
