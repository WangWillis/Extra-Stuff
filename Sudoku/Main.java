//package sudoku;
public class Main{
	public static void main(String[] args)
	{
		Grid g = TestGridSupplier.getPuzzle3();		// or any other puzzle
		Solver solver = new Solver(g);
		solver.solve();
		System.out.println("Ours: ");
		System.out.println(solver.getSolutions());
		System.out.println("Given: ");
		System.out.println(TestGridSupplier.getSolution3());
		// Print out your solution, or test if it equals() the solution in TestGridSupplier.
	}
}