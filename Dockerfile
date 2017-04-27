# Select base image
FROM ubuntu:16.04

ENV JAVA_VER 8
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
# Install java
RUN echo 'deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main' >> /etc/apt/sources.list && \
    echo 'deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main' >> /etc/apt/sources.list && \
    apt-key adv --keyserver keyserver.ubuntu.com --recv-keys C2518248EEA14886 && \
    apt-get update && \
    echo oracle-java${JAVA_VER}-installer shared/accepted-oracle-license-v1-1 select true |  /usr/bin/debconf-set-selections && \
    apt-get install -y --force-yes --no-install-recommends oracle-java${JAVA_VER}-installer oracle-java${JAVA_VER}-set-default && \
    apt-get clean && \
    rm -rf /var/cache/oracle-jdk${JAVA_VER}-installer
RUN apt-get update
RUN apt-get -qq -y install curl
RUN curl -sL https://deb.nodesource.com/setup_6.x -o nodesource_setup.sh
RUN bash nodesource_setup.sh
RUN apt-get install -yqq nodejs
RUN apt-get install -yqq build-essential
RUN npm install -g @angular/cli@1.0.1
RUN npm install -g http-serve

# copy app files
COPY target/fugu-1.0.jar  /usr/src/app/target/

# tell the port number the container should expose
EXPOSE 4200

# run the application
CMD ["java", "-jar", "usr/src/app/target/fugu-1.0.jar"]
