#!/bin/sh
docker build -t janbed/eqservice .
docker run --name some_service -d -p 8092:8080 janbed/eqservice