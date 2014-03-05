#!/bin/bash
mvn clean install -o
mvn jboss-as:deploy --f ear/pom.xml
mvn jboss-as:deploy --f ear/pom.xml
mvn install -o --f webclient/pom.xml
mvn jboss-as:deploy --f webclient/pom.xml
notify-send 'Maven' 'Initial Database Deploy done!!!'