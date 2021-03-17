FROM openjdk:11.0-jdk-slim
VOLUME /tmp
COPY /target/product-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8100
ENV JAVA_OPTS=""
RUN sh -c "touch product-0.0.1-SNAPSHOT.jar"
ENTRYPOINT [ "java", "-jar", "product-0.0.1-SNAPSHOT.jar" ]