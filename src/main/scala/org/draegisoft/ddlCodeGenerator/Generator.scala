package org.draegisoft.ddlCodeGenerator

abstract class Generator(val packageName: String) {

  def tableType2orm(tt: TableType): String = ormTemplate.replaceAll("@package@", packageName)
                                                        .replaceAll("@className@", tt.name.capitalize)

  def save(path: java.io.File): Unit = ()

  def columnType2obj(ct: ColumnType): String

  def dataType2orm(dt: DataType): String

  def ormTemplate: String 

}
