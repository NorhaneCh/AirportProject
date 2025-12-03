##### Stage 1 — Build frontend (React) #####
FROM node:20 AS frontend-build

WORKDIR /app

# Copy package.json and package-lock.json (or yarn.lock) for frontend
COPY frontend/package*.json frontend/

# Install frontend dependencies
RUN cd frontend && npm install

# Copy full frontend source code
COPY frontend frontend/

# Build React app (production)
RUN cd frontend && npm run build


##### Stage 2 — Build backend (Spring Boot) #####
FROM maven:3.9-eclipse-temurin-21 AS backend-build

WORKDIR /app

# Copy full project
COPY . .

# Copy built frontend from the frontend-build stage
# React outputs production files to 'build' folder by default
COPY --from=frontend-build /app/frontend/build ./frontend-dist

# Copy built frontend into Spring Boot static resources
RUN rm -rf src/main/resources/static && \
    mkdir -p src/main/resources/static && \
    cp -r ./frontend-dist/* src/main/resources/static/

# Build backend JAR without tests
RUN mvn -pl ./ -am package -DskipTests


##### Stage 3 — Runtime image #####
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the fat JAR from the build stage
COPY --from=backend-build /app/target/server-0.0.1-SNAPSHOT.jar app.jar

# Spring Boot default port
EXPOSE 8080

# Optional: allow extra JVM args via JAVA_OPTS
ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
