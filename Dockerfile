# Usar OpenJDK 17 como base
FROM openjdk:17-jdk-slim as builder

# Definir variable de entorno del servicio
ARG SERVICE_NAME

WORKDIR /opt/app
# Copiar directorio raiz del ${SERVICE_NAME} de mi equipo al raiz del docker /opt/app
COPY ./ .

RUN ./mvnw -pl ./${SERVICE_NAME} --also-make clean install -DskipTests

FROM openjdk:17-jdk-slim

ARG SERVICE_NAME

WORKDIR /opt/app
COPY --from=builder /opt/app/${SERVICE_NAME}/target/*.jar app.jar
# Comando de ejecución
ENTRYPOINT ["java", "-jar", "app.jar"]