
/*
 * BASIC INFORMATION
 ********************************************************/

name := "ubirch-rest-akka-http-utils"
version := "0.4.1" // NOTE: please keep major.minor version synchronized with restAkkaHttpTest
description := "shared custom classes related to akka-http-experimental (for example certain directives)"
organization := "com.ubirch.util"
homepage := Some(url("http://ubirch.com"))
scalaVersion := "2.11.12"
scalacOptions ++= Seq(
  "-feature"
)
scmInfo := Some(ScmInfo(
  url("https://github.com/ubirch/ubirch-rest-akka-http-utils"),
  "https://github.com/ubirch/ubirch-rest-akka-http-utils.git"
))

/*
 * CREDENTIALS
 ********************************************************/

(sys.env.get("CLOUDREPO_USER"), sys.env.get("CLOUDREPO_PW")) match {
  case (Some(username), Some(password)) =>
    println("USERNAME and/or PASSWORD found.")
    credentials += Credentials("ubirch.mycloudrepo.io", "ubirch.mycloudrepo.io", username, password)
  case _ =>
    println("USERNAME and/or PASSWORD is taken from /.sbt/.credentials")
    credentials += Credentials(Path.userHome / ".sbt" / ".credentials")
}

/*
 * RESOLVER
 ********************************************************/

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"))


/*
 * PUBLISHING
 ********************************************************/

val resolverUbirchUtils = "ubirch.mycloudrepo.io" at "https://ubirch.mycloudrepo.io/repositories/ubirch-utils-mvn"

publishTo := Some(resolverUbirchUtils)
publishMavenStyle := true


/*
 * DEPENDENCIES
 ********************************************************/


// Versions
val akkaV = "2.5.11"
val akkaHttpV = "10.1.3"
val ubirchKeyV = "0.11.5-SNAPSHOT"

// Groups
val akkaG = "com.typesafe.akka"

// Dependencies
lazy val akkaHttp = akkaG %% "akka-http" % akkaHttpV
lazy val akkaHttpCors = "ch.megard" %% "akka-http-cors" % "0.3.0"
lazy val akkaHttpTestkit = akkaG %% "akka-http-testkit" % akkaHttpV
lazy val akkaStream = akkaG %% "akka-stream" % akkaV
lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"


libraryDependencies ++= Seq(
  akkaHttp,
  akkaStream,
  akkaHttpCors,
  akkaHttpTestkit,
  scalaTest
)