FROM tomcat:11

RUN rm -rf /usr/local/tomcat/webapps/*
COPY web/target/web-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

