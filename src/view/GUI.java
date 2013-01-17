package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
//import java.awt.geom.GeneralPath;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.Figure;

import controller.Controller;

import observer.IObserver;

// XXXXXXXXXXXXX GUI XXXXXXXXXXXXXXXXXXXXXXXXXXXXX
public class GUI extends JFrame implements IObserver {

	private static final long serialVersionUID = 1L;

	GamePaint gamePaint;
	

	private Controller controller;

	public GUI(Controller controller) {
		super("Mensch ï¿½rger dich nicht");
		gamePaint = new GamePaint(controller);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		getContentPane().setLayout(new BorderLayout());

		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(new GridLayout(2, 1));
		JButton wuerfel = new JButton("Würfel");
		wuerfel.addActionListener(new ButtonListener());
		
		JPanel panelWest = new JPanel();
		panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.PAGE_AXIS));
		
		JButton figur0 = new JButton("Figur0");
		JButton figur1 = new JButton("Figur1");
		JButton figur2 = new JButton("Figur2");
		JButton figur3 = new JButton("Figur3");

		panelWest.add(figur0);
		panelWest.add(figur1);
		panelWest.add(figur2);
		panelWest.add(figur3);

			
		
		JTextField output = new JTextField();
		
		panelSouth.add(wuerfel);
		panelSouth.add(output);

		getContentPane().add(gamePaint, BorderLayout.CENTER);
		getContentPane().add(panelSouth, BorderLayout.PAGE_END);
		getContentPane().add(panelWest,BorderLayout.LINE_END);

		setSize(630, 650);
		setResizable(false);
		setVisible(true);
		this.controller = controller;
	}

	public void updatePrintDice() {
		// TODO Auto-generated method stub

	}

	public void updatePrintArray() {
		gamePaint.repaint();
	}

	public void updatePrintFigures() {
		// TODO Auto-generated method stub

	}

}

class ButtonListener implements ActionListener {
	  ButtonListener() {
	  }

	  public void actionPerformed(ActionEvent e) {
	    if (e.getActionCommand().equals("Würfel")) {
	      System.out.println("Button1 has been clicked");
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

		// // Raster zeichnen
		// g2.setColor(Color.WHITE);
		// drawGrid(g2);

		// // Koordinaten Zeichnen
		// g2.setColor(Color.GREEN);
		// drawCoord(g2);

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

	private void drawGrid(Graphics2D g2) {
		for (int i = 0; i <= 550; i = i + 50) {
			for (int j = 0; j <= 550; j = j + 50) {
				g2.drawLine(i, j, 0, j);
			}
		}
		for (int i = 0; i <= 550; i = i + 50) {
			for (int j = 0; j <= 550; j = j + 50) {
				g2.drawLine(j, i, j, 0);
			}
		}
	}

	private void drawCoord(Graphics2D g2) {
		for (int i = 0; i < 550; i = i + 50) {
			for (int j = 0; j < 550; j = j + 50) {
				g2.drawString(i + ":" + j, i, j + 50);
			}
		}
	}

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
