ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"
ThisBuild / scalacOptions := "-deprecation" :: "-feature" :: "-unchecked" :: Nil
run / javaOptions += "-Xmx12G"
run / fork := true
run / connectInput := true
ThisBuild / javacOptions ++= Seq("-source", "17", "-target", "17")
lazy val root = (project in file("."))
  .settings(
    name := "CountSeq",
  )
