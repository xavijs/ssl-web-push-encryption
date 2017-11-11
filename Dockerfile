FROM blacklabelops/java:jre8

MAINTAINER XaviJS <xavijs2@gmail.com>

COPY ./build/libs/webpush-0.0.1-SNAPSHOT.jar /app/app.jar
COPY ./keystore.p12 /app/keystore.p12
WORKDIR /app

EXPOSE 443

CMD java -jar -Dspring.profiles.active=dev app.jar