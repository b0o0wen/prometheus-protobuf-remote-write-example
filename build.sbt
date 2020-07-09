name := "protobuf_demo"

version := "0.1"

scalaVersion := "2.11.7"

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)

libraryDependencies += "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf"

// https://mvnrepository.com/artifact/com.google.protobuf/protobuf-java
libraryDependencies += "com.google.protobuf" % "protobuf-java" % "3.5.0"

// https://mvnrepository.com/artifact/com.google.protobuf/protobuf-java-util
libraryDependencies += "com.google.protobuf" % "protobuf-java-util" % "3.7.1"

libraryDependencies += "com.alibaba" % "fastjson" % "1.2.70"

// https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient
libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.5.2"

// https://mvnrepository.com/artifact/org.xerial.snappy/snappy-java
libraryDependencies += "org.xerial.snappy" % "snappy-java" % "1.1.7.3"
