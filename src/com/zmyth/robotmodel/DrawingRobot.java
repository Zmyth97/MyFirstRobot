package com.zmyth.robotmodel;

public class DrawingRobot
{
	public int calculateAngle(int numberOfSides)
	{
		int turnTime = 1500 / numberOfSides;
		return turnTime;
	}
}
