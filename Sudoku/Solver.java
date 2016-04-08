//package sudoku;

import java.util.*;


public class Solver 
{
	private Grid problem;
	private ArrayList<Grid> solutions;
	
	
	public Solver(Grid problem)
	{
		this.problem = problem;
	}
	
	
	public void solve()
	{
		solutions = new ArrayList<>();
		solveRecurse(problem);
	}
	
		
	// 
	// FINISH THIS.
	//
	// Standard backtracking recursive solver.
	//
	private void solveRecurse(Grid grid)
	{		
		Evaluation eval = evaluate(grid);
		if (eval == Evaluation.ABANDON)
		{
			return;
		}
		else if (eval == Evaluation.ACCEPT)
		{
			solutions.add(grid);
		}
		else
		{
			ArrayList<Grid> nextGrids = grid.next9Grids();
			for(Grid nextGrid: nextGrids)
				solveRecurse(nextGrid);
		}
	}
	
	//
	// COMPLETE THIS
	//
	// Returns Evaluation.ABANDON if the grid is illegal. 
	// Returns ACCEPT if the grid is legal and complete.
	// Returns CONTINUE if the grid is legal and incomplete.
	//
	public Evaluation evaluate(Grid grid)
	{
		if(grid.isLegal() == false)
			return Evaluation.ABANDON;
		else if(grid.isFull())
			return Evaluation.ACCEPT;
		else
			return Evaluation.CONTINUE;
	}

	
	public ArrayList<Grid> getSolutions()
	{
		return solutions;
	}
}
