sbtPlugin := true

organization := "com.typesafe"

name := "sbt-jshint-plugin"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.10.3"

resolvers ++= Seq(
    Resolver.url("sbt snapshot plugins", url("http://repo.scala-sbt.org/scalasbt/sbt-plugin-snapshots"))(Resolver.ivyStylePatterns),
    Resolver.sonatypeRepo("snapshots"),
    "Typesafe Snapshots Repository" at "http://repo.typesafe.com/typesafe/snapshots/"
    )

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.2.3",
  "io.spray" %% "spray-json" % "1.2.5",
  "org.webjars" % "jshint" % "2.3.0",
  "org.webjars" % "webjars-locator" % "0.6",
  "org.specs2" %% "specs2" % "2.2.2" % "test",
  "junit" % "junit" % "4.11" % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.2.3" % "test"
)

addSbtPlugin("com.typesafe" % "sbt-js-engine" % "1.0.0-SNAPSHOT")

scriptedSettings

scriptedLaunchOpts <+= version apply { v => s"-Dproject.version=$v" }

publishMavenStyle := false

publishTo := {
    val isSnapshot = version.value.contains("-SNAPSHOT")
    val scalasbt = "http://repo.scala-sbt.org/scalasbt/"
    val (name, url) = if (isSnapshot)
                        ("sbt-plugin-snapshots", scalasbt + "sbt-plugin-snapshots")
                      else
                        ("sbt-plugin-releases", scalasbt + "sbt-plugin-releases")
    Some(Resolver.url(name, new URL(url))(Resolver.ivyStylePatterns))
}