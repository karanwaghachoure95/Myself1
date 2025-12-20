# ===============================
# ✅ Step 1: Build Stage
# ===============================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and source
COPY pom.xml .
COPY src ./src

# Build the WAR (skip tests for faster build)
RUN mvn clean package -DskipTests

# ===============================
# ✅ Step 2: Run Stage
# ===============================
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy built WAR file from build stage
COPY --from=build /app/target/*.war app.war

# Expose port 8080 for Render
EXPOSE 8080

# Run the WAR file
ENTRYPOINT ["java", "-jar", "app.war"]
