FROM centos:centos7
MAINTAINER Lucas Martins	

RUN curl -L -o jdk8.rpm "https://www.dropbox.com/s/zsa71scuujumr1i/jdk-8u31-linux-x64.rpm?dl=1" 
RUN yum install -y jdk8.rpm
RUN rm jdk8.rpm

ENV JAVA_HOME "/usr/java/jdk1.8.0_31"
ENV PATH $PATH:$JAVA_HOME/bin

CMD ["java", "-version"]