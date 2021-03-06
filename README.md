[![Java CI with Maven](https://github.com/dumitruc/pokemon/actions/workflows/maven.yml/badge.svg)](https://github.com/dumitruc/pokemon/actions/workflows/maven.yml)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=dumitruc_pokemon&metric=bugs)](https://sonarcloud.io/summary/new_code?id=dumitruc_pokemon)



# About
Project init using spring boot [initializr](https://start.spring.io/). Front API service to get some fun info about pokemons.
This is a training exercise.
## /pokemon/{pokemon-name}
This will return basic information of a pokemon 
## /pokemon/translated/{pokemon-name}
This will return the pokemon with translated description

# Development setup
 * Install [git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
 * Install [Java JDK](https://docs.oracle.com/en/java/javase/17/install/overview-jdk-installation.html)
 * Install [maven](https://maven.apache.org/install.html)
 * Install [docker](https://docs.docker.com/get-docker/)

# Getting Started

## Download the repository locally
```shell
git clone https://github.com/dumitruc/pokemon.git
```
## Go inside the repository
```shell
cd pokemon
```
This is your working directory home, all the commands should be executed form this folder.

## Build the service as a jar
```shell
mvn clean install
```
## Build the docker container
```shell
docker build -t pokemon .
```
## Run the service in docker
```shell
docker run --rm -p8080:8080 pokemon 
```

## Call the service:
http://localhost:8080/pokemon/mewtwo

http://localhost:8080/pokemon/translated/mewtwo