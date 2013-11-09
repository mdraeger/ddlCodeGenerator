name := "ddlCodeGenerator"

version := "0.1"

scalaVersion := "2.10.3"

scalacOptions in Test ++= Seq("-Yrangepos")

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

scalacOptions += "-deprecation"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
)
