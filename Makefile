APPLICATION_CONTAINER_NAME=application-partner

down:
	@docker-compose down --v
run:
	@$(MAKE) down
	./gradlew clean build -x test
	docker-compose up -d --build
	docker logs ${APPLICATION_CONTAINER_NAME} --follow