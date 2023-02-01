FROM adoptopenjdk/openjdk11
MAINTAINER Abzal <abzal.tugan@gmail.com>

ENV TZ=Asia/Almaty
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
VOLUME /tmp
ARG JAR_FILE
ADD ./target/web-2-0.0.1-SNAPSHOT.jar /app/
EXPOSE 56435
ENTRYPOINT ["java","-jar","/app/web-2-0.0.1-SNAPSHOT.jar"]
