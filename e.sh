#!/bin/bash
mvn clean install -o
mvn clean install -o --f webclient/pom.xml
mvn install -o
mvn install -o --f webclient/pom.xml
notify-send 'Maven' 'Initial Database Deploy done!!!'