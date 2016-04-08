class Move{
	private int x, y;
	public Move(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getX(){return x;}
	public int getY(){return y;}
	public static boolean won(int [][] game, int pX, int pY){
		boolean win = false;
		if(pX == pY){
			for(int i = 0; i < game.length; i++){
				if(game[i][i] == game[pX][pY]){
					win = true;
				}else{
					win = false;
					break;
				}
			}
		}
		if((pX+pY) == game.length-1 && !win){
			for(int i = 0; i < game.length; i++){
				if(game[i][game.length-i-1] == game[pX][pY]){
					win = true;
				}else{
					win = false;
					break;
				}
			}
		}
		if(!win){
			for(int  i = 0; i < game.length; i++){
				if(game[pX][i] == game[pX][pY]){
					win = true;
				}else{
					win = false;
					break;
				}
			}
		}
		if(!win){
			for(int i = 0; i < game.length; i++){
				if(game[i][pY] == game[pX][pY]){
					win = true;
				}else{
					win = false;
					break;
				}
			}
		}
		return win;
	}
}
class MontiCarloTree{
	private class GameNode{
		public int wins, gamesPlayed;
		public Move move = null;
		private GameNode [] children;
		private int size, index;
		public GameNode(){
			wins = 0;
			gamesPlayed = 0;
			size = 2;
			index = -1;
			children = new GameNode [size];
		}
		public GameNode(int numChild){
			wins = 0;
			gamesPlayed = 0;
			size = 2*numChild;
			index = -1;
			if(numChild > 0){
				children = new GameNode [numChild];
			}else{
				children = null;
			}
		}
		private GameNode [] resize(){
			GameNode [] temp = new GameNode [2*size];
			for(int i = 0; i < index; i++){
				temp[i] = children[i];
			}
			size *= 2;
			System.gc();
			return temp;
		}
		public void addGameNode(GameNode temp){
			index++;
			if(index >= size){
				children = resize();
			}
			children[index] = temp;
		}
		public void setChildren(GameNode [] temp){
			children = temp;
		}
		public void setMove(Move move){
			this.move = move;
		}
		public void sumWinGame(){
			if(children != null){
				for(int i = 0; i <= index; i++){
					wins += children[i].wins;
					gamesPlayed += children[i].gamesPlayed;
				}
			}
		}
	}
	boolean aiPlayer;
	public MontiCarloTree(boolean aiPlayer){
		this.aiPlayer = aiPlayer;
	}

	private int [][] arrCpy(int [][] cpy){
		int [][] temp = new int [cpy.length][cpy.length];
		for(int i = 0; i < cpy.length; i++){
			for(int j = 0; j < cpy[i].length; j++){
				temp[i][j] = cpy[i][j];
			}
		}
		return temp;
	}

	private int freeSpots(int [][] game){
		int freeSpot = 0;
		for(int i = 0; i < game.length; i++){
			for(int j = 0; j < game[i].length; j++){
				if(game[i][j] == 0){
					freeSpot++;
				}
			}
		}
		return freeSpot;
	}

	public Move chooseMove(int [][] game){
		GameNode [] freeSpot = new GameNode [freeSpots(game)];
		int spot = 0;
		for(int i = 0; i < game.length; i++){
			for(int j = 0; j < game[i].length; j++){
				//System.out.println(freeSpot.length);
				if(game[i][j] == 0){
					int [][] temp = arrCpy(game);
					freeSpot[spot] = choose(temp, i, j);
					spot++;
				}
			}
		}
		GameNode bestMove = freeSpot[0];
		for(int i = 1; i < freeSpot.length; i++){
			if((double)bestMove.wins/bestMove.gamesPlayed < (double)freeSpot[i].wins/freeSpot[i].gamesPlayed){
				bestMove = freeSpot[i];
			}
		}
		System.gc();
		//System.out.println("Hi");
		return bestMove.move;
	}
	private GameNode choose(int [][] game, int x, int y){
		if(aiPlayer == true)
			game[x][y] = 1;
		else
			game[x][y] = 2;
		GameNode node = new GameNode();
		node.setMove(new Move(x,y));
		if(Move.won(game, x, y)){
			//printGame(game);
			node.wins = 1;
			node.gamesPlayed = 1;
			return node;
		}else if(freeSpots(game) != 0){
			//printGame(game);
			MontiCarloTree opp = new MontiCarloTree(!aiPlayer);
			Move oppMove = opp.chooseMove(game);
			//System.out.println(oppMove.getX() + " " + oppMove.getY());
			//printGame(game);
			if(aiPlayer == true)
				game[oppMove.getX()][oppMove.getY()] = 2;
			else
				game[oppMove.getX()][oppMove.getY()] = 1;
			if(Move.won(game, oppMove.getX(), oppMove.getY())){
				node.wins = -1;
				node.gamesPlayed = 1;
				return node;
			}else if(freeSpots(game) != 0){
				for(int i = 0; i < game.length; i++){
					for(int j = 0; j < game.length; j++){
						if(game[i][j] == 0){
							int [][] tempGame = arrCpy(game);
							node.addGameNode(choose(tempGame, i, j));
						}
					}
				}
				// int [][] tempGame = arrCpy(game);
				// node.setgetMoves(tempGame);
				node.sumWinGame();
			}else{
				node.wins = 0;
				node.gamesPlayed = 1;
			}
		}else{
			node.wins = 0;
			node.gamesPlayed = 1;
		}
		return node;
	}
	// private GameNode [] getMoves(int [][] game){
	// 	GameNode [] freeSpot = new GameNode [freeSpots(game)];
	// 	int spot = 0;
	// 	for(int i = 0; i < game.length; i++){
	// 		for(int j = 0; j < game[i].length; j++){
	// 			//System.out.println(i + " " + j);
	// 			if(game[i][j] == 0){
	// 				int [][] temp = arrCpy(game);
	// 				freeSpot[spot] = choose(temp, i, j);
	// 				spot++;
	// 			}
	// 		}
	// 	}
	// 	return freeSpot;
	// }
	public void printGame(int [][] game){
		for(int i = 0; i < game.length; i++){
			for(int j = 0; j < game[i].length; j++){
				System.out.print(game[i][j]);
			}
			System.out.print('\n');
		}
		System.out.println();
	}
}