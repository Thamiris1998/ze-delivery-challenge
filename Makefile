APPLICATION_CONTAINER_NAME=app

test:
	@docker-compose down --v
	./gradlew build
run:
	@$(MAKE) down
	./gradlew clean build -x test
	docker-compose up -d --build
down:
	@docker-compose down --v