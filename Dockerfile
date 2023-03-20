FROM openjdk:17.0.1
MAINTAINER ivanmros
USER root:root
COPY out/artifacts/pruebaFinal_jar/prueba-final.jar ./
ENTRYPOINT ["java", "-jar", "prueba-final.jar"]
