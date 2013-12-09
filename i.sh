#!/bin/bash
mvn install -o
mvn install -o --f webclient/pom.xml
notify-send 'Maven' 'Deploy done!!!'
