#!/bin/sh

docker image rm -f fitmaster-front:0.1

docker build \
    -t fitmaster-front:0.1 \
    .

docker-compose up
