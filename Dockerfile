# Step 1: Build WAR
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml . 
COPY src ./src
RUN mvn clean package -DskipTests

# Step 2: Run on Tomcat
FROM tomcat:9.0-jdk17-temurin

# Clean default apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Optional: bind Render PORT dynamically
ENV PORT=8080

# Deploy WAR as ROOT
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Optional: logs to stdout
RUN ln -sf /dev/stdout /usr/local/tomcat/logs/catalina.out

EXPOSE 8080

CMD ["catalina.sh", "run"]
