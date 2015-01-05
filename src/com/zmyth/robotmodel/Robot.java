package com.zmyth.robotmodel;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.I2CPort;
import lejos.nxt.I2CSensor;
import lejos.nxt.LightSensor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.SoundSensor;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.util.Delay;

public class Robot
{
	private int DEGREE_360 = 1500;

	NXTMotor engineA;
	NXTMotor engineB;
	MotorPort engineC;
	UltrasonicSensor objectSensor; 
    TouchSensor poker;
    TouchSensor sidePoker;
    LightSensor lightSensor;
    SoundSensor soundSensor;


	public Robot()
	{
		engineA = new NXTMotor(MotorPort.A);
		engineB = new NXTMotor(MotorPort.B);
		engineA.setPower(0);
		engineB.setPower(0);
		objectSensor = new UltrasonicSensor(SensorPort.S3);
		poker = new TouchSensor(SensorPort.S1);
		sidePoker = new TouchSensor(SensorPort.S4);
		soundSensor = new SoundSensor(SensorPort.S2);
		createListener();
	}
	
	private void turnOn()
	{
		engineA.setPower(MotorPort.MAX_POWER / 4 * 3);
		engineB.setPower(MotorPort.MAX_POWER / 4 * 3);
	}
	public void drawShape(int sides)
	{
		turnOn();
		int turnTime = 1500 / sides;

		for (int x = 0; x < sides; x++)
		{
			engineA.backward();
			engineB.forward();
			Delay.msDelay(turnTime);
			engineA.backward();
			engineB.backward();
			Delay.msDelay(500);
		}
		turnOff();
	}
	
	public void circleRoom()
	{
		turnOn();
		while (true)
		{
			engineA.backward();
			engineB.backward();
			if (objectSensor.getDistance() < 24)
			{
				engineA.backward();
				engineB.forward();
				Delay.msDelay(DEGREE_360/4);
			}
		}
	}

	public void danceLikeABeast()
	{
		Delay.msDelay(500);
		turnOn();
		engineA.backward();
		engineB.backward();
		if(soundSensor.readValue() >= 60)
		{
			engineA.backward();
			engineB.forward();
			Delay.msDelay(DEGREE_360);
			engineA.backward();
			engineB.backward();
			Delay.msDelay(500);
			for (int x = 0; x < 5; x++)
			{
				engineA.backward();
				engineB.forward();
				Delay.msDelay(DEGREE_360/6);
				engineA.forward();
				engineB.backward();
				Delay.msDelay(DEGREE_360/6);
			}
			turnOff();
		}
	}
	
	public void avoidTouch()
	{
		engineA.setPower(MotorPort.MAX_POWER/3);
		engineB.setPower(MotorPort.MAX_POWER/3);
		engineA.backward();
		engineB.backward();
		if(poker.isPressed())
		{
			Delay.msDelay(500);
			engineA.backward();
			engineB.forward();
			Delay.msDelay(DEGREE_360/4);
		}
		else if (sidePoker.isPressed())
		{
			Delay.msDelay(500);
			engineA.forward();
			engineB.backward();
			Delay.msDelay(DEGREE_360/8);
			
		}
	}
	
	private void createListener()
	{
		Button.ESCAPE.addButtonListener(new ButtonListener()
		{
			
			@Override
			public void buttonReleased(Button b)
			{
				switch (b.getId())
				{
				case Button.ID_ESCAPE:
					System.exit(1);
				}
			}
			
			@Override
			public void buttonPressed(Button b)
			{
				switch (b.getId())
				{
				case Button.ID_ESCAPE:
					System.exit(1);
				}
			}
			
		});
		
		
		/*SensorPort.S2.addSensorPortListener(new SensorPortListener()
		{

			@Override
			public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue)
			{
				System.out.println("Value:" + soundSensor.readValue());
				if(soundSensor.readValue() >= 60)
				{
					danceLikeABeast();
				}
				
			}
			
		});*/
		
	}
	
	private void turnOff()
	{
		engineA.setPower(0);
		engineB.setPower(0);
	}
}
