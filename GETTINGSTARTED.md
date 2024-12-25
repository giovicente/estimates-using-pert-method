# How to Run This Program with Command Line

1 - Ensure that Maven is correctly installed by running the command `mvn -v`. It should return a message similar to the one below:
```
Apache Maven 3.9.9 (8e8579a9e76f7d015ee5ec7bfcdc97d260186937)
Maven home: C:\Program Files\Apache\Maven\apache-maven-3.9.9
Java version: 17.0.2, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-17.0.2
Default locale: pt_BR, platform encoding: Cp1252
OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"
```
2 - If the command doesn’t work, check if Maven is properly installed and configured in the system `PATH`: https://maven.apache.org/install.html.

3 - Navigate to the root directory of your project, where the `pom.xml` file is located.

4 - Run the command `mvn clean install`. The `clean` step removes previously generated files, ensuring the build starts from scratch, while the `install` step compiles the code, runs tests, and places the generated artifact in the local repository.

5 - Navigate to the directory where your `.jar` file was generated.

6 - Execute the command `java -jar estimates-using-pert-method.jar`.

7 - The program should run correctly if these steps are followed.