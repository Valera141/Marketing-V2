FROM maven:3.9.9-eclipse-temurin-21-alpine

WORKDIR /app

COPY . .

# Esto ahora funcionará porque las versiones en pom.xml son correctas
RUN mvn clean package -DskipTests

EXPOSE 8080

# Usamos el nombre corto que definimos en <finalName>
ENTRYPOINT ["java", "-jar", "target/app.jar"]
