#!/bin/bash
mvn clean install -o
notify-send 'Maven' 'Services deploy done!!!'