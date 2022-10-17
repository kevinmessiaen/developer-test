# Odd calculator

A generic Spring Boot library that will calculate the odds for the Millennium Falcon to rach Endor.

## Requirement

This library require Java 17+ version to be installed

You can use the maven wrapper provided by replacing `mvn` by `./mvnw`

If you want to use an IDE to edit the code, you should install lombok plugin

## How to build the library

In order to build the library locally, simply run `./mvnw clean package`

## How to use it

In order to use it, you will need to provide `MillenniumFalconLocationProperties` bean with an absolute or 
relative path to the [millennium-falcon.json](https://github.com/lioncowlionant/developer-test).
