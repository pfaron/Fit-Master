FROM openjdk:17
VOLUME /tmp
ARG JAR_FILE
ARG CONFIG_PROFILES

COPY ${JAR_FILE} app.jar

# In CMD Dockerfile arguments ARE NOT EVALUATED DURING BUILD!
# Because of it all needed params are set using ENV to
# make them be persisted in image and they are used in application.yml.
ENV CONFIG_PROFILES $CONFIG_PROFILES

CMD java \
    -Dserver.port=$PORT \
    -Djava.security.egd=file:/dev/./urandom \
    -jar /app.jar
