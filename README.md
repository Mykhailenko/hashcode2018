
# Tool to calculate score for the task in final google hashcode 2018.

## to score via gradle without argument (does not work)

`gradle build run`

## to build score fat jar

`gradle scoreJar`

## to score on solution

`./score.sh --challenge challenge.txt --solution solution.txt`

## to build all score fat jar

`gradle allScoreJar

## to score all solutions in the folder

`./all-score.sh --folder ./data/`