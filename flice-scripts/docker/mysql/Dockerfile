FROM mysql
MAINTAINER Lucas Martins

ADD entrypoint.sh /data/entrypoint.sh
RUN chmod +x /data/entrypoint.sh
ENTRYPOINT ["/data/entrypoint.sh"]

EXPOSE 3306
CMD ["mysqld"]