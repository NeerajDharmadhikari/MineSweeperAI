import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

public class P2_Dharmadhikari_Neeraj_GridViewInterface {
	P2_Dharmadhikari_Neeraj_MinesweeperController control = null;
	/*public static void main(String[] args) {
		P2_Dharmadhikari_Neeraj_GridViewInterface game = new P2_Dharmadhikari_Neeraj_GridViewInterface();
	}
	*/
	JPanel mainPanel;
	MyDrawingPanel drawingPanel;
	private javax.swing.Timer timer;
	boolean youLose = false;
	boolean youWin = false;
	JLabel mines;
	int offset = 100;
	int timeCount = 0;
	int delay = 100;
	JLabel time;
	boolean cheat = false;
	
	
	
	public P2_Dharmadhikari_Neeraj_GridViewInterface(P2_Dharmadhikari_Neeraj_MinesweeperController c){
		control = c;
		createLayout();
	}
	
	private void createLayout(){
		// Attributes
		
		JFrame window;
	
		// Create Java Window
		window = new JFrame("Minesweeper GUI");
		window.setBounds(100, 100, 220, 500);
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addKeyListener(new KeyListener() {			
			public void keyPressed(KeyEvent e){
				//System.out.println("inside keyPressed" + e.getKeyChar());
				if(e.getKeyChar() == 'a'){
					cheat = true;
					if(!youWin && !youLose){
						control.nextMove();
						int numOfTilesLeft = control.endCheck();
						if(numOfTilesLeft == 0){
							System.out.println("you win");
		        			youWin = true;
		        			timer.stop();
		        			String message = "You cheated and used the computer for help. But you won :)";
		        			JOptionPane.showMessageDialog(mainPanel, message);
						}
						if(control.youLose() == true){
							youLose = true;
							timer.stop();
							String message = "You cheated and used the computer for help. But you still lost :(";
		        			JOptionPane.showMessageDialog(mainPanel, message);
						}
						mainPanel.repaint();
					}
					
				}
			}
			
			public void keyReleased(KeyEvent e){
				//return;
			}
			
			public void keyTyped(KeyEvent e){
				//return;
			}
		
		});
		
		// create a JMenuBar
		JMenuBar menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		JMenu optionsMenu = new JMenu("Options");
		JMenu helpMenu = new JMenu("Help");
		// Create a "Save" JMenuItem
		JMenuItem newGame = new JMenuItem("New Game");
		JMenuItem newBegGame = new JMenuItem("New Beginner Game");
		JMenuItem newIntGame = new JMenuItem("New Intermediate Game");
		JMenuItem newExpGame = new JMenuItem("New Expert Game");
		JMenuItem quitItem = new JMenuItem("Exit");
		gameMenu.add(newGame);
		//gameMenu.add(newBegGame);
		//gameMenu.add(newIntGame);
		//gameMenu.add(newExpGame);
		// add an action listener for quit that exits the program
		quitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.newGame();
				youLose = false;
				youWin = false;
				timeCount = 0;
				timer.start();
				mainPanel.repaint();
				
			}
		});
		gameMenu.add(quitItem);
		menuBar.add(gameMenu);
		JMenuItem setNumOfMines = new JMenuItem("Set Number of Mines");
		//JMenuItem gridSize = new JMenuItem("GridSize");
		optionsMenu.add(setNumOfMines);
		setNumOfMines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Set Number of Mines to:";
				String strNumOfMines = JOptionPane.showInputDialog(message);
				int numOfMines = Integer.parseInt(strNumOfMines);
				if(numOfMines < 0 || numOfMines > 64){
					numOfMines = 10;
					System.out.print("Invalid Number. Keeping value at 10 mines.");
				}
				control.setNumOfMines(numOfMines);
				youLose = false;
				youWin = false;
				timeCount = 0;
				timer.start();
				mainPanel.repaint();
			}
		});
		//optionsMenu.add(gridSize);
		menuBar.add(optionsMenu);
		JMenuItem howToPlay = new JMenuItem("How To Play Instructions");
		JMenuItem about = new JMenuItem("About");
		helpMenu.add(howToPlay);
		helpMenu.add(about);
		
		
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JEditorPane helpContent = null;
				try {
					helpContent = new JEditorPane(new URL("file:about.html"));
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JScrollPane helpPane = new JScrollPane(helpContent);
				JOptionPane.showMessageDialog(null, helpPane, "About", JOptionPane.PLAIN_MESSAGE, null);
			}
		});
		howToPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JEditorPane helpContent = null;
				try {
					helpContent = new JEditorPane(new URL("file:howToPlay.html"));
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JScrollPane helpPane = new JScrollPane(helpContent);
				JOptionPane.showMessageDialog(null, helpPane, "How To Play", JOptionPane.PLAIN_MESSAGE, null);
			}
		});
		menuBar.add(helpMenu);
		menuBar.setPreferredSize(new Dimension(100, 20));
		// add the menu bar to the window
		window.add(menuBar, BorderLayout.NORTH);		
		
		//everything else
		JPanel timeElapsed = new JPanel();
		timeElapsed.setLayout(null);
		timeElapsed.setBounds(30, 350, 130, 50);
		time = new JLabel("0");
		time.setLayout(null);
		time.setBounds(58, 7, 50, 50);
		timeElapsed.add(time);
		timeElapsed.setBorder(BorderFactory.createTitledBorder("Time Elapsed"));
		JPanel mineRemaining = new JPanel();
		mineRemaining.setLayout(null);
		mineRemaining.setBounds(30, 300, 130, 50);
		mines = new JLabel("0");
		mines.setLayout(null);
		mines.setBounds(58, 7, 50, 50);
		mineRemaining.add(mines);
		mineRemaining.setBorder(BorderFactory.createTitledBorder("Mines Remaining"));

		timer = new javax.swing.Timer(100, new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				timeCount += 100;
				if(timeCount > delay){
					time.setText("" + (timeCount/1000));				
				}
				
				
			}
		});
		
		timer.start();
		

		// JPanel to draw in
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		drawingPanel = new MyDrawingPanel();
		drawingPanel.setBounds(20, 20, 163, 263);
		drawingPanel.setBorder(BorderFactory.createEtchedBorder());
		mainPanel.add(drawingPanel);
		mainPanel.add(timeElapsed);
		mainPanel.add(mineRemaining);
		window.getContentPane().add(mainPanel);
		
		//defining mouse events
		mainPanel.addMouseListener(new MouseAdapter() {
        
        	@Override
        	public void mouseClicked(MouseEvent e){
        		if(youLose || youWin)
        			return;
        		int col = e.getX()/20;
    			int row = (e.getY() - 100)/20;
    			if(row <= 8 && col <= 8){
	    			String flgRev = "f";
	        		if (e.getButton() == MouseEvent.BUTTON1) {
	        			flgRev = "r";
	        		}
	        		int ret = control.handleMouseClick(row, col, flgRev);
	        		if(ret == 1){
	        			System.out.println("you win");
	        			youWin = true;
	        			timer.stop();
	        		}else if(ret == -1){
	        			System.out.println("you lose");
	        			youLose = true;
	        			timer.stop();
	        		}
    			}
         			
        		
        		drawingPanel.repaint();
        		
        	}
        });
		
		window.setVisible(true);
		

	}
	
	private class MyDrawingPanel extends JPanel {
		Color penColor;
		
		public MyDrawingPanel(){
			
		}
		
		static final long serialVersionUID = 1234567890L;

		public void paintComponent(Graphics g) {
			mines.setText("" + control.getBombsRemaining());
			g.setColor(Color.white);
			g.fillRect(2, 2, this.getWidth()-2, this.getHeight()-2);
			char[][] reveal = control.getRevArr();
			g.setColor(Color.lightGray);
			if(youLose){
				try {
					g.drawImage(ImageIO.read(new File("face_dead.gif")), 60, 5, 50, 50, null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(youWin){
				try {
					g.drawImage(ImageIO.read(new File("face_win.gif")), 60, 5, 50, 50, null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					g.drawImage(ImageIO.read(new File("face_smile.gif")), 60, 5, 50, 50, null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int row = 0; row < 8; row++){
				for (int col = 0; col < 8; col++){
				//g.drawLine(x, 0, x, this.getHeight());
					
					try {
						if(reveal[row][col] == '-')
							g.drawImage(ImageIO.read(new File("blank.gif")), col*20, offset + row*20, 20, 20, null);
						else if(reveal[row][col] == '*')
							g.drawImage(ImageIO.read(new File("bomb_revealed.gif")), col*20, offset + row*20, 20, 20, null);
						else if(reveal[row][col] == '1')
							g.drawImage(ImageIO.read(new File("num_1.gif")), col*20, offset + row*20, 20, 20, null);
						else if(reveal[row][col] == '2')
							g.drawImage(ImageIO.read(new File("num_2.gif")), col*20, offset + row*20, 20, 20, null);
						else if(reveal[row][col] == '3')
							g.drawImage(ImageIO.read(new File("num_3.gif")), col*20, offset + row*20, 20, 20, null);
						else if(reveal[row][col] == '4')
							g.drawImage(ImageIO.read(new File("num_4.gif")), col*20, offset + row*20, 20, 20, null);
						else if(reveal[row][col] == '5')
							g.drawImage(ImageIO.read(new File("num_5.gif")), col*20, offset + row*20, 20, 20, null);
						else if(reveal[row][col] == '6')
							g.drawImage(ImageIO.read(new File("num_6.gif")), col*20, offset + row*20, 20, 20, null);
						else if(reveal[row][col] == '7')
							g.drawImage(ImageIO.read(new File("num_7.gif")), col*20, offset + row*20, 20, 20, null);
						else if(reveal[row][col] == '8')
							g.drawImage(ImageIO.read(new File("num_8.gif")), col*20, offset + row*20, 20, 20, null);
						else if(reveal[row][col] == ' ')
							g.drawImage(ImageIO.read(new File("num_0.gif")), col*20, offset + row*20, 20, 20, null);
						else if(reveal[row][col] == 'F')
							g.drawImage(ImageIO.read(new File("bomb_flagged.gif")), col*20, offset + row*20, 20, 20, null);
						else if(reveal[row][col] == 'Q')
							g.drawImage(ImageIO.read(new File("bomb_question.gif")), col*20, offset + row*20, 20, 20, null);
						else if(reveal[row][col] == '#')
							g.drawImage(ImageIO.read(new File("bomb_wrong.gif")), col*20, offset + row*20, 20, 20, null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
	}

}

