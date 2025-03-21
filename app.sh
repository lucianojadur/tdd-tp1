# jar build
./gradlew clean build


# docker build and container run
docker build -t myapp .

docker run -p 8080:8080 \
    -e HOST=0.0.0.0 \
    -e PORT=8080 \
    -e ENVIRONMENT=development \
    -e DATABASE_NAME=class-connect \
    -e DATABASE_USER=user \
    -e DATABASE_PASSWORD=pass \
	--name courses-container courses_app
