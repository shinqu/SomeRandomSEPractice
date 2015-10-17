package com.blackdroidstudios.cleansweep.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.blackdroidstudios.cleansweep.map.FloorGenerator;
import com.blackdroidstudios.cleansweep.map.FloorMap;
import com.blackdroidstudios.cleansweep.map.Tile;

public class GUIPanel extends JPanel 
{
	Timer timer;

	private Rectangle[][] floorMap;
	FloorGenerator floorGenerator;
	Actor actor = new Actor();
	Tile currentTile;
	int neighbourCnt = 0;
	ArrayList<Tile> currentTileneighbours = new ArrayList<>();

	public GUIPanel() 
	{
		floorMap = new Rectangle[FloorMap.FLOOR_SIZE_X][FloorMap.FLOOR_SIZE_Y];
		actor.setX(10);
		actor.setY(10);
		//currentTile = floorGenerator.tileCollection[0][0];
		floorGenerator = new FloorGenerator();

		timer = new Timer(1000, paintTimer);
		timer.start();
	}

	public void initializePanel() {
		validate();
		repaint();
	}

	Action paintTimer = new AbstractAction() { // functionality of our timer:
		public void actionPerformed(ActionEvent e) {
			// if(pi < FloorMap.FLOOR_SIZE_X && pj < FloorMap.FLOOR_SIZE_Y){
			if (CheckIfAllTilesVisited() == false) {
				Move();
				repaint();
			} else
				timer.stop();
		}
	};

	public boolean Move() {
		if(currentTile == null){
			currentTile = floorGenerator.tileCollection[0][0];
			SetIsVisited(currentTile);
		}
		
		currentTileneighbours = currentTile.getNeighbours();

		if (neighbourCnt < currentTileneighbours.size()) {
			Tile neighbour = currentTile.getNeighbours().get(neighbourCnt);

			if (neighbour.getIsVisited() == true)
				neighbourCnt++;
			else {
				actor.setX(neighbour.getX());
				actor.setY(neighbour.getY());
				currentTile = neighbour;
				SetIsVisited(currentTile);
				neighbourCnt = 0;
			}
		}
		return true;
	}
	
	public boolean SetIsVisited(Tile tile) {
		for (int i = 0; i < FloorMap.FLOOR_SIZE_X; i++) {
			for (int j = 0; j < FloorMap.FLOOR_SIZE_Y; j++) {
				if (floorGenerator.tileCollection[i][j] == tile) {
					floorGenerator.tileCollection[i][j].setIsVisited(true);
					return true;
				}
			}
		}
		return true;
	}

	public boolean CheckIfAllTilesVisited() {
		boolean visited = true;
		for (int i = 0; i < FloorMap.FLOOR_SIZE_X; i++) {
			for (int j = 0; j < FloorMap.FLOOR_SIZE_Y; j++) {
				if (floorGenerator.tileCollection[i][j].getIsVisited() == false) {
					visited = false;
					return visited;
				}
			}
		}
		return visited;
	}

	// public void Move1() {
	// pj++;
	//
	// if (pj > FloorMap.FLOOR_SIZE_Y - 1) {
	// pi++;
	// pj = 0;
	// }
	//
	// if (pi < FloorMap.FLOOR_SIZE_X && pj < FloorMap.FLOOR_SIZE_Y) {
	// Tile temp = floorGenerator.tileCollection[pi][pj];
	// actor.setX(temp.getX());
	// actor.setY(temp.getY());
	// }
	// }

	// public boolean TempMove1(){
	// if(temp == 0)
	// {
	// currentBall.setX(130);
	// currentBall.setY(0);
	// }
	// else if(temp == 1){
	// currentBall.setX(250);
	// currentBall.setY(0);
	// }
	// else if(temp == 2){
	// currentBall.setX(0);
	// currentBall.setY(130);
	// }
	// else if(temp == 3){
	// currentBall.setX(130);
	// currentBall.setY(130);
	// }
	// else if(temp == 4){
	// currentBall.setX(250);
	// currentBall.setY(130);
	// }
	// else if(temp == 5){
	// currentBall.setX(0);
	// currentBall.setY(250);
	// }
	// else if(temp == 6){
	// currentBall.setX(130);
	// currentBall.setY(250);
	// }
	// else if(temp == 7){
	// currentBall.setX(250);
	// currentBall.setY(250);
	// }
	// temp++;
	// return true;
	// }

	public void paintMap(Graphics2D _g2d) throws IOException {
		for (int x = 0; x < FloorMap.FLOOR_SIZE_X; x++) 
		{
			for (int y = 0; y < FloorMap.FLOOR_SIZE_Y; y++) 
			{
				if ((x + y) % 2 == 0) 
				{
					_g2d.setColor(Color.GREEN);
					_g2d.fillRect(x * 125, y * 125, 125, 125);
				} else 
				{
					_g2d.setColor(Color.WHITE);
					_g2d.fillRect(x * 125, y * 125, 125, 125);
				}
			}
		}

		// Image image = new ImageIcon("CleanSweep.jpg").getImage();
		// _g2d.drawImage(image,3,4, this);

		BufferedImage myPicture = ImageIO.read(getClass().getResource("/resources/images/smallCleanSweep.jpg"));
		// ImageIO.read(new File("/Test/Content/Images/CleanSweep.jpg"));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		add(picLabel);

		_g2d.drawImage(myPicture, actor.getX(), actor.getY(), Color.BLACK, null);
		_g2d.dispose();
	}

	public void addActor(Graphics gd) throws IOException {
		// Do stuff
		// tilesToPaint.add(tile);

		// gd.setColor(Color.RED);
		//
		// BufferedImage myPicture = ImageIO.read(new
		// File("/Test/Content/Images/CleanSweep.jpg"));
		// JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		// add(picLabel);
		//
		// gd.drawImage(myPicture, 0, 0, Color.BLACK, null);

		validate();
		repaint();
	}

	// REALLY IMPORTANT!!!!! O.o
	// Paint function
	public void paint(Graphics gd) {
		Graphics2D g2d = (Graphics2D) gd;
		// Paint map
		try {
			paintMap(g2d);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
