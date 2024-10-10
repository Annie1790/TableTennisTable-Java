FROM gradle:7.5.1 AS base

WORKDIR /app

COPY . .

CMD ./gradlew run

FROM base AS prod
ENTRYPOINT ./gradlew run --console=plain

FROM base AS test
ENTRYPOINT ./gradlew test