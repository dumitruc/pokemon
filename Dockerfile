FROM openjdk:11

COPY target/pokemon-0.0.1-SNAPSHOT.jar /usr/bin/myapp/pokemon.jar
WORKDIR /usr/bin/myapp

ENTRYPOINT ["java", "-jar", "pokemon.jar"]
CMD ["DEFAULT-ARG"]

