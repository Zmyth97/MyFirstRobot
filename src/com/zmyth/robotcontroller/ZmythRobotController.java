package com.zmyth.robotcontroller;

import lejos.nxt.SensorPort;

import com.zmyth.robotmodel.Robot;

public class ZmythRobotController
{

	private Robot myRobot;
	private int SQUARE = 4;
	private int TRIANGLE = 3;
	private int OCTAGON = 8;
	private int CIRCLE = 1;
	private int HEXAGON = 6;

	public ZmythRobotController()
	{
		myRobot = new Robot();
	}

	public void start()
	{
		/*while(true)
		{
		myRobot.danceLikeABeast();
		}*/
		
		//drawShapes();
		
		//myRobot.circleRoom();
		
		while(true)
		{
			myRobot.avoidTouch();
		}

	}

	public void drawShapes()
	{
		myRobot.drawShape(CIRCLE);
		myRobot.drawShape(TRIANGLE);
		myRobot.drawShape(SQUARE);
		myRobot.drawShape(OCTAGON);
		myRobot.drawShape(HEXAGON);
	}
}
