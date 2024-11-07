# Fast setup
Inside the project directory do:

./mvnw clean package
docker build -t dbcon-app .
docker-compose up -d