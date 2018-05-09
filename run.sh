#!/usr/bin/env bash

gradle build fatJar

pathToJar="build/libs/hashcode2018-all-1.0-SNAPSHOT.jar"

java -jar $pathToJar $@