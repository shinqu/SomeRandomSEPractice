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
	
	//Static variables
	private static final int TILE_SIZE = 50; //This will be the size of the Tile
	
	//Variables
	private Timer timer;
	private Rectangle[][] floorMap;
	private ArrayList<Tile> sensorTiles;
	private Image cleanSweepSprite;
	private Tile cleanSweepTile;

	public GUIPanel() 
	{
		initializeRectGrid();
		sensorTiles = new ArrayList<Tile>();
		
		try
		{
			File path = new File("src/resources/images/CleanSweep_Sprite.png");
			cleanSweepSprite = ImageIO.read(path);
		}catch(Exception e)
		{
			cleanSweepSprite = null;
		}
	}
	
	public void addNewTile(Tile _tile)
	{
		if(!sensorTiles.contains(_tile))
		{
			sensorTiles.add(_tile);
		}
		refreshScreen();
	}
	
	public void updateCSPos(Tile _tile)
	{
		cleanSweepTile = _tile;
	}

	/**
	 *<p> Refreshes the panel.</p>
	 *<p>If you just updated something in the panel, it's a good idea to use this! :)</p>
	 */
	public void refreshScreen() 
	{
		validate();
		repaint();
	}
	/**
	 * Intended for internal use only
	 * <p>Initializes the rectangle array</p>
	 */
	private void initializeRectGrid()
	{
		floorMap = new Rectangle[FloorMap.FLOOR_SIZE_X][FloorMap.FLOOR_SIZE_Y];
		for(int x = 0; x < FloorMap.FLOOR_SIZE_X; x++)
		{
			for(int y = 0; y < FloorMap.FLOOR_SIZE_Y; y++)
			{
				floorMap[x][y] = new Rectangle();
				floorMap[x][y].x = x * TILE_SIZE;
				floorMap[x][y].y = y * TILE_SIZE;
				floorMap[x][y].width = TILE_SIZE;
				floorMap[x][y].height = TILE_SIZE;
			}
		}
	}


	// REALLY IMPORTANT!!!!! O.o
	// Paint function
	public void paint(Graphics gd) 
	{
		Graphics2D g2d = (Graphics2D) gd;
		
		//Clear all the screen before painting anything!
		g2d.clearRect(0, 0, GUIFrame.GUIPANEL_SIZE_X, GUIFrame.FRAME_SIZE_Y);
		
		// Paint map
		try 
		{
			paintMap(g2d);
			paintVisitedTiles(g2d);
			paintCSPosition(g2d);
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paintMap(Graphics2D _g2d) throws IOException 
	{
		for (int x = 0; x < FloorMap.FLOOR_SIZE_X; x++) 
		{
			for (int y = 0; y < FloorMap.FLOOR_SIZE_Y; y++) 
			{
				_g2d.setColor(Color.DARK_GRAY);
				_g2d.fillRect(floorMap[x][y].x, floorMap[x][y].y, floorMap[x][y].width, floorMap[x][y].height);
			}
		}
		refreshScreen();
	}
	
	public void paintVisitedTiles(Graphics2D _g2d)
	{
		if(!sensorTiles.isEmpty())
		{
			for(Tile _tile : sensorTiles)
			{
				_g2d.setColor(_tile.getColor());
				_g2d.fillRect(floorMap[_tile.getX()][_tile.getY()].x, floorMap[_tile.getX()][_tile.getY()].y, TILE_SIZE, TILE_SIZE);
				if(_tile.getSprite() != null && _tile.getDirt() > 0)
				{
					_g2d.drawImage(_tile.getSprite(), floorMap[_tile.getX()][_tile.getY()].x, floorMap[_tile.getX()][_tile.getY()].y, this);
				}
			}
		}
	}
	
	public void paintCSPosition(Graphics2D _g2d)
	{
		_g2d.setColor(cleanSweepTile.getColor());
		if(cleanSweepSprite != null)
		{
			_g2d.drawImage(cleanSweepSprite, floorMap[cleanSweepTile.getX()][cleanSweepTile.getY()].x, floorMap[cleanSweepTile.getX()][cleanSweepTile.getY()].y, this);
		}
	}
}
