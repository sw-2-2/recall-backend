# 1단계: 빌드
FROM gradle:8.5-jdk17 AS build
WORKDIR /app

# 캐싱을 위해 설정 파일만 먼저 복사
COPY build.gradle settings.gradle ./
# gradle 폴더 전체(wrapper 포함) 복사
COPY gradle ./gradle
COPY gradlew ./
RUN chmod +x gradlew

# 의존성 미리 받기 (빌드 속도 개선)
RUN ./gradlew dependencies --no-daemon

# 소스 복사 및 빌드
COPY src ./src
# plain jar 생성을 방지하거나 명확한 결과물을 위해 실행
RUN ./gradlew bootJar -x test --no-daemon

# 2단계: 실행
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# 보안을 위해 비루트 사용자 추가
RUN addgroup -S spring && adduser -S spring -G spring

# 빌드 결과물 복사 시 소유권을 spring 유저로 지정
COPY --from=build --chown=spring:spring /app/build/libs/app.jar app.jar

# 유저 전환
USER spring

EXPOSE 8080

# ENTRYPOINT는 그대로 유지해도 좋습니다.
ENTRYPOINT ["java", "-jar", "-Duser.timezone=Asia/Seoul", "app.jar"]