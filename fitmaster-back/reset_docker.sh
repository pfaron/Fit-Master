#!/bin/sh

docker stop fitmaster-backend
docker stop fitmaster-database

docker rm fitmaster-backend
docker rm fitmaster-database

docker image rm -f fitmaster-back:0.1
