#FROM openjdk:17.0.1
#MAINTAINER ivanmros
#USER root:root
#COPY out/artifacts/pruebaFinal_jar/prueba-final.jar ./
#ENTRYPOINT ["java", "-jar", "prueba-final.jar"]
FROM openjdk

WORKDIR /app

COPY ./out/artifacts/pruebaFinal_jar/prueba-final.jar /app/prueba-final.jar

CMD ["java", "-jar", "prueba-final.jar"]