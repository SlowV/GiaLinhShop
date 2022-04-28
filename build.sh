mvn clean install -DskipTests &&
docker build -t fruit-app . &&
docker-compose -f src/main/docker/app.yml down &&
docker-compose -f src/main/docker/app.yml up -d &&
docker image prune -a -f