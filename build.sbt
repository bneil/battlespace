import sbt._

import scalariform.formatter.preferences._

name := "BattleSpace"

organization := "com.neilconcepts"

version := "0.1.0-SNAPSHOT"

homepage := Some(url("https://neil-concepts.com"))

startYear := Some(2015)

seq(Revolver.settings: _*)

/* scala versions and options */
scalaVersion := "2.11.7"

// These options will be used for *all* versions.
scalacOptions ++= Seq(
  "-deprecation"
  , "-unchecked"
  , "-encoding", "UTF-8"
  , "-Xlint"
  , "-Yclosure-elim"
  , "-Yinline"
  , "-Xverify"
  , "-feature"
  , "-language:postfixOps"
  //,"-optimise"
)

javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation", "-source", "1.7", "-target", "1.7")
//javaOptions in Universal ++= Seq(
//  "-J-server",
//  "-J-Xms3g -Xmx3g",
//  "-J-XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled",
//  "-J-XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=68",
//  "-J-XX:+ScavengeBeforeFullGC -XX:+CMSScavengeBeforeRemark",
//  "-J-XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M"
//)


/* dependencies */
libraryDependencies ++= Seq(
  // -- config
  "com.typesafe" % "config" % "1.3.0",
  // -- testing --
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  // -- Logging --
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  // -- Joda --
  "joda-time" % "joda-time" % "2.7",
  // -- Finch --
  "com.github.finagle" %% "finch-core" % "0.9.2",
  "com.github.finagle" %% "finch-circe" % "0.9.2",
  "com.twitter" %% "twitter-server" % "1.16.0",
  "io.circe" %% "circe-core" % "0.2.1",
  "io.circe" %% "circe-generic" % "0.2.1",
  "io.circe" %% "circe-jawn" % "0.2.1",
  // --scalaz
  "org.scalaz" %% "scalaz-core" % "7.1.3"
)

fork := true

resolvers ++= Seq(
   Resolver.sonatypeRepo("snapshots"),
  "TM" at "http://maven.twttr.com",
  "Secured Central Repository" at "https://repo1.maven.org/maven2"

)

scalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(IndentLocalDefs, true)
  .setPreference(IndentPackageBlocks, true)
  .setPreference(IndentSpaces, 2)
  .setPreference(MultilineScaladocCommentsStartOnFirstLine, false)
