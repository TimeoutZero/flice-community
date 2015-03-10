FROM lucasgmartins/java8
MAINTAINER Lucas Martins

ENV FLICE_CORE_JAR_SOURCE "../../../../flice-projects/flice-core/target/flice-core.jar"
ENV FLICE_CORE_JAR_DEST	  "/data/flice-core.jar"

ENV FLICE_CORE_YAML_SOURCE  "../../../../flice-projects/flice-core/src/main/resources/docker.yml"
ENV FLICE_CORE_YAML_DEST	"/data/docker.yml"

ADD FLICE_CORE_JAR_SOURCE FLICE_CORE_JAR_DEST
RUN java -jar flice-core.jar server /data/docker.yml

EXPOSE ["8080", "9090"]