#!/bin/sh

docker stop fitmaster-frontend

docker rm fitmaster-frontend

docker image rm -f fitmaster-front:0.1
