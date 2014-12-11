package CatchA;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.*;

public class CatchTheBlock implements ActionListener {
	private JFrame frame;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JButton[] buttons;
	private JLabel result;
	private ImageIcon image;
    private int time;
    private int terminate;
    private int index;
    private int totalNumber = 0;
    private int clickNumber = 0;
    private Image img;
    
    public static void main(String[] args) {
    	CatchTheBlock catchA = new CatchTheBlock();
    	catchA.play();
    }
    
    public CatchTheBlock() {
    	init();
    }
    
    public void init() {
        frame = new JFrame("Catch-the-A");
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 600);
        panel1 = new JPanel();
        panel2 = new JPanel();
        frame.getContentPane().add("North",panel1);
        frame.getContentPane().add("Center",panel2);
        panel3 = new JPanel();
        panel1.setLayout(new GridLayout(3,1));
        panel1.add(panel3);
        result = new JLabel("");
        result.setText("Score: 0 / 0");
        panel3.add(result);
        panel2.setLayout(new GridLayout(6,6));
        buttons = new JButton[36];
        for(index = 0;index < 36; index++) {
            buttons[index] = new JButton();
            try {
            	img = ImageIO.read(getClass().getResource("A.jpg"));
            	image = new ImageIcon(img);
            	buttons[index].setIcon(image);
            	buttons[index].setBorderPainted(false);
            	buttons[index].setContentAreaFilled(false);
            }
            catch (IOException ex) { }
            buttons[index].addActionListener(this); 
            panel2.add(buttons[index]);
            buttons[index].setVisible(false); 
            buttons[index].setEnabled(false);
        }
    }
    
    public void play(){
        Random rm = new Random();
        while (terminate <= 30000) {
            index = rm.nextInt(36);
            buttons[index].setVisible(true);
            buttons[index].setEnabled(true);
            totalNumber++;
            time = (int) (Math.random() * 750) + 250;
            terminate += time;
            try {
                Thread.currentThread();
                Thread.sleep(time);
            }
            catch (InterruptedException e) {
            	System.exit(0);
            };
            result.setText("Score: " + String.valueOf(clickNumber) + " / " + String.valueOf(totalNumber));  
            buttons[index].setVisible(false);
            buttons[index].setEnabled(false);
        }
        String display = "<html>Game Over!" + "<br>" + "Your score was: " + String.valueOf(clickNumber) + "<br>" + "<br>" + "BUILD SUCCESSFUL (total time: " + (terminate / 1000) + " seconds)</html>";
        result.setText(display);
    }
    
    public void actionPerformed(ActionEvent e) {
        String order = e.getActionCommand();
        if (order.equalsIgnoreCase("")) {
            clickNumber++;
            JButton b = (JButton) e.getSource();
            b.setVisible(false);
            b.setEnabled(false);
        }
    }
}