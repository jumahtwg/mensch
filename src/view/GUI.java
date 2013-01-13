package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;


public class GUI extends JFrame{
    private static GUI self = null;
    private JMenu spielMenu;
    private JMenuBar menuBar;
    
    private JButton wuerfel;
    
    private ImageIcon background;
    private JPanel panel;
    private JFrame frame;
    
  //  private String imageOrdnerPfad = "img" + File.separator;
    
    public static GUI getInstance(){
            if(self == null)
            {
                    self = new GUI();
            }
            return self;
    }
    
    public void spielbrettAnzeigen(){
    	frame = new JFrame("Mensch aerger dich nicht");
    	frame.setMinimumSize(new Dimension(650, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
      
        //Hintergrundbild
        JLabel label = new JLabel();
        label.setBounds(0, 0, 650, 650);
        background = new ImageIcon("C:\\Temp\\EclipseWorkSpace\\mensch\\src\\view\\SpielbrettHintergrund.jpg");
        label.setIcon(background);        

        panel = new JPanel();
        panel.setLayout(null);
        panel.add(label);
        
        //Wuerfelbutton
        wuerfel = new JButton("Wuerfel");
        wuerfel.addActionListener(new WuerfelListener());
        wuerfel.setBounds(0, 700, 75, 75);
        panel.add(wuerfel);
        
        JComponent dotw = new PixelComponent(Color.WHITE,100,100, 50,50);
        JComponent dotb = new PixelComponent(Color.black,100,100, 20,20);
        panel.add(dotw);
        
        frame.getContentPane().add(panel);
        
        refresh();



    }
    public void paint(Graphics g)  
    {  
     super.paint(g);  
      
     //draw circle outline  
     g.drawOval(50,50,100,100);  
      
     //set color to RED  
     //So after this, if you draw anything, all of it's result will be RED  
     g.setColor(Color.RED);  
      
     //fill circle with RED  
     g.fillOval(50,50,100,100);  
    }  
    
    public void refresh()
    {
            panel.setVisible(true);
            panel.repaint();
            frame.pack();
            frame.repaint();
            frame.setVisible(true);         
    }

    
    /**---------------LISTENERS----------------**/
    private class WuerfelListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    public static void main(String[] args){
    	GUI gui = new GUI();
    	gui.getInstance();
    	gui.spielbrettAnzeigen();
    }


}
