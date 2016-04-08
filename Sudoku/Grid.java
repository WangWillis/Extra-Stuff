
import java.util.*;


public class Grid 
{
	private int[][] values;
	private int x, y;
	public Grid(String[] rows)
	{
		x = 0;
		y = 0;
		values = new int[9][9];
		for (int j=0; j<9; j++)
		{
			String row = rows[j];
			char[] charray = row.toCharArray();
			for (int i=0; i<9; i++)
			{
				char ch = charray[i];
				if (ch != '.')
					values[j][i] = ch - '0';
			}
		}
	}
	public Grid(int[][] val, int num, int x, int y){
		values = new int[val.length][val.length];
		for(int i = 0; i < val.length; i++){
			for(int j = 0; j < val[i].length; j++){
				values[i][j] = val[i][j];
			}
		}
		this.x = x;
		this.y = y;
		values[x][y] = num;
	}
	public String toString()
	{
		String s = "";
		for (int j=0; j<9; j++)
		{
			for (int i=0; i<9; i++)
			{
				int n = values[j][i];
				if (n == 0)
					s += '.';
				else
					s += (char)('0' + n);
			}
			s += "\n";
		}
		return s;
	}
	
	
	//
	// COMPLETE THIS
	//
	//
	// Finds an empty member of values[][]. Returns an array list of 9 grids that look like the current grid,
	// except the empty member contains 1, 2, 3 .... 9. Returns null if the current grid is full.
	//
	// Example: if this grid = 1........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//
	// Then the returned array list would contain:
	//
	// 11.......          12.......          13.......          14.......    and so on     19.......
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	//
	public ArrayList<Grid> next9Grids(){
		if(!this.isFull()){
			while(values[x][y] != 0){
				if(y < values[x].length-1){
					y++;
				}else if(x < values.length-1){
					x++;
					y = 0;
				}
			}
			ArrayList<Grid> nextGrids = new ArrayList<Grid>();
			for(int g = 1; g <= 9; g++){
				Grid grids = new Grid(this.values, g, this.x, this.y);
				nextGrids.add(grids);
			}
			return nextGrids;
		}
		return null;
	}
	// Returns true if this grid is legal. A grid is legal if no row, column, or zone contains
	// a repeated 1, 2, 3, 4, 5, 6, 7, 8, or 9.
	public boolean isLegal(){
		int num = values[x][y];
		if(num != 0){
			for(int i = 0; i < values.length; i++){
				if((values[x][i] == num && i != y) || (values[i][y] == num && i != x)){
					return false;
				}
			}
			return checkSquare();
		}
		return true;
	}
	// Returns true if every cell member of values[][] is a digit from 1-9.
	public boolean isFull(){
		for(int i = 0; i < values.length; i++){
			for(int h = 0; h < values.length; h++){
				if(values[i][h] == 0){
					return false;
				}
			}
		}
		return true;
	}
	private boolean checkSquare(){
		int tempX = x, tempY = y;
		int num = values[x][y];
		while(tempX%3 != 0){
			tempX--;
		}
		while(tempY%3 != 0){
			tempY--;
		}
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if((tempX+i != x) && (tempY+j != y) && (values[tempX+i][tempY+j] == num)){
					return false;
				}
			}
		}
		return true;
	}
	// Returns true if x is a Grid and, for every (i,j), 
	// x.values[i][j] == this.values[i][j].
	public boolean equals(Object x)
	{
		Grid that = (Grid)x;
		for(int i = 0; i < values.length; i++){
			for(int h = 0; h < values.length; h++){
				if(this.values[i][h] != that.values[i][h])
					return false;
			}
		}
		return true;
	}
}