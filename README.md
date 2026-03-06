# Performance Analysis on Several Search Algorithms

## To Run

1. Run the following commands, using the absolute path to libs/puzzle.jar in your project directory as "-Dfile"
```console
mvn install:install-file "-Dfile=.../project/libs/puzzle.jar" "-DgroupId=edu.stockton" "-DartifactId=puzzle" "-Dversion=1.0" "-Dpackaging=jar"   
```
```console
mvn clean package
```

2. Then, run with:
```console
java -jar target/8puzzle-analysis-1.0.0.jar
```
