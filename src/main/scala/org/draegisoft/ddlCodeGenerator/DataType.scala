package org.draegisoft.ddlCodeGenerator

sealed trait DataType

case class IntegerType(length: Int) extends DataType
case class BigIntType(length: Int) extends DataType
case class FloatType() extends DataType
case class DoubleType() extends DataType
case class DecimalType(precision: Int, scale: Int) extends DataType

case class BooleanType() extends DataType

case class StringType(length: Int) extends DataType
case class TextType() extends DataType

case class DateType() extends DataType
case class TimeType() extends DataType
case class TimestampType() extends DataType
