FROM lucasgmartins/java8
MAINTAINER Lucas Martins

ADD target/flice-core.jar /data/flice-core.jar
ADD src/main/resources/docker.yml /data/docker.yml
CMD java -jar /data/flice-core.jar server /data/docker.yml

EXPOSE 8080 9090