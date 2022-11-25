FROM adoptopenjdk/openjdk11

WORKDIR /app/example
COPY build/libs/test-rest-template-*.jar test-rest-template.jar

EXPOSE 8080

CMD java \
    -Xmx250M \
    -noverify -XX:TieredStopAtLevel=1 \
    -XX:NativeMemoryTracking=summary -XX:+UnlockDiagnosticVMOptions -XX:+PrintNMTStatistics \
    -jar test-rest-template.jar
