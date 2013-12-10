#!/bin/bash
mvn install -o --f webclient/pom.xml
notify-send 'Maven' 'webclient install done!!!'
