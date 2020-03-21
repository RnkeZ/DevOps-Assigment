FROM openjdk:8-slim

ENV SPRING_PROFILE="default"


ADD /target/infobip-redis-service.war infobip-redis-service.war
ENTRYPOINT echo Europe/Zagreb > /etc/timezone && \
	java  -jar -Dspring.profiles.active=$SPRING_PROFILE /infobip-redis-service.war
