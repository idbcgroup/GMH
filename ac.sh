#!/bin/bash
mvn clean install -o
mvn clean install -o --f webclient/pom.xml
notify-send 'Maven' 'ALL clean install done!!!'
