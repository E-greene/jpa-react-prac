# 1. 빌드 스테이지
FROM node:20.19.2 AS build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# 2. Nginx 스테이지 (정적 파일 서비스)
FROM nginx:stable
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 80
