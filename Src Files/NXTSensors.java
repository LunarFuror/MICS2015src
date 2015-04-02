//NXT code

package Robot;

import java.io.*;

import lejos.nxt.*;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.comm.*;

public class NXTSensors {
	public static DataOutputStream dataOut;
	public static DataInputStream dataIn;
	public static USBConnection USBLink;
	public static TouchSensor touchRightFront = new TouchSensor(SensorPort.S2);
	public static TouchSensor touchLeftFront = new TouchSensor(SensorPort.S3);
//	public static TouchSensor touchRightBack = new TouchSensor(SensorPort.S3);
//	public static TouchSensor touchLeftBack = new TouchSensor(SensorPort.S4);//multiplexer 1
    public static ColorSensor colorFront = new ColorSensor(SensorPort.S1);//multiplexer 2
//    public static ColorSensor colorBack = new ColorSensor(SensorPort.S6);//multiplexer 3
	public static int request = 0;
	public static int sensors = 0;
	public static TSM touchMulti = new TSM(SensorPort.S4);
	
	public static void main(String[] args) {
		connect();
		// Loops until you push da button.
		while (!Button.ENTER.isDown()) {
			// Waits for something to read

			// tries to read it
			try {
				// if read, sets the request to it.
				if (dataIn.available() != 0){
					System.out.print("Read ");
					request = dataIn.readInt();
				}
			} catch (IOException e) {
				// IF READING FAILS
				System.out.print("I CAN'T NOT READ NONE TOO GOOD!!!   ");
			}
			
			// if that request is 1, then NXT sends sensor data
			// though I doubt we'll ever request anything else if we wanted
			// to break this up
			// change this if to a switch.
			if (request == 1) {
				System.out.print("Requested ");
				request = 0;
				try {
					// generate, write, print.
					generateOutput();
					System.out.print("Send ");
					//this may be hanging
					dataOut.writeInt(sensors);
					dataOut.flush();
					System.out.print("Sent ");
					
					System.out.print(" " + sensors);
				} catch (IOException e) {
					// IF WRITING FAILS
					System.out.print("ALL WORK AND NO PLAY MAKES JACK A DULL BOY!!!   ");
				}			
			}
		}
	}

	/**
	 * Waits for a connection to the pc/pi. START NXT BEFORE TRYING TO CONNECT
	 * TO IT.
	 */
	public static void connect() {
		System.out.println("Listening");
		USBLink = USB.waitForConnection();
		dataOut = USBLink.openDataOutputStream();
		dataIn = USBLink.openDataInputStream();
	}

	/**
	 * Resets the sensor data, then generates it by simply adding to the sensor
	 * integer. The sensor integer basically is an 8bit number but ... you read
	 * it normally. Math for decoding is below. You shouldn't be reading this
	 * anyway. Why are you reading this? Don't you trust my work?
	 */
	public static void generateOutput() {
		System.out.print("Generate ");
		sensors = 0;
		int response = touchMulti.readSensors();
		
		if (touchRightFront.isPressed())
			sensors += 1;
		
		if (touchLeftFront.isPressed())
			sensors += 2;
		
		if ((response & 1) == 1)
			sensors += 4;
		
		if ((response & 2) == 2)
			sensors += 8;
		
		if (colorFront.getColorID() == Color.RED)
			sensors += 16;
		
		if (colorFront.getColorID() == Color.BLACK)
			sensors += 32;
		
//		if (colorBack.getColorID() == Color.RED)
//			sensors += 64;
//		
//		if (colorBack.getColorID() == Color.BLACK)
//			sensors += 128;
	}

	/**
	 * PULL THIS OUT TO USE WHEN DECODING
	 */
	public static void decode() {
		// Does the decoding, either add another global variable that this
		// modifies, or find a way to use this in your own way.
		while (sensors != 0) {
			// COLOR BACK
			if ((sensors - 128) >= 0) {
				sensors -= 128;
				System.out.println("COLOR BACK : BLACK");
			} else if ((sensors - 64) >= 0) {
				sensors -= 64;
				System.out.println("COLOR BACK : RED");
			} else
				System.out.println("COLOR BACK : white");
			
			// COLOR FRONT
			if ((sensors - 32) >= 0) {
				sensors -= 32;
				System.out.println("COLOR FRONT : BLACK");
			} else if ((sensors - 16) >= 0) {
				sensors -= 16;
				System.out.println("COLOR FRONT : RED");
			} else
				System.out.println("COLOR BACK : white");
			
			// TOUCH BACK LEFT
			if ((sensors - 8) >= 0) {
				sensors -= 8;
				System.out.println("TOUCH BACK LEFT : TRUE");
			} else
				System.out.println("TOUCH BACK LEFT : false");
			
			// TOUCH BACK RIGHT
			if ((sensors - 4) >= 0) {
				sensors -= 4;
				System.out.println("TOUCH BACK RIGHT : TRUE");
			} else
				System.out.println("TOUCH BACK RIGHT : false");
			
			// TOUCH FRONT LEFT
			if ((sensors - 2) >= 0) {
				sensors -= 2;
				System.out.println("TOUCH FRONT LEFT : TRUE");
			} else
				System.out.println("TOUCH FRONT LEFT : false");
			
			// TOUCH FRONT RIGHT
			if ((sensors - 1) >= 0) {
				sensors -= 1;
				System.out.println("TOUCH FRONT RIGHT : TRUE");
			} else
				System.out.println("TOUCH FRONT RIGHT : false");
		}
	}
}
