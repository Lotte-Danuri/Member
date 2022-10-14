FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/member-0.0.1-SNAPSHOT.jar MemberServer.jar
ENTRYPOINT ["java","-jar","MemberServer.jar"]