# README

The SLUBER service is a RESTful service which performs operations on passengers and drivers.

## Installation
This project requires JDK 14

* Clone repo: https://bitbucket.org/tschwebach/sluber-service
* Build:  mvn clean install
* Run: mvn spring-boot:run
* Build Docker image:  docker build -t sluber-service -f ./Dockerfile .
* Run Docker image:  docker run -p 8080:8080 --name sluber-service sluber-service