#!/bin/bash
mvn clean install -o
mvn install -o
mvn clean -o --f webclient/pom.xml
mvn install -o --f webclient/pom.xml
notify-send 'Maven' 'Deploy done!!!'
aplay beep.wav
