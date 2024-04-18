FROM maven:3.9.5-eclipse-temurin-21-alpine as build
#
WORKDIR /is-my-burguer
#
COPY ./ ./
RUN mvn clean
RUN mvn install

ENV POSTGRES_USER="uri"
ENV POSTGRES_PASSWORD="uri"
ENV MONGO_PASSWORD="uri"
ENV MONGO_USERNAME="uri"

FROM eclipse-temurin:21-jdk-alpine as main
EXPOSE 8080
EXPOSE 8081
EXPOSE 8099
EXPOSE 8943
EXPOSE 5006

COPY --from=build /is-my-burguer-auth/api-main-build/src/main/resources/springboot.crt springboot.crt
RUN keytool -importcert -file springboot.crt -alias springboot -keystore $JDK_HOME/jre/lib/security/cacerts
COPY --from=build /is-my-burguer-auth/api-main-build/target/is-my-burguer-auth.jar is-my-burguer-auth.jar

ENTRYPOINT ["java","-jar","is-my-burguer-auth.jar","--server.port=8943","-Dspring.profiles.active=production"]
#CMD ["sleep","infinity"] Only for testing