public class TicTacToe{
	public static int [][] makeGame(int size){
		int [][] temp = new int [size][size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				temp[i][j] = 0;
			}
		}
		return temp;
	}
	public static void main(String[] args) {
		MontiCarloTree ai = new MontiCarloTree(true);
		int [][] game = makeGame(3);
		game[0][0] = 1;
		game[1][1] = 1;
		game[0][1] = 2;
		game[0][2] = 2;
		Move test = ai.chooseMove(game);
		System.out.println(test.getX() + " " + test.getY());
	}
}