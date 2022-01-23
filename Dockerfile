FROM openjdk:17-oracle
COPY build/libs/Display-PredictIt-Data-Webpage-0.0.1-SNAPSHOT.jar app.jar
CMD apt-get install libfreetype6
ENTRYPOINT ["java","-jar","/app.jar"]