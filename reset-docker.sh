#!/bin/sh

docker stop fitmaster-frontend
docker stop fitmaster-backend
docker stop fitmaster-database

docker rm fitmaster-frontend
docker rm fitmaster-backend
docker rm fitmaster-database

docker image rm -f fitmaster-front:0.1
docker image rm -f fitmaster-back:0.1
