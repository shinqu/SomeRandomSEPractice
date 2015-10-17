package com.blackdroidstudios.cleansweep.gui;

public class Actor {
	/*
	 * Simple Object class to store the ball's co-ordinates.
	 */
	private int x;
	private int y;
	private int z;

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public Actor() {
		;
	} // default constructor

	public int getX() { // accessor/GET method for data
		return x;
	}

	public void setX(int x2) { // mutator/SET method for data
		x = x2;
	}

	public int getY() {
		return y;
	}

	public void setY(int y2) {
		y = y2;
	}
}
