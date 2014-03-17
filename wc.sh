#!/bin/bash
mvn clean install -o --f webclient/pom.xml
mvn jboss-as:deploy --f webclient/pom.xml
notify-send 'Maven' 'Webclient clean install done!!!'
