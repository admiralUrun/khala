name := "khala"
 
version := "1.0" 
      
lazy val `khala` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice,
  "org.typelevel" %% "cats-core" % "2.1.1",
  "org.tpolecat" %% "doobie-core" % "0.8.8",
  "mysql" % "mysql-connector-java" % "5.1.12"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

      