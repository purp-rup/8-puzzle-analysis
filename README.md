# Performance Analysis on Several Search Algorithms

This program benchmarks six search algorithms on randomly generated 8-puzzle instances. 
For each even optimal path length from 2 to 12, it generates 100 puzzles and solves them using the following algorithms:
- <b> Uniform Cost Search (UCS) 
- A* Search with Misplaced Tiles (A*1)
- A* Search with Manhattan Distance (A*2)
- Iterative Deepening (ID)
- Iterative Deepening A* Search with Misplaced Tiles (IDA*1)
- Iterative Deepening A* Search with Manhattan Distance (IDA*2) </b>

Results are averaged across each puzzle set and displayed in three formatted ASCII tables comparing states expanded,
states generated, and max states in memory&mdash;making it easy to analyze the trade-offs between informed and uninformed search algorithms.

## To Run

1. Run the following commands, using the absolute path to libs/puzzle.jar in your project directory for "-Dfile"
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

## Authors
- <b> Andrew Miraglia</b> <br/>
  miraglia@go.stockton.edu <br/>

- <b> Alexander DeSilvio</b> <br/>
  desilvia@go.stockton.edu <br/>
