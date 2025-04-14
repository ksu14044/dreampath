# 1단계: Vite + React 빌드
From node:20 AS build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# 2단계: nginx 배포
FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html