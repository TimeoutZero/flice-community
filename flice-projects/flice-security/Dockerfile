FROM lucasgmartins/java8
MAINTAINER Lucas Martins

ADD target/flice-security.jar /data/flice-security.jar
ADD src/main/resources/docker.yml /data/docker.yml
CMD java -jar /data/flice-security.jar server /data/docker.yml

EXPOSE 8081 9091