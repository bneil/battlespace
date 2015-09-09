resolvers ++= Seq(
    Classpaths.typesafeReleases,
    Classpaths.sbtPluginReleases,
    "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/"
  )

libraryDependencies += "com.h2database" % "h2" % "1.4.187"

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.2.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.0.1")

addSbtPlugin("com.eed3si9n" % "sbt-unidoc" % "0.3.3")

addSbtPlugin("org.brianmckenna" % "sbt-wartremover" % "0.14")

addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.4.0")

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.7.0")

addSbtPlugin("com.sksamuel.scapegoat" %% "sbt-scapegoat" % "1.0.0")

addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.0")

addSbtPlugin("io.spray" % "sbt-revolver" % "0.7.1")