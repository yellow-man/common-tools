// General

organization := "yokohama.yellow_man"

name := """common-tools"""

version := "0.0.1"

scalaVersion := "2.11.6"

publishTo := Some(Resolver.file("common-tools",file("C:\\Users\\yellow-man\\workspace\\git\\release\\common-tools\\"))(Patterns(true, Resolver.mavenStyleBasePattern)))

javacOptions ++= Seq("-encoding", "UTF-8")