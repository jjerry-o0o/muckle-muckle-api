# 빌드
FROM gradle:8.7-jdk17 AS builder
WORKDIR /app

# 빌드 설정 파일 복사
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
RUN gradle dependencies --no-daemon || true

# 소스 복사 & 빌드
COPY src ./src
RUN gradle clean bootJar --no-daemon

# 실행
FROM eclipse-temurin:17-jre
WORKDIR /app

# jar 복사
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]