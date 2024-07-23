FROM sbtscala/scala-sbt:eclipse-temurin-jammy-21.0.2_13_1.10.1_2.13.14

WORKDIR /zhttp
COPY src src
COPY project project
COPY build.sbt build.sbt
RUN sbt assembly

EXPOSE 8080
CMD java -Xms2G -Xmx2G -server -jar /zhttp/target/scala-2.13/zio-http-assembly-1.0.0.jar