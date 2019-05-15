#FROM quay.octanner.io/base/oct-openjdk:11-2.0 #has a bug that makes 'mvn test' fail
FROM openjdk:11.0.3-jdk-slim as builder

COPY . .

RUN ./mvnw install

RUN cp `ls /target/sbmirror*.jar` sbmirror.jar

FROM quay.octanner.io/base/oct-openjdk:11-2.0

COPY --from=builder /sbmirror.jar /app/sbmirror.jar

COPY ./start.sh start.sh

CMD ./start.sh