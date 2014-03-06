#!/bin/bash
mvn jboss-as:deploy --f ear/pom.xml
mvn jboss-as:deploy --f ear/pom.xml
mvn jboss-as:deploy --f webclient/pom.xml
notify-send 'Maven' 'Initial Database Deploy done!!!'