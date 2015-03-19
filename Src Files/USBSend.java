//PC code

package fasdf;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

import lejos.pc.comm.NXTCommLogListener;
import lejos.pc.comm.NXTConnector;
 
public class USBSend {	
	
	public static int sensorCode = 0;
	public static NXTConnector conn = new NXTConnector();

	public static DataInputStream inDat;
	public static DataOutputStream outDat;
		
	public static void main(String[] args) 
	
		//uuuuuh... just leave this working and be sure the NXTBrick program is started BEFORE this program runs.
		conn.addLogListener(new NXTCommLogListener(){

			public void logEvent(String message) {
				System.out.println("USBSend Log.listener: "+message);
				
			}

			public void logEvent(Throwable throwable) {
				System.out.println("USBSend Log.listener - stack trace: ");
				 throwable.printStackTrace();
				
			}
			
		} 
		);
		
		
		
		if (!conn.connectTo("usb://")){
			System.err.println("No NXT found using USB");
			System.exit(1);
		}
		
		//This is where the request magic happens.
		inDat = new DataInputStream(conn.getInputStream());
		outDat = new DataOutputStream(conn.getOutputStream());
		try {
			outDat.writeInt(0);
			outDat.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//might not need this part... gotta test it still.
		while(true)
		{
			//a basic wait example, this wouldn't be in the final code, you'd just call the two methods.
			Scanner scan = new Scanner(System.in);
			System.out.print("Type and push the enter:");
			String thing = scan.next();
			
			
			//This sends a request for the sensor data, specifically the writeInt 1, be sure flush is in there
			
			requestSensorData();
//			decode();
		}
	}
	
	/**
	* This will request the sensor data and update the sensor code variable.
	*/
	public static void requestSensorData(){
		//This sends a request for the sensor data, specifically the writeInt 1, be sure flush is in there
		try {
			System.out.print("A ");
			outDat.writeInt(1);
			outDat.flush();
			System.out.print("B ");
		} catch (IOException ioe) {
			System.err.println("IO Exception writing bytes");
		}
	        
		//This is where it tries to read the data sent back.
		//except this for some reason isn't working ever.
		try {		
			System.out.println("Available is" + inDat.available());
			while(inDat.available() != 0){}
			if(inDat.available() != 0){
				System.out.print("C ");
				System.out.println("WE READ THE DO");
				sensorCode = inDat.readInt();
			}
	    } catch (IOException ioe) {
	        System.err.println("IO Exception reading reply");
	    }            
		
		//Print sensorCode
	    System.out.println("Received " + sensorCode);
	}
	
	/**
	* This will do the math and convert the sensor code into handy dandy sensor... stuff.
	*/	
	public static void decode() {
		// Does the decoding, either add another global variable that this
		// modifies, or find a way to use this in your own way.
		while (sensorCode != 0) {
			// COLOR BACK
			if ((sensorCode - 128) >= 0) {
				sensorCode -= 128;
				System.out.println("COLOR BACK : BLACK");
			} else if ((sensorCode - 64) >= 0) {
				sensorCode -= 64;
				System.out.println("COLOR BACK : RED");
			} else
				System.out.println("COLOR BACK : white");
			
			// COLOR FRONT
			if ((sensorCode - 32) >= 0) {
				sensorCode -= 32;
				System.out.println("COLOR FRONT : BLACK");
			} else if ((sensorCode - 16) >= 0) {
				sensorCode -= 16;
				System.out.println("COLOR FRONT : RED");
			} else
				System.out.println("COLOR BACK : white");
			
			// TOUCH BACK LEFT
			if ((sensorCode - 8) >= 0) {
				sensorCode -= 8;
				System.out.println("TOUCH BACK LEFT : TRUE");
			} else
				System.out.println("TOUCH BACK LEFT : false");
			
			// TOUCH BACK RIGHT
			if ((sensorCode - 4) >= 0) {
				sensorCode -= 4;
				System.out.println("TOUCH BACK RIGHT : TRUE");
			} else
				System.out.println("TOUCH BACK RIGHT : false");
			
			// TOUCH FRONT LEFT
			if ((sensorCode - 2) >= 0) {
				sensorCode -= 2;
				System.out.println("TOUCH FRONT LEFT : TRUE");
			} else
				System.out.println("TOUCH FRONT LEFT : false");
			
			// TOUCH FRONT RIGHT
			if ((sensorCode - 1) >= 0) {
				sensorCode -= 1;
				System.out.println("TOUCH FRONT RIGHT : TRUE");
			} else
				System.out.println("TOUCH FRONT RIGHT : false");
		}
	}
}
