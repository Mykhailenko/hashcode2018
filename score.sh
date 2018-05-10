#!/usr/bin/env bash

gradle scoreJar

pathToJar="build/libs/hashcode2018-score-1.0-SNAPSHOT.jar"

java -jar $pathToJar $@