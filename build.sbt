

lazy val commonSettings = Seq(
  scalaVersion := "2.11.8"
)

lazy val core = (project in file("core"))
  .settings(
    name := "bintray-test-core"
  )

lazy val play = (project in file("play"))
  .settings(
    name := "bintray-test-play"
  )
  .settings(commonSettings: _*)
  .settings(publishSettings: _*)

lazy val root = (project in file("."))
  .settings(
    name := "bintray-test",
    publishArtifact := false
  )
  .settings(commonSettings: _*)
  .settings(publishSettings: _*)
  .aggregate(core, play)

lazy val publishSettings = Seq(
  publishTo := {
    val repo = if (version.value endsWith "SNAPSHOT") "snapshot" else "release"
    Some("Kaliber Internal " + repo.capitalize + " Repository" at "https://jars.kaliber.io/artifactory/libs-" + repo + "-local")
  }
)

bintrayOrganization := Some("jorkzijlstra")

bintrayReleaseOnPublish in ThisBuild := false

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

