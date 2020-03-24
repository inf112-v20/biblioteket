# INF112 biblioteket ![pipeline](https://github.com/inf112-v20/biblioteket/workflows/pipeline/badge.svg) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=inf112&metric=alert_status)](https://sonarcloud.io/dashboard?id=inf112) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/e66eb58244f141a0afa780171e94a1f2)](https://www.codacy.com/gh/inf112-v20/biblioteket?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=inf112-v20/biblioteket&amp;utm_campaign=Badge_Grade)

Simple skeleton with libgdx. 

## *NOTE*:

The delivered assignment is on the `master` branch.

## Known bugs
Currently throws "WARNING: An illegal reflective access operation has occurred",
when the java version used is >8. This has no effect on function or performance,
and is just a warning.

## How to run

You can either run the project in your IDE of choice, if you're using IntelliJ
IDEA you can simply just run the Main class. If you are using `maven` from the
command line you can package the game and its dependencies by running `mvn -B
package` and then `java -jar
target/roborally-1.0-SNAPSHOT-jar-with-dependencies.jar`.
