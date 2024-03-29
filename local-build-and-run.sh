#!/bin/sh

docker image rm -f fitmaster-front:0.1
docker image rm -f fitmaster-back:0.1

cd ./fitmaster-back
./gradlew clean build
docker build \
    --build-arg JAR_FILE=`ls build/libs/*.jar` \
    --build-arg CONFIG_PROFILES=dev \
    -t fitmaster-back:0.1 \
    .

cd ..

cd fitmaster-front

docker build \
    -t fitmaster-front:0.1 \
    .

cd ..

docker-compose up
