# 1. 빌드 스테이지
# Java 11 JDK 이미지 사용
FROM eclipse-temurin:11-jdk AS build
# 작업 디렉토리 지정
WORKDIR /app
# 모든 프로젝트 파일 복사
COPY . .
# gradle 빌드 (테스트 생략, jar 파일 생성)
RUN ./gradlew build -x test

# 2. 실행 스테이지
# 더 가벼운 JRE 이미지를 런타임으로 사용
FROM eclipse-temurin:11-jre
# 실행 디렉토리
WORKDIR /app
# jar 복사
COPY --from=build /app/build/libs/*.jar app.jar
# 8080포트 오픈 (Springboot 기본)
EXPOSE 8081
# 컨테이너 실행 시 jar 파일 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
