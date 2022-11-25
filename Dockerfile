FROM adoptopenjdk/openjdk11

WORKDIR /app/example
COPY build/libs/test-backend-*.jar test-backend.jar

EXPOSE 8080

CMD java \
    -Xmx200M \
	-jar test-backend.jar
