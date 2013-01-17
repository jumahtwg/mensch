package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Figure;

import controller.Controller;
import controller.Controller.GAME_STATE;

import observer.IObserver;

// XXXXXXXXXXXXX GUI XXXXXXXXXXXXXXXXXXXXXXXXXXXXX
public class GUI extends JFrame implements IObserver {

	private static final long serialVersionUID = 1L;

	GamePaint gamePaint;
	

	private Controller controller;
	private JPanel panelSouth;
	private JButton wuerfel;
	private JButton enter;
	private JPanel panelWest;
	private JButton figur0 ;
	private JButton figur1 ;
	private JButton figur2 ;
	private JButton figur3 ;
	
	private JTextField output;
	private JTextField input;
	private JTextField status;
	
	
	
	public GUI(Controller controller) {
		super("Mensch ï¿½rger dich nicht");
		
		this.controller = controller;
		
		gamePaint = new GamePaint(controller);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		ActionListener buttons = new ButtonListener(this.controller);
		
		getContentPane().setLayout(new BorderLayout());

		panelSouth = new JPanel();
		panelSouth.setLayout(new GridLayout(2, 2));
		
		wuerfel = new JButton("Würfel");
		wuerfel.addActionListener(buttons);
		
		enter = new JButton("Enter");
		enter.addActionListener(buttons);
		
		panelWest = new JPanel();
		panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.PAGE_AXIS));
		
		figur0 = new JButton("Figur0");
		figur1 = new JButton("Figur1");
		figur2 = new JButton("Figur2");
		figur3 = new JButton("Figur3");
		figur0.addActionListener(buttons);
		figur1.addActionListener(buttons);
		figur2.addActionListener(buttons);
		figur3.addActionListener(buttons);
		
		panelWest.add(figur0);
		panelWest.add(figur1);
		panelWest.add(figur2);
		panelWest.add(figur3);

			
		
		output = new JTextField();
		input = new JTextField();
		status = new JTextField();
		
		status.setEditable(false);
		output.setEditable(false);
		
		panelSouth.add(wuerfel);
		panelSouth.add(status);
		panelSouth.add(output);
		panelSouth.add(input);
		panelSouth.add(enter);
		
		getContentPane().add(gamePaint, BorderLayout.CENTER);
		getContentPane().add(panelSouth, BorderLayout.PAGE_END);
		getContentPane().add(panelWest,BorderLayout.LINE_END);

		setSize(630, 650);
		setResizable(false);
		setVisible(true);		
	}

	public void updatePrintDice() {

	}

	public void updateShowGameFrame() {
		gamePaint.repaint();
		status.setText("Es wurde gewuerfelt:" + controller.getRoll());
		int c = controller.getActivePlayer().getPlayerID();
		switch(c){
		case 0:
			output.setText("Grüner Spieler ist dran!");
			break;
		case 1:
			output.setText("Gelber Spieler ist dran!");
			break;
		case 2:
			output.setText("Blauer Spieler ist dran!");
			break;
		case 3:
		    output.setText("Roter Spieler ist dran!");
			break;
		}
	}

	public void updatePrintFigures() {
		// TODO Auto-generated method stub

	}
	public void updateInput(){
		///
	}
	
	public void updateChooseFigure() {
		
		
	}
	
	public void inputChoosePlayerCount(){
		
	}

	public void updateObserversRoll() {
		
	}

	
	private class ButtonListener implements ActionListener {
		private Controller controller;
		
		public ButtonListener(Controller controller) {
			super();
			this.controller = controller;
		}

		public void actionPerformed(ActionEvent e) {
			if (controller.getStatus() == GAME_STATE.ROLL){
				if (e.getActionCommand().equals("Würfel")) {
					controller.doDice();
					status.setText("Es wurde gewuerfelt:" + controller.getRoll());
				}
			}
			
			if (controller.getStatus() == GAME_STATE.CHOOSE_FIG) {
				if(e.getActionCommand().equals("Figur0")) {
					controller.setPickFigure(0);
				}
				if (e.getActionCommand().equals("Figur1")) {
					controller.setPickFigure(1);
				}
				if (e.getActionCommand().equals("Figur2")) {
					controller.setPickFigure(2);
				}
				if (e.getActionCommand().equals("Figur3")) {
					controller.setPickFigure(3);
				}
			}
			if(controller.getStatus()== GAME_STATE.CHOOSE_PLAYER_COUNT){
				if(e.getActionCommand().equals("Enter")) {
					controller.inputPlayerCount(Integer.parseInt(input.getText()));
				}
			}
			
		}
	}

	// XXXXXXXXXXXXXX Zeichnen des Spiels XXXXXXXXXXXXXXXXXXXXXXX
	class GamePaint extends JComponent {

		private static final long serialVersionUID = 1L;

		public int stoneField = 1;
		private Controller controller;

		public GamePaint(Controller controller) {
			super();
			this.controller = controller;
		}

		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;

			// Hintergrund zeichenen
			g2.setColor(Color.BLACK);
			drawBackground(g2);

			// Spielfeld zeichnen
			g2.setColor(Color.WHITE);

			drawField(g2);
			drawTargets(g2);
			for (int i = 0; i < controller.getAnzahlMitspieler(); i++) {
				try {

					drawStacks(g2, i);

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

//			 // Raster zeichnen
//			 g2.setColor(Color.WHITE);
//			 drawGrid(g2);
//
//			 // Koordinaten Zeichnen
//			 g2.setColor(Color.GREEN);
//			 drawCoord(g2);

		}

		// Methode zum Zeichnen der Speilsteine
		private void drawBackground(Graphics2D g2) {
			g2.fillRect(0, 0, 550, 550);
		}

		// Methode zum Zeichnen des Spielfeldes

		public void drawField(Graphics2D g2) {
			ArrayList<String> fieldCoords = (ArrayList<String>) controller
					.getFieldCoords();
			Figure[] fieldFigures = controller.getPgArray();

			String pattern = " ";
			int k = 0;
			for (String src : fieldCoords) {
				String[] parts = src.split(pattern);

				String firstValue = parts[0];
				int i = Integer.parseInt(firstValue);
				String secondValue = parts[1];
				int j = Integer.parseInt(secondValue);

				if (fieldFigures[k] != null) {
					int c = fieldFigures[k].getPlayerID();
					switch (c) {
					case 0:
						drawFigure(g2, i, j, Color.GREEN);
						drawFigureID(g2, i, j, k);

						break;
					case 1:
						drawFigure(g2, i, j, Color.YELLOW);
						drawFigureID(g2, i, j, k);
						break;
					case 2:
						drawFigure(g2, i, j, Color.BLUE);
						drawFigureID(g2, i, j, k);
						break;
					case 3:
						drawFigure(g2, i, j, Color.RED);
						drawFigureID(g2, i, j, k);
						break;
					default:
						g2.setColor(Color.WHITE);
					}
				} else {
					g2.setColor(Color.WHITE);
					g2.fillRect(i, j, 50, 50);
				}
				k++;
			}

		}

		public void drawTargets(Graphics2D g2) {
			String pattern = " ";
			for (int k = 0; k < 4; k++) {
				ArrayList<String> targetCoords = (ArrayList<String>) controller
						.getTargetCoords(k);
				Figure[] targetFigures = controller.getTargetFigureArray(k);
				int p = 0;
				for (String src : targetCoords) {
					String[] parts = src.split(pattern);

					String firstValue = parts[0];
					int i = Integer.parseInt(firstValue);
					String secondValue = parts[1];
					int j = Integer.parseInt(secondValue);

					if (targetFigures[p] != null) {
						int c = targetFigures[p].getPlayerID();
						switch (c) {
						case 0:
							drawFigure(g2, i, j, Color.GREEN);
							// drawFigureID(g2, i, j, k);
							break;
						case 1:
							drawFigure(g2, i, j, Color.YELLOW);
							// drawFigureID(g2, i, j, k);
							break;
						case 2:
							drawFigure(g2, i, j, Color.BLUE);
							// drawFigureID(g2, i, j, k);
							break;
						case 3:
							drawFigure(g2, i, j, Color.RED);
							// drawFigureID(g2, i, j, k);
							break;
						default:
							g2.setColor(Color.WHITE);
						}
					} else {
						g2.setColor(Color.GRAY);
						g2.fillRect(i, j, 50, 50);
					}
					p++;
				}
			}

		}

		public void drawStacks(Graphics2D g2, int playerID)
				throws FileNotFoundException {
			ArrayList<String> stackCoord = (ArrayList<String>) controller
					.getStackCoords();
			int ss = 0;
			int drawn = 0;
			int zeilevon = 0;
			int zeilebis = 0;
			String pattern = " ";
			if (playerID == 0) {
				ss = controller.getStackSize(0);
				zeilevon = 0;
				zeilebis = 4;
			} else if (playerID == 1) {
				ss = controller.getStackSize(1);
				zeilevon = 4;
				zeilebis = 8;
			} else if (playerID == 2) {
				ss = controller.getStackSize(2);
				zeilevon = 8;
				zeilebis = 12;
			} else if (playerID == 3) {
				ss = controller.getStackSize(3);
				zeilevon = 12;
				zeilebis = 16;
			}

			for (; zeilevon < zeilebis; zeilevon++) {
				String[] parts = stackCoord.get(zeilevon).split(pattern);

				String firstValue = parts[0];
				int i = Integer.parseInt(firstValue);
				String secondValue = parts[1];
				int j = Integer.parseInt(secondValue);

				if (stackCoord.get(zeilevon).contains("Spieler1") && drawn < ss) {
					drawFigure(g2, i, j, Color.GREEN);
					drawn++;
				}
				if (stackCoord.get(zeilevon).contains("Spieler2") && drawn < ss) {
					drawFigure(g2, i, j, Color.YELLOW);
					drawn++;
				}
				if (stackCoord.get(zeilevon).contains("Spieler3") && drawn < ss) {
					drawFigure(g2, i, j, Color.BLUE);
					drawn++;
				}
				if (stackCoord.get(zeilevon).contains("Spieler4") && drawn < ss) {
					drawFigure(g2, i, j, Color.RED);
					drawn++;
				}
			}

		}

//		private void drawGrid(Graphics2D g2) {
//			for (int i = 0; i <= 550; i = i + 50) {
//				for (int j = 0; j <= 550; j = j + 50) {
//					g2.drawLine(i, j, 0, j);
//				}
//			}
//			for (int i = 0; i <= 550; i = i + 50) {
//				for (int j = 0; j <= 550; j = j + 50) {
//					g2.drawLine(j, i, j, 0);
//				}
//			}
//		}
//
//		private void drawCoord(Graphics2D g2) {
//			for (int i = 0; i < 550; i = i + 50) {
//				for (int j = 0; j < 550; j = j + 50) {
//					g2.drawString(i + ":" + j, i, j + 50);
//				}
//			}
//		}

		private void drawFigureID(Graphics2D g2, int x, int y, int k) {
			int id = controller.getFigureOnPos(k).getFigureID();
			g2.setColor(Color.BLACK);

			g2.drawString("Figur " + id, x + 5, y + 50 - 25);

		}

		private void drawFigure(Graphics2D g2, int x, int y, Color color) {

			g2.setColor(color);
			g2.fillRect(x, y, 50, 50);

		}
	}

	
	
}


