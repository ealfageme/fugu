# Select base image
FROM ubuntu:16.04

RUN apt-get update
RUN apt-get -qq -y install curl
RUN curl -sL https://deb.nodesource.com/setup_6.x -o nodesource_setup.sh
RUN bash nodesource_setup.sh
RUN apt-get install -yqq nodejs
RUN apt-get install -yqq build-essential
RUN npm install -g @angular/cli@1.0.1

# copy app files
COPY fugu/ /usr/src/app/fugu/
WORKDIR /usr/src/app/fugu/
# tell the port number the container should expose
EXPOSE 4200

RUN npm install 

# run the application
CMD ["ng", "serve"]

