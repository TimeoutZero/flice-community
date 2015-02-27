FROM centos:centos7
MAINTAINER Lucas Martins 

RUN rpm -Uvh "http://nginx.org/packages/centos/7/noarch/RPMS/nginx-release-centos-7-0.el7.ngx.noarch.rpm"
RUN yum install -y nginx

EXPOSE 80 443

CMD ["nginx", "-g", "daemon off;"]