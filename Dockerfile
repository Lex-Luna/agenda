# Usa una imagen base de Tomcat
FROM tomcat:9.0-jdk11

# Copia tu archivo WAR al directorio de aplicaciones de Tomcat
COPY /target/Agenda.war /usr/local/tomcat/webapps/
