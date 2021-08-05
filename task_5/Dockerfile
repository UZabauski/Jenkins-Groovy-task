FROM jenkins/jenkins:latest

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

COPY ./scripts/ /usr/share/jenkins/ref/init.groovy.d/

COPY MyPlugins.txt /usr/share/jenkins/ref/Plugins.txt

RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/Plugins.txt