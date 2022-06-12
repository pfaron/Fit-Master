#!/bin/sh

cd ./fitmaster-back
./gradlew clean build
docker build \
    --build-arg JAR_FILE=`ls build/libs/*.jar` \
    --build-arg CONFIG_PROFILES=dev \
    -t fitmaster-back:latest \
    .

cd ..

cd fitmaster-front

docker build \
    -t fitmaster-front:latest \
    .

cd ..

docker-compose up