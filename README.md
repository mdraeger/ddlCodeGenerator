ddlCodeGenerator

Purpose is to create net.liftweb.mapper (maybe squeryl in the future) compliant ORM classes based on an existing database schema. Lift mapper does a great job of creating a schema based on the ORM, but sometimes one has the problem that there is a (quite complex) database already up and running and our web app has to use it.
Aim of this little project is therefore to create a library that generates mapper classes based on the existing ddl file. At first this will only support MySQL DDL, because that's what I need in a project that I'm currently involved in at the office.

As a Scala and Lift newbie, I don't expect the code to be very elegant. Suggestions are welcome!

The code for the TableDdlParser was inspired here: http://www.mostlyblather.com/2010/03/scala-external-dsl-and-combinator.html

For the unlikely users of the library:
The outcome will be generated code. Never change generated code. Don't even think about adapting it to your purpose. Instead, subclass the resulting mapper classes and override whatever you need to be changed.

