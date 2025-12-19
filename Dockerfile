# ===============================
# ✅ Step 1: Build Stage
# ===============================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and source
COPY pom.xml .
COPY src ./src

# Build the JAR (skip tests)
RUN mvn clean package -DskipTests

# ===============================
# ✅ Step 2: Run Stage
# ===============================
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy built JAR file
COPY --from=build /app/target/*.jar app.jar

# Expose port (Render uses 8080)
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
