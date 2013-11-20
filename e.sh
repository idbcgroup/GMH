#!/bin/bash
mvn clean install -o
mvn install -o
notify-send 'Maven' 'Deploy done!!!'
