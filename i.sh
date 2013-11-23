#!/bin/bash
mvn install -o
notify-send 'Maven' 'Deploy done!!!'
aplay beep.wav
