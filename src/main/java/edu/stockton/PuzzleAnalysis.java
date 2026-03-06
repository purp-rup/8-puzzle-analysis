package edu.stockton;

import csis4463.*;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import java.util.ArrayList;
import java.util.function.Function;

public class PuzzleAnalysis {

  /**
   * Generates and returns a list of puzzles with a specific optimal path length.
   *
   * @param numPuzzles the number of puzzles to be generated
   * @param optPathLen the length of the optimal path for this set of puzzles
   * @return the list of puzzles
   */
  public static ArrayList<SlidingTilePuzzle> generatePuzzles(int numPuzzles, int optPathLen) {
    ArrayList<SlidingTilePuzzle> puzzles = new ArrayList<>();

    for (int i = 0; i < numPuzzles; i++) {
      puzzles.add(new SlidingTilePuzzle(3, 3, optPathLen));
    }

    return puzzles;
  }

  /**
   * Solves a list of puzzles using a specified solver method and computes the averaged data from
   * all the puzzles.
   *
   * @param solver the solver method from the SlidingTilePuzzleSolver class
   * @param puzzles the list of puzzles
   * @return a list of averaged data (states expanded, states generated, max states in memory)
   */
  public static ArrayList<Double> solvePuzzles(
      Function<SlidingTilePuzzle, PuzzleSolution> solver, ArrayList<SlidingTilePuzzle> puzzles) {
    int expandedStates = 0;
    int generatedStates = 0;
    int maxStatesMemory = 0;

    for (SlidingTilePuzzle puzzle : puzzles) {
      PuzzleSolution solution = solver.apply(puzzle);

      expandedStates += solution.getNumberOfStatesExpanded();
      generatedStates += solution.getNumGenerated();
      maxStatesMemory += solution.getNumberOfStatesInMemory();
    }

    // Store averaged data
    ArrayList<Double> averagedData = new ArrayList<>();
    averagedData.add(expandedStates / (double) puzzles.size());
    averagedData.add(generatedStates / (double) puzzles.size());
    averagedData.add(maxStatesMemory / (double) puzzles.size());

    return averagedData;
  }

  public static void main(String[] args) {
    // Setup tables
    AsciiTable numStatesExpanded = new AsciiTable();
    numStatesExpanded.addRule();
    numStatesExpanded.addRow(null, null, null, null, null, null, "Num States Expanded");
    numStatesExpanded.addRule();
    numStatesExpanded.addRow("L", "UCS", "A*1", "A*2", "ID", "IDA*1", "IDA*2");
    numStatesExpanded.addRule();

    AsciiTable numStatesGenerated = new AsciiTable();
    numStatesGenerated.addRule();
    numStatesGenerated.addRow(null, null, null, null, null, null, "Num States Generated");
    numStatesGenerated.addRule();
    numStatesGenerated.addRow("L", "UCS", "A*1", "A*2", "ID", "IDA*1", "IDA*2");
    numStatesGenerated.addRule();

    AsciiTable numStatesInMemory = new AsciiTable();
    numStatesInMemory.addRule();
    numStatesInMemory.addRow(null, null, null, null, null, null, "Max States In Memory");
    numStatesInMemory.addRule();
    numStatesInMemory.addRow("L", "UCS", "A*1", "A*2", "ID", "IDA*1", "IDA*2");
    numStatesInMemory.addRule();

    // Generate data for all sets and add to relevant table
    for (int i = 2; i <= 12; i += 2) {
      ArrayList<SlidingTilePuzzle> puzzleSet = generatePuzzles(100, i);

      ArrayList<Double> ucsData =
          solvePuzzles(SlidingTilePuzzleSolver::uniformCostSearch, puzzleSet);
      ArrayList<Double> aStar1Data =
          solvePuzzles(SlidingTilePuzzleSolver::AStarSearchMisplacedTiles, puzzleSet);
      ArrayList<Double> aStar2Data =
          solvePuzzles(SlidingTilePuzzleSolver::AStarSearchManhattanDistance, puzzleSet);
      ArrayList<Double> idData =
          solvePuzzles(SlidingTilePuzzleSolver::iterativeDeepening, puzzleSet);
      ArrayList<Double> idaStar1Data =
          solvePuzzles(SlidingTilePuzzleSolver::idaStarMisplacedTiles, puzzleSet);
      ArrayList<Double> idaStar2Data =
          solvePuzzles(SlidingTilePuzzleSolver::idaStarManhattanDistance, puzzleSet);

      numStatesExpanded.addRow(
          i,
          ucsData.get(0),
          aStar1Data.get(0),
          aStar2Data.get(0),
          idData.get(0),
          idaStar1Data.get(0),
          idaStar2Data.get(0));
      numStatesGenerated.addRow(
          i,
          ucsData.get(1),
          aStar1Data.get(1),
          aStar2Data.get(1),
          idData.get(1),
          idaStar1Data.get(1),
          idaStar2Data.get(1));
      numStatesInMemory.addRow(
          i,
          ucsData.get(2),
          aStar1Data.get(2),
          aStar2Data.get(2),
          idData.get(2),
          idaStar1Data.get(2),
          idaStar2Data.get(2));

      numStatesExpanded.addRule();
      numStatesGenerated.addRule();
      numStatesInMemory.addRule();
    }

    // Configure text and print
    numStatesExpanded.setTextAlignment(TextAlignment.CENTER);
    numStatesGenerated.setTextAlignment(TextAlignment.CENTER);
    numStatesInMemory.setTextAlignment(TextAlignment.CENTER);

    String render1 = numStatesExpanded.render();
    String render2 = numStatesGenerated.render();
    String render3 = numStatesInMemory.render();

    System.out.println(render1);
    System.out.println(render2);
    System.out.println(render3);
  }
}
