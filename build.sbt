import sbtassembly.Plugin.AssemblyKeys._

name := "recs-api"

version := "1.0"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.0.13",
  "com.typesafe.akka" %% "akka-actor" % "2.3.4",
  "com.typesafe.akka" %% "akka-slf4j" % "2.3.4",
  "io.spray" %% "spray-can" % "1.3.1",
  "io.spray" %% "spray-routing" % "1.3.1",
  "io.spray" %% "spray-client" % "1.3.1",
  "io.spray" %% "spray-json" % "1.3.2",
  "io.spray" %% "spray-testkit" % "1.3.3" % "test",
  "org.apache.httpcomponents" % "httpclient" % "4.4.1",
  "org.apache.httpcomponents" % "httpcore" % "4.4.1",
  "commons-codec" % "commons-codec" % "1.10",
  "commons-logging" % "commons-logging" % "1.2",
  "com.google.code.gson" % "gson" % "2.3.1",
  "org.scalatest" %% "scalatest" % "2.2.0" % "test",
  "junit" % "junit" % "4.12",
  "org.mockito" % "mockito-core" % "1.8.5"
)

assemblySettings

mainClass in assembly := Some("com.sky.assignment.Application")

