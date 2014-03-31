#!/bin/bash
mvn clean install 
mvn clean install --f webclient/pom.xml
mvn install 
notify-send 'Maven' 'Initial Database Compile done!!!'