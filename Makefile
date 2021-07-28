APPLICATION_CONTAINER_NAME=app

down:
	@docker-compose down --v
run:
	@$(MAKE) down
	./gradlew clean build -x test
	docker-compose up -d --build
test:
	@$(MAKE) down
	docker-compose up -d --build
	./gradlew build