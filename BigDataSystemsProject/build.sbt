name := "BigDataSystemsProject"

version := "1.0"

scalaVersion := "2.11.8"


libraryDependencies += "org.apache.spark" %% "spark-core" % "2.0.1" % "provided"
libraryDependencies ++= Seq("org.apache.spark" %% "spark-sql" % "2.0.1" % "provided"
  exclude("org.apache.parquet","parquet-format"))

libraryDependencies ++= Seq("org.apache.spark" % "spark-hive_2.11" % "2.0.1" % "provided"
  exclude("org.datanucleus","datanucleus-rdbms")
  exclude("org.datanucleus","datanucleus-core")
  exclude("org.datanucleus","datanucleus-api-jdo"))

libraryDependencies ++= Seq("org.apache.flink" %% "flink-scala" % "1.0.0",
  "org.apache.flink" %% "flink-clients" % "1.0.0",
  "org.apache.flink" %% "flink-streaming-scala" % "1.0.0")

assemblyMergeStrategy in assembly := {
  case PathList("javax", xs @ _*) => MergeStrategy.last
  case PathList("com", xs @ _*) => MergeStrategy.last
  case PathList("org", xs @ _*) => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
