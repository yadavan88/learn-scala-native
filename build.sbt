scalaVersion := "2.13.7"

// Set to false or remove if you want to show stubs as linking errors
nativeLinkStubs := true

enablePlugins(ScalaNativePlugin)

libraryDependencies ++= Seq(
    "com.lihaoyi" %%% "mainargs" % "0.2.2",
    "com.lihaoyi" %%% "fansi" % "0.3.0"
)
