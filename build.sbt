import sbt._
import Keys._

import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

scalaVersion in ThisBuild := "2.11.12"

ensimeScalaVersion in ThisBuild := "2.11.12"

scalaJSModuleKind := ModuleKind.CommonJSModule

skip in packageJSDependencies := false
jsDependencies += "org.webjars" % "jquery" % "2.1.4" / "2.1.4/jquery.js"


lazy val commonSettings =  Seq(
  organization := "com.sumologic",
  version := "0.1.0",
  scalaVersion := "2.11.12",
  jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
)

lazy val root = (project in file(".")).settings(commonSettings)
  .aggregate(test).enablePlugins(ScalaJSPlugin)

lazy val test = (project in file("test"))
  .settings(commonSettings ++ Seq(
    name := "scala-node-test",
    libraryDependencies ++= Seq("org.specs2" %% "specs2" % "2.3.12" % "test")
  )).dependsOn(main % "compile").enablePlugins(ScalaJSPlugin)

lazy val main = (project in file("main"))
  .settings(commonSettings ++ Seq(
    name := "scala-node-main",
//    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.3",
      "be.doeraene" %%% "scalajs-jquery" % "0.9.1",
      "fr.hmil" %%% "roshttp" % "2.1.0",
      "io.monix" %%% "monix" % "2.3.0",
      "io.monix" %%% "monix-reactive" % "2.3.0",
      "io.monix" %%% "monix-execution" % "2.3.3",
      "io.monix" %%% "monix-types" % "2.3.0")
  )).enablePlugins(ScalaJSPlugin)
