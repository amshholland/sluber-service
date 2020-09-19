FROM openjdk:14
VOLUME /tmp
COPY target/sluber*jar /app.jar
COPY ./entrypoint.sh /
ENTRYPOINT /entrypoint.sh
