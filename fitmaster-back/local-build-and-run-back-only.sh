#!/bin/sh

#docker image rm -f fitmaster-back:0.1

./gradlew clean build
docker build \
    --build-arg JAR_FILE=`ls build/libs/*.jar` \
    --build-arg CONFIG_PROFILES=dev \
    -t fitmaster-back:0.1 \
    .

docker-compose up
