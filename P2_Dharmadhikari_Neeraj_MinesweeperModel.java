import java.util.*;

public class P2_Dharmadhikari_Neeraj_MinesweeperModel {
	public static final char MINE = '*';
	public static final char FLAG = 'F';
	public static final char TILE = '-';
	public static final char EMPTY = ' ';
	public static final char QUES = 'Q';
	boolean youLose = false;
	boolean youWin = false;
	int vertical[] = {-1, -1, -1, 0, 0, +1, +1, +1};
    int horizontal[] = {-1, 0, +1, -1, +1, -1, 0, +1};
    boolean play = true;
	int[][] table;
	char[][] reveal;
	int tiles = 64;
	int numOfMines;
	int bombsRemaining;
	
	public P2_Dharmadhikari_Neeraj_MinesweeperModel(){
		table = new int[8][8];
		reveal = new char[8][8];
		fillWithTiles(reveal);
		numOfMines = 10;
		addMines(table);
		calcNums(table);
		youLose = false;
	}
	public P2_Dharmadhikari_Neeraj_MinesweeperModel(int x){
		table = new int[8][8];
		reveal = new char[8][8];
		fillWithTiles(reveal);
		numOfMines = x;
		addMines(table);
		calcNums(table);
		youLose = false;
	}
	
	public void fillWithTiles(char[][] reveal){
		for(int col = 0; col < reveal[0].length; col++){
			for(int row = 0; row < reveal.length; row++){
				reveal[row][col] = TILE;
			}
		}
	}
	
	public void addMines(int table[][]){
		Random r = new Random();
		//numOfMines = r.nextInt(10) + 6;
		//numOfMines = 5; //?
		bombsRemaining = numOfMines;
		int i = 0;
		while(i < numOfMines){
			int row = r.nextInt(8);
			int col = r.nextInt(8);
			if(table[row][col] != -1){
				table[row][col] = -1;
				i++;
			}
		}
	}
	
	public void calcNums(int table[][]){
		for(int col = 0; col < table[0].length; col++){
			for(int row = 0; row < table.length; row++){
				for(int i = 0; i < 8; i++){
					int checkRow = row + vertical[i];
		            int checkCol = col + horizontal[i];
		            if(checkRow < table.length && checkRow >= 0 && checkCol < table[0].length && checkCol >= 0){
		            	if(table[checkRow][checkCol] == -1 && table[row][col] != -1){
		            		table[row][col]++;
		            	}
		            }
				}
			}
		}
	}
	
	public int[][] getTable(){
		return table;
	}
	
	public char[][] getReveal(){
		return reveal;
	}
	
	public void reveal(int x, int y){
		if(reveal[x][y] == TILE || reveal[x][y] == FLAG || reveal[x][y] == QUES) {
			if(reveal[x][y] == FLAG){
				bombsRemaining++;
			}
			if(table[x][y] == -1){
				reveal[x][y] = MINE;
				tiles--;
				youLose = true;
			}else if(table[x][y] == 0){
				tiles--;
				reveal[x][y] = EMPTY;
				for(int i = 0; i < 8; i++){
					int checkRow = x + vertical[i];
		            int checkCol = y + horizontal[i];
		            if(checkRow < reveal.length && checkRow >= 0 && checkCol < reveal[0].length && checkCol >= 0){
		            	if(table[checkRow][checkCol] != -1){
		            		reveal(checkRow, checkCol);
		            	}
		            }
				}
			}else{
				tiles--;
				reveal[x][y] = (char)('0' + table[x][y]);
			}
		}
	}
	
	public void flag(int x, int y){
		if(reveal[x][y] == TILE){
			reveal[x][y] = FLAG;
			bombsRemaining--;
		}else if(reveal[x][y] == FLAG){
			reveal[x][y] = QUES;
			bombsRemaining++;
		}else if(reveal[x][y] == QUES){
			reveal[x][y] = TILE;
		}
	}
	
	public void revealAll(){
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				if(table[row][col] == -1){
					reveal(row, col);
				}
			}
			
		}
	}
	
	public int getBombsRemaining(){
		return bombsRemaining;
	}
	
	public void setNumOfMines(int x){
		numOfMines = x;
	}
	
	public int getRows(){
		return 8;
	}
	
	public int getCols(){
		return 8;
	}
	
	public int getNumOfTiles(){
		int numOfBlankTiles = 0;
		for(int col = 0; col < 8; col++){
			for(int row = 0; row < 8; row++){
				if(reveal[row][col] == '-'){
					numOfBlankTiles++;
				}
			}
		}
		return numOfBlankTiles;
	}
}
