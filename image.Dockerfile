FROM --platform=linux/amd64 openjdk:latest

WORKDIR /usr/src/app

COPY url404check.jar /home/url404check.jar

CMD ["java","-jar","/home/url404check.jar"]
