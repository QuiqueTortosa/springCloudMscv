FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/msvc-cursos

COPY ./pom.xml /app
COPY ./msvc-cursos/.mvn ./.mvn
COPY ./msvc-cursos/mvnw .
COPY ./msvc-cursos/pom.xml .

#RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
RUN ./mvnw dependency:go-offline
#Hacen practicamente lo mismo

COPY ./msvc-cursos/src ./src

RUN ./mvnw clean package -DskipTests
#maven.main.skip ni compila ni ejecuta
#repackage empaqueta el codigo sin el codigo fuetne y sin el test

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs

COPY --from=builder /app/msvc-cursos/target/msvc-cursos-0.0.1-SNAPSHOT.jar .

EXPOSE 8002

CMD sleep 10 && java -jar msvc-cursos-0.0.1-SNAPSHOT.jar
#CMD ["java", "-jar", "msvc-cursos-0.0.1-SNAPSHOT.jar"]