Identify which contacts are potentially duplicates

In order to execute the code you need to have installed Java 21, Gradle 8.3 and Gradle Wrapper.

Within the resources directory you can find some sample files to test the code. For simplicity, they were included in the resources' directory.

For a proper solution files should be obtained from a storage, or sent via http request.

When executing the program, 3 files should be created in the output directory for each Accuracy level.

In terminal, at root directory, execute the following commands:

build
```
gradle clean build
```

run
```
./gradlew run
```