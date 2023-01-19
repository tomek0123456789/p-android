FROM gradle:7.5.1-jdk17

RUN apt-get update

RUN mkdir ktor_api

COPY ktor_api ./ktor_api

EXPOSE 8080

CMD ["gradle", "run", "-p", "ktor_api"]
