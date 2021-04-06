#!/bin/sh
ls

docker build -t my-php-app -f deployment/Dockerfile .

docker run -it --rm --name my-running-app my-php-app


