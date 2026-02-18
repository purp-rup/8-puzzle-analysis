package edu.stockton;

import csis4463.*;
import java.util.ArrayList;
import java.util.function.Function;

public class SomeExamples {

	/**
	 *
	 * @param numPuzzles
	 * @param optPathLen
	 * @return
	 */
	public static ArrayList<SlidingTilePuzzle> generatePuzzles(int numPuzzles, int optPathLen)
	{
		ArrayList<SlidingTilePuzzle> puzzles = new ArrayList<>();

		for(int i = 0; i < numPuzzles; i++)
		{
			puzzles.add(new SlidingTilePuzzle(3, 3, optPathLen));
		}

		return puzzles;
	}

	/**
	 *
	 * @param solver
	 * @param puzzles
	 *
	 * Using the Function class allows me to pass a static method as a parameter.
	 * Since all the methods I plan on passing in share the same parameters and return types,
	 * I can create a Function<T, R> object where T is the puzzle being input,
	 * and R is resulting solution path.
	 */
	public static void solvePuzzles(Function<SlidingTilePuzzle, PuzzleSolution> solver, ArrayList<SlidingTilePuzzle> puzzles)
	{
		int expandedStates = 0;
		int generatedStates = 0;
		int maxStatesMemory = 0;

		for(SlidingTilePuzzle puzzle : puzzles)
		{
			PuzzleSolution solution = solver.apply(puzzle);

			expandedStates += solution.getNumberOfStatesExpanded();
			generatedStates += solution.getNumGenerated();
			maxStatesMemory += solution.getNumberOfStatesInMemory();
		}

		// Print averaged values
		System.out.println("Avg. Expanded States		: " + expandedStates / puzzles.size());
		System.out.println("Avg. Generated States		: " + generatedStates / puzzles.size());
		System.out.println("Avg. Max States in Memory	: " + maxStatesMemory / puzzles.size());
	}

	public static void main(String[] args) {
		
		
		// To construct an 8-puzzle first two params should be 3 (a 3 by 3 puzzle). 
		// The 3rd parameter is the length of the shortest path from this state to goal state.
		// E.g., 4 as the 3rd param will generate a random puzzle such that it is possible to
		// find a solution with length 4.
		SlidingTilePuzzle puzzle = new SlidingTilePuzzle(3, 3, 4);
		
		// The SlidingTilePuzzle class overrides the toString method, so you can output
		// a puzzle state with a call to System.out.println as follows.
		System.out.println(puzzle);
		
		// You can solve a puzzle using the implementations of search algorithms provided in the
		// class SlidingTilePuzzleSolver.  See the documentation of that class for the search algorithms
		// included.  You call them all the same way.  Here is an example with A* and Manhattan distance.
		PuzzleSolution solution = SlidingTilePuzzleSolver.AStarSearchManhattanDistance(puzzle);


		
		// After the search is done, you can find the solution as well as statistics about the search
		// via the PuzzleSolution object.  See the documentation for names of the methods.  Here
		// are a couple examples.
		
		// The getSolution method gives you the path as an ArrayList.
		ArrayList<SlidingTilePuzzle> path = solution.getSolution();
		
		System.out.println("Solution path");
		for (SlidingTilePuzzle s : path) {
			System.out.println();
			System.out.println(s);
		}
		
		System.out.println();
		System.out.println("Num states expanded: " + solution.getNumberOfStatesExpanded());
		System.out.println("Num states generated: " + solution.getNumGenerated());
		System.out.println("Num states in memory: " + solution.getNumberOfStatesInMemory());
		System.out.println("Path length: " + solution.getPathLength());
	}
}