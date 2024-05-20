	.DEFAULT_GOAL := build-run

run:
	@./gradlew run

test:
	@./gradlew test --warning-mode fail

build:
	@./gradlew clean build

build-run: build run

.PHONY: build
