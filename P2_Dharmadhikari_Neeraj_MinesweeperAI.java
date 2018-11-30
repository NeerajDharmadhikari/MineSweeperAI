import java.util.*;

public class P2_Dharmadhikari_Neeraj_MinesweeperAI {
	
	P2_Dharmadhikari_Neeraj_MinesweeperModel m;
	int vertical[] = {-1, -1, -1, 0, 0, +1, +1, +1};
    int horizontal[] = {-1, 0, +1, -1, +1, -1, 0, +1};
    boolean nextMoveDone = false;
    
	public P2_Dharmadhikari_Neeraj_MinesweeperAI(P2_Dharmadhikari_Neeraj_MinesweeperModel mod){
		m = mod;
	}
	
	public void setModel(P2_Dharmadhikari_Neeraj_MinesweeperModel mod){
		m = mod;
	}
	
	public void nextMove(){
		int nRows = m.getRows();
		int nCols = m.getCols();
		nextMoveDone = false;
		System.out.println("calling flagAI");
		flagAI(nRows, nCols);
		if(nextMoveDone == false){
			System.out.println("calling revealAI");
			revealAI(nRows, nCols);
		}
		if(nextMoveDone == false){
			System.out.println("calling randomRev");
			revealRandom(nRows, nCols);
		}
	}
	
	public void revealRandom(int nRows, int nCols){
		Random r = new Random();
		ArrayList <Integer> posRows = new ArrayList<Integer>();
		ArrayList <Integer> posCols = new ArrayList<Integer>();
		for(int col = 0; col < nCols; col++){
			for(int row = 0; row < nCols; row++){
				if(m.reveal[row][col] == '-'){
					posRows.add(row); //add all the possible tile rows to an array
					posCols.add(col); //add all the possible tile cols to an array
				}
			}
		}
		int index = r.nextInt(posRows.size()); //select a random tile
		int row = posRows.get(index);
		int col = posCols.get(index);
		m.reveal(row, col);
		nextMoveDone = true;
	}
	
	public void flagAI(int nRows, int nCols){
		for(int col = 0; col < nRows; col++){
			for(int row = 0; row < nCols; row++){
				if(m.reveal[row][col] == ' ' || m.reveal[row][col] == '-' || m.reveal[row][col] == 'F' || m.reveal[row][col] == 'Q'){
					continue; //do nothing
				}
				int numOfUnrevealed = 0;
				for(int i = 0; i < 8; i++){
					int checkRow = row + vertical[i];
		            int checkCol = col + horizontal[i];
		            if(checkRow < m.table.length && checkRow >= 0 && checkCol < m.table[0].length && checkCol >= 0){
		            	if(m.reveal[checkRow][checkCol] == '-' || m.reveal[checkRow][checkCol] == 'F' || m.reveal[checkRow][checkCol] == 'Q'){
		            		numOfUnrevealed++;	
		            	}
		            }
				}
				if(numOfUnrevealed == m.table[row][col]){
            		//flag the first square
					for(int i = 0; i < 8; i++){
						int checkRow = row + vertical[i];
			            int checkCol = col + horizontal[i];
			            if(checkRow < m.table.length && checkRow >= 0 && checkCol < m.table[0].length && checkCol >= 0){
			            	if(m.reveal[checkRow][checkCol] == '-'){
			            		m.flag(checkRow, checkCol);
			            		nextMoveDone = true;
								return;
			            	}
			            }
					}

            		
            	}
			}
		}
	}
	
	public void revealAI(int nRows, int nCols){
		for(int col = 0; col < nRows; col++){
			for(int row = 0; row < nCols; row++){
				int numOfUnrevealed = 0;
				int numOfFlags = 0;
				for(int i = 0; i < 8; i++){
					int checkRow = row + vertical[i];
		            int checkCol = col + horizontal[i];
		            if(checkRow < m.table.length && checkRow >= 0 && checkCol < m.table[0].length && checkCol >= 0){
		            	if(m.reveal[checkRow][checkCol] == '-'){
		            		numOfUnrevealed++;
		            	}else if(m.reveal[checkRow][checkCol] == 'F' || m.reveal[checkRow][checkCol] == 'Q'){
		            		numOfFlags++;
		            	}
		            }
				}
				if(numOfFlags == m.table[row][col] && numOfUnrevealed > 0){            		
            		//there are squares that are not flagged, but there are flags equal to value of box*/
            		//reveal one unflagged square (assuming the user has flagged correctly)
            		//return so that it goes only one move at a time
					for(int i = 0; i < 8; i++){
						int checkRow = row + vertical[i];
			            int checkCol = col + horizontal[i];
			            if(checkRow < m.table.length && checkRow >= 0 && checkCol < m.table[0].length && checkCol >= 0){
			            	if(m.reveal[checkRow][checkCol] == '-'){
			            		m.reveal(checkRow, checkCol);
			            		nextMoveDone = true;
								return;		
							}
			            }
					}

            	}
			}
		}
	}

}
