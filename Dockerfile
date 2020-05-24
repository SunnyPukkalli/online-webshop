# TOMCAT FROM DOCKER HUB
FROM tomcat:8.5-jdk8



# COPY WAR FILE FROM MACHINE INTO DOCKER
COPY ./target/onlineshop.war onlineshop.war
COPY ./target/onlineshop.war /usr/local/tomcat/webapps/onlineshop.war

# EXPOSE THIS PORT FOR NETWORKING PURPOSE
EXPOSE 8433

# CHECK JAVA VERSION
CMD ["java -version" ]

# START TOMCAT
CMD ["catalina.sh","run"]
