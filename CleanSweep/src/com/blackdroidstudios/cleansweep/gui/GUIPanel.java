package com.blackdroidstudios.cleansweep.gui;

/**
 * @author Armando Garcin
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.blackdroidstudios.cleansweep.map.FloorMap;

public class GUIPanel extends JPanel
{
	//Variables
	private Rectangle[][] floorMap;
	Ball currentBall = new Ball();
	
	//private ArrayList<Tiles> tilesToPaint;
	
	public GUIPanel()
	{
		floorMap = new Rectangle[FloorMap.FLOOR_SIZE_X][FloorMap.FLOOR_SIZE_Y];
		currentBall.setX(10);
		currentBall.setY(10); 
		new Timer(50, paintTimer).start();
	}
	
	public void initializePanel()
	{
		validate();
		repaint();
	}
	
	Action paintTimer = new AbstractAction()
	{ 
		// functionality of our timer:
		public void actionPerformed(ActionEvent e) 
		{
			// set X and Y co-ordinates that will then be fetched when drawing
			// the ball Image on the JPanel.
			currentBall.setX(currentBall.getX() + 5);
			currentBall.setY(currentBall.getY() + 5);
			
			
			repaint();
		}
	};
	
	public void paintMap(Graphics2D _g2d) throws IOException
	{
		for(int x = 0; x < FloorMap.FLOOR_SIZE_X; x++)
		{
			for(int y = 0; y < FloorMap.FLOOR_SIZE_Y; y++)
			{
				if((x + y) % 2 == 0)
				{
					_g2d.setColor(Color.GREEN);
					_g2d.fillRect(x*125, y*125, 125, 125);
				}else
				{
					_g2d.setColor(Color.WHITE);
					_g2d.fillRect(x*125, y*125, 125, 125);
				}
			}
		}
		
//		Image image = new ImageIcon("CleanSweep.jpg").getImage();
//		_g2d.drawImage(image,3,4, this);
		
		BufferedImage myPicture = ImageIO.read(getClass().getResource("/resources/images/smallCleanSweep.jpg"));
		//ImageIO.read(new File("/Test/Content/Images/CleanSweep.jpg"));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		add(picLabel);
		
		_g2d.drawImage(myPicture, currentBall.getX(), currentBall.getY(), Color.BLACK, null);
		_g2d.dispose();
	}
	
	public void addActor(Graphics gd) throws IOException
	{
		//Do stuff
		//tilesToPaint.add(tile);
		
//		gd.setColor(Color.RED);
//		
//		BufferedImage myPicture = ImageIO.read(new File("/Test/Content/Images/CleanSweep.jpg"));
//		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//		add(picLabel);
//		
//		gd.drawImage(myPicture, 0, 0, Color.BLACK, null);
		
		validate();
		repaint();
	}
	
	//REALLY IMPORTANT!!!!! O.o
	//Paint function
	public void paint(Graphics gd)
	{
		Graphics2D g2d = (Graphics2D)gd;
		//Paint map
		try {
			paintMap(g2d);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
