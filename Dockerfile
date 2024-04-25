FROM maven:3.9.5-eclipse-temurin-21-alpine as build
#
WORKDIR /is-my-burguer-auth
#
COPY ./ ./
RUN mvn clean
RUN mvn install

FROM eclipse-temurin:21-jdk-alpine as main
EXPOSE 8943
EXPOSE 5006

COPY --from=build /is-my-burguer-auth/api-main-build/src/main/resources/springboot.crt springboot.crt
RUN keytool -importcert -file springboot.crt -alias springboot -keystore $JDK_HOME/jre/lib/security/cacerts
COPY --from=build /is-my-burguer-auth/api-main-build/target/is-my-burguer-auth.jar is-my-burguer-auth.jar

ENTRYPOINT ["java","-jar","is-my-burguer-auth.jar","--spring.profiles.active=production","-Dserver.port=8943"]
#CMD ["sleep","infinity"] Only for testing