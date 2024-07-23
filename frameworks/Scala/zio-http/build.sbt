name := "zio-http"
version := "1.1.0"
scalaVersion := "2.13.14"
lazy val root = (project in file("."))
  .settings(
    resolvers += Resolver.mavenLocal,
    libraryDependencies ++=
      Seq(
        "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core"   % "2.30.7",
        "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "2.30.7" % Provided,
        "dev.zio" % "zio_2.13" % "2.1.6+5-8b092233-SNAPSHOT",
        "dev.zio" % "zio-http_2.13" % "3.0.0-RC9",
      ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"),
  )
assemblyMergeStrategy in assembly := {
 case PathList("META-INF", _*) => MergeStrategy.discard
 case _                        => MergeStrategy.first
}

