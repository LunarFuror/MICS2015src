import java.io.*;
import lejos.nxt.*;
import lejos.nxt.comm.*;

public class CommTest 
{
	public static DataOutputStream dataOut; 
	public static DataInputStream dataIn;
	public static USBConnection USBLink;
	public static TouchSensor touchRight = new TouchSensor(SensorPort.S1);
	public static TouchSensor touchLeft = new TouchSensor(SensorPort.S2);
	public static ColorSensor colorFront = new ColorSensor(SensorPort.S3);
	public static ColorSensor colorBack = new ColorSensor(SensorPort.S4);
	public boolean request = false;
	public boolean dataRecieved = false;
	public boolean[] sensors = {false, false, false, false, false, false};
	
	public static void main(String[] args) { 
		NXTConnection connection = null;
		System.out.println("Waiting for Button Press...");
		while(!Button.ENTER.isDown()){}
		connect();
		
		try {
			dataOut.writeInt(1234);
			System.out.println("NAILED IT!");
		} catch (IOException e ) {
			System.out.println(" write error "+e); 
		}
	}   
	
	public static void connect()
	{  
		System.out.println("Listening");
		USBLink = USB.waitForConnection();
		dataOut = USBLink.openDataOutputStream();
		dataIn = USBLink.openDataInputStream();
	}
	
	public static void generateOutput(){
		//Right Touch
		if(touchRight.isPressed())
			sensors[0] = true;
		else
			sensors[0] = false;
		
		//Left Touch
		if(touchLeft.isPressed())
			sensors[1] = true;
		else
			sensors[1] = false;
		
		//Color Front
		if(colorFront.getColorID()==Color.RED){
			sensors[2] = true;
			sensors[3] = false;
		}
		else if(colorFront.getColorID()==Color.BLACK){
			sensors[2] = false;
			sensors[3] = true;
		}
		else {
			sensors[2] = false;
			sensors[3] = false;
		}
		
		//Color Back
		if(colorBack.getColorID()==Color.RED){
			sensors[4] = true;
			sensors[5] = false;
		}
		else if(colorBack.getColorID()==Color.BLACK){
			sensors[4] = false;
			sensors[5] = true;
		}
		else {
			sensors[4] = false;
			sensors[5] = false;
		}
	}
}