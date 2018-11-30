import java.util.Scanner;

public class P2_Dharmadhikari_Neeraj_MinesweeperController {
	P2_Dharmadhikari_Neeraj_MinesweeperModel game = new P2_Dharmadhikari_Neeraj_MinesweeperModel();
	//P2_Dharmadhikari_Neeraj_MinesweeperView gameView = new P2_Dharmadhikari_Neeraj_MinesweeperView();
	P2_Dharmadhikari_Neeraj_GridViewInterface gridView = new P2_Dharmadhikari_Neeraj_GridViewInterface(this);
	P2_Dharmadhikari_Neeraj_MinesweeperAI aIntel = new P2_Dharmadhikari_Neeraj_MinesweeperAI(game);
	
	/*
	public void playMineSweeper(){
		int row;
		int col;
		boolean bflag = false;
		boolean breveal = false;
		Scanner in = new Scanner(System.in);
		System.out.println("Minesweeper");
		System.out.println("");
		
		while(game.youLose == false && game.youWin == false){
			
			gameView.printTable(game.getTable(), game.getReveal());
			System.out.println("Would you like to flag a cell or reveal a cell?");
			System.out.print("Enter 'f' or 'r' --> ");
			String flgRev = in.next();
			if(flgRev.equals("f")){
				bflag = true;
				breveal = false;
			}else if(flgRev.equals("r")){
				bflag = false;
				breveal = true;
			}
			System.out.println("");
			System.out.print("Enter a Row: ");
			String sRow = in.next();
			row = Integer.parseInt(sRow) - 1;
			System.out.print("Enter a Col: ");
			String sCol = in.next();
			col = Integer.parseInt(sCol) - 1;
			System.out.println("");
			
			if(breveal == true){
				game.reveal(row, col);
			}else{
				game.flag(row, col);
			}
			if(game.tiles == game.numOfMines){
				game.youWin = true;
				break;
			}
			
		}
		//gameView.printTable(game.table, game.reveal);
		if(game.youWin == false){		
			System.out.println("You Lose. oops");
		}else{
			System.out.println("You Win. nice");
		}
	}
	*/
	
	public int handleMouseClick(int row, int col, String flgRev){
		if(flgRev.equals("r")){
			game.reveal(row - 1, col - 1);
			System.out.println(row + " r " + col);
		}else{
			game.flag(row - 1, col - 1);
			System.out.println(row + " f " + col);
		}
		if(game.youLose){
			game.revealAll();
			return -1; //game lost
			
		}
		if(game.tiles == game.numOfMines){
			game.youWin = true;
			return 1;//game won
		}
		return 0; //continue game
	}
	
	public char[][] getRevArr(){
		return game.getReveal();
		
	}
	
	public int getBombsRemaining(){
		return game.getBombsRemaining();
	}
	
	public void newGame(){
		game = new P2_Dharmadhikari_Neeraj_MinesweeperModel();
		aIntel.setModel(game);
		
	}
	
	public void setNumOfMines(int x){
		game = new P2_Dharmadhikari_Neeraj_MinesweeperModel(x);
		aIntel.setModel(game);
	}
	
	public void nextMove(){
		aIntel.nextMove();
	}
	
	public int endCheck(){
		return game.getNumOfTiles();
	}
	
	public boolean youLose(){
		if(game.youLose == true){
			return true;
		}
		return false;
	}
}
