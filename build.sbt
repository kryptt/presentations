import com.typesafe.sbt.SbtSite.SiteKeys._
import com.typesafe.sbt.SbtGhPages.GhPagesKeys._
import sbtunidoc.Plugin.UnidocKeys._

val commonSettings = Seq(
  organization := "com.rodolfohansen",
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.11.7",
  autoAPIMappings := true
)

val presSettings = tutSettings ++ site.settings ++ Seq(
  site.addMappingsToSiteDir(tut, "tut"),
  watchSources <++= tutSourceDirectory map { path => (path ** "*.html").get },
  ghpagesNoJekyll := false,
  includeFilter in makeSite := "*.html" | "*.css" | "*.png" | "*.jpg" | "*.gif" | "*.js" | "*.swf" | "*.yml" | "*.md"
)

lazy val adts = (project in file("algebraic-data-types")).
  settings(commonSettings: _*).
  settings(presSettings: _*).
  settings(name := "algebraic-data-types")

lazy val presentations = (project in file(".")).
  settings(commonSettings: _*).
  settings(unidocSettings: _*).
  settings(ghpages.settings: _*).
  settings(
    git.remoteRepo := "git@github.com:kryptt/presentations.git"
  ).
  aggregate (adts)
