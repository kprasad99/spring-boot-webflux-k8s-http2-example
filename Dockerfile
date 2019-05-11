FROM openjdk:11-jre-slim
# FROM amazoncorretto:11
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","io.github.kprasad99.sb.webflux.SpringBootWebfluxK8sHttp2ExampleApplication"]
#ENTRYPOINT exec java $JAVA_OPTS -cp app:app/lib/* io.github.kprasad99.sb.webflux.SpringBootWebfluxK8sHttp2ExampleApplication

#ENTRYPOINT exec java $JAVA_OPTS -jar spring-boot-webflux-k8s-http2-example.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar spring-boot-webflux-k8s-http2-example.jar

