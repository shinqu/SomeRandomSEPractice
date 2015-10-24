package com.blackdroidstudios.cleansweep.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.blackdroidstudios.cleansweep.gui.GUIControl;

public class GUITest 
{
	@Test
	public void BasicGUISetup()
	{
		try
		{
			GUIControl ctrl = new GUIControl();
			ctrl.initializeGUI();
			
			assertEquals(1280, ctrl.getFrameSizeX());
		}catch(Exception e)
		{
			
		}
	}
}
