FROM adoptopenjdk/openjdk11

WORKDIR /app/example
COPY build/libs/test-webclient-*.jar test-webclient.jar

EXPOSE 8080

CMD java \
    -Xmx320M \
    -noverify -XX:TieredStopAtLevel=1 \
    -XX:NativeMemoryTracking=summary -XX:+UnlockDiagnosticVMOptions -XX:+PrintNMTStatistics \
    -Dio.netty.leakDetection.level=paranoid \
	-jar test-webclient.jar
