FROM ubuntu:22.04 

ENV TZ = "Europe/Warsaw"

RUN apt-get update && \
    apt-get -y install software-properties-common &&\
    apt-get -y install tzdata &&\
    apt-get -y install curl && \
    apt-get -y install unzip && \
    apt-get -y install zip

RUN apt-get update && \
    add-apt-repository ppa:deadsnakes/ppa

RUN apt-get update

RUN apt-get -y install python3.8 openjdk-8-jdk

RUN curl -s "https://get.sdkman.io" | bash
RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh; sdk install kotlin; sdk install gradle"

ENV PATH=$PATH:/root/.sdkman/candidates/gradle/7.5.1/bin

RUN mkdir HelloWorld

COPY HelloWorld ./HelloWorld

CMD ["gradle", "run", "-p", "/HelloWorld"]