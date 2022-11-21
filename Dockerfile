FROM adoptopenjdk/openjdk11

WORKDIR /app/example
COPY build/libs/test-rest-template-*.jar test-rest-template.jar

EXPOSE 8080

CMD java \
    -Xmx190M \
	-jar test-rest-template.jar
