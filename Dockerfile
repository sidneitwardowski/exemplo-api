FROM openjdk:16
MAINTAINER Sidnei Twardowski

#ENV LANG pt_BR.UTF-8
#ENV LANGUAGE pt_BR:pt:en
#ENV TZ America/Sao_Paulo

WORKDIR /app

COPY target/ExemploApiRest-1.0.0.jar /app/ExemploApiRest-1.0.0.jar

#RUN apk add ttf-dejavu
#RUN apk --update add fontconfig ttf-dejavu

ENTRYPOINT ["java", "-jar", "ExemploApiRest-1.0.0.jar", "-Djava.awt.headless=true"]