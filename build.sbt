name := "ddlCodeGenerator"

version := "0.1"

scalaVersion := "2.10.3"

scalacOptions in Test ++= Seq("-Yrangepos")

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "2.3.1" % "test"
)
