# learn-scala-native
A simple project to learn basics of [Scala Native](https://scala-native.readthedocs.io/en/latest/). A detailed blog is available here.

This simple project creates a command line app to base64 encode and decode a text. 

We can run the project using:
```
sbt run
```

To create a native binary packaging, use:

```
sbt nativeLink
```

This will generate the native application under the path `/target/scala-2.13/`. 
Then we can run the application by passing the necessary arguments. For example, to encode a string, we can invoke:

```
./learn-scala-native-out -t ScalaNative --mode encode
```

To decode a string, we can use:

```
./learn-scala-native-out -t U2NhbGFOYXRpdmU= --mode decode
```
