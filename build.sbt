// General

organization := "yokohama.yellow_man"

name := """common-tools"""

version := "1.0.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.slf4j"      % "slf4j-api"       % "1.7.22",
  "ch.qos.logback" % "logback-classic" % "1.1.10",
  "ch.qos.logback" % "logback-core"    % "1.1.10"
)

publishTo := Some(Resolver.file("common-tools",file("../release/common-tools/"))(Patterns(true, Resolver.mavenStyleBasePattern)))

javacOptions ++= Seq("-encoding", "UTF-8")