// General

organization := "yokohama.yellow_man"

name := """common-tools"""

version := "1.0.0"

scalaVersion := "2.11.6"

publishTo := Some(Resolver.file("common-tools",file("../release/common-tools/"))(Patterns(true, Resolver.mavenStyleBasePattern)))

javacOptions ++= Seq("-encoding", "UTF-8")