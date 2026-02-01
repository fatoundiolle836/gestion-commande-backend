# Étape 1 : Build du projet avec Maven + JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copier uniquement pom.xml pour télécharger les dépendances
COPY pom.xml .
RUN mvn dependency:go-offline

# Copier le code source et builder
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Image finale légère avec JRE 21
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copier le jar généré depuis l’étape build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
