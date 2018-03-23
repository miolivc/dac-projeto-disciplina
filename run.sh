mvn clean install
cd app-json/
mvn clean install
cd ..
# docker-compose build --no-cache
docker-compose up -d --build
