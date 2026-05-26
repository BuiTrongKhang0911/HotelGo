# ==========================================
# STAGE 1: BUILD ALL MODULES
# ==========================================
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app

# Copy entire project
COPY . .

# Build all modules at once
RUN mvn clean package -DskipTests

# ==========================================
# STAGE 2: RUNTIME (Dynamic service selection)
# ==========================================
FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app

# ARG to determine which service to run
ARG SERVICE_NAME

# Copy the specific service JAR from builder stage
COPY --from=builder /app/${SERVICE_NAME}/target/*.jar app.jar

# Expose ports (actual port depends on service configuration)
EXPOSE 8080 8081

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
