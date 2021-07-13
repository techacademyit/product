FROM openjdk:11.0.11-oraclelinux8  AS build

RUN mkdir /source /source/src /source/.mvn /source/.mvn/wrapper /source/dist
WORKDIR /source
COPY src/ src
COPY .mvn/wrapper .mvn/wrapper/
COPY mvnw pom.xml ./
RUN ./mvnw install -DskipTests=true
RUN VERSION=`./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout` && \
    ARTIFACT=`./mvnw help:evaluate -Dexpression=project.artifactId -q -DforceStdout`  && \
    cp target/${ARTIFACT}-${VERSION}.jar dist/app.jar

FROM openjdk:11.0.11-oraclelinux8  
RUN mkdir /app
WORKDIR /app
COPY --from=build /source/dist/app.jar ./
ENTRYPOINT [ "java", "-jar", "app.jar" ]
